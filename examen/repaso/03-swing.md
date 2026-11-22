# Java Swing y NetBeans: teoría (Unidad 2)

## AWT y Swing

| | AWT (`java.awt`) | Swing (`javax.swing`) |
|---|---|---|
| Qué es | La biblioteca gráfica original | Construida **encima** de AWT |
| Componentes | «Pesados»: los dibuja el sistema operativo | «Ligeros»: los dibuja Java; se ven igual en todos los sistemas |
| Nombres | `Button`, `Frame`, `Label` | Empiezan por **J**: `JButton`, `JFrame`, `JLabel` |
| Hoy se usa para | Colores, *layouts* y eventos (`Color`, `BorderLayout`, `ActionEvent`) | Los componentes |

Por eso un programa Swing importa de los dos paquetes: componentes de `javax.swing` y *layouts*, colores y eventos de `java.awt` y `java.awt.event`.

```esquema Jerarquía de clases de Swing (lo esencial)
  java.awt.Component
  └── java.awt.Container
        ├── java.awt.Window
        │     └── java.awt.Frame ──► javax.swing.JFrame      (ventana principal)
        │     └── java.awt.Dialog ─► javax.swing.JDialog     (cuadro de diálogo)
        └── javax.swing.JComponent                            (todos los J… ligeros)
              ├── JPanel  JScrollPane  JToolBar  JMenuBar
              ├── JLabel  JProgressBar  JTable  JList
              ├── AbstractButton
              │     ├── JButton   JToggleButton ─► JCheckBox, JRadioButton
              │     └── JMenuItem ─► JMenu
              └── JTextComponent ─► JTextField ─► JPasswordField
                                    JTextArea
```

## Contenedores y componentes

Una interfaz Swing es un **árbol**: la ventana contiene paneles y los paneles contienen componentes u otros paneles. El *Navigator* de NetBeans enseña justo ese árbol.

```esquema Árbol de contención de la calculadora
  JFrame «Calculadora»            (BorderLayout)
  ├── JPanel panelVisor           NORTH  (BorderLayout)
  │     ├── JLabel expresion      NORTH  «125 +»
  │     ├── JTextField pantalla   CENTER «7,5»
  │     └── JPanel panelMemoria   SOUTH  (GridLayout 1 x 5)
  │           └── MC MR M+ M− MS
  └── JPanel panelTeclado         CENTER (GridLayout 6 x 4)
        └── 24 JButton  % CE C ← 1/x x² √x ÷ 7 8 9 × …
```

| Clase | Qué es |
|---|---|
| `JFrame` | Ventana principal con barra de título |
| `JDialog` | Cuadro de diálogo; puede ser modal |
| `JPanel` | Contenedor para agrupar |
| `JScrollPane` | Contenedor con barras de desplazamiento (para tablas y áreas de texto) |
| `JToolBar` | Barra de herramientas |
| `JLabel` | Texto o imagen que el usuario no edita |
| `JButton` | Botón |
| `JTextField` / `JTextArea` / `JPasswordField` | Texto de una línea / de varias / oculto |
| `JCheckBox` / `JRadioButton` + `ButtonGroup` | Casillas / opciones excluyentes |
| `JComboBox` / `JList` | Desplegable / lista |
| `JTable` + `DefaultTableModel` | Tabla y sus datos |
| `JProgressBar` / `JSlider` / `JSpinner` | Barra de progreso / deslizador / número con flechas |
| `JMenuBar` › `JMenu` › `JMenuItem` | Barra de menú › menú › opción |
| `JOptionPane` / `JFileChooser` | Diálogos hechos / elegir fichero |

Métodos que valen para casi todos: `setText`/`getText`, `setEnabled`, `setVisible`, `setBackground`, `setForeground`, `setFont`, `setToolTipText`.

## Layout managers

Un *layout manager* decide **posición y tamaño** de los componentes de un contenedor. Se usa en vez de coordenadas fijas para que la ventana se adapte al cambiar de tamaño, de fuente o de sistema.

```esquema Los cuatro layouts que más se usan
  BorderLayout (JFrame)            GridLayout(2, 3)               FlowLayout (JPanel)
  ┌──────────────────────────┐     ┌────────┬────────┬────────┐   ┌──────────────────────┐
  │          NORTH           │     │   1    │   2    │   3    │   │ [Uno] [Dos] [Tres]   │
  ├──────┬────────────┬──────┤     ├────────┼────────┼────────┤   │ [Cuatro] [Cinco]  ◄─ │
  │ WEST │   CENTER   │ EAST │     │   4    │   5    │   6    │   │ salta de línea       │
  │      │ (se queda  │      │     └────────┴────────┴────────┘   └──────────────────────┘
  │      │ lo que     │      │     todas las celdas IGUALES;      respeta el tamaño
  │      │  sobra)    │      │     se rellena por filas, de       preferido; en fila,
  ├──────┴────────────┴──────┤     izquierda a derecha            de izquierda a derecha
  │          SOUTH           │
  └──────────────────────────┘     BoxLayout: una sola fila o una sola columna
  1 componente por zona
```

| *Layout* | Cómo coloca | Por defecto en |
|---|---|---|
| `FlowLayout` | En fila; salta de línea si no cabe. Respeta el tamaño preferido | **`JPanel`** |
| `BorderLayout` | Cinco zonas; el centro se lleva el espacio sobrante | **`JFrame`** (su *content pane*) |
| `GridLayout` | Rejilla con **todas las celdas iguales** | |
| `BoxLayout` | Una fila o una columna | |
| `GridBagLayout` | Rejilla flexible, celdas de distinto tamaño. El más potente y el más complejo | |
| `CardLayout` | Paneles apilados, solo uno visible | |
| `GroupLayout` | El que genera NetBeans en ***Free Design*** | Formularios de NetBeans |
| `null` | Posición absoluta con `setBounds`. No se adapta | |

> **Pregunta de test.** `JPanel` → `FlowLayout`. `JFrame` → `BorderLayout`. `GridLayout` = celdas iguales. NetBeans (*Free Design*) → `GroupLayout`.

La calculadora es el ejemplo perfecto de por qué se combinan: `BorderLayout` para el reparto general y `GridLayout` para el teclado[[n: Por qué no se usa *Free Design* (el modo por defecto de NetBeans) para el teclado: con 24 botones habría que alinearlos uno a uno y, al agrandar la ventana, no crecerían igual. Con `GridLayout(6, 4)` basta soltar los botones y todos miden lo mismo y crecen a la vez. Es una decisión de diseño que conviene saber justificar: consistencia (heurística 4) y menos trabajo.]].

## Eventos y oyentes

Swing está **dirigido por eventos**: el programa no sigue un orden fijo, espera a que el usuario haga algo. Es el **modelo de delegación de eventos**:

```esquema Modelo de delegación de eventos
   ┌──────────┐ 1. el usuario pulsa  ┌──────────────┐ 3. Swing llama a   ┌───────────────────┐
   │ USUARIO  │ ───────────────────► │ FUENTE       │ ─────────────────► │ OYENTE (listener) │
   └──────────┘                      │ JButton «7»  │   actionPerformed  │ ActionListener    │
                                     └──────┬───────┘   (ActionEvent e)  └───────────────────┘
                                            │ 2. crea un EVENTO               ▲
                                            ▼                                 │
                              ┌──────────────────────────┐  antes, una vez:   │
                              │ ActionEvent              │  boton.addActionListener(oyente)
                              │ getSource() → el botón   │  ◄── REGISTRO ─────┘
                              │ getActionCommand() → «7» │
                              └──────────────────────────┘
```

| Evento | Interfaz oyente | Método | Cuándo |
|---|---|---|---|
| `ActionEvent` | `ActionListener` | `actionPerformed` | Clic en botón, **Intro** en un `JTextField`, elegir opción de **menú**, tic de un `javax.swing.Timer` |
| `MouseEvent` | `MouseListener` | `mouseClicked`, `mousePressed`, `mouseReleased`, `mouseEntered`, `mouseExited` | Ratón |
| `KeyEvent` | `KeyListener` | `keyPressed`, `keyReleased`, `keyTyped` | Teclado |
| `WindowEvent` | `WindowListener` | `windowClosing`, `windowOpened`… | Ventana |
| `ItemEvent` | `ItemListener` | `itemStateChanged` | Casilla, `JComboBox` |
| `ChangeEvent` | `ChangeListener` | `stateChanged` | `JSlider`, `JSpinner` |
| `FocusEvent` | `FocusListener` | `focusGained`, `focusLost` | Foco |
| `ListSelectionEvent` | `ListSelectionListener` | `valueChanged` | Selección en `JList` o `JTable` |

Las **adaptadoras** (`MouseAdapter`, `KeyAdapter`, `WindowAdapter`) implementan la interfaz con todos los métodos vacíos para sobrescribir solo el que interesa.

Tres formas de escribir un oyente:

```java
// 1. La propia ventana es el oyente
public class Ventana extends JFrame implements ActionListener {
    boton.addActionListener(this);
    public void actionPerformed(ActionEvent e) { ... }
}

// 2. Clase anónima (lo que genera NetBeans, que luego llama a botonXActionPerformed)
boton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) { ... }
});

// 3. Lambda (Java 8+), la más corta
boton.addActionListener(e -> calcular());
```

## El hilo de eventos (EDT)

Swing **no es seguro frente a hilos**: todo lo que crea o cambia la interfaz debe ejecutarse en un único hilo, el ***Event Dispatch Thread*** (EDT).

```esquema Por qué la ventana se congela con Thread.sleep
  EDT (un solo hilo atiende TODO):
  ──[pintar]──[clic «Iniciar»]──[ oyente: for(...) { sleep(100); barra++ } ]──[pintar]──►
                                 └──── 10 s ocupado: NO pinta, NO atiende clics ────┘
                                       la ventana se queda blanca y la barra salta 0 → 100

  Con javax.swing.Timer:
  ──[pintar]──[clic]──[tic]──[pintar]──[tic]──[pintar]──[clic Pausar]──[tic]──[pintar]──►
                     cada tic es cortito: entre tic y tic el EDT pinta y atiende clics
```

- Arrancar: `SwingUtilities.invokeLater(() -> new Ventana().setVisible(true));` (NetBeans genera `java.awt.EventQueue.invokeLater(...)`, que es lo mismo).
- Los oyentes ya corren en el EDT: si uno tarda (bucle largo, `Thread.sleep`), **la ventana se congela**.
- Tareas periódicas: **`javax.swing.Timer`**, cuyo oyente corre en el EDT. Tareas largas en segundo plano: **`SwingWorker`**[[n: `SwingWorker` ejecuta `doInBackground()` en otro hilo y luego `done()` (y `process()` para resultados parciales) otra vez en el EDT, así que puede tocar la interfaz sin peligro. Para el torrent simulado no hace falta: no hay trabajo pesado, solo actualizar números cada 200 ms, que es justo para lo que existe `javax.swing.Timer`. Si el profesor pregunta «¿y si la descarga fuera real?», la respuesta es `SwingWorker`, con `publish(progreso)` para mover la barra.]].
- **Ojo con el `import`**: es `javax.swing.Timer`, no `java.util.Timer` (ese ejecuta su tarea en otro hilo).

## NetBeans: zonas del entorno

```esquema Las zonas de NetBeans con un JFrame Form abierto
  ┌───────────────────────────────────────────────────────────────────────────────┐
  │ File  Edit  View  …  Run  …      [► Run F6]  [Build F11]                      │
  ├──────────────┬─────────────────────────────────────────────┬──────────────────┤
  │ PROJECTS     │ Calculadora.java                            │ PALETTE          │
  │ ▸ proyecto   │ [Source] [Design] [History]                 │ Swing Containers │
  │   ▸ Source   │ ┌─────────────────────────────┐             │ Swing Controls   │
  │     Packages │ │                             │  ◄ vista    │ Swing Menus      │
  │     ▸ paquete│ │      el formulario          │    DESIGN   │ …                │
  ├──────────────┤ │                             │             ├──────────────────┤
  │ NAVIGATOR    │ └─────────────────────────────┘             │ PROPERTIES       │
  │ Form …       │                                             │ [Properties]     │
  │ └ [JFrame]   │                                             │ [Events] [Code]  │
  │   └ jPanel1  │  ◄ árbol de componentes: aquí se editan     │ text, font,      │
  │     └ jBut…  │    menús, layouts y el orden                │ background…      │
  └──────────────┴─────────────────────────────────────────────┴──────────────────┘
```

| Zona | Para qué |
|---|---|
| **Projects** | Árbol de proyectos y ficheros |
| **Navigator** | Árbol de componentes del formulario. **Imprescindible**: cambiar *layouts*, menús, orden y nombres |
| **Palette** | Componentes para arrastrar |
| **Properties** | Pestañas ***Properties***, ***Events*** y ***Code*** del componente seleccionado |
| Vista ***Design*** / ***Source*** | Diseño gráfico / código. Están sincronizadas |

## Código que genera NetBeans

- El constructor llama a **`initComponents()`**, que crea y coloca todos los componentes.
- `initComponents()` y la declaración de variables están en **bloques protegidos** (fondo gris) que **no se editan a mano**: se cambian desde *Design* y *Properties*[[n: Si de verdad necesitas meter código dentro de `initComponents()` (por ejemplo, para crear un componente con un constructor especial), se hace desde la pestaña *Code* de *Properties*: *Custom Creation Code*, *Pre-Init Code* o *Post-Init Code*. NetBeans lo inserta en el bloque protegido por ti. Para el examen basta con escribir lo tuyo **después** de `initComponents()` en el constructor.]].
- El diseño se guarda en un fichero **`.form`** (XML) junto al `.java`. Si se borra, se pierde la vista *Design*.
- Lo tuyo va **después de `initComponents()`** en el constructor, en los métodos de evento o en métodos nuevos que añadas.
- NetBeans usa **nombres completos** (`javax.swing.JButton`) en el código generado, sin `import`.

| Acción | Tecla |
|---|---|
| *Run Project* | **F6** (la primera vez pregunta la clase principal) |
| *Run File* (solo este fichero) | **Mayús + F6** |
| *Build Project* | **F11** |
| *Clean and Build* | **Mayús + F11** |
| *Format* (ordenar el código) | **Alt + Mayús + F** |
| *Fix Imports* | **Ctrl + Mayús + I** |
| Completar código | **Ctrl + Espacio** |

Carpetas: un proyecto **Ant** guarda fuentes en `src/`, clases en `build/classes/` y el `.jar` en `dist/`. Un proyecto **Maven** guarda fuentes en `src/main/java/` y todo lo generado en `target/`.

## Menús, tablas y diálogos

- **Menús**: `JMenuBar` (la barra) › `JMenu` (desplegable) › `JMenuItem` (opción) o `JMenu` anidado; `JSeparator` es una línea. La barra **no va en el *content pane***: se pone con `setJMenuBar(barra)`. Una opción de menú lanza un **`ActionEvent`**, igual que un botón.
- **Mnemónico** (`setMnemonic`): letra subrayada, **Alt + letra** abre el menú. **Acelerador** (`setAccelerator`): atajo directo como **Ctrl + O**, sin abrir el menú.
- **`JTable`** va siempre dentro de un **`JScrollPane`** (si no, no se ven las cabeceras). Los datos viven en el modelo, **`DefaultTableModel`**: `addRow`, `removeRow`, `setValueAt`, `getValueAt`, `getRowCount`.
- **`JDialog`**: `super(padre, titulo, modal)`. **`modal = true` bloquea** la ventana padre hasta cerrarse. `dispose()` cierra y libera; `setVisible(false)` solo oculta.

```java
JOptionPane.showMessageDialog(this, "Texto", "Título", JOptionPane.WARNING_MESSAGE);
int r = JOptionPane.showConfirmDialog(this, "¿Seguro?", "Título", JOptionPane.YES_NO_OPTION);
String s = JOptionPane.showInputDialog(this, "Nombre:");
```

## Lo mínimo de una ventana

```java
JFrame ventana = new JFrame("Título");
ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
ventana.add(panel);
ventana.pack();
ventana.setLocationRelativeTo(null);
ventana.setVisible(true);
```

| Método | Qué hace | Si se olvida |
|---|---|---|
| `setDefaultCloseOperation(EXIT_ON_CLOSE)` | Cerrar la ventana termina el programa | Por defecto es `HIDE_ON_CLOSE`: se oculta y el proceso sigue vivo |
| `pack()` | Ajusta la ventana al tamaño preferido del contenido | Sale diminuta (alternativa: `setSize`) |
| `setLocationRelativeTo(null)` | Centra en la pantalla | Sale arriba a la izquierda |
| `setVisible(true)` | Muestra la ventana | No se ve nada: las ventanas nacen invisibles |

Valores de cierre: `EXIT_ON_CLOSE`, `DISPOSE_ON_CLOSE`, `HIDE_ON_CLOSE` y `DO_NOTHING_ON_CLOSE`.
