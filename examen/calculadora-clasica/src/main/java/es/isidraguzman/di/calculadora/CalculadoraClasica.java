package es.isidraguzman.di.calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Calculadora clásica, como la estándar de Windows: un visor y un teclado de
 * 6 x 4 botones, más la fila de memoria.
 *
 * Está escrita a mano para poder leerla de arriba abajo. Con el editor visual
 * de NetBeans se obtiene la misma ventana: initComponents() sustituye a
 * construirVentana(), y los métodos de la parte «LÓGICA» se copian tal cual
 * en la vista Source, debajo del constructor.
 */
public class CalculadoraClasica extends JFrame {

    // ---------------------------------------------------------------- colores
    private static final Color FONDO = new Color(32, 32, 32);
    private static final Color CIFRA = new Color(59, 59, 59);
    private static final Color FUNCION = new Color(50, 50, 50);
    private static final Color IGUAL = new Color(76, 194, 255);
    private static final Color TEXTO = Color.WHITE;

    // ------------------------------------------------- componentes que se leen
    private final JLabel expresion = new JLabel(" ", SwingConstants.RIGHT);
    private final JTextField pantalla = new JTextField("0");
    private JButton botonMC;
    private JButton botonMR;

    // --------------------------------------------------- estado de la cuenta
    private double acumulado = 0;          // primer operando ya guardado
    private String operacion = null;       // +, −, ×, ÷ pendiente (null = ninguna)
    private boolean nuevaEntrada = true;   // la próxima cifra sustituye al visor
    private boolean error = false;         // el visor muestra un mensaje de error
    private double memoria = 0;
    private boolean hayMemoria = false;

    // Formato español: coma decimal y como mucho 10 decimales, sin ceros de más
    private final DecimalFormat formato =
            new DecimalFormat("0.##########", new DecimalFormatSymbols(new Locale("es", "ES")));

    public CalculadoraClasica() {
        super("Calculadora");
        construirVentana();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    // =====================================================================
    // INTERFAZ (lo que en NetBeans hace el editor visual)
    // =====================================================================

    private void construirVentana() {
        // Visor: grande, alineado a la derecha y de solo lectura
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setFont(new Font("Segoe UI", Font.BOLD, 40));
        pantalla.setBackground(FONDO);
        pantalla.setForeground(TEXTO);
        pantalla.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));

        expresion.setForeground(Color.LIGHT_GRAY);
        expresion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        expresion.setBorder(BorderFactory.createEmptyBorder(8, 10, 0, 10));

        // Fila de memoria: 5 botones planos en una rejilla de 1 x 5
        JPanel filaMemoria = new JPanel(new GridLayout(1, 5));
        filaMemoria.setBackground(FONDO);
        for (String texto : new String[]{"MC", "MR", "M+", "M−", "MS"}) {
            JButton b = crearBoton(texto, FONDO);
            filaMemoria.add(b);
            if (texto.equals("MC")) {
                botonMC = b;
            } else if (texto.equals("MR")) {
                botonMR = b;
            }
        }

        JPanel norte = new JPanel(new BorderLayout());
        norte.setBackground(FONDO);
        norte.add(expresion, BorderLayout.NORTH);
        norte.add(pantalla, BorderLayout.CENTER);
        norte.add(filaMemoria, BorderLayout.SOUTH);

        // Teclado: 6 filas x 4 columnas, todas las celdas iguales
        String[] teclas = {
            "%", "CE", "C", "⌫",
            "1/x", "x²", "√x", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "−",
            "1", "2", "3", "+",
            "+/-", "0", ",", "="
        };
        JPanel teclado = new JPanel(new GridLayout(6, 4, 2, 2));
        teclado.setBackground(FONDO);
        teclado.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        for (String t : teclas) {
            Color color = t.matches("[0-9]") ? CIFRA : (t.equals("=") ? IGUAL : FUNCION);
            teclado.add(crearBoton(t, color));
        }

        getContentPane().setBackground(FONDO);
        add(norte, BorderLayout.NORTH);
        add(teclado, BorderLayout.CENTER);
        actualizarMemoria();
    }

    /** Crea un botón con estilo plano. Todos comparten el mismo oyente. */
    private JButton crearBoton(String texto, Color fondo) {
        JButton b = new JButton(texto);
        // «⌫» no existe en Segoe UI: para ese botón se usa Segoe UI Symbol
        String fuente = texto.equals("⌫") ? "Segoe UI Symbol" : "Segoe UI";
        b.setFont(new Font(fuente, Font.PLAIN, 18));
        b.setBackground(fondo);
        b.setForeground(fondo == IGUAL ? Color.BLACK : TEXTO);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(14, 22, 14, 22));
        b.addActionListener(this::botonPulsado);
        return b;
    }

    /** Un único oyente: decide qué hacer según el texto del botón. */
    private void botonPulsado(ActionEvent evt) {
        String t = evt.getActionCommand();      // por defecto, el texto del botón
        switch (t) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> digito(t);
            case "," -> coma();
            case "+", "−", "×", "÷" -> operador(t);
            case "=" -> igual();
            case "%" -> porcentaje();
            case "CE" -> borrarEntrada();
            case "C" -> borrarTodo();
            case "⌫" -> retroceso();
            case "1/x" -> inverso();
            case "x²" -> cuadrado();
            case "√x" -> raiz();
            case "+/-" -> cambiarSigno();
            case "MC", "MR", "M+", "M−", "MS" -> botonMemoria(t);
            default -> { }
        }
    }

    // =====================================================================
    // LÓGICA (en NetBeans: se pega en Source, debajo del constructor)
    // =====================================================================

    private void digito(String d) {
        if (error) {
            borrarTodo();
        }
        if (nuevaEntrada) {                    // empieza un número nuevo
            pantalla.setText("0");
            nuevaEntrada = false;
        }
        String actual = pantalla.getText();
        if (actual.replace("-", "").replace(",", "").length() >= 16) {
            return;                            // como mucho 16 cifras
        }
        pantalla.setText(actual.equals("0") ? d : actual + d);
    }

    private void coma() {
        if (error) {
            borrarTodo();
        }
        if (nuevaEntrada) {
            pantalla.setText("0,");
            nuevaEntrada = false;
        } else if (!pantalla.getText().contains(",")) {
            pantalla.setText(pantalla.getText() + ",");
        }
    }

    private void operador(String op) {
        if (error) {
            return;
        }
        if (operacion != null && !nuevaEntrada) {
            // Encadenar: 2 + 3 + … resuelve primero 2 + 3
            double resultado = aplicar(acumulado, operacion, leerPantalla());
            if (error) {
                return;
            }
            mostrar(resultado);
        }
        acumulado = leerPantalla();
        operacion = op;
        nuevaEntrada = true;
        expresion.setText(formato.format(acumulado) + " " + op);
    }

    private void igual() {
        if (error || operacion == null) {
            return;
        }
        double b = leerPantalla();
        double resultado = aplicar(acumulado, operacion, b);
        if (error) {
            return;
        }
        expresion.setText(formato.format(acumulado) + " " + operacion + " "
                + formato.format(b) + " =");
        mostrar(resultado);
        operacion = null;
        nuevaEntrada = true;
    }

    /** Hace la cuenta. La división entre cero se comprueba a mano. */
    private double aplicar(double a, String op, double b) {
        switch (op) {
            case "+": return a + b;
            case "−": return a - b;
            case "×": return a * b;
            case "÷":
                if (b == 0) {
                    mostrarError("No se puede dividir entre cero");
                    return 0;
                }
                return a / b;
            default: return b;
        }
    }

    private void porcentaje() {
        if (error) {
            return;
        }
        double x = leerPantalla();
        double valor;
        if (operacion == null) {
            valor = 0;                                   // como Windows
        } else if (operacion.equals("+") || operacion.equals("−")) {
            valor = acumulado * x / 100;                 // 200 + 10 % → 200 + 20
        } else {
            valor = x / 100;                             // 200 × 10 % → 200 × 0,1
        }
        mostrar(valor);
        nuevaEntrada = true;
    }

    private void borrarEntrada() {                       // CE: solo el número actual
        if (error) {
            borrarTodo();
            return;
        }
        pantalla.setText("0");
        nuevaEntrada = true;
    }

    private void borrarTodo() {                          // C: todo, incluida la operación
        acumulado = 0;
        operacion = null;
        nuevaEntrada = true;
        error = false;
        pantalla.setFont(pantalla.getFont().deriveFont(40f));   // tamaño normal
        pantalla.setText("0");
        expresion.setText(" ");
    }

    private void retroceso() {
        if (error) {
            borrarTodo();
            return;
        }
        if (nuevaEntrada) {
            return;                                      // no se borra un resultado
        }
        String t = pantalla.getText();
        t = t.substring(0, t.length() - 1);
        pantalla.setText(t.isEmpty() || t.equals("-") ? "0" : t);
    }

    private void inverso() {
        if (error) {
            return;
        }
        double x = leerPantalla();
        if (x == 0) {
            mostrarError("No se puede dividir entre cero");
            return;
        }
        expresion.setText("1/(" + formato.format(x) + ")");
        mostrar(1 / x);
        nuevaEntrada = true;
    }

    private void cuadrado() {
        if (error) {
            return;
        }
        double x = leerPantalla();
        expresion.setText("sqr(" + formato.format(x) + ")");
        mostrar(x * x);
        nuevaEntrada = true;
    }

    private void raiz() {
        if (error) {
            return;
        }
        double x = leerPantalla();
        if (x < 0) {
            mostrarError("Entrada no válida");
            return;
        }
        expresion.setText("√(" + formato.format(x) + ")");
        mostrar(Math.sqrt(x));
        nuevaEntrada = true;
    }

    private void cambiarSigno() {
        if (error) {
            return;
        }
        String t = pantalla.getText();
        if (t.equals("0")) {
            return;
        }
        pantalla.setText(t.startsWith("-") ? t.substring(1) : "-" + t);
    }

    private void botonMemoria(String t) {
        if (error) {
            return;
        }
        switch (t) {
            case "MC" -> { memoria = 0; hayMemoria = false; }
            case "MR" -> { mostrar(memoria); nuevaEntrada = true; }
            case "M+" -> { memoria += leerPantalla(); hayMemoria = true; nuevaEntrada = true; }
            case "M−" -> { memoria -= leerPantalla(); hayMemoria = true; nuevaEntrada = true; }
            case "MS" -> { memoria = leerPantalla(); hayMemoria = true; nuevaEntrada = true; }
            default -> { }
        }
        actualizarMemoria();
    }

    /** MC y MR solo se pueden pulsar si hay algo guardado (prevención de errores). */
    private void actualizarMemoria() {
        botonMC.setEnabled(hayMemoria);
        botonMR.setEnabled(hayMemoria);
    }

    // ------------------------------------------------------------ auxiliares

    /** Lee el visor. Java usa punto decimal; el visor, coma. */
    private double leerPantalla() {
        return Double.parseDouble(pantalla.getText().replace(',', '.'));
    }

    private void mostrar(double valor) {
        pantalla.setText(formato.format(valor));
    }

    private void mostrarError(String mensaje) {
        pantalla.setFont(pantalla.getFont().deriveFont(22f));   // el mensaje es largo
        pantalla.setText(mensaje);
        error = true;
        operacion = null;
        nuevaEntrada = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraClasica().setVisible(true));
    }
}
