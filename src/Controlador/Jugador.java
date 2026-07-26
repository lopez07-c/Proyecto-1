/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author matam
 */
public class Jugador {

    private Modelo.Jugador jugador;

    public Jugador(Modelo.Jugador jugador) {
        this.jugador = jugador;
    }

    public void registrarIntento() {
        jugador.setIntentos(jugador.getIntentos() + 1);
    }

    public void registrarPareja() {
        jugador.setParejasEncontradas(jugador.getParejasEncontradas() + 1);

    }

    public void sumarPuntos(int puntos) {
        jugador.setPuntaje(jugador.getPuntaje() + puntos);
    }

    public void restarPuntos(int puntos) {
        jugador.setPuntaje(jugador.getPuntaje() - puntos);

        if (jugador.getPuntaje() < 0) {
            jugador.setPuntaje(0);
        }
    }

    public void reiniciarJugador() {
        jugador.setPuntaje(0);
        jugador.setIntentos(0);
        jugador.setParejasEncontradas(0);
    }
}
