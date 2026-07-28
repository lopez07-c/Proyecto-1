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
    
      
    public void mostrarCarta() {
        carta.setVisible(true);
    }

    
    public void ocultarCarta() {
        if (!carta.isEncontrada()) {
            carta.setVisible(true);
        }
    }

    
    public void marcarEncontrada() {
        carta.setEncontrada(true);
        carta.setVisible(true);
    }

    
    public void reiniciarCarta() {
        carta.setVisible(true);
        carta.isEncontrada() ;
    }

    
    public boolean esPareja(Modelo.Carta otraCarta) {
        if (otraCarta == null) {
            return false;
        }
        return this.carta.getId() == otraCarta.getId();
    }

    
    public boolean puedeSeleccionarse() {
        return !carta.isVisible() && !carta.isEncontrada();
    }

    
}

