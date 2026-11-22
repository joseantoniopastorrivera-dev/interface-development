package es.isidraguzman.di.torrent;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;                     // OJO: javax.swing.Timer, no java.util.Timer
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Cliente torrent simulado: barra de menú, barra de herramientas, panel con
 * los datos de la descarga y barra de estado. No descarga nada: un
 * javax.swing.Timer hace avanzar la barra cada 200 ms.
 *
 *  ┌ Archivo  Descarga  Ayuda ─────────────────┐  JMenuBar
 *  │ [Añadir] | [▶ Iniciar] [‖ Pausar] [■ Cancelar] │  JToolBar   (NORTH)
 *  │ Archivo:     ubuntu-24.04.iso              │
 *  │ Tamaño:      5.734 MB                      │  JPanel GridLayout (CENTER)
 *  │ [▓▓▓▓▓▓▓░░░░░░ 47 %]                       │
 *  │ Velocidad / Pares / Tiempo restante        │
 *  │ Descargando…                               │  JLabel (SOUTH)
 *  └────────────────────────────────────────────┘
 */
public class TorrentConMenu extends JFrame {

    private static final int INTERVALO_MS = 200;      // cada cuánto avanza la simulación

    // Estados posibles de la descarga
    private static final String PARADA = "Parada";
    private static final String DESCARGANDO = "Descargando…";
    private static final String PAUSADA = "Pausada";
    private static final String COMPLETADA = "Completada";

    // Componentes que se leen o cambian después: atributos
    private final JLabel valorArchivo = new JLabel("ubuntu-24.04.iso");
    private final JLabel valorTamano = new JLabel();
    private final JProgressBar barra = new JProgressBar(0, 100);
    private final JLabel valorDescargado = new JLabel();
    private final JLabel valorVelocidad = new JLabel("-");
    private final JLabel valorPares = new JLabel("-");
    private final JLabel valorRestante = new JLabel("-");
    private final JLabel barraEstado = new JLabel(PARADA);

    private final JButton botonIniciar = new JButton("▶ Iniciar");
    private final JButton botonPausar = new JButton("‖ Pausar");
    private final JButton botonCancelar = new JButton("■ Cancelar");
    private final JMenuItem opcionIniciar = new JMenuItem("Iniciar");
    private final JMenuItem opcionPausar = new JMenuItem("Pausar");
    private final JMenuItem opcionCancelar = new JMenuItem("Cancelar");

    // Estado de la simulación
    private final Timer reloj = new Timer(INTERVALO_MS, e -> avanzar());
    private final Random azar = new Random();
    private double tamanoMB = 5734;
    private double descargadoMB = 0;
    private String estado = PARADA;

    public TorrentConMenu() {
        super("Torrent simulado");
        setJMenuBar(crearMenu());                         // el menú va aparte del contenido
        add(crearBarraHerramientas(), BorderLayout.NORTH);
        add(crearPanelDescarga(), BorderLayout.CENTER);
        add(crearBarraEstado(), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actualizarDatos();
        actualizarControles();
        pack();
        setLocationRelativeTo(null);
    }

    // =====================================================================
    // INTERFAZ
    // =====================================================================

    private JMenuBar crearMenu() {
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setMnemonic(KeyEvent.VK_A);             // Alt + A abre el menú
        JMenuItem opcionAnadir = new JMenuItem("Añadir torrent…");
        opcionAnadir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        opcionAnadir.addActionListener(e -> anadirTorrent());
        JMenuItem opcionSalir = new JMenuItem("Salir");
        opcionSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        opcionSalir.addActionListener(e -> salir());
        menuArchivo.add(opcionAnadir);
        menuArchivo.addSeparator();
        menuArchivo.add(opcionSalir);

        JMenu menuDescarga = new JMenu("Descarga");
        menuDescarga.setMnemonic(KeyEvent.VK_D);
        opcionIniciar.addActionListener(e -> iniciar());
        opcionPausar.addActionListener(e -> pausar());
        opcionCancelar.addActionListener(e -> cancelar());
        menuDescarga.add(opcionIniciar);
        menuDescarga.add(opcionPausar);
        menuDescarga.add(opcionCancelar);

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem opcionAcercaDe = new JMenuItem("Acerca de…");
        opcionAcercaDe.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Torrent simulado\nExamen de Desarrollo de Interfaces",
                "Acerca de", JOptionPane.INFORMATION_MESSAGE));
        menuAyuda.add(opcionAcercaDe);

        JMenuBar barraMenu = new JMenuBar();
        barraMenu.add(menuArchivo);
        barraMenu.add(menuDescarga);
        barraMenu.add(menuAyuda);
        return barraMenu;
    }

    private JToolBar crearBarraHerramientas() {
        JButton botonAnadir = new JButton("➕ Añadir");
        botonAnadir.setToolTipText("Añadir un fichero .torrent (Ctrl+O)");
        botonAnadir.addActionListener(e -> anadirTorrent());
        botonIniciar.setToolTipText("Iniciar o reanudar la descarga");
        botonIniciar.addActionListener(e -> iniciar());
        botonPausar.setToolTipText("Pausar la descarga");
        botonPausar.addActionListener(e -> pausar());
        botonCancelar.setToolTipText("Cancelar y volver a empezar");
        botonCancelar.addActionListener(e -> cancelar());

        JToolBar barraHerramientas = new JToolBar();
        barraHerramientas.setFloatable(false);             // que no se pueda arrancar
        barraHerramientas.add(botonAnadir);
        barraHerramientas.addSeparator();
        barraHerramientas.add(botonIniciar);
        barraHerramientas.add(botonPausar);
        barraHerramientas.add(botonCancelar);
        return barraHerramientas;
    }

    private JPanel crearPanelDescarga() {
        barra.setStringPainted(true);                      // escribe «47 %» dentro
        valorArchivo.setFont(valorArchivo.getFont().deriveFont(Font.BOLD));

        // Rejilla de 2 columnas: etiqueta | valor
        JPanel datos = new JPanel(new GridLayout(0, 2, 10, 6));
        datos.add(new JLabel("Archivo:"));
        datos.add(valorArchivo);
        datos.add(new JLabel("Tamaño:"));
        datos.add(valorTamano);
        datos.add(new JLabel("Descargado:"));
        datos.add(valorDescargado);
        datos.add(new JLabel("Velocidad:"));
        datos.add(valorVelocidad);
        datos.add(new JLabel("Pares:"));
        datos.add(valorPares);
        datos.add(new JLabel("Tiempo restante:"));
        datos.add(valorRestante);

        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        panel.add(datos, BorderLayout.CENTER);
        panel.add(barra, BorderLayout.SOUTH);
        return panel;
    }

    private JLabel crearBarraEstado() {
        barraEstado.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, java.awt.Color.GRAY),
                BorderFactory.createEmptyBorder(3, 8, 3, 8)));
        return barraEstado;
    }

    // =====================================================================
    // LÓGICA
    // =====================================================================

    private void iniciar() {
        estado = DESCARGANDO;
        reloj.start();
        actualizarControles();
    }

    private void pausar() {
        reloj.stop();
        estado = PAUSADA;
        valorVelocidad.setText("-");
        valorRestante.setText("-");
        actualizarControles();
    }

    private void cancelar() {
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Cancelar la descarga? Se perderá lo descargado.",
                "Cancelar descarga", JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;                                        // el usuario se arrepiente
        }
        reiniciar();
    }

    private void reiniciar() {
        reloj.stop();
        descargadoMB = 0;
        estado = PARADA;
        valorVelocidad.setText("-");
        valorPares.setText("-");
        valorRestante.setText("-");
        actualizarDatos();
        actualizarControles();
    }

    /** Un tic del reloj: avanza la descarga una cantidad aleatoria. */
    private void avanzar() {
        double velocidad = 40 + azar.nextDouble() * 80;    // 40 a 120 MB/s
        descargadoMB += velocidad * INTERVALO_MS / 1000.0;  // espacio = velocidad × tiempo
        if (descargadoMB >= tamanoMB) {
            descargadoMB = tamanoMB;
            reloj.stop();
            estado = COMPLETADA;
            valorVelocidad.setText("-");
            valorRestante.setText("00:00");
            actualizarDatos();
            actualizarControles();
            JOptionPane.showMessageDialog(this, valorArchivo.getText() + " se ha descargado.",
                    "Descarga completada", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int segundos = (int) ((tamanoMB - descargadoMB) / velocidad);
        valorVelocidad.setText(String.format("%.1f MB/s", velocidad));
        valorPares.setText((3 + azar.nextInt(20)) + " conectados");
        valorRestante.setText(String.format("%02d:%02d", segundos / 60, segundos % 60));
        actualizarDatos();
    }

    private void anadirTorrent() {
        if (estado.equals(DESCARGANDO)) {
            JOptionPane.showMessageDialog(this, "Pausa o cancela la descarga actual antes de añadir otra.",
                    "Descarga en curso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("Ficheros torrent (*.torrent)", "torrent"));
        if (selector.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;                                        // pulsó Cancelar
        }
        File fichero = selector.getSelectedFile();
        valorArchivo.setText(fichero.getName().replaceFirst("\\.torrent$", ""));
        tamanoMB = 300 + azar.nextInt(1700);               // tamaño inventado: 0,3 a 2 GB
        reiniciar();
    }

    private void salir() {
        if (estado.equals(DESCARGANDO)) {
            int r = JOptionPane.showConfirmDialog(this, "Hay una descarga en curso. ¿Salir?",
                    "Salir", JOptionPane.YES_NO_OPTION);
            if (r != JOptionPane.YES_OPTION) {
                return;
            }
        }
        System.exit(0);
    }

    // ------------------------------------------------------------ auxiliares

    /** Refresca tamaño, descargado y barra. */
    private void actualizarDatos() {
        valorTamano.setText(String.format("%,.0f MB", tamanoMB));
        valorDescargado.setText(String.format("%,.1f MB", descargadoMB));
        barra.setValue((int) (descargadoMB * 100 / tamanoMB));
    }

    /** Activa solo lo que tiene sentido en cada estado (prevención de errores). */
    private void actualizarControles() {
        boolean descargando = estado.equals(DESCARGANDO);
        boolean completada = estado.equals(COMPLETADA);
        boolean puedeIniciar = !descargando && !completada;

        botonIniciar.setText(estado.equals(PAUSADA) ? "▶ Reanudar" : "▶ Iniciar");
        opcionIniciar.setText(estado.equals(PAUSADA) ? "Reanudar" : "Iniciar");
        botonIniciar.setEnabled(puedeIniciar);
        opcionIniciar.setEnabled(puedeIniciar);
        botonPausar.setEnabled(descargando);
        opcionPausar.setEnabled(descargando);
        botonCancelar.setEnabled(!estado.equals(PARADA));
        opcionCancelar.setEnabled(!estado.equals(PARADA));
        barraEstado.setText(estado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TorrentConMenu().setVisible(true));
    }
}
