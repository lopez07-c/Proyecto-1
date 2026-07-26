/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Esteban
 */
public class Juego {
    
    private Tablero tablero;
    private Jugador jugador;
    private Cronometro cronometro;
    private boolean juegoFinalizado;
    private Carta primeraCarta;
    private Carta segundaCarta;
    private boolean juegoTerminado;

    public Juego(Tablero tablero, Jugador jugador, Cronometro cronometro) {
        this.tablero = tablero;
        this.jugador = jugador;
        this.cronometro = cronometro;
        this.juegoFinalizado = false;
        this.primeraCarta = null;
        this.segundaCarta = null;
        this.juegoTerminado = false;
    }
    

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Cronometro getCronometro() {
        return cronometro;
    }

    public void setCronometro(Cronometro cronometro) {
        this.cronometro = cronometro;
    }

    public boolean isJuegoFinalizado() {
        return juegoFinalizado;
    }

    public void setJuegoFinalizado(boolean juegoFinalizado) {
        this.juegoFinalizado = juegoFinalizado;
    }

    public Carta getPrimeraCarta() {
        return primeraCarta;
    }

    public void setPrimeraCarta(Carta primeraCarta) {
        this.primeraCarta = primeraCarta;
    }

    public Carta getSegundaCarta() {
        return segundaCarta;
    }

    public void setSegundaCarta(Carta segundaCarta) {
        this.segundaCarta = segundaCarta;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public void setJuegoTerminado(boolean juegoTerminado) {
        this.juegoTerminado = juegoTerminado;
    }
    
    
}
