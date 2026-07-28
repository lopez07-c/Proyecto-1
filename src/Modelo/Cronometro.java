/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author UTN
 */
public class Cronometro {
     
    private long tiempoInicio;
    private long tiempoFin;
    private boolean ejecutando;

    
    public Cronometro() {
        this.tiempoInicio = 0;
        this.tiempoFin = 0;
        this.ejecutando = false;
    }

  
    public Cronometro(long tiempoInicio, long tiempoFin, boolean ejecutando) {
        this.tiempoInicio = tiempoInicio;
        this.tiempoFin = tiempoFin;
        this.ejecutando = ejecutando;
    }

  

    public long getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(long tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public long getTiempoFin() {
        return tiempoFin;
    }

    public void setTiempoFin(long tiempoFin) {
        this.tiempoFin = tiempoFin;
    }

    public boolean isEjecutando() {
        return ejecutando;
    }

    public void setEjecutando(boolean ejecutando) {
        this.ejecutando = ejecutando;
    }
}


