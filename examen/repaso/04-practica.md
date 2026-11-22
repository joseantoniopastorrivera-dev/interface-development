# NetBeans desde cero (usuario «examen»)

## Qué te vas a encontrar

El día del examen entras con un usuario nuevo, «examen». NetBeans arranca **como recién instalado**: sin proyectos, sin configuración y con la carpeta de Maven vacía. Lo que veías en la foto de clase (*Projects* a la izquierda, *Palette* y *Properties* a la derecha) solo aparece cuando abres un formulario.

```esquema Del NetBeans vacío a la ventana funcionando
 ┌──────────────┐   ┌──────────────┐   ┌──────────────┐   ┌──────────────┐   ┌──────────────┐   ┌──────────────┐
 │ 1 Abrir      │──►│ 2 New Project│──►│ 3 JFrame     │──►│ 4 Diseño     │──►│ 5 Código     │──►│ 6 Run        │
 │ NetBeans     │   │ Maven › Java │   │ Form         │   │ Palette +    │   │ Events +     │   │ Mayús+F6     │
 │              │   │ Application  │   │              │   │ Properties   │   │ Source       │   │              │
 └──────────────┘   └──────────────┘   └──────────────┘   └──────────────┘   └──────────────┘   └──────────────┘
      1 min              2 min              1 min              15 min             20 min             1 min      
```

## Primer arranque

1. Abre NetBeans. Si sale un aviso de **estadísticas de uso**, pulsa *No*. Si sale uno de **actualizaciones**, ciérralo.
2. Comprueba el JDK: ***Tools › Java Platforms***. Tiene que aparecer un JDK (17 o superior). Si no hay ninguno, avisa al profesor: sin JDK no se compila nada.
3. Si al crear el primer proyecto sale ***Activate*** o «*Activating Java SE*», pulsa *Next* o *Activate* y espera unos segundos. Es normal la primera vez y no necesita Internet.

## Crear el proyecto

1. ***File › New Project*** (**Ctrl + Mayús + N**).
2. *Categories*: **Java with Maven**. *Projects*: **Java Application**. *Next*.
3. *Project Name*: `CalculadoraExamen`. Deja la *Project Location* que propone (o elige el Escritorio). *Group Id*: `es.examen`. *Finish*.
4. NetBeans crea el paquete `es.examen.calculadoraexamen` y una clase `CalculadoraExamen.java` con un `main` vacío.
5. **Compila ya**, antes de diseñar nada: clic derecho en el proyecto › ***Clean and Build***. Si en la ventana *Output* sale **BUILD SUCCESS**, Maven funciona. Si sale **BUILD FAILURE** con errores de descarga, el usuario «examen» no tiene Internet o no tiene la caché de Maven[[n: Maven descarga de Internet sus *plugins* la primera vez que compila un proyecto y los guarda en `C:\Users\<usuario>\.m2`. Un usuario recién creado tiene esa carpeta vacía. Si los PC del examen no tienen salida a Internet, **cualquier** proyecto Maven fallará al compilar, por bien hecho que esté. Por eso se compila nada más crear el proyecto: si falla, pierdes un minuto y no cuarenta. Los proyectos **Ant** no descargan nada y compilan siempre.]]. Pasa al **plan B**.

> **Plan B: proyecto Ant.** *File › New Project* › ***Java with Ant*** › ***Java Application*** › **desmarca *Create Main Class*** › *Finish*. Todo lo demás de este repaso es idéntico.

## Crear la ventana (JFrame Form)

1. En *Projects*, despliega *Source Packages*, clic derecho en el paquete › ***New › JFrame Form…*** (si no aparece: *New › Other… › Swing GUI Forms › JFrame Form*).
2. *Class Name*: `Calculadora`. *Finish*.
3. Se abre la vista ***Design*** con un formulario gris vacío, la ***Palette*** a la derecha y el ***Navigator*** abajo a la izquierda. Esto es lo que había en tu foto.

> **La trampa del `main` vacío.** Maven ejecuta con **F6** la clase que creó en el paso 4 (`CalculadoraExamen`), que no hace nada: pulsas F6, sale BUILD SUCCESS y **no aparece ninguna ventana**. Soluciones: ejecuta la ventana con clic derecho › ***Run File*** (**Mayús + F6**), o cambia la clase principal en clic derecho en el proyecto › *Properties* › *Run* › *Main Class* › *Browse* › `Calculadora`[[n: El `JFrame Form` que crea NetBeans ya trae su propio `public static void main` al final (el que hace `java.awt.EventQueue.invokeLater(() -> new Calculadora().setVisible(true))`). Por eso *Run File* funciona directamente. Si quieres que F6 también funcione, cambia la *Main Class* o borra `CalculadoraExamen.java`; en Maven, la clase principal queda escrita en el `pom.xml`, en la propiedad `exec.mainClass`.]].

## Las cinco acciones del editor visual

| Quiero… | Cómo |
|---|---|
| Cambiar el *layout* de un contenedor | En el **Navigator**, clic derecho sobre el `JFrame` o el `JPanel` › ***Set Layout*** › *Border Layout*, *Grid Layout*… |
| Configurar el `GridLayout` (filas, columnas) | En el Navigator, despliega el panel y selecciona el nodo **GridLayout** › *Properties*: *Rows*, *Columns*, *Horizontal Gap*, *Vertical Gap* |
| Elegir la zona de `BorderLayout` (norte, centro…) | Selecciona el componente › *Properties* › apartado ***Layout*** › ***Direction***: *North*, *Center*… |
| Cambiar el texto o el nombre de la variable | Clic derecho › ***Edit Text*** / ***Change Variable Name…*** |
| Crear el método de un evento | Doble clic en el botón, o *Properties* › pestaña ***Events*** › ***actionPerformed*** |

Y para probar sin compilar: el botón del ojo, ***Preview Design***, encima del formulario.

# Calculadora clásica

## Qué hay que construir

![Calculadora clásica terminada (proyecto de referencia de examen/calculadora-clasica)](img/calculadora-clasica.png)

```esquema Esqueleto de la calculadora: layouts y nombres
  JFrame «Calculadora»  ── Set Layout › Border Layout
  ┌─────────────────────────────────────┐
  │ panelVisor (North) ─ Border Layout  │
  │ ┌─────────────────────────────────┐ │
  │ │                   125 +         │ │ ← JLabel expresion      (North)
  │ │                     7,5         │ │ ← JTextField pantalla   (Center)
  │ │ [MC] [MR] [M+] [M−] [MS]        │ │ ← panelMemoria (South) GridLayout 1 x 5
  │ └─────────────────────────────────┘ │
  │ panelTeclado (Center) ─ Grid 6 x 4  │
  │ ┌───────┬───────┬───────┬───────┐   │
  │ │   %   │  CE   │   C   │   ←   │   │
  │ │  1/x  │  x²   │  √x   │   ÷   │   │
  │ │   7   │   8   │   9   │   ×   │   │
  │ │   4   │   5   │   6   │   −   │   │
  │ │   1   │   2   │   3   │   +   │   │
  │ │  +/-  │   0   │   ,   │   =   │   │
  │ └───────┴───────┴───────┴───────┘   │
  └─────────────────────────────────────┘
  Los 29 botones llaman al MISMO método: botonPulsado(evt)
```

La idea clave que lo hace rápido: **un único método para los 29 botones**. Cada botón manda su propio texto en el evento (`evt.getActionCommand()` devuelve «7», «+», «CE»…), y un `switch` decide qué hacer. Así no hay que renombrar 29 variables ni escribir 29 métodos[[n: Es la misma idea que el `actionPerformed` común de la calculadora sencilla de clase, que distinguía el botón con `getActionCommand()`. La alternativa, un método por botón (`boton7ActionPerformed`, `boton8ActionPerformed`…), también es correcta pero son 29 métodos casi iguales: rompe el principio de no repetir código (DRY) y multiplica los sitios donde equivocarse.]].

## Paso a paso en el editor visual

**A. La estructura (5 min)**

1. En el Navigator, clic derecho en **[JFrame]** › *Set Layout* › ***Border Layout***.
2. En *Properties* del `JFrame`: `title` = `Calculadora`; `defaultCloseOperation` = `EXIT_ON_CLOSE` (ya viene así).
3. Arrastra un **Panel** (*Swing Containers*) al formulario. En *Properties › Layout › Direction* pon ***North***. Clic derecho › *Change Variable Name* › `panelVisor`. Clic derecho › *Set Layout* › ***Border Layout***.
4. Arrastra otro **Panel** al centro del formulario: *Direction* ***Center***, nombre `panelTeclado`, *Set Layout* › ***Grid Layout***. En el Navigator, selecciona su nodo *GridLayout*: ***Rows* 6, *Columns* 4, *Horizontal Gap* 2, *Vertical Gap* 2**.

**B. El visor (3 min)**

5. Arrastra un **Label** dentro de `panelVisor`: *Direction* ***North***, nombre `expresion`, `text` = un espacio, `horizontalAlignment` = ***RIGHT***.
6. Arrastra un **Text Field** dentro de `panelVisor`: *Direction* ***Center***, nombre `pantalla`, `text` = `0`, desmarca **`editable`**, `horizontalAlignment` = ***RIGHT***, `font` = *Segoe UI*, negrita, 36.
7. (Fila de memoria, si te da tiempo) Otro **Panel** dentro de `panelVisor` con *Direction* ***South***, *Set Layout* › *Grid Layout* con 1 fila y 5 columnas, y dentro 5 botones: `MC`, `MR`, `M+`, `M−`, `MS`. Renombra solo dos: `botonMC` y `botonMR`.

**C. El teclado (7 min)**

8. Arrastra un **Button** dentro de `panelTeclado`. Ponle la fuente (*Segoe UI*, 18) y desmarca `focusPainted`.
9. Selecciónalo, **Ctrl + C** y luego **Ctrl + V** 23 veces sobre `panelTeclado`: las copias conservan la fuente. El `GridLayout` las va colocando por filas, de izquierda a derecha.
10. Cambia el texto de cada uno (clic derecho › *Edit Text*) en este orden: `%` `CE` `C` `⌫` / `1/x` `x²` `√x` `÷` / `7` `8` `9` `×` / `4` `5` `6` `−` / `1` `2` `3` `+` / `+/-` `0` `,` `=`[[n: Los símbolos ⌫ ² √ ÷ × − no están en el teclado. Opciones en el examen: copiarlos de este PDF o del Mapa de caracteres de Windows (Win + R › `charmap`), o usar texto plano: `<-`, `x^2`, `raiz`, `/`, `*`, `-`. Si cambias un texto, cambia **también** el mismo texto en el `switch` del código: el `switch` compara exactamente lo que pone el botón.]]. Si alguno queda mal colocado, en el Navigator usa clic derecho › ***Move Up*** / ***Move Down***.

**D. Un solo evento para todos (2 min)**

11. En el **Navigator**, haz clic en el primer botón y **Mayús + clic** en el último (de los dos paneles, con **Ctrl + clic** puedes añadir los cinco de memoria). Quedan todos seleccionados.
12. *Properties* › pestaña ***Events*** › en ***actionPerformed*** escribe `botonPulsado` y pulsa **Intro**. NetBeans crea **un** método y lo conecta a todos los seleccionados[[n: Si tu versión de NetBeans no deja asignarlo a varios a la vez, hazlo botón a botón: selecciona un botón › *Events* › *actionPerformed* y, en vez de escribir, despliega la lista: aparece `botonPulsado` porque ya existe. Elegirlo conecta ese botón al mismo método.]] y te lleva a la vista *Source*, dentro del método.

**E. El código (10-20 min)**: el del apartado siguiente.

**F. Probar**: clic derecho en `Calculadora.java` › ***Run File*** (**Mayús + F6**).

## El código que escribes tú

En *Source* solo se puede escribir fuera de los bloques grises. Van tres trozos: los **atributos** (justo debajo de `public class Calculadora extends javax.swing.JFrame {`), el **cuerpo de `botonPulsado`** y los **métodos de lógica** (debajo del constructor o al final, antes del `main`).

**Versión mínima** (lo que tiene que funcionar sí o sí):

```java
// ===== 1. ATRIBUTOS: debajo de «public class Calculadora extends javax.swing.JFrame {»
private double acumulado = 0;          // (1) primer número, ya guardado
private String operacion = null;       // (2) +, −, ×, ÷ pendiente; null = ninguna
private boolean nuevaEntrada = true;   // (3) la próxima cifra empieza un número nuevo
private boolean error = false;         // (4) el visor muestra un mensaje de error

// ===== 2. CUERPO del método que creó NetBeans
private void botonPulsado(java.awt.event.ActionEvent evt) {
    String t = evt.getActionCommand();                     // (5) texto del botón pulsado
    switch (t) {                                           // (6)
        case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> digito(t);
        case "," -> coma();
        case "+", "−", "×", "÷" -> operador(t);
        case "=" -> igual();
        case "CE" -> { pantalla.setText("0"); nuevaEntrada = true; }
        case "C" -> borrarTodo();
        case "⌫" -> retroceso();
        case "+/-" -> cambiarSigno();
        default -> { }                                     // (7) lo que aún no está hecho
    }
}

// ===== 3. LÓGICA: debajo del constructor
private void digito(String d) {
    if (error) borrarTodo();
    if (nuevaEntrada) {                                    // (8)
        pantalla.setText("0");
        nuevaEntrada = false;
    }
    String actual = pantalla.getText();
    pantalla.setText(actual.equals("0") ? d : actual + d); // (9) sin ceros a la izquierda
}

private void coma() {
    if (nuevaEntrada) {
        pantalla.setText("0,");
        nuevaEntrada = false;
    } else if (!pantalla.getText().contains(",")) {        // (10) solo una coma
        pantalla.setText(pantalla.getText() + ",");
    }
}

private void operador(String op) {
    if (error) return;
    if (operacion != null && !nuevaEntrada) {              // (11) 2 + 3 + … encadena
        mostrar(aplicar(acumulado, operacion, leerPantalla()));
        if (error) return;
    }
    acumulado = leerPantalla();
    operacion = op;
    nuevaEntrada = true;
    expresion.setText(pantalla.getText() + " " + op);      // (12) «125 +»
}

private void igual() {
    if (error || operacion == null) return;
    double b = leerPantalla();
    double r = aplicar(acumulado, operacion, b);
    if (error) return;
    expresion.setText(formato(acumulado) + " " + operacion + " " + formato(b) + " =");
    mostrar(r);
    operacion = null;
    nuevaEntrada = true;
}

private double aplicar(double a, String op, double b) {
    switch (op) {
        case "+": return a + b;
        case "−": return a - b;
        case "×": return a * b;
        case "÷":
            if (b == 0) {                                  // (13) con double no salta excepción
                mostrarError("No se puede dividir entre cero");
                return 0;
            }
            return a / b;
        default: return b;
    }
}

private void borrarTodo() {
    acumulado = 0; operacion = null; nuevaEntrada = true; error = false;
    pantalla.setText("0");
    expresion.setText(" ");
}

private void retroceso() {
    if (nuevaEntrada || error) return;                     // no se borra un resultado
    String t = pantalla.getText();
    t = t.substring(0, t.length() - 1);
    pantalla.setText(t.isEmpty() || t.equals("-") ? "0" : t);
}

private void cambiarSigno() {
    String t = pantalla.getText();
    if (error || t.equals("0")) return;
    pantalla.setText(t.startsWith("-") ? t.substring(1) : "-" + t);
}

// ===== auxiliares
private double leerPantalla() {                            // (14) visor con coma → double
    return Double.parseDouble(pantalla.getText().replace(',', '.'));
}

private String formato(double v) {                         // (15) 5.0 → «5», 3.5 → «3,5»
    String s = (v == Math.rint(v) && Math.abs(v) < 1e15)
            ? String.valueOf((long) v) : String.valueOf(v);
    return s.replace('.', ',');
}

private void mostrar(double v) {
    pantalla.setText(formato(v));
}

private void mostrarError(String mensaje) {
    pantalla.setText(mensaje);
    error = true; operacion = null; nuevaEntrada = true;
}
```

**Extras** (si sobra tiempo): añade estos `case` al `switch` y sus métodos.

```java
case "%" -> porcentaje();
case "1/x" -> unaria("1/x");
case "x²" -> unaria("x²");
case "√x" -> unaria("√x");
case "MC", "MR", "M+", "M−", "MS" -> botonMemoria(t);

private void unaria(String f) {                            // operaciones de un solo número
    if (error) return;
    double x = leerPantalla();
    if (f.equals("1/x") && x == 0) { mostrarError("No se puede dividir entre cero"); return; }
    if (f.equals("√x") && x < 0)   { mostrarError("Entrada no válida"); return; }
    double r = switch (f) {
        case "1/x" -> 1 / x;
        case "x²"  -> x * x;
        default    -> Math.sqrt(x);
    };
    expresion.setText(f.replace("x", "(" + formato(x) + ")"));
    mostrar(r);
    nuevaEntrada = true;
}

private void porcentaje() {                                // 200 + 10 % → 200 + 20
    if (error) return;
    double x = leerPantalla();
    if (operacion == null) x = 0;
    else if (operacion.equals("+") || operacion.equals("−")) x = acumulado * x / 100;
    else x = x / 100;
    mostrar(x);
    nuevaEntrada = true;
}

private double memoria = 0;                                // atributo (súbelo con los otros)

private void botonMemoria(String t) {
    if (error) return;
    switch (t) {
        case "MC" -> memoria = 0;
        case "MR" -> mostrar(memoria);
        case "M+" -> memoria += leerPantalla();
        case "M−" -> memoria -= leerPantalla();
        case "MS" -> memoria = leerPantalla();
    }
    nuevaEntrada = true;
    botonMC.setEnabled(memoria != 0);                      // prevención de errores
    botonMR.setEnabled(memoria != 0);
}
```

Y en el constructor, **después de `initComponents();`**:

```java
setLocationRelativeTo(null);        // centrada
botonMC.setEnabled(false);          // al empezar no hay nada en memoria
botonMR.setEnabled(false);
```

## Explicación línea a línea

| Nº | Qué hace y por qué así |
|---|---|
| 1 | `double` y no `int`: admite decimales y `7 ÷ 2` da 3,5 |
| 2 | Guardar la operación como texto permite usar el mismo símbolo que el botón. `null` significa «no hay operación pendiente» |
| 3 | La bandera más importante: tras pulsar un operador o «=», la siguiente cifra **sustituye** el visor en vez de añadirse |
| 4 | Con un error en pantalla, cualquier tecla de cifra empieza de cero, como la de Windows |
| 5 | `getActionCommand()` devuelve, si no se cambia, el **texto del botón**. Es lo que permite un solo método para todos |
| 6 | `switch` con flechas (`->`, Java 14+): no necesita `break` y no hay «caída» de un `case` al siguiente[[n: Si el proyecto estuviera configurado para Java 8 (no debería: NetBeans 31 usa el JDK instalado, 17 o más), el `switch` con flechas no compila. Se escribe a la antigua: `case "+": operador(t); break;`. Para agrupar valores: `case "0": case "1": … digito(t); break;`.]] |
| 7 | `default` vacío: los botones que aún no programaste no hacen nada en vez de romper |
| 8 | Si toca número nuevo, se limpia el visor y se baja la bandera |
| 9 | Operador ternario: si el visor es «0», la cifra lo sustituye; si no, se añade al final |
| 10 | `contains(",")` impide escribir «3,5,2» |
| 11 | Encadenar: en `2 + 3 +`, al pulsar el segundo «+» se resuelve `2 + 3` y se muestra 5, como la calculadora de Windows |
| 12 | La etiqueta de arriba enseña la operación pendiente: **visibilidad del estado del sistema** (heurística 1) |
| 13 | Con `double`, dividir entre 0 **no lanza excepción**: da `Infinity`. Por eso se comprueba a mano y se avisa (heurística 9) |
| 14 | El visor usa coma decimal (español), pero `Double.parseDouble` solo entiende punto: se cambia antes de convertir |
| 15 | Muestra «5» y no «5.0», y cambia el punto por coma. Es lo que hace un `DecimalFormat` con formato español (el que usa el proyecto de referencia)[[n: `double` guarda los números en binario y algunos decimales no tienen representación exacta: `0.1 + 0.2` da `0.30000000000000004`. Este `formato` sencillo lo mostraría tal cual. El proyecto de referencia usa `new DecimalFormat("0.##########", new DecimalFormatSymbols(new Locale("es", "ES")))`, que redondea a 10 decimales y ya pone la coma: `0,1 + 0,2 = 0,3`. Para dinero de verdad se usaría `BigDecimal`. Si el profesor prueba 0,1 + 0,2, esto es lo que hay que saber explicar.]] |

```esquema Cómo cambia el estado al calcular 12 + 3 =
  tecla   pantalla  acumulado  operacion  nuevaEntrada   expresion
  ─────   ────────  ─────────  ─────────  ────────────   ─────────
  (inicio)    0         0        null        true
    1         1         0        null        false
    2        12         0        null        false
    +        12        12         +          true        12 +
    3         3        12         +          false       12 +
    =        15        12        null        true        12 + 3 =
```

## Cómo demostrarlo

| Pulsa | Debe salir | Qué demuestra |
|---|---|---|
| `2 + 3 =` | 5 | Lo básico |
| `2 + 3 + 4 =` | 9 | Encadenar operaciones |
| `7 ÷ 2 =` | 3,5 | Decimales con coma |
| `1 ÷ 0 =` | No se puede dividir entre cero | Error controlado |
| `1 , 5 × 2 =` | 3 | Coma decimal al escribir |
| `1 2 3 ⌫` | 12 | Retroceso |
| `2 + 5 CE 3 =` | 5 | CE borra solo el número actual |
| `5 +/-` | -5 | Cambio de signo |
| `9 √x` / `4 x²` / `4 1/x` | 3 / 16 / 0,25 | Operaciones de un número |
| `2 0 0 + 1 0 % =` | 220 | Porcentaje como en Windows |

## 🔧 Modificaciones que te pueden pedir

**Que funcione con el teclado** (heurística 7: flexibilidad). En el constructor, después de `initComponents()`:

```java
String teclas = "0123456789+-*/=,";
for (char c : teclas.toCharArray()) {
    String simbolo = switch (c) { case '-' -> "−"; case '*' -> "×"; case '/' -> "÷"; default -> String.valueOf(c); };
    getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(javax.swing.KeyStroke.getKeyStroke(c), simbolo);
    getRootPane().getActionMap().put(simbolo, new javax.swing.AbstractAction() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
            botonPulsado(new java.awt.event.ActionEvent(this, 0, simbolo));
        }
    });
}
```

**Colores como la de Windows**: selecciona todos los botones de cifras en el Navigator › `background` = `[59,59,59]`, `foreground` = blanco; el «=» con `background` `[76,194,255]`; el `JFrame` y los paneles con `[32,32,32]`. Por código: `boton.setBackground(new java.awt.Color(59, 59, 59));`.

**Límite de 16 cifras** (al principio de `digito`, después del `if (nuevaEntrada)`):

```java
if (pantalla.getText().replace("-", "").replace(",", "").length() >= 16) return;
```

**Botón de potencia `xʸ`**: es una operación de **dos** números, así que va con `+ − × ÷`: añade `"xʸ"` al `case` de `operador(t)` y en `aplicar` un `case "xʸ": return Math.pow(a, b);`.

**Historial de operaciones**: un `JTextArea` dentro de un `JScrollPane` en el `EAST` del `JFrame`, y en `igual()` añadir `historial.append(expresion.getText() + " " + pantalla.getText() + "\n");`.

**Menú Edición › Copiar**: `JMenuBar` › `JMenu` «Edición» › `JMenuItem` «Copiar» (acelerador Ctrl + C) con:

```java
java.awt.Toolkit.getDefaultToolkit().getSystemClipboard()
        .setContents(new java.awt.datatransfer.StringSelection(pantalla.getText()), null);
```

## Preguntas que te puede hacer el profesor

| Pregunta | Respuesta |
|---|---|
| ¿Por qué un solo método para todos los botones? | Porque todos hacen lo mismo con distinto dato: el texto del botón llega en `getActionCommand()`. Menos código repetido y un solo sitio que mantener |
| ¿Por qué `GridLayout` en el teclado? | Celdas iguales que crecen juntas al redimensionar. Con *Free Design* habría que alinear 24 botones a mano |
| ¿Por qué `BorderLayout` en la ventana? | El visor arriba con su altura natural y el teclado en el centro, que se lleva todo el espacio que sobra |
| ¿Por qué el visor es un `JTextField` no editable y no un `JLabel`? | Se puede seleccionar y copiar el resultado, pero el usuario no puede escribir letras: **prevención de errores** |
| ¿Qué pasa si divides entre cero? | Con `double` no hay excepción, saldría `Infinity`. Lo compruebo antes y muestro un mensaje claro (heurística 9) |
| ¿Por qué `replace(',', '.')`? | El usuario ve coma decimal (idioma del usuario, heurística 2) pero `parseDouble` espera punto |
| ¿Dónde escribes tu código? | Fuera de los bloques grises: atributos arriba, lógica en métodos propios y lo de inicio después de `initComponents()` |
| ¿Qué heurísticas cumple? | 1 (la expresión pendiente se ve), 2 (símbolos de calculadora real), 3 (C, CE, ⌫), 4 (todos los botones iguales), 5 (MC y MR desactivados sin memoria), 9 (mensajes de error claros) |

# Torrent simulado

## Qué hay que construir

![Torrent simulado terminado (proyecto de referencia de examen/simulador-torrent, clase TorrentConMenu)](img/torrent-con-menu.png)

Lo que pidió el profesor es una **interfaz** que parezca un cliente torrent y **simule** una descarga: no se descarga nada. Un **`javax.swing.Timer`** cambia los datos cada poco tiempo para que parezca que pasa algo[[n: Lo que se evalúa es la interfaz: estructura (menú, barra de herramientas, zona central, barra de estado), *feedback* del estado y controles coherentes. La parte «de mentira» es solo un número que crece. Si el enunciado del examen concreta algo distinto (una tabla con varias descargas, por ejemplo), el proyecto `examen/simulador-torrent` trae también `SimuladorTorrent`, la versión con `JTable` y una barra de progreso por fila.]].

```esquema Esqueleto del torrent: zonas, layouts y nombres
  JFrame «Torrent simulado» ── Border Layout
  ┌────────────────────────────────────────────────────────┐
  │ Archivo   Descarga   Ayuda                             │ ← JMenuBar (fuera del content pane)
  ├────────────────────────────────────────────────────────┤
  │ [+ Añadir] │ [> Iniciar] [|| Pausar] [x Cancelar]      │ ← JToolBar  (North)
  ├────────────────────────────────────────────────────────┤
  │  Archivo:          ubuntu-24.04.iso                    │
  │  Tamaño:           5.734 MB                            │   panelDatos (Center)
  │  Descargado:       709,4 MB                            │   GridLayout 0 x 2
  │  Velocidad:        84,2 MB/s                           │   (etiqueta | valor)
  │  Pares:            8 conectados                        │
  │  Tiempo restante:  01:02                               │
  │  [▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░ 12 % ░░░░░░░░░░░░░░░░░░░]  │ ← JProgressBar barra
  ├────────────────────────────────────────────────────────┤
  │ Descargando…                                           │ ← JLabel barraEstado (South)
  └────────────────────────────────────────────────────────┘
```

```esquema Menús del torrent
  Archivo (Alt+A)               Descarga (Alt+D)        Ayuda
  ├─ Añadir torrent…  Ctrl+O    ├─ Iniciar / Reanudar   └─ Acerca de…
  ├─ ──────────── (separador)   ├─ Pausar
  └─ Salir            Ctrl+Q    └─ Cancelar
```

```esquema Estados de la descarga y qué controles se activan
                 Iniciar                    Pausar
   ┌────────┐ ──────────► ┌─────────────┐ ─────────► ┌─────────┐
   │ PARADA │             │ DESCARGANDO │            │ PAUSADA │
   └────────┘ ◄────┐      └──────┬──────┘ ◄───────── └────┬────┘
        ▲          │             │ llega al 100 %  Reanudar│
        │      Cancelar          ▼                          │
        │   (con confirmación) ┌────────────┐               │
        └──────────────────────│ COMPLETADA │    Cancelar ──┘
          Añadir otro torrent  └────────────┘
  Iniciar: solo en PARADA o PAUSADA · Pausar: solo DESCARGANDO · Cancelar: todo menos PARADA
```

## Estrategia: construirlo por capas

No intentes hacerlo todo de golpe. Cada capa compila y se puede enseñar:

```esquema Las cuatro capas del torrent
  CAPA 1 (15 min) ─ barra + Iniciar/Pausar + Timer      → ya simula una descarga  = aprobado
  CAPA 2 (10 min) ─ JMenuBar con Archivo/Descarga/Ayuda → «con menú arriba»
  CAPA 3 (10 min) ─ JToolBar + barra de estado          → aspecto de cliente torrent
  CAPA 4 (15 min) ─ velocidad, pares, tiempo restante,  → nota alta
                    confirmaciones, JFileChooser, atajos
```

## Paso a paso en el editor visual

**Proyecto y ventana**: igual que en el apartado 13, con un *JFrame Form* llamado `TorrentSimulado`. Navigator › clic derecho en [JFrame] › *Set Layout* › ***Border Layout***. `title` = `Torrent simulado`.

**Capa 1: la zona central**

1. Arrastra un **Panel** con *Direction* ***Center***, nombre `panelDatos`, *Set Layout* › ***Grid Layout*** con ***Rows* 0** y ***Columns* 2**, huecos 10 y 6 (filas 0 = «las que hagan falta»). En `border` elige ***EmptyBorder*** de 12.
2. Dentro, de dos en dos, un **Label** de título y un **Label** de valor: `Archivo:` / `valorArchivo` (texto `ubuntu-24.04.iso`), `Tamaño:` / `valorTamano`, `Descargado:` / `valorDescargado`, `Velocidad:` / `valorVelocidad`, `Pares:` / `valorPares`, `Tiempo restante:` / `valorRestante`.
3. Arrastra un **Progress Bar** (*Swing Controls*) al final del panel: nombre `barra`, marca ***stringPainted*** (escribe «12 %» dentro), `maximum` 100.

**Capa 2: el menú**

4. *Palette* › *Swing Menus* › arrastra una **Menu Bar** sobre el formulario. Aparece arriba con dos menús, *File* y *Edit*.
5. Cambia sus textos a `Archivo` y `Descarga` (clic derecho › *Edit Text*). En el Navigator, clic derecho en la *JMenuBar* › ***Add Menu*** para el tercero: `Ayuda`.
6. Para cada opción: clic derecho en el `JMenu` en el Navigator › ***Add From Palette › Menu Item***. Archivo: `Añadir torrent…` (`opcionAnadir`), ***Separator*** y `Salir` (`opcionSalir`). Descarga: `Iniciar` (`opcionIniciar`), `Pausar` (`opcionPausar`), `Cancelar` (`opcionCancelar`). Ayuda: `Acerca de…` (`opcionAcercaDe`).
7. Atajos: en `opcionAnadir`, propiedad ***accelerator*** › botón «…» › pulsa **Ctrl + O**. Igual con `opcionSalir` y **Ctrl + Q**. En los `JMenu`, propiedad ***mnemonic***: `A` y `D`.

**Capa 3: barra de herramientas y barra de estado**

8. *Swing Containers* › **Tool Bar** con *Direction* ***North***. Desmarca ***floatable***.
9. Dentro, cuatro **Button**: `+ Añadir` (`botonAnadir`), `► Iniciar` (`botonIniciar`), `‖ Pausar` (`botonPausar`), `■ Cancelar` (`botonCancelar`). Entre el primero y el segundo, un ***Separator*** de *Swing Controls*. Pon un `toolTipText` a cada uno (heurística 10).
10. Un **Label** con *Direction* ***South***: nombre `barraEstado`, texto `Parada`, `border` › ***MatteBorder*** con 1 píxel arriba (línea separadora).

**Eventos** (doble clic en cada botón; en las opciones de menú, clic derecho en el Navigator › *Events › Action › actionPerformed*). Cada método solo llama a uno de los métodos de lógica:

| Componente | Su método llama a |
|---|---|
| `botonIniciar`, `opcionIniciar` | `iniciar();` |
| `botonPausar`, `opcionPausar` | `pausar();` |
| `botonCancelar`, `opcionCancelar` | `cancelar();` |
| `botonAnadir`, `opcionAnadir` | `anadirTorrent();` |
| `opcionSalir` | `salir();` |
| `opcionAcercaDe` | `javax.swing.JOptionPane.showMessageDialog(this, "Torrent simulado", "Acerca de", javax.swing.JOptionPane.INFORMATION_MESSAGE);` |

## El código que escribes tú

```java
// ===== 1. ATRIBUTOS: debajo de «public class TorrentSimulado extends javax.swing.JFrame {»
private static final int INTERVALO_MS = 200;                     // (1)
private final javax.swing.Timer reloj =
        new javax.swing.Timer(INTERVALO_MS, e -> avanzar());     // (2)
private final java.util.Random azar = new java.util.Random();
private double tamanoMB = 5734;
private double descargadoMB = 0;
private String estado = "Parada";                                // (3)

// ===== 2. CONSTRUCTOR: después de initComponents();
actualizarDatos();
actualizarControles();
setLocationRelativeTo(null);

// ===== 3. LÓGICA
private void iniciar() {
    estado = "Descargando…";
    reloj.start();                                               // (4)
    actualizarControles();
}

private void pausar() {
    reloj.stop();
    estado = "Pausada";
    valorVelocidad.setText("-");
    valorRestante.setText("-");
    actualizarControles();
}

private void avanzar() {                                         // (5) un tic del reloj
    double velocidad = 40 + azar.nextDouble() * 80;              // (6) 40 a 120 MB/s
    descargadoMB += velocidad * INTERVALO_MS / 1000.0;           // (7) v × t
    if (descargadoMB >= tamanoMB) {                              // (8)
        descargadoMB = tamanoMB;
        reloj.stop();
        estado = "Completada";
        valorVelocidad.setText("-");
        valorRestante.setText("00:00");
        actualizarDatos();
        actualizarControles();
        javax.swing.JOptionPane.showMessageDialog(this,
                valorArchivo.getText() + " se ha descargado.");
        return;
    }
    int seg = (int) ((tamanoMB - descargadoMB) / velocidad);
    valorVelocidad.setText(String.format("%.1f MB/s", velocidad));
    valorPares.setText((3 + azar.nextInt(20)) + " conectados");
    valorRestante.setText(String.format("%02d:%02d", seg / 60, seg % 60));   // (9)
    actualizarDatos();
}

private void actualizarDatos() {
    valorTamano.setText(String.format("%,.0f MB", tamanoMB));
    valorDescargado.setText(String.format("%,.1f MB", descargadoMB));
    barra.setValue((int) (descargadoMB * 100 / tamanoMB));       // (10)
}

private void actualizarControles() {                             // (11)
    boolean descargando = estado.equals("Descargando…");
    boolean puedeIniciar = !descargando && !estado.equals("Completada");
    botonIniciar.setText(estado.equals("Pausada") ? "► Reanudar" : "► Iniciar");
    botonIniciar.setEnabled(puedeIniciar);
    opcionIniciar.setEnabled(puedeIniciar);
    botonPausar.setEnabled(descargando);
    opcionPausar.setEnabled(descargando);
    botonCancelar.setEnabled(!estado.equals("Parada"));
    opcionCancelar.setEnabled(!estado.equals("Parada"));
    barraEstado.setText(estado);
}

// ===== Capa 4
private void cancelar() {
    int r = javax.swing.JOptionPane.showConfirmDialog(this,
            "¿Cancelar la descarga? Se perderá lo descargado.",
            "Cancelar descarga", javax.swing.JOptionPane.YES_NO_OPTION);
    if (r != javax.swing.JOptionPane.YES_OPTION) return;         // (12)
    reiniciar();
}

private void reiniciar() {
    reloj.stop();
    descargadoMB = 0;
    estado = "Parada";
    valorVelocidad.setText("-");
    valorPares.setText("-");
    valorRestante.setText("-");
    actualizarDatos();
    actualizarControles();
}

private void anadirTorrent() {
    if (estado.equals("Descargando…")) {
        javax.swing.JOptionPane.showMessageDialog(this, "Pausa o cancela la descarga actual.");
        return;
    }
    javax.swing.JFileChooser selector = new javax.swing.JFileChooser();
    selector.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Ficheros torrent (*.torrent)", "torrent"));
    if (selector.showOpenDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION) return;
    valorArchivo.setText(selector.getSelectedFile().getName().replaceFirst("\\.torrent$", ""));
    tamanoMB = 300 + azar.nextInt(1700);                         // tamaño inventado
    reiniciar();
}

private void salir() {
    if (estado.equals("Descargando…")) {
        int r = javax.swing.JOptionPane.showConfirmDialog(this,
                "Hay una descarga en curso. ¿Salir?", "Salir",
                javax.swing.JOptionPane.YES_NO_OPTION);
        if (r != javax.swing.JOptionPane.YES_OPTION) return;
    }
    System.exit(0);
}
```

## Explicación línea a línea

| Nº | Qué hace y por qué así |
|---|---|
| 1 | Constante: cada cuánto avanza la simulación. 200 ms = 5 veces por segundo, suficiente para que la barra se mueva fluida |
| 2 | **`javax.swing.Timer`**: cada 200 ms ejecuta `avanzar()` **en el hilo de Swing**, así que puede tocar la interfaz sin peligro. Se escribe con el paquete completo para no confundirlo con `java.util.Timer` |
| 3 | El estado como texto sirve a la vez para la lógica y para la barra de estado. En el proyecto de referencia son constantes (`DESCARGANDO`, `PAUSADA`…) para no equivocarse al escribirlas |
| 4 | `start()` arranca el reloj; `stop()` lo para. Pausar es solo parar el reloj: los datos se quedan donde estaban |
| 5 | Cada tic es muy corto: calcula, cambia textos y termina. Entre tic y tic, Swing repinta y atiende los clics |
| 6 | Velocidad aleatoria para que parezca real: varía en cada tic |
| 7 | Física de la ESO: espacio = velocidad × tiempo. 200 ms son 0,2 s |
| 8 | Al llegar al final se para el reloj **antes** de mostrar el mensaje (si no, seguiría sumando detrás del diálogo) |
| 9 | `%02d` rellena con ceros: 1 minuto y 2 segundos se ve «01:02» |
| 10 | La barra va de 0 a 100: el porcentaje se calcula y se convierte a entero |
| 11 | Un único método decide qué se puede pulsar en cada estado: **prevención de errores** (heurística 5) y botón y opción de menú siempre coherentes |
| 12 | Confirmar antes de algo que pierde datos: **control y libertad del usuario** y prevención de errores |

> **El error más grave de este ejercicio.** Simular con un `for` y `Thread.sleep()` dentro del método del botón. Ese método se ejecuta en el hilo de Swing: mientras duerme, la ventana **no se repinta**, se queda congelada y la barra salta de 0 a 100 de golpe (figura del apartado 12.5). Y en el `import`, `javax.swing.Timer`, nunca `java.util.Timer`.

## Capa 1 escrita a mano (por si el editor visual se complica)

Si el editor visual se resiste, esta versión cabe en una hoja y compila tal cual en una clase Java normal (*New › Java Class*):

```java
import java.awt.GridLayout;
import javax.swing.*;

public class TorrentMinimo extends JFrame {
    private final JProgressBar barra = new JProgressBar(0, 100);
    private final JButton botonIniciar = new JButton("Iniciar");
    private final JLabel estado = new JLabel("Detenido");
    private final Timer reloj = new Timer(100, e -> avanzar());   // javax.swing.Timer

    public TorrentMinimo() {
        super("Descarga simulada");
        barra.setStringPainted(true);
        botonIniciar.addActionListener(e -> iniciarOPausar());
        JButton botonReiniciar = new JButton("Reiniciar");
        botonReiniciar.addActionListener(e -> { reloj.stop(); barra.setValue(0);
            botonIniciar.setText("Iniciar"); botonIniciar.setEnabled(true); estado.setText("Detenido"); });

        JPanel botones = new JPanel();
        botones.add(botonIniciar);
        botones.add(botonReiniciar);
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("ubuntu-24.04.iso"));
        panel.add(barra);
        panel.add(estado);
        panel.add(botones);

        JMenuBar menu = new JMenuBar();                 // capa 2 en cuatro líneas
        JMenu archivo = new JMenu("Archivo");
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(e -> System.exit(0));
        archivo.add(salir);
        menu.add(archivo);
        setJMenuBar(menu);

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void avanzar() {
        barra.setValue(barra.getValue() + 1);
        if (barra.getValue() >= barra.getMaximum()) {
            reloj.stop();
            estado.setText("Completado");
            botonIniciar.setEnabled(false);
        }
    }

    private void iniciarOPausar() {
        if (reloj.isRunning()) {
            reloj.stop();  botonIniciar.setText("Reanudar"); estado.setText("Pausado");
        } else {
            reloj.start(); botonIniciar.setText("Pausar");   estado.setText("Descargando…");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TorrentMinimo().setVisible(true));
    }
}
```

## Cómo demostrarlo

| Haz | Debe pasar | Heurística que enseñas |
|---|---|---|
| Abrir la aplicación | «Pausar» y «Cancelar» desactivados; barra de estado «Parada» | 5. Prevención de errores |
| Pulsar Iniciar | La barra avanza, cambian velocidad, pares y tiempo restante; estado «Descargando…» | 1. Visibilidad del estado |
| Pulsar Pausar | Se para; el botón pasa a «Reanudar» | 3. Control y libertad; 6. Reconocimiento |
| Descarga › Iniciar (menú) | Hace lo mismo que el botón | 4. Consistencia |
| Cancelar | Pide confirmación antes de perder lo descargado | 5. Prevención; 3. Control |
| Ctrl + O | Abre el selector de ficheros `.torrent` | 7. Flexibilidad y eficiencia |
| Dejar que acabe | Mensaje «se ha descargado» y estado «Completada» | 1. Visibilidad |
| Pasar el ratón por un botón | Sale su *tooltip* | 10. Ayuda |

## 🔧 Modificaciones que te pueden pedir

**Botón y opción «Reiniciar» sin confirmación**: un `JMenuItem` más en Descarga cuyo evento llame a `reiniciar();`.

**Velocidad límite elegida por el usuario** con un `JSlider` (0 a 200 MB/s) en la barra de herramientas, nombre `limite`; en `avanzar()`:

```java
double velocidad = Math.min(40 + azar.nextDouble() * 80, limite.getValue());
if (velocidad <= 0) return;                    // límite 0 = no avanza
```

**Abrir la carpeta al terminar** (en `avanzar()`, tras el mensaje):

```java
try { java.awt.Desktop.getDesktop().open(new java.io.File(System.getProperty("user.home"))); }
catch (java.io.IOException ex) { barraEstado.setText("No se pudo abrir la carpeta"); }
```

**Icono en la ventana**: `setIconImage(new javax.swing.ImageIcon(getClass().getResource("/torrent.png")).getImage());` con la imagen en `src/main/resources`.

**Varias descargas a la vez**: `JTable` con un `DefaultTableModel` (columnas Nombre, Tamaño, Progreso, Velocidad, Estado) dentro de un `JScrollPane`, y en `avanzar()` un `for` que recorre las filas. Es la clase `SimuladorTorrent` del proyecto de referencia; la barra dentro de la celda se hace con un `TableCellRenderer` que es una `JProgressBar`.

**Barra de menú con iconos**: propiedad `icon` del `JMenuItem` › *Image* › elige un `.png` del proyecto.

## Preguntas que te puede hacer el profesor

| Pregunta | Respuesta |
|---|---|
| ¿Por qué un `Timer` y no un bucle con `sleep`? | El bucle bloquea el hilo de eventos: la ventana no se repinta ni responde. El `Timer` de Swing ejecuta cada tic en ese hilo, pero cortito, y entre tics Swing sigue atendiendo |
| ¿Qué diferencia hay entre `javax.swing.Timer` y `java.util.Timer`? | El de Swing ejecuta su oyente en el EDT y puede tocar la interfaz; el de `java.util` usa otro hilo y tocar componentes desde ahí no es seguro |
| ¿Por qué la barra de menú no va en `NORTH`? | Porque un `JFrame` tiene un sitio propio para ella, `setJMenuBar`, fuera del *content pane*. En `NORTH` va la barra de herramientas |
| ¿Mnemónico o acelerador? | Mnemónico: Alt + letra abre el menú. Acelerador: Ctrl + tecla ejecuta la opción directamente |
| ¿Por qué se desactivan botones? | Para que el usuario no pueda hacer algo sin sentido (pausar lo que no corre): prevención de errores. Y se desactivan a la vez el botón y la opción de menú |
| Si la descarga fuera real, ¿qué cambiarías? | `SwingWorker`: descarga en segundo plano con `doInBackground()` y avisa del progreso con `publish()`/`setProgress()`, que llegan al EDT |
| ¿Qué es `stringPainted`? | Que la `JProgressBar` escriba dentro el porcentaje |
