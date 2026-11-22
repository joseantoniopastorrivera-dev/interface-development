# Casos prácticos, errores y chuleta

## ¿Qué pasaría si…?

| Situación | Qué ocurre |
|---|---|
| Quito `setVisible(true)` | El programa arranca y no se ve nada |
| Quito `EXIT_ON_CLOSE` | La ventana se cierra pero el proceso sigue vivo (en NetBeans, el botón rojo de parar sigue activo) |
| Quito `pack()` y no pongo `setSize` | Ventana diminuta, solo la barra de título |
| Pongo dos componentes en `BorderLayout.CENTER` | Solo se ve el último: cada zona admite **uno**. Hay que meterlos en un `JPanel` |
| Añado la `JTable` sin `JScrollPane` | Se ven las filas pero no las cabeceras |
| Uso `int` en la calculadora | `7 ÷ 2` da 3 y `7 ÷ 0` lanza `ArithmeticException` |
| Escribo `"x + y = " + x + y` | Con 2 y 3 muestra `2.03.0`: concatena texto. Faltan paréntesis: `+ (x + y)` |
| No compruebo la división entre cero con `double` | Sale `Infinity` en el visor |
| Pongo `Thread.sleep` en un botón | La ventana se congela mientras dura |
| Importo `java.util.Timer` | Su tarea corre en otro hilo: tocar la interfaz desde ahí no es seguro (y su API es distinta: no compila con `new Timer(200, e -> …)`) |
| Importo `java.util.*` y `javax.swing.*` y uso `Timer` | Error de compilación: «reference to Timer is ambiguous». Importa `javax.swing.Timer` explícitamente |
| Pulso F6 en un proyecto Maven recién creado | Ejecuta la clase con el `main` vacío: BUILD SUCCESS y ninguna ventana. Usa **Mayús + F6** sobre el formulario |
| Borro el `.form` | Se pierde la vista *Design*; solo queda el código |
| Cambio el texto de un botón de la calculadora | Su `case` del `switch` deja de coincidir y el botón no hace nada |
| No paro el `Timer` al llegar al 100 % | Sigue sumando: la barra pasa de 100 (se queda llena) y los datos se disparan |

## Corrige este error

| Síntoma | Causa | Arreglo |
|---|---|---|
| `NullPointerException` al pulsar un botón | El componente se declaró como atributo y **otra vez** como variable local en el constructor: el atributo sigue siendo `null` | En el constructor, `campo = new JTextField();` sin el tipo delante |
| El botón no hace nada | No tiene oyente (`addActionListener`) o en NetBeans no se le asignó el evento | Doble clic en el botón o *Events › actionPerformed* |
| `NumberFormatException` en *Output* | `parseDouble` con texto que no es número (o con coma) | `replace(',', '.')` y `try/catch` |
| La barra salta de 0 a 100 | Bucle con `sleep` en el hilo de Swing | `javax.swing.Timer` |
| «cannot find symbol: method avanzar()» | El método está mal escrito o dentro de otro método | Revisa llaves: cada método de lógica va fuera de los demás, dentro de la clase |
| «illegal start of expression» al pegar código | Has pegado un método **dentro** de otro método (por ejemplo, dentro de `botonPulsado`) | Saca el método: va al nivel de la clase |
| No deja escribir donde quiero | Es un bloque protegido (gris) de NetBeans | Escribe fuera, o usa *Properties › Code* |
| Los botones salen enormes | Están en el `CENTER` de un `BorderLayout` o en un `GridLayout`, que estiran | Mételos en un `JPanel` con `FlowLayout` o en una `JToolBar` |
| Un componente añadido por código no aparece | El contenedor no se ha recolocado | `revalidate();` y `repaint();` |
| «No main classes found» | No hay clase principal configurada | *Run File* (Mayús + F6) o *Properties › Run › Main Class* |
| BUILD FAILURE con «Could not resolve…» o «Plugin… could not be resolved» | Maven sin Internet en el usuario nuevo | Proyecto nuevo con **Java with Ant** |
| Los símbolos ÷ × ⌫ salen como cuadrados | La fuente no tiene ese carácter | Otra fuente (*Segoe UI Symbol*) o texto plano: `/`, `*`, `<-` |

## Chuleta de teoría

```esquema Chuleta de teoría en una página
  NORMAN ....... 7 etapas: meta·intención·especificar·ejecutar | percibir·interpretar·evaluar
                 golfo EJECUCIÓN «¿cómo lo hago?»  · golfo EVALUACIÓN «¿qué ha pasado?»
  ABOWD-BEALE .. añaden la INTERFAZ como traductora
  ACM SIGCHI 87  diseño · evaluación · implementación
  HITOS ........ 1963 ratón Engelbart · 1968 The Demo · 1981 Xerox Star · 1984 Lisa (apuntes)
  WIMP ......... Windows Icons Menus Pointer — Xerox
  MDI app · SDI documento · TDI pestañas · MODAL bloquea
  ICONOS ....... similar · analógica (tijeras) · arbitraria (X)
  NIELSEN USAB.  aprender · eficiencia · recordar · errores · satisfacción
  MEMORIA ...... corto plazo 7±2 y 30 s · COLORES 5-7 · daltónicos 8 % H, 1 % M
  GESTALT ...... cierre · figura-fondo · simplicidad
  ARTEFACTOS ... heurísticas · guías (experimento) · patrones (probados) · inspección
  HEURÍSTICAS .. Shneiderman 8 · Nielsen-Molich 9 · Nielsen 10 (249 problemas)
  PATRONES ..... Alexander años 70 · OO 1987 · HCI 1998 · Van Duyne (web)
  DCU .......... requisitos → análisis → diseño → EVALUACIÓN (la más importante)
  PROTOTIPOS ... horizontal = muchas funciones · vertical = mucho detalle
                 papel (baja/baja) · wireframe (baja V/alta F) · mock-up (alta V/baja F) · Hi-Fi
  ACCESIBILIDAD  diseño universal Mace 1991, 7 principios · WCAG A/AA/AAA · WAI-ARIA dinámico
  WEB .......... anchura > profundidad · enlaces estructurales/asociativos/embebidos
                 60 caracteres por línea · 11-13 pt · nunca scroll horizontal · < 1 s
  CASCADA ...... no centrada en el usuario · REQUISITO no funcional = restricción
  CUESTIONARIOS  SUS Likert 10 · CSUQ 19 · PUTQ 8 factores
  ENTREVISTA ... no estructurada < semi < grupo < estructurada (control)
  KLM .......... K 0,2 · P 1,1 · H 0,4 · M 1,35 · B 0,1 · BB 0,2
  FITTS ........ distancia + tamaño (APUNTAR) · HICK nº opciones (DECIDIR)
  TEST USAB. ... 5-10 usuarios · 5 encuentran 75-80 % · VI manipulo, VD mido
  INSPECCIÓN ... heurística = expertos · recorrido cognitivo = aprendizaje, 4 preguntas
  PARADIGMAS ... Weiser 1991 ubicua · Milgram 1994 real→RA→VA→virtual · Bush Memex 1945
  DISPOSITIVOS . táctil = puntero directo · ratón = indirecto · QWERTY 1868 · Dvorak 1936
  COLOR ........ impresiona · expresa · construye · azul confianza · rojo pasión/peligro
```

## Chuleta de Swing y NetBeans

```esquema Chuleta de Swing en una página
  LAYOUTS ...... JPanel → FlowLayout · JFrame → BorderLayout · NetBeans Free Design → GroupLayout
                 GridLayout(filas, columnas, hgap, vgap) = celdas iguales
  EVENTOS ...... botón, menú, Intro, Timer → ActionEvent → actionPerformed
                 fuente + evento + oyente (addXxxListener)
  EDT .......... invokeLater para arrancar · javax.swing.Timer para repetir · SwingWorker para tareas largas
  VENTANA ...... EXIT_ON_CLOSE · pack() · setLocationRelativeTo(null) · setVisible(true)
  MENÚ ......... setJMenuBar(JMenuBar › JMenu › JMenuItem) · mnemónico Alt · acelerador Ctrl
  TABLA ........ JTable dentro de JScrollPane · datos en DefaultTableModel
  DIÁLOGOS ..... JOptionPane.showMessageDialog / showConfirmDialog / showInputDialog
  NETBEANS ..... initComponents() no se toca · .form = diseño · F6 run · Mayús+F6 run file
                 F11 build · Navigator › Set Layout · Events › actionPerformed
  CALCULADORA .. un método para todos: evt.getActionCommand() + switch
                 acumulado · operacion · nuevaEntrada · replace(',', '.') · ÷0 a mano
  TORRENT ...... Timer(200, e -> avanzar()) · start/stop · barra.setValue(%)
                 actualizarControles(): botón y menú activos según el estado
```

## Plan de ensayo y el día del examen

```esquema Plan de ensayo de esta noche (cronometrado)
  ┌──────────────────────────────────────────────────────────────────────────┐
  │ 1. Calculadora en NetBeans con el PDF delante ............ 40 min        │
  │ 2. Calculadora OTRA VEZ, proyecto nuevo, SIN mirar ....... 30 min        │
  │ 3. Torrent capa 1 + 2 + 3 con el PDF delante ............. 40 min        │
  │ 4. Torrent capa 1 SIN mirar (TorrentMinimo) .............. 15 min        │
  │ 5. Teoría: leer recuadros, tablas y esquemas (2 a 12) .... 60 min        │
  │ 6. Banco de test entero, corregir, repasar las falladas .. 40 min        │
  │ 7. Chuletas de este apartado antes de dormir ............. 10 min        │
  └──────────────────────────────────────────────────────────────────────────┘
```

**Antes de empezar el examen**

- Abre NetBeans en cuanto puedas: tarda en arrancar la primera vez.
- Crea el proyecto y haz ***Clean and Build*** **antes** de diseñar. Si falla, Ant.
- Si te dejan llevar algo, que sean los símbolos ÷ × − ⌫ ² √ escritos para copiarlos.

**En el test**

- Tres vueltas: las que sabes; las que permiten descartar; las de lotería, en blanco.
- Cuidado con «¿cuál **NO** es…?»: subráyalo mentalmente.
- Trampas favoritas: Fitts/Hick, Norman 7 etapas, Shneiderman 8 / Nielsen-Molich 9 / Nielsen 10, horizontal/vertical, `JPanel`/`JFrame`.

**En la práctica**

1. Que compile y se vea la ventana (aunque esté vacía).
2. El esqueleto con sus *layouts* (`BorderLayout` + `GridLayout`).
3. La lógica mínima (calculadora: cifras, + − × ÷, =, C; torrent: capa 1).
4. Prueba con la tabla de «Cómo demostrarlo».
5. Extras solo si funciona lo anterior: menú, barra de estado, colores, atajos.
6. Guarda a menudo (**Ctrl + S**) y prueba a menudo (**Mayús + F6**).
