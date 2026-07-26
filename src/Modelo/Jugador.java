/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author matam
 */

public class Jugador {
    
    private int puntaje;
    private int intentos;
    private int parejasEncontradas;

    public Jugador() {
        this.puntaje = 0;
        this.intentos = 0;
        this.parejasEncontradas = 0;
    }

    public Jugador(int puntaje, int intentos, int parejasEncontradas) {
        this.puntaje = puntaje;
        this.intentos = intentos;
        this.parejasEncontradas = parejasEncontradas;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getIntentos() {
        return intentos;
    }

    public int getParejasEncontradas() {
        return parejasEncontradas;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public void setParejasEncontradas(int parejasEncontradas) {
        this.parejasEncontradas = parejasEncontradas;
    }
}
