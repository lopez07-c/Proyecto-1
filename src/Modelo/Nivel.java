/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Modelo;

/**
 *
 * @author josea
 */
public enum Nivel {
    PRINCIPIANTE(8, 4, 4),
    INTERMEDIO(16, 4, 8),
    AVANZADO(32, 8, 8);

    private final int parejas;
    private final int filas;
    private final int columnas;

    Nivel(int parejas, int filas, int columnas) {
        this.parejas = parejas;
        this.filas = filas;
        this.columnas = columnas;
    }

    public int getParejas() { return parejas; }
    public int getTotalCartas() { return parejas * 2; }
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }

    @Override
    public String toString() {
        String nombre = name().charAt(0) + name().substring(1).toLowerCase();
        return nombre;
    }
   
}
