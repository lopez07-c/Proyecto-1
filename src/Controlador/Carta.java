/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author UTN
 */
public class Carta {
     private Modelo.Carta carta = new Modelo.Carta();
    
      // Muestra la carta
    public void mostrarCarta() {
        carta.setVisible(true);
    }

    // Oculta la carta si aún no fue encontrada
    public void ocultarCarta() {
        if (!carta.isEncontrada()) {
            carta.setVisible(true);
        }
    }

    // Marca la carta como encontrada
    public void marcarEncontrada() {
        carta.setEncontrada(true);
        carta.setVisible(true);
    }

    // Reinicia el estado de la carta
    public void reiniciarCarta() {
        carta.setVisible(true);
        carta.isEncontrada() ;
    }

    // Compara si dos cartas forman una pareja
    public boolean esPareja(Modelo.Carta otraCarta) {
        if (otraCarta == null) {
            return false;
        }
        return this.carta.getId() == otraCarta.getId();
    }

    // Verifica si la carta puede seleccionarse
    public boolean puedeSeleccionarse() {
        return !carta.isVisible() && !carta.isEncontrada();
    }

    
}

