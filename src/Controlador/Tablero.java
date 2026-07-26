/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;

/**
 *
 * @author ricar
 */
public class Tablero {

    Modelo.Tablero tableroModel;

    public Tablero(Modelo.Tablero tablero) {
        this.tableroModel = tablero;
    }
    
    // Inicializa el tablero
    public void inicializarTablero() {
        tableroModel.setTablero(new Modelo.Carta[tableroModel.getFilas()][tableroModel.getColumnas()]);
        distribuirParejas();
    }
    
    // Distribuye las parejas aleatoriamente
    public void distribuirParejas() {
        ArrayList<Modelo.Carta> listaCartas = new ArrayList<>();
        
        // Crear las parejas
        for (int i = 1; i <= tableroModel.getTotalParejas(); i++) {
            ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/Img-" + i + ".png"));
            listaCartas.add(new Modelo.Carta(i, imagen));
            listaCartas.add(new Modelo.Carta(i, imagen));
        }
        // Mezclar las cartas
        Collections.shuffle(listaCartas);
        int indice = 0;
        // Colocar las cartas en la matriz
        for (int f = 0; f < tableroModel.getFilas(); f++) {
            for (int c = 0; c < tableroModel.getColumnas(); c++) {
                tableroModel.getTablero()[f][c] = listaCartas.get(indice);
                indice++;
            }
        }
    }

    // Obtiene una carta según su posición
    public Modelo.Carta obtenerCarta(int fila, int columna) {
        if (fila >= 0 && fila < tableroModel.getFilas() && columna >= 0 && columna < tableroModel.getColumnas()) {
            return tableroModel.getTablero()[fila][columna];
        }
        return null;
    }

    // Compara dos cartas
    public boolean compararCartas(Modelo.Carta carta1, Modelo.Carta carta2) {
        if (carta1.getImagen().equals(carta2.getImagen())) {
            carta1.setEncontrada(true);
            carta2.setEncontrada(true);
            return true;
        }
        carta1.setVisible(false);
        carta2.setVisible(false);
        return false;
    }

    // Verifica si el juego terminó
    public boolean juegoFinalizado() {
        for (int f = 0; f < tableroModel.getFilas(); f++) {
            for (int c = 0; c < tableroModel.getColumnas(); c++) {
                if (!tableroModel.getTablero()[f][c].isEncontrada()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Reinicia el tablero
    public void reiniciarTablero() {
        inicializarTablero();
    }

}