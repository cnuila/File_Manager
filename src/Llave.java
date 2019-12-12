
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos Nuila
 */
public class Llave implements Serializable{
    
    private String llave;
    private long offset;
    private int tamano;

    private static final long SerialVersionUID = 1008L;
    
    public Llave() {
        llave = "";
        offset = 0;
        tamano = 0;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }   

    @Override
    public String toString() {
        return llave;
    }
    
    
    
}
