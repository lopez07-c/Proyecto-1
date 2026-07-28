/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javax.swing.JButton;

/**
 *
 * @author Esteban
 */
public class Juego {

    private Modelo.Juego juego;
    private Controlador.Tablero tablero;
    private Controlador.Cronometro cronometro;
    private Controlador.Jugador jugador;

    public Juego(Modelo.Juego juego, Tablero tablero, Cronometro cronometro, Jugador jugador) {
        this.juego = juego;
        this.tablero = tablero;
        this.cronometro = cronometro;
        this.jugador = jugador;
    }

   
    public void iniciarPartida() {
        tablero.inicializarTablero();
        juego.getJugador().setPuntaje(0);
        juego.getJugador().setIntentos(0);
        juego.getJugador().setParejasEncontradas(0);
        cronometro.reiniciar();
        cronometro.iniciar();
        juego.setPrimeraCarta(null);
        juego.setSegundaCarta(null);
        juego.setJuegoTerminado(false);
    }

    
    public void procesarSeleccion(JButton btn, int fila, int columna) {

        Modelo.Carta carta = tablero.obtenerCarta(fila, columna);
        if (carta == null) {
            return;
        }
        if (carta.isVisible() || carta.isEncontrada()) {
            return;
        }
        carta.setBtn(btn);
        carta.setVisible(true);

        if (juego.getPrimeraCarta() == null) {
            juego.setPrimeraCarta(carta);
        } else {
            juego.setSegundaCarta(carta);
            jugador.registrarIntento();
            verificarPareja();
        }
    }

    
    public void verificarPareja() {

        if (tablero.compararCartas(juego.getPrimeraCarta(), juego.getSegundaCarta())) {
            juego.getPrimeraCarta().setEncontrada(true);
            juego.getSegundaCarta().setEncontrada(true);
            jugador.registrarPareja();
            jugador.sumarPuntos(100);
        } else {
            jugador.restarPuntos(20);
            juego.getPrimeraCarta().setVisible(false);
            juego.getSegundaCarta().setVisible(false);

        }
        finalizarJuego();
    }

    
    public void actualizarJugador(int puntos) {

        jugador.sumarPuntos(puntos);

    }

    
    public void finalizarJuego() {

        if (tablero.juegoFinalizado()) {

            juego.setJuegoTerminado(true);
            cronometro.detener();
        }
    }

  
    public void reiniciarJuego() {

        iniciarPartida();

    }

    public Modelo.Juego getJuego() {
        return juego;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Cronometro getCronometro() {
        return cronometro;
    }
    
    

}