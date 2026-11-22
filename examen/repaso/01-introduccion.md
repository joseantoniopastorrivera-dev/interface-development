# Cómo usar este repaso

## El examen

```esquema Reparto de la nota del examen
 NOTA FINAL (10 puntos)
 ┌──────────────────────┬─────────────────────────────────┐
 │  TEST         40 %   │  PRÁCTICA                 60 %  │
 │  4 opciones          │  NetBeans + Java Swing          │
 │  1 correcta          │  · Calculadora clásica          │
 │  fallo = −⅓          │  · Torrent simulado con menú    │
 │  blanco = 0          │                                 │
 └──────────────────────┴─────────────────────────────────┘
   4 puntos                6 puntos
```

| Parte | Qué entra | Dónde está en este repaso |
|---|---|---|
| Test (40 %) | Todo el curso *Interfaces de Usuario* de la UC3M (temas 1 a 5), la psicología del color y la teoría de Swing y NetBeans de la Unidad 2 | Apartados 2 a 12 y banco de test del apartado 16 |
| Práctica (60 %) | Las dos actividades hechas en clase con NetBeans: la calculadora clásica y la interfaz de un torrent que simula una descarga | Apartados 13 a 15 |

La práctica vale más que el test, así que si esta noche solo te da tiempo a una cosa, que sea **montar la calculadora y el torrent en NetBeans sin mirar**.

## Cómo está organizado

```esquema Mapa del documento
            ┌────────────────────────────┐
            │ 1. Cómo usar este repaso   │
            └─────────────┬──────────────┘
          ┌───────────────┼──────────────────────────┐
          ▼               ▼                          ▼
 ┌─────────────────┐ ┌──────────────────┐ ┌──────────────────────┐
 │ TEORÍA (test)   │ │ SWING / NETBEANS │ │ PRÁCTICA (60 %)      │
 │ 2. IPO          │ │ 12. Teoría Swing │ │ 13. NetBeans de cero │
 │ 3. Interfaces   │ │     y NetBeans   │ │ 14. Calculadora      │
 │ 4. Usabilidad   │ └──────────────────┘ │ 15. Torrent          │
 │ 5. Prototipos   │                      └──────────┬───────────┘
 │ 6. Accesibilidad│                                 │
 │ 7. Diseño web   │                                 ▼
 │ 8. Interacción  │                      ┌──────────────────────┐
 │ 9. Evaluación   │─────────────────────►│ 16. Banco de test    │
 │ 10. Paradigmas  │                      │ 17. Chuleta y ensayo │
 │ 11. Color       │                      └──────────────────────┘
 └─────────────────┘
```

Cómo moverte por el PDF:

- El **índice** y el **índice de figuras** son enlaces: pulsa una línea y vas a ese apartado.
- Un número entre paréntesis en rojo, como el de esta frase[[n: Así funcionan todas las notas: el número rojo del texto te trae aquí, y el «(n) ↩» del principio de la nota te devuelve al sitio exacto del texto. Las notas amplían algo que no hace falta para el test pero ayuda a entenderlo o a defenderlo si el profesor pregunta.]], lleva a su **nota** al final del documento. Al principio de cada nota, el mismo número te devuelve al sitio exacto del que venías.
- Cada pregunta del banco de test tiene un enlace **«✔ solución»**. En la tabla de soluciones, el número de la pregunta te devuelve a ella.
- Los recuadros amarillos marcan lo que más se presta a pregunta de test: listas cerradas, números, autores y años.

## Estrategia para un test que resta

Acertar suma 1 y fallar resta ⅓. Lo que decide si contestas es **cuántas opciones puedes descartar con seguridad**:

| Situación | Probabilidad de acertar | Ganancia media | Qué hacer |
|---|---|---|---|
| No descartas ninguna | 1 de 4 | ¼ − ¾ · ⅓ = **0** | En blanco: de media no ganas nada y te ahorras la mala suerte |
| Descartas una | 1 de 3 | ⅓ − ⅔ · ⅓ = **+0,11** | Contesta |
| Descartas dos | 1 de 2 | ½ − ½ · ⅓ = **+0,33** | Contesta siempre |
| La sabes | 1 | **+1** | Contesta |

La penalización de −⅓ con 4 opciones está calculada para que contestar al azar valga exactamente 0[[n: Es la «corrección por azar» clásica: con *k* opciones, la penalización justa es 1/(k − 1). Con 4 opciones, 1/3. Así, quien responde todo al azar saca de media un 0, igual que quien lo deja en blanco. Por eso la regla de oro es contestar si puedes quitar al menos una opción: en cuanto descartas una, la esperanza pasa a ser positiva.]]. Por eso la regla es sencilla: **si puedes quitar al menos una opción con seguridad, contesta; si las cuatro te parecen igual de posibles, déjala en blanco.**

```esquema Las tres vueltas al test
 1ª vuelta ──► contesta solo las que SABES          (rápido, sin dudar)
 2ª vuelta ──► las que permiten DESCARTAR 1 o 2     (piensa, elimina, contesta)
 3ª vuelta ──► las de lotería se quedan EN BLANCO   (no regales ⅓)
```

## Plan para esta noche y para mañana

```esquema Plan de ensayo de esta noche
 ┌─ 1 ─────────────┐   ┌─ 2 ─────────────┐   ┌─ 3 ────────────────┐   ┌─ 4 ──────────┐
 │ Calculadora en  │──►│ Torrent en      │──►│ Teoría + banco de  │──►│ Chuleta del  │
 │ NetBeans, desde │   │ NetBeans, desde │   │ test (apartados    │   │ apartado 17  │
 │ cero (ap. 14)   │   │ cero (ap. 15)   │   │ 2 a 12 y 16)       │   │ antes de     │
 │ ≈ 60 min        │   │ ≈ 60 min        │   │ ≈ 90 min           │   │ dormir       │
 └─────────────────┘   └─────────────────┘   └────────────────────┘   └──────────────┘
```

En el examen, en la práctica: **primero que compile y se vea la ventana**, después la lógica, y al final los detalles (colores, validaciones, atajos). Una ventana que se ve y hace algo puntúa; una ventana preciosa que no compila, no.
