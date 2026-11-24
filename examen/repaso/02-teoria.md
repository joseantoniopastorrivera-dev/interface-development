# Interacción persona-ordenador (tema 1)

## Idea central y definiciones

Un producto interactivo se diseña pensando en **quién** lo va a usar, **qué** tareas hace bien y mal y **dónde** lo usará. Para saberlo hay que observar y escuchar al usuario.

- Primero se habló de *Man-Machine Interface*. A mediados de los 80 aparece **HCI** (*Human-Computer Interaction*), también CHI. En español, **IPO**.
- Definición de **ACM SIGCHI (1987)**: disciplina relacionada con el **diseño, la evaluación y la implementación** de sistemas interactivos para el uso humano, y con el estudio de los fenómenos que los rodean.
- Es **multidisciplinar**: psicología, diseño, ergonomía, informática, sociología…

```esquema La IPO: tres verbos y muchas disciplinas
           ┌────────────────── IPO / HCI ──────────────────┐
           │    DISEÑO  ·  EVALUACIÓN  ·  IMPLEMENTACIÓN   │
           └───────────────────────┬───────────────────────┘
     ┌──────────────┬──────────────┼──────────────┬──────────────┐
 Psicología    Diseño gráfico   Ergonomía     Informática     Sociología
 (cómo piensa) (cómo se ve)     (el cuerpo)   (cómo funciona) (los grupos)
```

**Objetivos de la IPO**: desarrollar o mejorar la **seguridad**, la **utilidad** (qué puede hacer el sistema), la **efectividad**, la **eficiencia** (el botón más usado, el más cercano) y la **usabilidad** (fácil de usar, de aprender y libre de errores).

| Año | Hito (según los apuntes) |
|---|---|
| 1963 | Primer ratón: **Douglas Engelbart**, Stanford |
| 1968 | *The Demo* («la madre de todas las demos») de Engelbart |
| 1981 | Primer dispositivo comercial con ratón: **Xerox Star 8010** |
| 1984 | Primer ordenador comercial con éxito: **Apple Lisa**[[n: Errata de los apuntes: el Lisa salió en 1983 y fue un fracaso comercial; el que triunfó en 1984 fue el **Macintosh**. Si el test lo pregunta con la frase de los apuntes, contesta lo que dicen los apuntes (Lisa); si te lo pregunta el profesor en persona, puedes lucirte con la aclaración.]] |

Los tres «héroes» de la IPO que citan los apuntes: **Don Norman**, **Ben Shneiderman** y **Steve Jobs** («el diseño no es solo cómo se ve, es cómo funciona»).

## Factores para que un objeto se pueda usar

| Factor | Qué significa | Ejemplo |
|---|---|---|
| **Visibilidad** | Los controles se ven | Un botón que no está escondido |
| **Causalidad** | Se ve el efecto de lo que haces | Pulsas y algo cambia al instante |
| **Restricciones visibles** | El diseño impide el uso incorrecto | Una puerta que solo se empuja por un lado |
| **Coincidencia** (*mapping*) | Relación natural entre control y efecto | Mandos colocados como los fogones |
| **Efectos de transferencia** | Lo aprendido en un objeto se aplica a otro | De la máquina de escribir al procesador de textos |
| **Estereotipos** | Símbolos que todos entendemos aunque ya no representen la realidad | El teléfono con auricular, el disquete de «guardar» |
| **Modelos conceptuales** | La idea que la gente tiene de cómo funciona algo (puede ser errónea) | Subir más el termostato «calienta más rápido» |
| **Diferencias individuales y culturales** | No todos los usuarios son iguales | El color de luto cambia según el país |

```esquema Mapping: bueno frente a malo
  MAL MAPPING                         BUEN MAPPING
  fogones:   (A) (B)                  fogones:   (A) (B)
             (C) (D)                             (C) (D)
  mandos:  [1] [2] [3] [4]            mandos:    [A] [B]
  ¿qué mando enciende qué fuego?                 [C] [D]
                                      el mando está donde está su fuego
```

## Vocabulario del modelo de interacción

| Término | Definición | Ejemplo |
|---|---|---|
| **Dominio** | Área de habilidad y conocimiento del mundo real | Administración pública |
| **Meta** | **Qué** quiere conseguir el usuario | Hacer una solicitud |
| **Tarea** | **Cómo** conseguir la meta con un dispositivo concreto | Por carta o por email |
| **Acción** | Tarea que no implica resolver problemas ni estructuras de control | Redactar, franquear, enviar |
| **Plan** | Conjunto de tareas para conseguir una meta | Comprar sobre, escribir destinatario, poner sello |

> **Truco para el test.** Meta = **qué**. Tarea = **cómo**. Si una opción dice «cómo conseguir el objetivo con un dispositivo», es la tarea, no la meta.

## Modelo de Norman: ejecución y evaluación

Don Norman, *The Design of Everyday Things* (1988). Es quien populariza el **diseño centrado en el usuario**. El usuario formula un plan, lo ejecuta en la interfaz y observa el resultado para decidir si necesita más acciones.

```esquema Las 7 etapas del modelo de Norman
                        ┌──────────────────┐
                        │ 1. META          │
                        └───┬──────────▲───┘
            EJECUCIÓN       │          │      EVALUACIÓN
     ┌──────────────────────▼─┐      ┌─┴──────────────────────────┐
     │ 2. Formular INTENCIÓN  │      │ 7. EVALUAR respecto a meta │
     │ 3. ESPECIFICAR acciones│      │ 6. INTERPRETAR el estado   │
     │ 4. EJECUTAR acciones   │      │ 5. PERCIBIR el estado      │
     └──────────┬─────────────┘      └─────────────▲──────────────┘
                │  golfo de EJECUCIÓN              │ golfo de EVALUACIÓN
                ▼  «¿cómo lo hago?»                │ «¿qué ha pasado?»
          ┌─────────────────────── SISTEMA ────────┴─────────┐
          │            (el mundo / la interfaz)              │
          └──────────────────────────────────────────────────┘
```

- 7 etapas: **4 de ejecución contando la meta** (meta, intención, especificar, ejecutar) y **3 de evaluación** (percibir, interpretar, evaluar).
- Dos lenguajes: el **del sistema** (núcleo, atributos computacionales) y el **del usuario** (tarea, atributos psicológicos).
- **Golfo (abismo) de ejecución**: diferencia entre las acciones que el usuario quiere hacer y las que el sistema permite. Quiero «grabar» y me obliga a elegir canal, duración y confirmar.
- **Golfo de evaluación**: distancia entre cómo el sistema muestra su estado y lo que el usuario esperaba. Pulso grabar y nada indica que esté grabando.
- **Distancia semántica**: entre el significado de los elementos de la interfaz y el **objetivo** del usuario. **Distancia articulatoria**: entre ese significado y su **representación física**.
- **Modelo de Abowd y Beale**: amplía el de Norman metiendo la **interfaz de usuario de forma explícita**, como traductora entre el lenguaje del usuario y el del sistema.

```esquema Abowd y Beale: la interfaz como traductora
   USUARIO ──articulación──► ENTRADA ──actuación──► SISTEMA
      ▲                     ╲  INTERFAZ  ╱               │
      └────observación──── SALIDA ◄──presentación────────┘
```

> **Pregunta de test.** «¿Cuál NO es una etapa de Norman?»: descarta cualquier opción que no sea meta, intención, especificar, ejecutar, percibir, interpretar o evaluar. Ejemplos típicos de trampa: «compilar», «diseñar», «prototipar».

En el examen de la UC3M de 2016 pidieron aplicar las 7 etapas a «buscar el vuelo más barato de Madrid a Roma» en una web de aerolínea[[n: La solución oficial: 1) meta: buscar el vuelo más barato; 2) intención: usar la web de la aerolínea; 3) especificar: abrir la web, pulsar «Reservar vuelo», escribir origen y destino, elegir fechas, pulsar buscar; 4) ejecutar esas acciones; 5) percibir: la web muestra resultados; 6) interpretar: están ordenados por hora, hay que leer los precios; 7) evaluar: el precio me convence. Abismos de ejecución: el botón dice «Reservar» y yo solo quiero buscar; deja elegir una vuelta anterior a la ida. Abismos de evaluación: una capa transparente bloquea la pantalla sin decir qué pasa; no se puede saber ni cambiar el orden de los resultados.]]. Es un buen ejercicio mental: piensa siempre en **dónde me atasco al hacer** (ejecución) y **dónde no entiendo lo que pasó** (evaluación).

## Interfaz de usuario

Es el **canal** por el que se comunican usuario y ordenador. Tiene dos caras:

- **Lógica**: utilidad y usabilidad (sistemas y aplicaciones).
- **Física**: **ergonomía** (dispositivos).

# Interfaces de usuario (tema 2.1)

## Los nueve tipos

| Tipo | Lo esencial |
|---|---|
| 1. **Comandos** | Terminal. Eficiente, precisa y veloz, pero difícil de aprender. Alternativa para personas con discapacidad visual |
| 2. **WIMP y GUI** | Ventanas, iconos, menús y puntero. La primera WIMP la diseñó **Xerox** |
| 3. **Multimedia** | Combina varios medios. Mejora el aprendizaje y la aceptación |
| 4. **Realidad virtual** | Simulación gráfica con sensación de presencia. Simuladores de vuelo |
| 5. **Tableros** | Visualizar datos complejos y detectar patrones o anomalías |
| 6. **Móvil** | Pantalla pequeña, multitáctil, uso en movimiento |
| 7. **Voz** | Siri, Alexa. Útil para información concreta y para accesibilidad |
| 8. **Compartida** | Varios usuarios y varias entradas a la vez. Pantallas grandes o mesas |
| 9. **Cerebro-ordenador** | Ondas cerebrales que controlan un dispositivo. Requiere entrenamiento |

**WIMP** = **W**indows, **I**cons, **M**enus, **P**ointing device.

En el examen de 2018 preguntaron qué heurísticas de Nielsen suele violar una interfaz de comandos como la *shell* de Unix: **control y libertad del usuario** (muchos comandos no se pueden deshacer), **consistencia** (la misma letra es una opción distinta en cada comando) y **visibilidad del estado** (la lista de comandos posibles no se ve).

## Ventanas, MDI, SDI y TDI

Las ventanas se inventaron para superar el límite físico de la pantalla. Formas de organizarlas: ***tiled*** (en mosaico, permiten arrastrar y soltar), ***overlapping*** (solapadas: aprovechan el espacio pero pueden volverse inmanejables) y ***cascading*** (en cascada: aprovechan el espacio y ordenan).

```esquema MDI, SDI y TDI
  MDI (centrada en la APLICACIÓN)   SDI (centrada en el DOCUMENTO)   TDI (PESTAÑAS)
  ┌─ App ──────────────────┐        ┌─ Doc1 ──┐ ┌─ Doc2 ──┐       ┌─ App ─────────────┐
  │ menú                   │        │ menú    │ │ menú    │       │[Doc1][Doc2][Doc3] │
  │ ┌─Doc1──┐  ┌─Doc2──┐   │        │         │ │         │       │                   │
  │ │       │  │       │   │        │         │ │         │       │  Doc2 visible     │
  │ └───────┘  └───────┘   │        └─────────┘ └─────────┘       │                   │
  └────────────────────────┘        una ventana por documento     └───────────────────┘
  hijas atrapadas en la madre       (Word moderno)                navegadores, NetBeans
```

| | MDI (*Multiple Document Interface*) | SDI (*Single Document Interface*) | TDI (*Tabbed Document Interface*) |
|---|---|---|---|
| Centrada en | La **aplicación** | El **documento** | Variante de MDI con **pestañas** (*workbook*) |
| Ventajas | Conserva recursos, espacio coordinado, varios documentos a la vista | Menús y barras reflejan lo que se ve, menos complejidad visual | Cambio rápido entre documentos |
| Desventajas | Los menús cambian según el documento; las hijas no salen del área de la madre (se pierde la ventaja de varias pantallas) | No se agrupan documentos relacionados; la barra de tareas se llena | |

## Cajas de diálogo, paneles y pestañas

- **Caja de diálogo**: espacio para funciones secundarias (propiedades, confirmar, alertar de errores).
  - **Modal**: el usuario **no puede hacer otra cosa** hasta cerrarla.
  - **No modal**: se puede seguir usando el resto del programa.
- **Paneles**: agrupan funciones relacionadas, acceso sin menús. **Marcos** (*frames*): se minimizan y cambian de tamaño.
- **Pestañas**: apilan contenido. Varias filas de pestañas **rompen la consistencia** porque cambian de sitio al pulsar.

## Iconos y menús

| Correspondencia del icono | Ejemplo |
|---|---|
| **Similar** | La imagen de un fichero para un fichero |
| **Analógica** | Unas tijeras para «cortar» |
| **Arbitraria** | Una X para «borrar» |

Menús: **lista** (pocas opciones, todas visibles), **expandible** (muchas opciones, en cascada) y **contextual** (las opciones dependen de la tarea en curso, clic derecho).

Guías de fabricante: Apple *Human Interface Guidelines*, Microsoft *UX checklist*, Google *Material Design*.

# Usabilidad (tema 2.2)

## Definiciones

- **Miller**: propiedad que refleja la facilidad de uso de un sistema.
- **Nielsen**: atributo de calidad con **5 componentes**.
- **Bevan** (como la ISO 9241-11): nivel de **eficacia, eficiencia y satisfacción** con que unos usuarios concretos logran unos objetivos concretos en un **contexto** concreto.

```esquema Los 5 componentes de la usabilidad según Nielsen
                               ── USABILIDAD (Nielsen) ──
                                           │
          ┌────────────────┬───────────────┴┬────────────────┬────────────────┐
   ┌──────┴───────┐ ┌──────┴───────┐ ┌──────┴───────┐ ┌──────┴───────┐ ┌──────┴───────┐
   │ Facilidad    │ │ Eficiencia   │ │ Facilidad    │ │ Errores      │ │ Satisfacción │
   │ de aprender  │ │              │ │ de recordar  │ │ (pocos)      │ │ (subjetiva)  │
   │ learnability │ │              │ │ memorability │ │              │ │              │
   └──────────────┘ └──────────────┘ └──────────────┘ └──────────────┘ └──────────────┘
   Truco: «A-E-R-E-S»  Aprender · Eficiencia · Recordar · Errores · Satisfacción
```

> **Usabilidad ≠ experiencia de usuario (UX).** La usabilidad es un atributo de calidad de la interfaz. La UX es **más amplia**: toda la relación del usuario con la empresa, sus servicios y sus productos.

Las **metáforas** (papelera, carpeta, escritorio) ayudan porque combinan algo conocido con algo nuevo, pero pueden fallar al internacionalizar. Hay que probarlas con usuarios[[n: En el examen de 2018 pidieron definir metáfora y dar tres ejemplos de un sistema operativo. Respuesta modelo: una metáfora combina conocimiento familiar con conocimiento nuevo para que el usuario entienda el sistema; ejemplos: el **escritorio**, las **carpetas** y la **papelera**. En el mismo examen pidieron definir **modelo mental**: la idea transitoria y cambiante que el usuario tiene de cómo funciona un sistema y cómo se hacen las tareas; importa en el DCU porque si el diseño no coincide con él, el usuario se equivoca.]].

## El ser humano: percepción, memoria y razonamiento

**Percepción.** El tamaño y la profundidad percibidos dependen del **ángulo visual**. Leyes de la **Gestalt** («el todo es diferente de la suma de las partes»):

```esquema Tres leyes de la Gestalt
   CIERRE                 FIGURA Y FONDO            SIMPLICIDAD
   ┌──   ──┐              ░░░░░░░░░░░░░             ○ ○ ○ ○
   │       │  ← vemos     ░░░▓▓▓▓▓▓▓░░░  ← vemos    ○ ○ ○ ○  ← vemos «un
           │  un cuadrado ░░░▓▓▓▓▓▓▓░░░  la figura  ○ ○ ○ ○    cuadrado de
   └──   ──┘  completo    ░░░░░░░░░░░░░  (fondo     ○ ○ ○ ○    círculos»,
                                         ignorado)             no 16 sueltos
```

- **Brillo**: luminancia percibida (subjetivo). **Contraste**: relación entre la luz de un objeto y la de su fondo.
- **Color**: **matiz** (longitud de onda), **intensidad** (brillo) y **saturación** (cantidad de blanco).
- Usa **entre 5 y 7 colores**. Los **grises claros** son los mejores fondos. El color sirve para **categorizar, diferenciar o destacar**, nunca como única vía de información: la interfaz debería funcionar en blanco y negro. Daltónicos: **8 % de los hombres** y **1 % de las mujeres**.
- **Fríos**: calma, profesionalidad. **Cálidos**: pasión, energía. **Complementarios**: opuestos en el círculo cromático (fuerte contraste). **Análogos**: vecinos (esquemas armónicos).

**Memoria:**

```esquema Los tres almacenes de memoria
  estímulo ──► SENSORIAL ──atención──► CORTO PLAZO ──repaso──► LARGO PLAZO
               icónica,                 ≤ 30 segundos            tiempo indefinido
               ambiental, táctil        7 ± 2 elementos          capacidad ilimitada
                                        (por eso menús cortos
                                         y opciones agrupadas)
```

**Razonamiento:**

| Tipo | Qué es | Ejemplo de los apuntes |
|---|---|---|
| **Deductivo** | De lo general a lo particular, con lógica | Pedro es persona; las personas comen pescado; Pedro come pescado |
| **Inductivo** | Generalizar a partir de casos | Veo un avión con hélices; todos los aviones las tienen |
| **Abductivo** | Explicar un efecto por su causa probable | Santi corre cuando bebe; lo veo correr; ha bebido |

**Beneficios de la usabilidad**: menos costes de aprendizaje y de asistencia, más aceptación, mejor imagen, mejor calidad de vida, menos costes de producción y más productividad.

## Principios de diseño y artefactos

Los principios de diseño son **abstracciones generalizables** que orientan al diseñador. Vienen de la **teoría, la experiencia y el sentido común**. **No existe una regla de oro** que funcione siempre.

- Entender al usuario y el dominio. Claridad y simplicidad. Visibilidad de controles.
- **Consistencia**: lo parecido se hace igual. Inconsistencia clásica: el teclado del teléfono (1-2-3 arriba) frente al de la calculadora (7-8-9 arriba).
- Pistas visuales y auditivas. Organización lógica: lo más usado, más cerca.
- Ocultar o **desactivar** lo que no se puede usar en ese momento. *Feedback* del estado. Legibilidad y soporte de teclado.
- **Mensajes de error**: específicos («Falta el paréntesis izquierdo», no «Error de sintaxis»), constructivos, en tono positivo y centrados en el usuario.

```esquema Los cuatro artefactos de diseño
  HEURÍSTICAS ........ abstracciones generalizables: experiencia, sentido común, teoría
  GUÍAS DE DISEÑO .... recomendaciones basadas en la EXPERIMENTACIÓN
  PATRONES ........... soluciones PROBADAS a problemas RECURRENTES, recopiladas SISTEMÁTICAMENTE
  MÉTODOS DE INSPECCIÓN  procedimientos para EVALUAR el grado de usabilidad
```

## Las 10 heurísticas de Nielsen

Nielsen las sacó de analizar **249 problemas de usabilidad**. Salen en tres temas (2.2, 3.2 y 4.2) y en **todos** los exámenes de la UC3M: apréndelas de memoria y con un ejemplo cada una.

| Nº | Heurística | Ejemplo en la calculadora o el torrent |
|---|---|---|
| 1 | **Visibilidad del estado del sistema** | Barra de progreso, «Descargando…», la expresión «125 +» encima del visor |
| 2 | **Coincidencia entre el sistema y el mundo real** | Teclas +, −, ×, ÷ como una calculadora de verdad; palabras «Pausada», «Completada» |
| 3 | **Control y libertad del usuario** | Pausar, reanudar, cancelar; C, CE y ⌫ |
| 4 | **Consistencia y estándares** | Menú Archivo / … / Ayuda en el orden de siempre; botones iguales |
| 5 | **Prevención de errores** | «Pausar» desactivado si no se está descargando; MC y MR desactivados sin memoria; confirmar al cancelar |
| 6 | **Reconocimiento antes que recuerdo** | Opciones visibles en menús y botones, no comandos que memorizar |
| 7 | **Flexibilidad y eficiencia de uso** | Atajos Ctrl+O y Ctrl+Q para usuarios expertos |
| 8 | **Estética y diseño minimalista** | Solo los controles necesarios |
| 9 | **Ayudar a reconocer, diagnosticar y recuperarse de errores** | «No se puede dividir entre cero» |
| 10 | **Ayuda y documentación** | *Tooltips*, menú Ayuda › Acerca de |

```esquema Truco para recordar las 10 heurísticas
  1-3  el sistema y yo ......... ESTADO · MUNDO REAL · CONTROL
  4-6  no me hagas pensar ...... CONSISTENCIA · PREVENIR · RECONOCER
  7-8  rápido y limpio ......... FLEXIBILIDAD · MINIMALISMO
  9-10 cuando algo va mal ...... ERRORES · AYUDA
```

Así las preguntaron en los exámenes de 2016 y 2017 (dan un problema, dices la heurística)[[n: Ejemplos oficiales. 2016: «el botón Borrar campos es demasiado llamativo» → **prevención de errores**; «los enlaces no están subrayados» → **consistencia y estándares**; «los documentos se listan por un código raro en vez de por título» → **coincidencia con el mundo real**; «el formulario pide los datos personales cada vez que entras» → **flexibilidad y eficiencia**. 2017: «hay *feedback* visual de qué opción está seleccionada» → **visibilidad del estado**; «las etiquetas usan terminología familiar» → **coincidencia con el mundo real**; «las áreas se agrupan y se distinguen con cabeceras» → **estética y diseño minimalista**; «el experto puede definir macros y atajos» → **flexibilidad y eficiencia**; «es fácil moverse entre ventanas solapadas» → **control y libertad**.]]:

| Problema detectado | Heurística |
|---|---|
| El botón «Borrar campos» es demasiado llamativo | Prevención de errores |
| Los enlaces no están subrayados | Consistencia y estándares |
| Los productos aparecen por un código raro, no por su nombre | Coincidencia con el mundo real |
| Te pide tus datos cada vez que entras | Flexibilidad y eficiencia |
| Se ve qué opción del menú está seleccionada | Visibilidad del estado |
| El experto puede definir atajos y macros | Flexibilidad y eficiencia |

## Patrones de diseño

- Años **70**: **Christopher Alexander**, en **arquitectura** y urbanismo.
- **1987**: llegan a la programación orientada a objetos. **1998**: a la comunidad HCI.
- Capturan **prácticas, no teoría**. Formato: **nombre, problema, solución, discusión** (ventajas e inconvenientes), **ejemplos** y **patrones relacionados**.

# Metodología y prototipado (tema 2.3)

## Proceso de diseño y conocer al usuario

Proceso (Dix): qué se quiere (requisitos) → análisis → diseño → **prototipo** (evaluar e iterar) → implementar y desplegar.

- ***Stakeholders***: todos los afectados **directa o indirectamente** por el sistema.
- **Persona**: descripción detallada de un **usuario típico** inventado: objetivos, capacidades, actitudes, tareas, contexto.
- **Métodos etnográficos**: observar a la gente en su contexto real.
- **Escenarios**: historias detalladas de cómo será la interacción.

## Diseño centrado en el usuario (DCU)

El usuario está en el centro de todo el proceso, para minimizar su esfuerzo cognitivo.

```esquema El ciclo del diseño centrado en el usuario
                 ┌──────────────────────────┐
          ┌─────►│ 1. REQUISITOS del usuario│ contexto de uso:
          │      └────────────┬─────────────┘ quién, para qué, dónde
          │                   ▼
 ┌────────┴────────┐  ┌──────────────────────┐
 │ 4. EVALUACIÓN   │  │ 2. ANÁLISIS          │ entrevistas, grupos
 │ (la MÁS         │  │                      │ focales, encuestas
 │  importante)    │  └──────────┬───────────┘
 └────────▲────────┘             ▼
          │      ┌──────────────────────────┐
          └──────│ 3. DISEÑO (prototipos)   │
                 └──────────────────────────┘
               se repite hasta que funcione
```

Tres modelos que tienen que coincidir: el **modelo del diseñador**, la **imagen del sistema** (lo que el diseñador plasma en la interfaz) y el **modelo mental del usuario** (lo que el usuario interpreta). El problema aparece cuando el diseñador y el usuario no piensan igual, y la única vía de comunicación entre ellos es la imagen del sistema.

## Prototipos

Un prototipo puede ser un boceto, un *storyboard*, una presentación, un vídeo, un objeto físico, un *mock-up* en papel o un sistema con funciones limitadas. Sirve para **evaluar la idea, recibir *feedback* y decidir**.

- ***Rapid prototyping***: tres fases en bucle: **definir → evaluar → redefinir**.
- Cuatro cualidades: **representación**, **fidelidad**, **interacción** y **evolución**.
- **Horizontal** = mucha amplitud: **muchas funciones con poco detalle** (fidelidad funcional).
- **Vertical** = mucha profundidad: **pocas funciones con mucho detalle**.

```esquema Matriz de fidelidad de los prototipos
                     FIDELIDAD FUNCIONAL
                   baja                 alta
            ┌───────────────────┬────────────────────┐
  FIDELIDAD │ PAPEL             │ WIREFRAMES         │
  VISUAL    │ primeras fases,   │ usabilidad,        │
   baja     │ funciones         │ pruebas de concepto│
            ├───────────────────┼────────────────────┤
   alta     │ MOCK-UPS          │ HI-FI              │
            │ evaluar el flujo  │ último paso antes  │
            │ del sistema       │ del producto final │
            └───────────────────┴────────────────────┘
```

```esquema Prototipo horizontal frente a vertical
  HORIZONTAL (ancho, poco profundo)       VERTICAL (estrecho, muy profundo)
  ┌────┬────┬────┬────┬────┬────┐         ┌────┐
  │ F1 │ F2 │ F3 │ F4 │ F5 │ F6 │         │ F1 │
  └────┴────┴────┴────┴────┴────┘         │    │
  muchas funciones, poco detalle          │    │  una función,
                                          │    │  hasta el fondo
                                          └────┘
```

# Accesibilidad (tema 2.4)

## Conceptos y diseño universal

- **Tecnología adaptativa** (*assistive technology*): productos que **mejoran o aumentan las capacidades** de personas con discapacidad: lectores de pantalla, líneas Braille, magnificadores, teclados adaptados, ratón de pie.
- **Diseño universal** (Ron **Mace**, 1991): productos usables por todos **sin necesidad de adaptarlos**.
- ***Design for All***: engloba diseño accesible, inclusivo, sin barreras y universal.
- La **accesibilidad es un prerrequisito de la usabilidad**: lo que no se puede usar, no puede ser fácil de usar.

```esquema Los 7 principios del diseño universal
  1 EQUITATIVO ........ útil para todas las personas
  2 FLEXIBLE .......... se adapta a preferencias y habilidades
  3 SIMPLE E INTUITIVO  se entiende sin experiencia previa
  4 PERCEPTIBLE ....... la información llega por varios sentidos
  5 TOLERANTE AL ERROR  minimiza los riesgos de equivocarse
  6 MÍNIMO ESFUERZO ... se usa cómodamente y sin fatiga
  7 TAMAÑO Y ESPACIO .. sitio suficiente para acercarse y usarlo
  Truco: «E-F-S-P-T-M-T»  Ella Funciona Siempre Para Todos, Mira Tú
```

En el examen de 2018 pidieron completar huecos con «accesibilidad» o «usabilidad»: que personas que hablan **idiomas distintos** puedan usar el sistema igual es **accesibilidad**[[n: Es la idea que más sorprende: la accesibilidad no es solo discapacidad física. Los apuntes incluyen como factores de diversidad la edad, la experiencia, la cultura, el idioma y la clase social. Si el enunciado habla de «que todo el mundo pueda usarlo» (idioma, discapacidad, edad), es accesibilidad; si habla de «que sea fácil, rápido y agradable», es usabilidad.]].

## Diversidad y técnicas de interacción

- **Diversidad de usuarios**: discapacidad (percepción visual o auditiva, movimiento, cognición), **edad**, **experiencia**, **cultura** (el verde es sagrado en el Islam; el amarillo, en el budismo) y **social**.
- **Diversidad tecnológica**: el entorno también discapacita (ruido, oscuridad). Un usuario puede estar **temporalmente discapacitado** por su contexto.

Técnicas: **voz** (salida, reconocimiento y diálogo), **háptica** (tacto: Braille, vibración), **escaneo** (un marcador recorre la interfaz y el usuario pulsa un interruptor), ***eye-tracking***, gestos y cabeza, **interfaces cerebrales** (**invasivas** con sondas dentro o **no invasivas** con electrodos fuera), **lengua de signos** y **multimodales** (dos o más modos de entrada).

## Accesibilidad web

| Guía | Para qué |
|---|---|
| **WCAG** (*Web Content Accessibility Guidelines*) | Que todo el contenido web sea accesible. Niveles **A, AA y AAA** |
| **WAI-ARIA** (*Accessible Rich Internet Applications*) | Hacer accesibles el **contenido dinámico** y los controles avanzados (JavaScript) |

Herramientas: **TAW** (Test de Accesibilidad Web), validador del W3C, WebAIM.

# Diseño web (tema 3)

## Principios, estructura y navegación

- Diseñar para las necesidades del usuario. **Evitar la navegación lineal**. Facilitar la búsqueda.
- El usuario debe saber siempre **dónde está, qué puede hacer y cómo ir a otro sitio**.
- Tres aspectos: **estructura, navegación e interacción**.
- **Anchura antes que profundidad** (*first width, then depth*): mejor muchos enlaces en pocos niveles.

```esquema Anchura antes que profundidad
   MAL: profundo y estrecho              BIEN: ancho y poco profundo
        [Inicio]                                 [Inicio]
           │                      ┌──────┬──────┬───┴──┬──────┬──────┐
         [A]                     [A]    [B]    [C]    [D]    [E]    [F]
           │                      │      │
         [A1]                   [A1]   [B1]   3 niveles como mucho:
           │                                  inicio → secciones → subsecciones
         [A11] ← 4 clics para llegar
```

Las tres preguntas de la navegación: **¿Dónde estoy? ¿Dónde he estado? ¿Dónde puedo ir?**

| Tipo de enlace | A dónde lleva |
|---|---|
| **Estructural** | A otras páginas del **mismo sitio**. Define su estructura |
| **Asociativo** | A otro contenido de la **misma página** |
| **Embebido** | A otras páginas o a **otros sitios**. Información adicional |

Según Nielsen, lo que hace que un enlace parezca enlace es el **color azul** (y subrayado). Ayudas a la navegación: visita guiada, mapa del sitio, directorios, **migas de pan**, búsqueda, metáforas.

## Página, contenido y legibilidad

- El **contenido** por encima de la estética. Separar contenido de presentación.
- *Scroll*: lo importante arriba. **Nunca *scroll* horizontal**.
- **Widget**: componente que da información visual y facilita una interacción concreta.
- Descarga en **menos de 1 segundo**, como mucho 10.
- Texto corto, sin introducciones, con listas y tablas, sin jerga.
- **Utilidad** = solo el uso que se hace del sistema. **Usabilidad** = la utilidad más eficiencia, seguridad, aprendizaje, recuerdo y satisfacción.
- Legibilidad: alto contraste, líneas de unos **60 caracteres**, fuente de **11 a 13 puntos**, **sin mayúsculas** en texto corrido, alineado a la izquierda.

| Papel del color | Uso |
|---|---|
| **Subordinado** | Color base: fondos y espacios |
| **Principal** | Transmite: texto y títulos |
| **De realce** | Destaca: enlaces y acciones |

## Tres listas de heurísticas

| Shneiderman: 8 «reglas de oro» | Nielsen y Molich: 9 | Nielsen: 10 |
|---|---|---|
| Consistencia | Diálogo sencillo y natural | Las del apartado 4 |
| Atajos para usuarios habituales | Hablar el lenguaje del usuario | |
| Retroalimentación informativa | Minimizar la carga de memoria | |
| Diálogos con cierre[[n: Errata de las diapositivas de la UC3M: en la lista de Shneiderman, el punto 4 aparece como «Consistencia y estandarización», repitiendo el punto 1. La regla original de Shneiderman es «diseñar diálogos que indiquen el cierre» (que el usuario sepa cuándo ha terminado una secuencia, como el «Pedido confirmado»). Si una pregunta copia la diapositiva, da por buena la versión de los apuntes.]] | Consistencia | |
| Prevención y manejo simple de errores | Retroalimentación | |
| Acciones reversibles | Salidas claramente marcadas | |
| *Locus* de control interno | Atajos | |
| Reducir la carga de la memoria a corto plazo | Buenos mensajes de error | |
| | Prevenir errores | |

> **Pregunta de test.** Shneiderman **8**, Nielsen y Molich **9**, Nielsen **10**. «Acciones reversibles» y «*locus* de control interno» son de **Shneiderman**: si aparecen como heurística de Nielsen, es la opción falsa.

## Patrones web de Van Duyne

**Van Duyne**, *The Design of Sites*: un **lenguaje de patrones** es una colección de patrones relacionados entre sí. No dan una solución exacta ni resuelven todo. Fases del desarrollo web: **descubrimiento → exploración → refinamiento → producción**.

Los patrones tienen letra y número por familias. Los que salieron en los exámenes de 2016 y 2017:

| Patrón | Qué dice |
|---|---|
| **B3** *Hierarchical organization* | Organizar el contenido en jerarquía |
| **C1** *Homepage portal* | La portada dedica el 95 % del espacio al 95 % de los visitantes |
| **D7** *Inverted-pyramid writing style* | Titular conciso y descriptivo primero, detalles después |
| **D10** *Internationalized and localized content* | Contenido adaptado a idioma y país |
| **E1** *Site branding* | El logo de la marca arriba a la izquierda |
| **I3** *Clear first reads* | Un área de «primera lectura» que centra la atención |
| **J1** *Search action module* | Un buscador visible |
| **K3** *Tab rows* | Pestañas que marcan cuál está activa |
| **K6** *Location bread crumbs* | Migas de pan: saber dónde estás |
| **K11** *Familiar language* | Lenguaje familiar para el usuario |

# Diseño de la interacción (tema 4.1)

## Ciclos de vida y requisitos

- **Cascada**: lineal y secuencial; los requisitos **no se pueden cambiar** después; **no está centrado en el usuario**.
- **Ágil**: software que funciona antes que documentación, responder al cambio antes que seguir un plan, personas antes que procesos, colaboración con el cliente (que es parte del equipo).

```esquema Cascada frente a iterativo
  CASCADA                                   ITERATIVO / DCU
  Requisitos ─┐                              ┌──► Diseñar ──┐
              └► Diseño ─┐                   │              ▼
                         └► Código ─┐      Evaluar ◄─── Prototipar
                                    └► Pruebas   (el usuario en cada vuelta)
  sin vuelta atrás, sin usuario
```

Un **requisito** declara **qué** debe hacer un producto o **cómo** debe hacerlo.

- **Funcional**: qué hace el sistema («calcular la suma»).
- **No funcional**: restricciones del sistema o del desarrollo («funcionar en Windows y Mac», «responder en menos de 1 s»).
- Otros: de datos, del entorno, del usuario y de usabilidad.

## Recogida de datos

Técnicas: cuestionarios, entrevistas, grupos de interés y talleres, observación, estudio de documentación y software de registro (*key-loggers*, grabaciones de vídeo, notas). Se combinan[[n: En el examen de 2017 preguntaron cuáles de estas son técnicas de recogida de datos: grabaciones de vídeo, *key-loggers*, heurísticas de Nielsen, notas. La respuesta era vídeo, *key-loggers* y notas. Las **heurísticas de Nielsen NO** son una técnica de recogida de datos: son un artefacto de diseño que se usa en la **evaluación** heurística. Trampa clásica.]].

| Cuestionario | Qué mide |
|---|---|
| **QUIS** | Satisfacción subjetiva con aspectos de la interfaz |
| **CSUQ** | Satisfacción con la usabilidad, **19 preguntas** |
| **PUEU** | Utilidad percibida y facilidad de uso percibida |
| **NAU** | Los 5 atributos de Nielsen |
| **ASQ** | Tras un escenario: facilidad, tiempo e información de ayuda |
| **PUTQ** | **8 factores** de HCI |
| **SUMI** | Calidad de uso de un producto software |
| **SUS** | Escala **Likert de 10 elementos**. Visión global de la usabilidad |
| **TAM** | Aceptación de la tecnología |

- Un cuestionario necesita **validez** (mide lo que debe) y **fiabilidad** (da resultados consistentes). Antes se prueba un **piloto**.
- **Abiertas**: fáciles de hacer, difíciles de analizar. **Cerradas**: casillas, intervalos, **escala Likert** (grado de acuerdo) y **diferencial semántico** (entre dos adjetivos: fácil 1-2-3-4-5 difícil).

```esquema Entrevistas ordenadas por el control del entrevistador
  menos control ◄──────────────────────────────────────────► más control
  NO ESTRUCTURADA ── SEMIESTRUCTURADA ── EN GRUPO (focus group) ── ESTRUCTURADA
  (brainstorming aún menos)
```

La **observación** importa porque lo que la gente **dice** que hace y lo que **hace** no suelen coincidir.

## Escenarios, casos de uso y HTA

- **Escenario**: descripción **informal** de actividades con actores, actividades y objetos. Narrativa, diagrama de flujo, procedimiento o *storyboard*.
- **Caso de uso**: asociado a un **actor**, con un **rumbo normal** y **rumbos alternativos**.
- **HTA** (*Hierarchical Task Analysis*): divide la tarea en subtareas de forma **recursiva**, centrado en acciones **observables y físicas**; las subtareas se ordenan con **planes**.

```esquema HTA: tomar prestado un libro
  0. Tomar prestado un libro
  ├── 1. Ir a la biblioteca
  ├── 2. Encontrar el libro
  │     ├── 2.1 Acceder al catálogo     ├── 2.2 Acceder a la búsqueda
  │     ├── 2.3 Introducir criterios    ├── 2.4 Identificar el libro
  │     └── 2.5 Anotar la localización
  ├── 3. Ir a la estantería y coger el libro
  └── 4. Ir al mostrador
  Plan 0: 1-3-4. Si el libro no está en la estantería, 2-3-4.
  Plan 2: 2.1-2.4-2.5. Si no se identifica, 2.2-2.3-2.4-2.5.
```

En 2018 pidieron una HTA de una app de rutas («buscar y guardar en favoritos el Camino de Santiago en bici…»). La forma de la respuesta es siempre la misma: **0. meta**, subtareas numeradas **1, 2, 2.1…** y **planes** con condiciones («si no aparece en el listado, hacer 2-3-4»).

## Modelos predictivos: GOMS, KLM, Fitts y Hick

- **MHP** (*Model Human Processor*): sistema perceptivo, cognitivo y motor.
- **GOMS**: ***G**oals* (metas), ***O**perators* (operadores: acciones físicas y cognitivas), ***M**ethods* (métodos aprendidos) y ***S**election rules* (reglas para elegir método). Sirve para **comparar sistemas** y va bien en análisis sencillos[[n: El examen de 2016 abría con «explica GOMS y da un ejemplo». Respuesta modelo: GOMS modela el conocimiento y los procesos cognitivos del usuario. Metas: los estados que quiere conseguir; operadores: los procesos cognitivos y acciones físicas para lograrlas; métodos: procedimientos aprendidos para una meta; reglas de selección: para elegir método cuando hay varios. Ejemplo: borrar una palabra en Word (método 1: doble clic y Supr; método 2: Ctrl+Retroceso; regla: si las manos están en el teclado, método 2).]].
- **KLM** (*Keystroke-Level Model*): **predice el tiempo** que tarda un experto en hacer una tarea **sin errores** asignando un tiempo a cada acción.

| Operador KLM | Significado | Tiempo |
|---|---|---|
| **K** | Pulsar una tecla o botón | 0,2 s |
| **P** | Apuntar con el ratón | 1,1 s |
| **H** | Mover la mano de un dispositivo a otro | 0,4 s |
| **M** | Preparación mental | 1,35 s |
| **R(t)** | Respuesta del sistema | Se mide |
| **B** / **BB** | Pulsar o soltar el botón del ratón / clic completo | 0,1 s / 0,2 s |

```esquema Ejemplo de cálculo KLM
  Tarea: estoy tecleando, cojo el ratón y pulso dos botones
  M        H        P        K        P        K
  1,35  +  0,4  +  1,1  +  0,2  +  1,1  +  0,2  =  4,35 s
  M al empezar o al cambiar de tarea · H cada vez que cambias de dispositivo
```

- **Ley de Fitts**: el tiempo para **apuntar** a un objetivo depende de la **distancia** y del **tamaño**. ID = log₂(D/W + 1); MT = a + b·ID. Por eso los botones importantes son grandes y los **bordes de la pantalla** son las zonas más fáciles (el ratón «se para» en ellos).
- **Ley de Hick**: el tiempo para **decidir** crece con el **número de opciones**: T = a + b·log₂(n + 1). Por eso los menús cortos.

```esquema Fitts frente a Hick
  FITTS (apuntar)                          HICK (decidir)
  cerca y grande = rápido                  pocas opciones = rápido
  ●────►[████████]                         [A][B]          ← decides ya
  ●────────────────────►[▪]                [A][B][C][D][E][F][G][H]
  lejos y pequeño = lento                  muchas opciones = lento
```

> **Pregunta de test.** **Fitts** = distancia y tamaño (**apuntar**). **Hick** = número de opciones (**decidir**). No las cruces: es la trampa favorita.

## Diseño conceptual

Transforma los requisitos en un **modelo conceptual**: qué hace el sistema, cómo se comporta y qué aspecto tiene. El diseñador hace el modelo conceptual; el usuario se forma su **modelo mental**. Tres formas de pensarlo: el **modo de interacción**, la **metáfora** y el **paradigma de interacción**. Cinco preguntas para una metáfora: **estructura, relevancia, representación, comprensión y extensibilidad**.

# Evaluación e inspección (tema 4.2)

## Evaluación con usuarios

**Test de usabilidad**: usuarios típicos hacen tareas típicas en un entorno controlado y se graban. Se mide tiempo, número y tipo de errores.

- Tareas de unos **30 minutos**, mismas condiciones para todos, **consentimiento informado**.
- Normalmente **5 a 10 participantes**. Según Nielsen, **5 evaluadores encuentran el 75-80 %** de los problemas.

```esquema Con 5 usuarios ya se encuentra casi todo (Nielsen)
  % problemas
  100 ┤                         ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
   80 ┤              ▄▄▄▄▄▄▄▄▄▄█
   60 ┤        ▄▄▄▄▄█
   40 ┤    ▄▄▄█
   20 ┤ ▄▄█
    0 ┼─┴───┴───┴───┴───┴───┴───┴───┴───► usuarios
        1   2   3   4   5   6   7   8
                        ▲ con 5, ya ≈ 75-80 %
```

- **Variable independiente**: la que **manipula** el investigador (edad, dispositivo). **Dependiente**: la que se **mide** (tiempo, clics, % que acaba).

| Diseño experimental | Ventajas | Desventajas |
|---|---|---|
| **Entre-sujetos** (grupos distintos) | Sin efectos de orden | Muchos sujetos; influyen las diferencias individuales |
| **Intra-sujetos** (los mismos en todo) | Pocos sujetos; sin diferencias individuales | Hay que **contrabalancear** el orden |
| **Emparejados** | Como entre-sujetos con menos diferencias | El emparejamiento nunca es perfecto |

**Estudios de campo**: en entornos naturales (*in the wild*).

## Inspecciones sin usuarios

Las hacen **expertos**, no usuarios.

- **Evaluación heurística** (Nielsen, principios de los 90): expertos en usabilidad revisan la interfaz guiados por las heurísticas. Pasos: elegir heurísticas, elegir evaluadores, informarles, uso libre (unas 2 h), aplicar las heurísticas **individualmente**, anotar problemas y **puesta en común**. Ventaja: sin problemas éticos. Problemas: los expertos son caros, se escapan problemas importantes, salen muchos triviales y hay sesgos.
- **Recorrido cognitivo** (*cognitive walkthrough*): se centra en la **facilidad de aprendizaje**. Los expertos recorren un escenario con **4 preguntas**:

```esquema Las 4 preguntas del recorrido cognitivo
  1. ¿Intentará el usuario lograr el resultado correcto?
  2. ¿Se dará cuenta de que la acción correcta está disponible?
  3. ¿Asociará la acción correcta con el resultado que espera?
  4. Si hace la acción correcta, ¿verá que progresa?
```

- **Recorrido pluralístico**: en equipo (usuarios, diseñadores y expertos), primero por separado y luego se discute.
- **Analítica web** (Google Analytics): **tasa de rebote** (visitantes nuevos que se van tras ver una sola página), tasa de salida, tiempo medio y valor de página.

# Paradigmas de interacción (tema 5)

## Paradigmas físicos y conceptuales

- **Paradigma de interacción**: forma de pensar el diseño de la interacción. **Facetas**: los mecanismos para interactuar; los **dispositivos** son hardware y los **elementos de interacción**, software.
- **Gran escala**: *mainframes* con terminales (hoy, supercomputadoras). **Personal, en red y móvil**.
- **Tangible**: la información digital se maneja con **objetos físicos**.
- ***Embodied***: la computación se reparte por el entorno. **Mark Weiser** (Xerox PARC, **1991**): «las tecnologías más profundas son las que desaparecen». Tres formas: **ubicua** (integrada, no se percibe como objeto), **invisible** y **vestible** (*wearable*).
- **Virtual**: mundos virtuales.

```esquema Continuo de la virtualidad de Milgram (1994)
  ENTORNO   ◄──────────── REALIDAD MIXTA ────────────►  ENTORNO
  REAL         REALIDAD AUMENTADA    VIRTUALIDAD AUMENTADA    VIRTUAL
  (el mundo)   (real + algo virtual) (virtual + algo real)   (todo virtual)
```

Paradigmas conceptuales: tiempo compartido, unidades de vídeo (**Sketchpad**, 1962), herramientas de programación (Engelbart, 1968), ventanas (WIMP), metáfora, **manipulación directa**, comandos frente a acciones, **hipertexto** (Vannevar **Bush** y el **Memex**, 1945), multimodalidad, trabajo cooperativo (**CSCW**) y agentes.

## Dispositivos

```esquema Clasificación de los dispositivos de entrada
  ENTRADA
  ├── DISCRETOS ........... teclado, botonera
  ├── CONTINUOS (punteros)
  │     ├── DIRECTOS ...... pantalla táctil, lápiz óptico   (tocas donde actúas)
  │     └── INDIRECTOS .... ratón, joystick, trackball, tableta gráfica
  └── DE RECONOCIMIENTO ... gestos (Kinect), mirada (eye tracker), voz
```

- Teclados: **QWERTY (1868)**, de las máquinas de escribir; **Dvorak (1936)**, ordena las teclas por frecuencia para mover menos los dedos.
- Salida: pantallas **CRT** (pesadas, calientan) y **LCD** (finas), altavoces, ***head-up displays*** (proyectan en el campo visual sin tapar el entorno) y ***head-mounted displays*** (pantallas en la cabeza).
- El mejor dispositivo depende del **contexto, la tarea y el usuario**.

# Psicología del color (Unidad 1 y 4)

## Triple acción y significados

Cada color ejerce una **triple acción**: **impresiona** (llama la atención), **expresa** (provoca una emoción) y **construye** (se vuelve símbolo y comunica una idea). Los significados son de la **cultura occidental**: en Japón y en muchos países islámicos, el **blanco** simboliza la **muerte**.

| Color | Significado |
|---|---|
| **Rojo** | Pasión, peligro, vitalidad, acción. El más caliente. Puede despertar agresividad |
| **Naranja** | Alegría, juventud, calor. Optimismo y confianza |
| **Amarillo** | El más luminoso. Buen humor, actividad mental, creatividad |
| **Verde** | Esperanza, naturaleza. Calma y relaja. Neutraliza los cálidos |
| **Azul** | Profundidad, seriedad, **confianza**, tranquilidad. El más sobrio de los fríos. En exceso, tristeza |
| **Púrpura** | Misterio, espiritualidad, realeza. Algo melancólico |

Blanco, negro y gris «no son colores»: el **blanco** es la suma de todos, el **negro** la ausencia de color y el **gris** la mezcla de ambos.

```esquema Modelos de color RGB y HSV
  RGB (luz, pantallas)                 HSV / HSB
  R ███ rojo     0-255                 H matiz      (qué color: 0-360°)
  G ███ verde    0-255                 S saturación (cuánto color / blanco)
  B ███ azul     0-255                 V valor      (brillo)
  (255,255,255) = blanco · (0,0,0) = negro
  En Swing: new Color(76, 194, 255) → el azul del botón «=»
```
