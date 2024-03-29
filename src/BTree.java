
import java.io.Serializable;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos Nuila
 */
public class BTree implements Serializable{

    private NodoArbol raiz;
    private int orden;
    
    private static final long SerialVersionUID = 1008;

    public BTree(int orden) {
        this.raiz = null;
        this.orden = orden;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public void traverse() {
        if (raiz != null) {
            raiz.traverse();
        }
    }

    public NodoArbol search(Llave k) {
        if (raiz == null) {
            return null;
        } else {
            return raiz.search(k);
        }
    }

    public void insert(Llave k) {
        if (raiz == null) {
            raiz = new NodoArbol(orden, true);
            raiz.getKeys()[0] = k;
            raiz.setKeyNumber(1);
        } else {
            if (raiz.getKeyNumber() == (orden - 1)) {
                NodoArbol s = new NodoArbol(orden, false);
                s.getChildren()[0] = raiz;
                s.splitChild(0, raiz);
                int i = 0;
                if (comparacion(s.getKeys()[0].getLlave(), k.getLlave()).equals("Menor")) {
                    i++;
                }
                s.getChildren()[i].insertNonFull(k);
                raiz = s;
            } else {
                raiz.insertNonFull(k);
            }
        }
    }

    public void remove(Llave k) {
        if (raiz == null) {
            JOptionPane.showMessageDialog(null, "No hay registros", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            raiz.remove(k);
            if (raiz.getKeyNumber() == 0) {
                if (raiz.isLeaf()) {
                    raiz = null;
                } else {
                    raiz = raiz.getChildren()[0];
                }
            }
        }
    }

    public String comparacion(String dato1, String dato2) {
        boolean sigue = true;
        int i = 0;
        if (dato1.length() > dato2.length()) {
            return "Mayor";
        }
        if (dato1.length() < dato2.length()) {
            return "Menor";
        }
        while (sigue) {
            if (dato1.charAt(i) == dato2.charAt(i)) {
                i++;
                if (i >= dato1.length() || i >= dato2.length()) {
                    return "Iguales";
                }
            } else {
                if (dato1.charAt(i) > dato2.charAt(i)) {
                    return "Mayor";
                } else {
                    return "Menor";
                }
            }

        }
        return "";
    }

    @Override
    public String toString() {
        return "BTree[" + "\nraiz=" + raiz + "\n]";
    }

}
