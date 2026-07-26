/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author matam
 */
public class Cronometro {
    
    private Modelo.Cronometro cronometro;

    public Cronometro(Modelo.Cronometro cronometro) {
        this.cronometro = cronometro;
    }
  
    public void iniciar() {
        cronometro.setTiempoInicio(System.currentTimeMillis());
        cronometro.setEjecutando(true);
    }

    public void detener() {
        if (cronometro.isEjecutando()) {
            cronometro.setTiempoFin(System.currentTimeMillis());
            cronometro.setEjecutando(false);
        }
    }

    public void reiniciar() {
        cronometro.setTiempoInicio(0);
        cronometro.setTiempoFin(0);
        cronometro.setEjecutando(false);
    }

    public long obtenerTiempoTranscurrido() {
        if (cronometro.isEjecutando()) {
            return System.currentTimeMillis() - cronometro.getTiempoInicio();
        } else {
            return cronometro.getTiempoFin() - cronometro.getTiempoInicio();
        }
    }

    public long obtenerTiempoSegundos() {
        return obtenerTiempoTranscurrido() / 1000;
    }

    public String obtenerTiempoFormateado() {
        long segundos = obtenerTiempoSegundos();
        long minutos = segundos / 60;
        segundos = segundos % 60;

        return String.format("%02d:%02d", minutos, segundos);
    }
}