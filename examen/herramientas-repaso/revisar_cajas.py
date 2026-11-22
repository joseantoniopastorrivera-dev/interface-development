# Revisa que las cajas de los esquemas ASCII estén cerradas en las mismas columnas.
import re, sys, glob, os
VERT = set("│├┤┼┬┴▲▼►◄╲╱┐┘┌└")
def revisar(titulo, lineas):
    probs = []
    for r, l in enumerate(lineas):
        for a, ch in enumerate(l):
            if ch != "┌":
                continue
            b = l.find("┐", a + 1)
            if b < 0:
                probs.append(f"fila {r}: ┌ sin ┐"); continue
            fin = None
            for rr in range(r + 1, len(lineas)):
                la = lineas[rr]
                ca = la[a] if a < len(la) else " "
                cb = la[b] if b < len(la) else " "
                if ca == "└":
                    fin = rr
                    if cb != "┘":
                        probs.append(f"filas {r}-{rr}: └ en col {a} pero sin ┘ en col {b} ('{la.rstrip()}')")
                    break
                if ca not in VERT or cb not in VERT:
                    probs.append(f"fila {rr}: borde roto (col {a}='{ca}', col {b}='{cb}') → '{la.rstrip()}'")
                    break
            if fin is None and not probs:
                probs.append(f"fila {r}: caja sin cerrar")
    return probs
total = 0
for f in sorted(glob.glob(os.path.join(sys.argv[1], "*.md"))):
    md = open(f, encoding="utf-8").read()
    for m in re.finditer(r"^```esquema[ \t]+(.*?)\n(.*?)^```", md, re.S | re.M):
        p = revisar(m.group(1), m.group(2).split("\n"))
        if p:
            total += 1
            print(f"== {os.path.basename(f)} · {m.group(1)}")
            for x in p[:4]: print("   ", x)
print("Esquemas con problemas:", total)
