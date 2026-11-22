# -*- coding: utf-8 -*-
"""
Genera examen/repaso-examen-di.pdf a partir de los .md de examen/repaso/.

Sintaxis propia, además de Markdown normal:
  # Título / ## Título      apartados numerados (1. / 1.1.) que van al índice
  [[n: texto]]              nota numerada: (n) lleva a la nota y la nota vuelve aquí
  ```esquema Título         esquema ASCII/Unicode numerado como figura
  ![Título](img/x.png)      imagen numerada como figura
  ?? enunciado              pregunta de test (líneas a) b) c) d) y luego «=> letra | por qué»)

Uso:  python generar.py        (necesita markdown, pypdf y node con playwright-core)
"""
import html, os, re, subprocess, sys, json
import markdown
from pypdf import PdfReader

AQUI = os.path.dirname(os.path.abspath(__file__))
EXAMEN = os.path.dirname(AQUI)
FUENTES = os.path.join(EXAMEN, "repaso")
SALIDA_HTML = os.path.join(AQUI, "repaso.html")
SALIDA_PDF = os.path.join(EXAMEN, "repaso-examen-di.pdf")

PORTADA = """
<section class="portada">
  <p class="asig">Desarrollo de Interfaces · 2º DAM</p>
  <h1 class="titulo">Repaso para el examen</h1>
  <p class="sub">Teoría (curso <i>Interfaces de Usuario</i> de la UC3M y Unidad 2) y práctica con NetBeans</p>
  <pre class="ascii portada-arte">
 ┌───────────────────────────── EXAMEN ─────────────────────────────┐
 │                                                                  │
 │   ████████████████  40 %  TEST           4 opciones · fallo −⅓   │
 │   ████████████████████████  60 %  PRÁCTICA  NetBeans · Swing     │
 │                                                                  │
 │      Teoría  ──►  Test  ──►  Calculadora  ──►  Torrent           │
 └──────────────────────────────────────────────────────────────────┘</pre>
  <p class="version"><b>Material de estudio personal</b></p>
  <p class="datos">IES Isidra de Guzmán<br>Noviembre de 2026</p>
</section>
"""


def slug(n):
    return "sec-" + n.replace(".", "-").strip("-")


def procesar():
    md_total = ""
    for nombre in sorted(os.listdir(FUENTES)):
        if nombre.endswith(".md"):
            with open(os.path.join(FUENTES, nombre), encoding="utf-8") as f:
                md_total += f.read() + "\n\n"

    figuras = []      # (num, título, id)
    notas = []        # (num, texto)
    preguntas = []    # dicts
    secciones = []    # (nivel, numero, título, id)

    # --- 1 y 2. Esquemas ASCII e imágenes, numerados juntos y en orden de aparición.
    #     Se sacan antes de Markdown para que no los toque.
    bloques = {}

    def figura(m):
        n = len(figuras) + 1
        fid = "fig-%d" % n
        clave = "§§BLOQUE%d§§" % len(bloques)
        if m.group(1) is not None:                       # ```esquema Título
            titulo = m.group(1).strip()
            bloques[clave] = ('<figure id="%s" class="esquema"><pre class="ascii">%s</pre>'
                              '<figcaption>Figura %d. %s</figcaption></figure>'
                              % (fid, html.escape(m.group(2).rstrip("\n")), n, html.escape(titulo)))
        else:                                            # ![Título](ruta)
            titulo, ruta = m.group(3), m.group(4)
            ruta_abs = "file:///" + os.path.join(EXAMEN, ruta).replace("\\", "/")
            bloques[clave] = ('<figure id="%s" class="imagen"><img src="%s" alt="%s">'
                              '<figcaption>Figura %d. %s</figcaption></figure>'
                              % (fid, ruta_abs, html.escape(titulo), n, html.escape(titulo)))
        figuras.append((n, titulo, fid))
        return "\n" + clave + "\n"

    md_total = re.sub(r"^```esquema[ \t]+(.*?)\n(.*?)^```[ \t]*$|^!\[([^\]\n]*)\]\(([^)\n]*)\)[ \t]*$",
                      figura, md_total, flags=re.S | re.M)

    # --- 3. Preguntas de test
    def pregunta(m):
        n = len(preguntas) + 1
        enunciado = m.group(1).strip()
        opciones = re.findall(r"^([a-d])\) (.*)$", m.group(2), flags=re.M)
        resp, porque = [x.strip() for x in m.group(3).split("|", 1)]
        preguntas.append({"n": n, "enunciado": enunciado, "resp": resp, "porque": porque})
        ops = "".join('<li><span class="letra">%s)</span> %s</li>'
                      % (l, md_inline(t)) for l, t in opciones)
        clave = "§§BLOQUE%d§§" % len(bloques)
        bloques[clave] = ('<div class="pregunta" id="preg-%d"><p><b>%d.</b> %s</p><ul class="ops">%s</ul>'
                          '<a class="versol" href="#sol-%d">✔ solución %d</a></div>'
                          % (n, n, md_inline(enunciado), ops, n, n))
        return "\n" + clave + "\n"

    md_total = re.sub(r"^\?\? (.*?)\n((?:[a-d]\) .*\n)+)=> (.*)$", pregunta, md_total, flags=re.M)

    # --- 4. Notas [[n: texto]]
    def nota(m):
        n = len(notas) + 1
        notas.append((n, m.group(1).strip()))
        return '<a class="llamada" id="ref-%d" href="#nota-%d">(%d)</a>' % (n, n, n)

    md_total = re.sub(r"\[\[n:\s*(.*?)\]\]", nota, md_total, flags=re.S)

    # --- 5. Encabezados numerados
    c1, c2 = 0, 0
    lineas = []
    dentro_codigo = False
    for linea in md_total.split("\n"):
        if linea.startswith("```"):
            dentro_codigo = not dentro_codigo
        if not dentro_codigo and re.match(r"^#{1,2} ", linea):
            nivel = 1 if linea.startswith("# ") else 2
            titulo = linea.lstrip("#").strip()
            if titulo.startswith("!"):           # «# !Título» = sin número
                titulo = titulo[1:].strip()
                sid = "sec-" + re.sub(r"\W+", "-", titulo.lower())
                secciones.append((nivel, "", titulo, sid))
                lineas.append('<h%d id="%s" class="sinnum">%s</h%d>' % (nivel, sid, md_inline(titulo), nivel))
                continue
            if nivel == 1:
                c1, c2 = c1 + 1, 0
                num = "%d." % c1
            else:
                c2 += 1
                num = "%d.%d." % (c1, c2)
            sid = slug(num)
            secciones.append((nivel, num, titulo, sid))
            lineas.append('<h%d id="%s"><span class="num">%s</span> %s</h%d>'
                          % (nivel, sid, num, md_inline(titulo), nivel))
        else:
            lineas.append(linea)
    md_total = "\n".join(lineas)

    cuerpo = markdown.markdown(md_total, extensions=["tables", "fenced_code", "sane_lists", "md_in_html"])
    for clave, valor in bloques.items():
        cuerpo = cuerpo.replace("<p>%s</p>" % clave, valor).replace(clave, valor)

    return cuerpo, secciones, figuras, notas, preguntas


def md_inline(texto):
    h = markdown.markdown(texto)
    return re.sub(r"^<p>(.*)</p>$", r"\1", h, flags=re.S)


def montar(cuerpo, secciones, figuras, notas, preguntas, paginas):
    def pag(i):
        return '<span class="pag">%s</span>' % paginas.get(i, "")

    indice = '<section class="indice" id="indice"><h1 class="sinnum">Índice</h1><ul class="toc">'
    for nivel, num, titulo, sid in secciones:
        indice += ('<li class="n%d"><a href="#%s"><span class="t">%s %s</span>%s</a></li>'
                   % (nivel, sid, num, md_inline(titulo), pag(sid)))
    indice += ('<li class="n1"><a href="#soluciones"><span class="t">Soluciones del test</span>%s</a></li>'
               '<li class="n1"><a href="#notas"><span class="t">Notas</span>%s</a></li></ul></section>'
               % (pag("soluciones"), pag("notas")))

    indfig = '<section class="indice" id="indice-figuras"><h1 class="sinnum">Índice de figuras y esquemas</h1><ul class="toc">'
    for n, t, fid in figuras:
        indfig += '<li class="n2"><a href="#%s"><span class="t">Figura %d. %s</span>%s</a></li>' % (fid, n, html.escape(t), pag(fid))
    indfig += "</ul></section>"

    sol = '<section id="soluciones"><h1 class="sinnum">Soluciones del test</h1><table class="sol"><tr><th>Nº</th><th>Resp.</th><th>Por qué</th></tr>'
    for p in preguntas:
        sol += ('<tr id="sol-%d"><td><a href="#preg-%d">↩ %d</a></td><td class="r">%s</td><td>%s</td></tr>'
                % (p["n"], p["n"], p["n"], p["resp"], md_inline(p["porque"])))
    sol += "</table></section>"

    nts = '<section id="notas"><h1 class="sinnum">Notas</h1><ol class="notas">'
    for n, t in notas:
        nts += '<li id="nota-%d"><a class="vuelta" href="#ref-%d">(%d) ↩</a> %s</li>' % (n, n, n, md_inline(t))
    nts += "</ol></section>"

    with open(os.path.join(AQUI, "estilo.css"), encoding="utf-8") as f:
        css = f.read()
    return ('<!doctype html><html lang="es"><head><meta charset="utf-8"><title>Repaso DI</title>'
            '<style>%s</style></head><body>%s%s%s<main>%s</main>%s%s</body></html>'
            % (css, PORTADA, indice, indfig, cuerpo, sol, nts))


def imprimir():
    subprocess.run(["node", os.path.join(AQUI, "imprimir.js"), SALIDA_HTML, SALIDA_PDF], check=True)


def leer_paginas():
    """Lee del PDF en qué página cae cada destino con nombre (los id del HTML)."""
    r = PdfReader(SALIDA_PDF)
    out = {}
    for nombre, dest in r.named_destinations.items():
        try:
            out[nombre.lstrip("/")] = r.get_destination_page_number(dest) + 1
        except Exception:
            pass
    return out


if __name__ == "__main__":
    datos = procesar()
    paginas = {}
    for vuelta in (1, 2, 3):       # 1ª sin números; luego con los números ya medidos
        with open(SALIDA_HTML, "w", encoding="utf-8") as f:
            f.write(montar(*datos, paginas))
        imprimir()
        nuevas = leer_paginas()
        if nuevas == paginas:
            break
        paginas = nuevas
    cuerpo, secciones, figuras, notas, preguntas = datos
    print("Apartados %d · figuras %d · notas %d · preguntas %d · páginas %d"
          % (len(secciones), len(figuras), len(notas), len(preguntas), len(PdfReader(SALIDA_PDF).pages)))
    print("Destinos con página: %d" % len(paginas))
