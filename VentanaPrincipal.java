import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Memorama");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Imagen superior
        JLabel imagenLabel = new JLabel();
        try {
            ImageIcon icono = new ImageIcon("C:\\Users\\V16\\Downloads\\Memorama.png");
            Image imagenEscalada = icono.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
            imagenLabel.setIcon(new ImageIcon(imagenEscalada));
            imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (Exception e) {
            imagenLabel.setText("Memorama");
            imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imagenLabel.setFont(new Font("Arial", Font.BOLD, 24));
        }
        add(imagenLabel, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panelBotones.setBackground(new Color(245, 245, 245));

        // Botones personalizados
        JButton btnJugar = crearBotonConEstilo("Iniciar Juego", Color.GREEN, "jugar.png");
        JButton btnInstrucciones = crearBotonConEstilo("Instrucciones", Color.YELLOW, "instrucciones.png");
        JButton btnSalir = crearBotonConEstilo("Salir", Color.RED, "salir.png");
        btnSalir.setForeground(Color.WHITE);

        // Acciones
        btnJugar.addActionListener(e -> {
            new VentanaSeleccionJugadores();
            dispose();
        });

        btnInstrucciones.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Reglas del memorama:\n\n" +
                            "- Encuentra pares de tarjetas iguales.\n" +
                            "- Gana quien tenga más pares al final.\n" +
                            "- Elige un tipo de tarjetas antes de comenzar.",
                    "Instrucciones", JOptionPane.INFORMATION_MESSAGE);
        });

        btnSalir.addActionListener(e -> System.exit(0));

        // Agregar botones
        panelBotones.add(btnJugar);
        panelBotones.add(btnInstrucciones);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
    }

    private JButton crearBotonConEstilo(String texto, Color color, String iconoNombre) {
        JButton boton = new JButton(texto);

        // Ícono si existe
        try {
            ImageIcon icono = new ImageIcon(iconoNombre);
            Image img = icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(img));
        } catch (Exception ignored) {}

        // Colores y estilo
        boton.setBackground(color);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });

        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }
}

class VentanaSeleccionJugadores extends JFrame {

    public VentanaSeleccionJugadores() {
        setTitle("Seleccionar Jugadores");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("¿Cuántos jugadores jugarán?");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        panelBotones.setBackground(new Color(245, 245, 245));

        JButton btn2 = crearBoton("2 Jugadores", new Color(173, 216, 230), "2.png");
        JButton btn3 = crearBoton("3 Jugadores", new Color(144, 238, 144), "3.png");
        JButton btn4 = crearBoton("4 Jugadores", new Color(255, 182, 193), "4.png");

        // Acciones
        btn2.addActionListener(e -> {
            new VentanaSeleccionModoJuego(2);
            dispose();
        });
        btn3.addActionListener(e -> {
            new VentanaSeleccionModoJuego(3);
            dispose();
        });
        btn4.addActionListener(e -> {
            new VentanaSeleccionModoJuego(4);
            dispose();
        });

        panelBotones.add(btn2);
        panelBotones.add(btn3);
        panelBotones.add(btn4);

        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
    }

    private JButton crearBoton(String texto, Color color, String iconoNombre) {
        JButton boton = new JButton(texto);

        // Icono si existe
        try {
            ImageIcon icono = new ImageIcon(iconoNombre);
            Image img = icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(img));
        } catch (Exception ignored) {}

        boton.setBackground(color);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });

        return boton;
    }
}


class VentanaSeleccionModoJuego extends JFrame {

    private int numeroJugadores;

    public VentanaSeleccionModoJuego(int jugadores) {
        this.numeroJugadores = jugadores;

        setTitle("Seleccionar Modo de Juego");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Selecciona el tipo de tarjetas");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelModos = new JPanel(new GridLayout(1, 3, 20, 20));
        panelModos.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panelModos.setBackground(new Color(245, 245, 245));

        // Crear 3 opciones con imagen y botón debajo
        panelModos.add(crearPanelModo("Figuras", "C:\\Users\\V16\\Downloads\\PortadaAutos.jpg", new Color(173, 216, 230)));
        panelModos.add(crearPanelModo("Emojis", "C:\\Users\\V16\\Downloads\\PortadaFutbol.jpg", new Color(255, 239, 150)));
        panelModos.add(crearPanelModo("Animales", "C:\\Users\\V16\\Downloads\\PortadaMusica.jpg", new Color(255, 182, 193)));

        add(panelModos, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel crearPanelModo(String nombre, String imagenArchivo, Color colorBoton) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(250, 250, 250));

        JLabel imagen = new JLabel();
        try {
            ImageIcon icono = new ImageIcon(imagenArchivo);
            Image img = icono.getImage().getScaledInstance(220, 520, Image.SCALE_SMOOTH);
            imagen.setIcon(new ImageIcon(img));
            imagen.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (Exception e) {
            imagen.setText(nombre);
            imagen.setHorizontalAlignment(SwingConstants.CENTER);
            imagen.setFont(new Font("Arial", Font.PLAIN, 14));
        }

        JButton botonSeleccionar = new JButton("Seleccionar");
        botonSeleccionar.setBackground(colorBoton);
        botonSeleccionar.setFont(new Font("Arial", Font.BOLD, 13));
        botonSeleccionar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
        botonSeleccionar.setFocusPainted(false);
        botonSeleccionar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover
        botonSeleccionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonSeleccionar.setBackground(colorBoton.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonSeleccionar.setBackground(colorBoton);
            }
        });

        // Acción del botón: abrir ventana partida y cerrar esta ventana
        botonSeleccionar.addActionListener(e -> {
            new VentanaPartida(nombre, numeroJugadores);
            dispose();
        });

        panel.add(imagen, BorderLayout.CENTER);
        panel.add(botonSeleccionar, BorderLayout.SOUTH);

        return panel;
    }
    
    public class VentanaPartida extends JFrame {
        private int numeroJugadores;
        private String modo;
        private JButton[] botones;
        private ImageIcon reversoIcon;

        public VentanaPartida(String modoSeleccionado, int jugadores) {
            this.modo = modoSeleccionado;
            this.numeroJugadores = jugadores;
            this.reversoIcon = new ImageIcon(new ImageIcon("C:\\Users\\V16\\Downloads\\Carta.png").getImage().getScaledInstance(160, 140, Image.SCALE_SMOOTH));

            setTitle("Memorama - " + modo);
            setSize(800, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            // Panel para puntajes y turno
            JPanel panelInfo = new JPanel(new GridLayout(1, numeroJugadores + 1));
            JLabel[] puntajes = new JLabel[numeroJugadores];
            for (int i = 0; i < numeroJugadores; i++) {
                puntajes[i] = new JLabel("Jugador " + (i + 1) + ": 0 pts");
                panelInfo.add(puntajes[i]);
            }
            JLabel turno = new JLabel("Turno: Jugador 1");
            panelInfo.add(turno);
            add(panelInfo, BorderLayout.NORTH);

            // Panel del tablero
            JPanel panelTablero = new JPanel(new GridLayout(4, 5, 10, 10));
            botones = new JButton[20];

            
            List<ImageIcon> imagenes = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                String nombreArchivo = modo.toLowerCase() + i + ".png"; 
                ImageIcon icon = new ImageIcon(new ImageIcon(nombreArchivo).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
                imagenes.add(icon);
                imagenes.add(icon); // duplicar para el par
            }
            Collections.shuffle(imagenes);

            for (int i = 0; i < 20; i++) {
                JButton boton = new JButton();
                boton.setIcon(reversoIcon); // Imagen de reverso
                boton.setFocusable(false);

                ImageIcon iconoCorrecto = imagenes.get(i);

                boton.addActionListener(e -> {
                    boton.setIcon(iconoCorrecto); // Revelar imagen al hacer clic
                    boton.setEnabled(false); 
                });

                botones[i] = boton;
                panelTablero.add(boton);
            }

            add(panelTablero, BorderLayout.CENTER);
            setVisible(true);
        }
    }}






