package Modelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Esteban
 */
    
    public class Tablero {    
     // Atributos
    private Carta[][] CartasTablero;
    private int filas;
    private int columnas;
    private int totalParejas;

    
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.totalParejas = (filas * columnas) / 2;
        this.CartasTablero = new Carta[filas][columnas];
    }

   
    public Carta[][] getTablero() {
        return CartasTablero;
    }

    public void setTablero(Carta[][] tablero) {
        this.CartasTablero = tablero;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getTotalParejas() {
        return totalParejas;
    }

    public void setTotalParejas(int totalParejas) {
        this.totalParejas = totalParejas;
    }

    public Carta[][] getCartasTablero() {
        return CartasTablero;
    }

    public void setCartasTablero(Carta[][] CartasTablero) {
        this.CartasTablero = CartasTablero;
    }

    
    
    
}
    
    
    
    
    
    
    

