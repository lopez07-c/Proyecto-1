/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Juego;
import Modelo.Cronometro;
import Modelo.Jugador;
import Modelo.Tablero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class VentanaJuego extends javax.swing.JFrame {
        private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName());

    private final Modelo.Nivel nivel;
    private final int filas;
    private final int columnas;
    private static final int TAM_BOTON = 70;

    private Modelo.Tablero ModeloTablero;
    private Modelo.Jugador ModeloJugador = new Jugador();
    private Modelo.Cronometro ModeloCronometro = new Cronometro();
    private Modelo.Juego ModeloJuego;

    private Controlador.Tablero ControladorTablero;
    private Controlador.Cronometro ControladorCronometro;
    private Controlador.Jugador ControladorJugador;
    private Controlador.Juego juego;

    private JButton[][] botones;

    private Timer cronometroTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cronometroJbl.setText(juego.getCronometro().obtenerTiempoFormateado());
        }
    });

    
    public VentanaJuego(Modelo.Nivel nivel) {
        initComponents();
        
        
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        jScrollPane1.getHorizontalScrollBar().setUnitIncrement(20);

        this.nivel = nivel;
        this.filas = nivel.getFilas();
        this.columnas = nivel.getColumnas();
     
        ModeloTablero = new Tablero(filas, columnas);
        ModeloJuego = new Modelo.Juego(ModeloTablero, ModeloJugador, ModeloCronometro);

        ControladorTablero = new Controlador.Tablero(ModeloJuego.getTablero());
        ControladorCronometro = new Controlador.Cronometro(ModeloJuego.getCronometro());
        ControladorJugador = new Controlador.Jugador(ModeloJuego.getJugador());

        juego = new Juego(ModeloJuego, 
                ControladorTablero, 
                ControladorCronometro, 
                ControladorJugador);
        
         inicializarMatrizBotones();
    }
        
        private void inicializarMatrizBotones() {

    botones = new JButton[][]{

        {btn00, btn01, btn02, btn04, btn05, btn06, btn07,btn08},

        {btn09, btn10, btn11, btn12, btn13, btn14, btn15, btn16},

        {btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24},

        {btn25, btn26, btn27, btn28, btn29, btn30, btn31, btn32},

        {btn33, btn34, btn35, btn36, btn37, btn38, btn39, btn40},

        {btn41, btn42, btn43, btn44, btn45, btn46, btn47, btn48},

        {btn49, btn50, btn51, btn52, btn53, btn54, btn55, btn56},

        {btn57, btn58, btn59, btn60, btn61, btn62, btn63, btn64}

    };



        construirTableroVisual();

        puntaje.setText("0");
        intentos.setText("0");
        jLabelParejas.setText("0/" + nivel.getParejas());
        juego.iniciarPartida();
        juego.getCronometro().iniciar();
        

        cronometroTimer.setRepeats(true);
        cronometroTimer.start();
        }      
  

    private void construirTableroVisual() {

    ImageIcon imagenBase =
            new ImageIcon(getClass().getResource("/Imagenes/bala_100x100.png"));

    Icon reverso = new ImageIcon(imagenBase.getImage());

    for (int f = 0; f < botones.length; f++) {

        for (int c = 0; c < botones[f].length; c++) {

            JButton boton = botones[f][c];

            boton.setIcon(reverso);
            boton.setFocusable(false);

            final int fila = f;
            final int columna = c;

           
            for (ActionListener al : boton.getActionListeners()) {
                boton.removeActionListener(al);
            }
            jPanelTablero.revalidate();
jPanelTablero.repaint();

            boton.addActionListener(e ->
                    procesarSeleccionCarta(boton, fila, columna));

            boton.setVisible(f < filas && c < columnas);
            
           
            
        }
    }
} 
   

    
    

    private void procesarSeleccionCarta(JButton boton, int fila, int columna) {
        
        if (juego.getJuego().getPrimeraCarta() != null
            && juego.getJuego().getSegundaCarta() != null) {
        return;
        }

        juego.procesarSeleccion(boton, fila, columna);

        Modelo.Carta carta = juego.getTablero().obtenerCarta(fila, columna);
        Icon icono = new ImageIcon(carta.getImagen().getImage());
        boton.setIcon(icono);
        this.repaint();

        Modelo.Carta primeraCarta = juego.getJuego().getPrimeraCarta();
        Modelo.Carta segundaCarta = juego.getJuego().getSegundaCarta();

        if (segundaCarta != null) {
            // Verificar si realmente se encontró una pareja
    if (primeraCarta.isEncontrada()
            && segundaCarta.isEncontrada()) {

        jLabelParejas.setText(
            juego.getJuego().getJugador().getParejasEncontradas()
            + "/" + nivel.getParejas()
        );
    }
            Timer esconderCarta = new Timer(2000, e -> {
                if (!primeraCarta.isEncontrada() && !segundaCarta.isEncontrada()) {
                    ImageIcon imagenBase = new ImageIcon(getClass().getResource("/Imagenes/bala_100x100.png"));
                    Icon iconoBase = new ImageIcon(imagenBase.getImage());
                    primeraCarta.getBtn().setIcon(iconoBase);
                    segundaCarta.getBtn().setIcon(iconoBase);
                }

                puntaje.setText(String.valueOf(juego.getJuego().getJugador().getPuntaje()));
                intentos.setText(String.valueOf(juego.getJuego().getJugador().getIntentos()));

                juego.getJuego().setPrimeraCarta(null);
                juego.getJuego().setSegundaCarta(null);
            });
            esconderCarta.setRepeats(false);
            esconderCarta.start();
        }

        if (juego.getJuego().isJuegoTerminado()) {
            cronometroTimer.stop();
            JOptionPane.showMessageDialog(this,
                    "¡Completaste el nivel " + nivel + "!\n"
                    + "Puntaje: " + juego.getJuego().getJugador().getPuntaje() + "\n"
                    + "Intentos: " + juego.getJuego().getJugador().getIntentos() + "\n"
                    + "Tiempo: " + juego.getCronometro().obtenerTiempoFormateado(),
                    "Juego completado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    // initComponents() generado por el diseñador — no se toca.


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        cronometroJbl = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        puntaje = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        intentos = new javax.swing.JLabel();
        jButtonReiniciar = new javax.swing.JButton();
        jButtonVolver1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelTablero = new javax.swing.JPanel();
        btn57 = new javax.swing.JButton();
        btn50 = new javax.swing.JButton();
        btn58 = new javax.swing.JButton();
        btn60 = new javax.swing.JButton();
        btn61 = new javax.swing.JButton();
        btn62 = new javax.swing.JButton();
        btn63 = new javax.swing.JButton();
        btn31 = new javax.swing.JButton();
        btn19 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn02 = new javax.swing.JButton();
        btn40 = new javax.swing.JButton();
        btn26 = new javax.swing.JButton();
        btn18 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn01 = new javax.swing.JButton();
        btn35 = new javax.swing.JButton();
        btn59 = new javax.swing.JButton();
        btn00 = new javax.swing.JButton();
        btn46 = new javax.swing.JButton();
        btn43 = new javax.swing.JButton();
        btn48 = new javax.swing.JButton();
        btn34 = new javax.swing.JButton();
        btn42 = new javax.swing.JButton();
        btn53 = new javax.swing.JButton();
        btn54 = new javax.swing.JButton();
        btn52 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        btn09 = new javax.swing.JButton();
        btn51 = new javax.swing.JButton();
        btn41 = new javax.swing.JButton();
        btn33 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn49 = new javax.swing.JButton();
        btn44 = new javax.swing.JButton();
        btn36 = new javax.swing.JButton();
        btn28 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn04 = new javax.swing.JButton();
        btn45 = new javax.swing.JButton();
        btn37 = new javax.swing.JButton();
        btn29 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn05 = new javax.swing.JButton();
        btn38 = new javax.swing.JButton();
        btn30 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn56 = new javax.swing.JButton();
        btn47 = new javax.swing.JButton();
        btn39 = new javax.swing.JButton();
        btn55 = new javax.swing.JButton();
        btn06 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn07 = new javax.swing.JButton();
        btn32 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn64 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btn08 = new javax.swing.JButton();
        jLabelParejasTitulo = new javax.swing.JLabel();
        jLabelParejas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel12.setText("Cronometro");

        cronometroJbl.setText("00:00");

        jLabel13.setText("Puntaje");

        puntaje.setText("0");

        jLabel14.setText("Intentos");

        intentos.setText("0");

        jButtonReiniciar.setText("Reiniciar");
        jButtonReiniciar.addActionListener(this::jButtonReiniciarActionPerformed);

        jButtonVolver1.setText("Volver al menú");
        jButtonVolver1.addActionListener(this::jButtonVolver1ActionPerformed);

        jPanelTablero.setToolTipText("");
        jPanelTablero.setMaximumSize(new java.awt.Dimension(1400, 950));
        jPanelTablero.setPreferredSize(new java.awt.Dimension(1400, 950));

        btn57.setToolTipText("");

        btn50.setToolTipText("");

        btn58.setToolTipText("");

        btn60.setToolTipText("");

        btn61.setToolTipText("");

        btn62.setToolTipText("");

        btn63.setToolTipText("");

        btn31.setToolTipText("");

        btn27.setToolTipText("");

        btn40.setToolTipText("");

        btn26.setToolTipText("");

        btn01.addActionListener(this::btn01ActionPerformed);

        btn35.setToolTipText("");

        btn59.setToolTipText("");

        btn46.setToolTipText("");

        btn43.setToolTipText("");

        btn48.setToolTipText("");

        btn34.setToolTipText("");

        btn42.setToolTipText("");

        btn53.setToolTipText("");

        btn54.setToolTipText("");

        btn52.setToolTipText("");

        btn51.setToolTipText("");

        btn41.setToolTipText("");

        btn33.setToolTipText("");

        btn25.setToolTipText("");

        btn49.setToolTipText("");

        btn44.setToolTipText("");

        btn36.setToolTipText("");

        btn28.setToolTipText("");

        btn45.setToolTipText("");

        btn37.setToolTipText("");

        btn29.setToolTipText("");

        btn38.setToolTipText("");

        btn30.setToolTipText("");

        btn56.setToolTipText("");

        btn47.setToolTipText("");

        btn39.setToolTipText("");

        btn55.setToolTipText("");

        btn24.setToolTipText("");

        btn32.setToolTipText("");

        btn64.setToolTipText("");

        btn23.setToolTipText("");

        javax.swing.GroupLayout jPanelTableroLayout = new javax.swing.GroupLayout(jPanelTablero);
        jPanelTablero.setLayout(jPanelTableroLayout);
        jPanelTableroLayout.setHorizontalGroup(
            jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableroLayout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn57)
                    .addComponent(btn41)
                    .addComponent(btn33)
                    .addComponent(btn25)
                    .addComponent(btn17)
                    .addComponent(btn09)
                    .addComponent(btn00)
                    .addComponent(btn49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTableroLayout.createSequentialGroup()
                        .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelTableroLayout.createSequentialGroup()
                                .addComponent(btn50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn51))
                            .addGroup(jPanelTableroLayout.createSequentialGroup()
                                .addComponent(btn42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn43))
                            .addGroup(jPanelTableroLayout.createSequentialGroup()
                                .addComponent(btn58)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn59)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelTableroLayout.createSequentialGroup()
                                .addComponent(btn52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn56))
                            .addGroup(jPanelTableroLayout.createSequentialGroup()
                                .addComponent(btn60)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn62)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn64))
                            .addGroup(jPanelTableroLayout.createSequentialGroup()
                                .addComponent(btn44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn48))))
                    .addGroup(jPanelTableroLayout.createSequentialGroup()
                        .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn26)
                            .addComponent(btn34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelTableroLayout.createSequentialGroup()
                                .addComponent(btn35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn40))
                            .addGroup(jPanelTableroLayout.createSequentialGroup()
                                .addComponent(btn27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn32))))
                    .addGroup(jPanelTableroLayout.createSequentialGroup()
                        .addComponent(btn01)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn02)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn04)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn05)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn06)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn07)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn08))
                    .addGroup(jPanelTableroLayout.createSequentialGroup()
                        .addComponent(btn10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn16))
                    .addGroup(jPanelTableroLayout.createSequentialGroup()
                        .addComponent(btn18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn24)))
                .addContainerGap(564, Short.MAX_VALUE))
        );
        jPanelTableroLayout.setVerticalGroup(
            jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn00)
                    .addComponent(btn01)
                    .addComponent(btn02)
                    .addComponent(btn04)
                    .addComponent(btn05)
                    .addComponent(btn06)
                    .addComponent(btn07)
                    .addComponent(btn08))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn09)
                    .addComponent(btn10)
                    .addComponent(btn11)
                    .addComponent(btn12)
                    .addComponent(btn13)
                    .addComponent(btn14)
                    .addComponent(btn15)
                    .addComponent(btn16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn17)
                    .addComponent(btn18)
                    .addComponent(btn19)
                    .addComponent(btn20)
                    .addComponent(btn21)
                    .addComponent(btn22)
                    .addComponent(btn24)
                    .addComponent(btn23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn25)
                    .addComponent(btn26)
                    .addComponent(btn27)
                    .addComponent(btn28)
                    .addComponent(btn29)
                    .addComponent(btn30)
                    .addComponent(btn31)
                    .addComponent(btn32))
                .addGap(7, 7, 7)
                .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn33)
                    .addComponent(btn34)
                    .addComponent(btn35)
                    .addComponent(btn36)
                    .addComponent(btn37)
                    .addComponent(btn38)
                    .addComponent(btn39)
                    .addComponent(btn40))
                .addGap(3, 3, 3)
                .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn41)
                    .addComponent(btn43)
                    .addComponent(btn42)
                    .addComponent(btn44)
                    .addComponent(btn45)
                    .addComponent(btn46)
                    .addComponent(btn47)
                    .addComponent(btn48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn51)
                    .addComponent(btn53)
                    .addComponent(btn54)
                    .addComponent(btn50)
                    .addComponent(btn49)
                    .addComponent(btn52)
                    .addComponent(btn55)
                    .addComponent(btn56))
                .addGap(7, 7, 7)
                .addGroup(jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn57)
                    .addComponent(btn58)
                    .addComponent(btn60)
                    .addComponent(btn61)
                    .addComponent(btn62)
                    .addComponent(btn63)
                    .addComponent(btn59)
                    .addComponent(btn64))
                .addContainerGap(811, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanelTablero);

        jLabelParejasTitulo.setText("Parejas");

        jLabelParejas.setText("0/0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonReiniciar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelParejasTitulo)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelParejas)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonVolver1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(intentos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cronometroJbl, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(puntaje, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonReiniciar)
                    .addComponent(jButtonVolver1))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(intentos)
                    .addComponent(jLabelParejas)
                    .addComponent(jLabelParejasTitulo)
                    .addComponent(jLabel12)
                    .addComponent(cronometroJbl)
                    .addComponent(jLabel13)
                    .addComponent(puntaje))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReiniciarActionPerformed
         juego.reiniciarJuego();
    puntaje.setText("0");
    intentos.setText("0");
    jLabelParejas.setText("0/" + nivel.getParejas());
    
     ImageIcon imagenBase = new ImageIcon(getClass().getResource("/Imagenes/bala_100x100.png"));
    Icon iconoBase = new ImageIcon(imagenBase.getImage());
    for (JButton[] fila : botones) {
        for (JButton b : fila) {
            b.setIcon(iconoBase);
        }
    }

    }//GEN-LAST:event_jButtonReiniciarActionPerformed

    private void jButtonVolver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolver1ActionPerformed
        Vista.MenuJuego menu = new Vista.MenuJuego();
    menu.setVisible(true);
    menu.setLocationRelativeTo(null);
    this.dispose();
    }//GEN-LAST:event_jButtonVolver1ActionPerformed

    private void btn01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn01ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn00;
    private javax.swing.JButton btn01;
    private javax.swing.JButton btn02;
    private javax.swing.JButton btn04;
    private javax.swing.JButton btn05;
    private javax.swing.JButton btn06;
    private javax.swing.JButton btn07;
    private javax.swing.JButton btn08;
    private javax.swing.JButton btn09;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn17;
    private javax.swing.JButton btn18;
    private javax.swing.JButton btn19;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btn23;
    private javax.swing.JButton btn24;
    private javax.swing.JButton btn25;
    private javax.swing.JButton btn26;
    private javax.swing.JButton btn27;
    private javax.swing.JButton btn28;
    private javax.swing.JButton btn29;
    private javax.swing.JButton btn30;
    private javax.swing.JButton btn31;
    private javax.swing.JButton btn32;
    private javax.swing.JButton btn33;
    private javax.swing.JButton btn34;
    private javax.swing.JButton btn35;
    private javax.swing.JButton btn36;
    private javax.swing.JButton btn37;
    private javax.swing.JButton btn38;
    private javax.swing.JButton btn39;
    private javax.swing.JButton btn40;
    private javax.swing.JButton btn41;
    private javax.swing.JButton btn42;
    private javax.swing.JButton btn43;
    private javax.swing.JButton btn44;
    private javax.swing.JButton btn45;
    private javax.swing.JButton btn46;
    private javax.swing.JButton btn47;
    private javax.swing.JButton btn48;
    private javax.swing.JButton btn49;
    private javax.swing.JButton btn50;
    private javax.swing.JButton btn51;
    private javax.swing.JButton btn52;
    private javax.swing.JButton btn53;
    private javax.swing.JButton btn54;
    private javax.swing.JButton btn55;
    private javax.swing.JButton btn56;
    private javax.swing.JButton btn57;
    private javax.swing.JButton btn58;
    private javax.swing.JButton btn59;
    private javax.swing.JButton btn60;
    private javax.swing.JButton btn61;
    private javax.swing.JButton btn62;
    private javax.swing.JButton btn63;
    private javax.swing.JButton btn64;
    private javax.swing.JLabel cronometroJbl;
    private javax.swing.JLabel intentos;
    private javax.swing.JButton jButtonReiniciar;
    private javax.swing.JButton jButtonVolver1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabelParejas;
    private javax.swing.JLabel jLabelParejasTitulo;
    private javax.swing.JPanel jPanelTablero;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel puntaje;
    // End of variables declaration//GEN-END:variables
}
