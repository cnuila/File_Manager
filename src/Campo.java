/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos Nuila
 */
public class Campo {
    
    private String nombre;
    private String tipo;
    private int length;
    private boolean llavePrimaria;

    public Campo(String nombre, String tipo, int length, boolean llavePrimaria) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.length = length;
        this.llavePrimaria = llavePrimaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isLlavePrimaria() {
        return llavePrimaria;
    }

    public void setLlavePrimaria(boolean llavePrimaria) {
        this.llavePrimaria = llavePrimaria;
    }

    @Override
    public String toString() {
        String llave = "0";
        if (!llavePrimaria){
            llave = "1";
        }
        return nombre + ": " + tipo + "[" + length + "]"+llave;
    }
}
