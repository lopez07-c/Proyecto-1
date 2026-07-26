/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author UTN
 */
public class Carta {
    
    private int id;
    private ImageIcon imagen;
    private boolean visible;
    private boolean encontrada;
    private JButton btn;

    
    public Carta() {
        this.id = 0;
        this.imagen = null;
        this.visible = false;
        this.encontrada = false;
    }

    public Carta(int id, ImageIcon imagen) {
        this.id = id;
        this.imagen = imagen;
        this.visible = false;
        this.encontrada = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isEncontrada() {
        return encontrada;
    }

    public void setEncontrada(boolean encontrada) {
        this.encontrada = encontrada;
    }

    public JButton getBtn() {
        return btn;
    }

    public void setBtn(JButton btn) {
        this.btn = btn;
    }

    
     
}


