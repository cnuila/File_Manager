
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Carlos Nuila
 */
public class NodoArbol {

    private int keyNumber;
    private Llave[] keys;
    private boolean leaf;
    private NodoArbol[] children;
    private int t;

    public NodoArbol(int orden, boolean leaf) {
        this.leaf = leaf;
        this.t = orden;
        this.keys = new Llave[t - 1];
        llenarArreglo();
        this.children = new NodoArbol[t];
        this.keyNumber = 0;
    }
    
    private void llenarArreglo() {
        for (int i = 0; i < t - 1; i++) {
            keys[i] = new Llave();
        }
    }

    public int getKeyNumber() {
        return keyNumber;
    }

    public void setKeyNumber(int keyNumber) {
        this.keyNumber = keyNumber;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public Llave[] getKeys() {
        return keys;
    }

    public void setKeys(Llave[] keys) {
        this.keys = keys;
    }

    public NodoArbol[] getChildren() {
        return children;
    }

    public void setChildren(NodoArbol[] children) {
        this.children = children;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
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

    private int findKey(String k) {
        int index = 0;
        while (index < keyNumber && comparacion(keys[index].getLlave(), k).equals("Menor")) {
            index++;
        }
        return index;
    }

    public void remove(String k) {
        int index = findKey(k);

        if (index < keyNumber && keys[index].getLlave().equals(k)) {

            if (leaf) {
                removeFromLeaf(index);
            } else {
                removeFromNonLeaf(index);
            }
        } else {

            if (!leaf) {
                boolean flag = false;
                if (index == keyNumber) {
                    flag = true;
                }

                if (children[index].getKeyNumber() < (t / 2)) {
                    fill(index);
                }

                if (flag && index > keyNumber) {
                    children[index - 1].remove(k);
                } else {
                    children[index].remove(k);
                }
            } else {
                System.out.println("La llave no existen en el arbol");
            }
        }
    }

    private void removeFromLeaf(int index) {
        for (int i = index + 1; i < keyNumber; i++) {
            keys[i - 1] = keys[i];
        }
        keyNumber--;
    }

    private void removeFromNonLeaf(int index) {
        String k = keys[index].getLlave();

        if (children[index].getKeyNumber() >= (t / 2)) {
            String pred = getPred(index);
            keys[index].setLlave(pred);
            children[index].remove(pred);
        } else {
            if (children[index + 1].getKeyNumber() >= (t / 2)) {
                String succ = getSucc(index);
                keys[index].setLlave(succ);
                children[index + 1].remove(succ);
            } else {

                merge(index);
                children[index].remove(k);
            }
        }
    }

    private String getPred(int index) {
        NodoArbol cur = children[index];
        while (!cur.isLeaf()) {
            cur = cur.getChildren()[cur.getKeyNumber()];
        }
        return cur.getKeys()[cur.getKeyNumber() - 1].getLlave();
    }

    private String getSucc(int index) {
        NodoArbol cur = children[index + 1];
        while (!cur.isLeaf()) {
            cur = cur.getChildren()[0];
        }
        return cur.getKeys()[0].getLlave();
    }

    private void fill(int index) {
        if (index != 0 && children[index - 1].getKeyNumber() >= (t / 2)) {
            borrowFromPrev(index);
        } else {
            if (index != keyNumber && children[index + 1].getKeyNumber() >= (t / 2)) {
                borrowFromNext(index);
            } else {
                if (index != keyNumber) {
                    merge(index);
                } else {
                    merge(index - 1);
                }
            }
        }
    }

    private void borrowFromPrev(int index) {
        NodoArbol child = children[index];
        NodoArbol sibling = children[index - 1];

        for (int i = child.getKeyNumber() - 1; i >= 0; i--) {
            child.getKeys()[i + 1] = child.getKeys()[i];
        }

        if (!child.isLeaf()) {
            for (int i = child.getKeyNumber(); i >= 0; i--) {
                child.getChildren()[i + 1] = child.getChildren()[i];
            }
        }

        child.getKeys()[0] = keys[index - 1];

        if (!child.isLeaf()) {
            child.getChildren()[0] = sibling.getChildren()[sibling.getKeyNumber()];
        }

        keys[index - 1] = sibling.getKeys()[sibling.getKeyNumber() - 1];

        child.setKeyNumber(child.getKeyNumber() + 1);
        sibling.setKeyNumber(sibling.getKeyNumber() - 1);
    }

    private void borrowFromNext(int index) {
        NodoArbol child = children[index];
        NodoArbol sibling = children[index + 1];

        child.getKeys()[child.getKeyNumber()] = keys[index];

        if (!child.isLeaf()) {
            child.getChildren()[child.getKeyNumber() + 1] = sibling.getChildren()[0];
        }

        keys[index] = sibling.getKeys()[0];

        for (int i = 1; i < sibling.getKeyNumber(); i++) {
            sibling.getKeys()[i - 1] = sibling.getKeys()[i];
        }

        if (!sibling.isLeaf()) {
            for (int i = 1; i <= sibling.getKeyNumber(); i++) {
                sibling.getChildren()[i - 1] = sibling.getChildren()[i];
            }
        }

        child.setKeyNumber(child.getKeyNumber() + 1);
        sibling.setKeyNumber(sibling.getKeyNumber() - 1);
    }

    private void merge(int index) {
        NodoArbol child = children[index];
        NodoArbol sibling = children[index + 1];

        child.getKeys()[t / 2 - 1] = keys[index];

        for (int i = 0; i < sibling.getKeyNumber(); i++) {
            child.getKeys()[i + t / 2] = sibling.getKeys()[i];
        }

        if (!child.isLeaf()) {
            for (int i = 0; i <= sibling.getKeyNumber(); i++) {
                child.getChildren()[i + t / 2] = sibling.getChildren()[i];
            }
        }

        for (int i = index + 1; i < keyNumber; i++) {
            keys[i - 1] = keys[i];
        }

        for (int i = index + 2; i <= keyNumber; i++) {
            children[i - 1] = children[i];
        }

        child.setKeyNumber(child.getKeyNumber() + sibling.getKeyNumber() + 1);
        keyNumber--;
    }

    public void insertNonFull(String k) {
        int i = keyNumber - 1;
        if (leaf) {
            while (i >= 0 && comparacion(keys[i].getLlave(), k).equals("Mayor")) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = new Llave();
            keys[i + 1].setLlave(k);
            keyNumber++;
        } else {
            while (i >= 0 && comparacion(keys[i].getLlave(), k).equals("Mayor")) {
                i--;
            }

            if (children[i + 1].getKeyNumber() == (t - 1)) {

                splitChild(i + 1, children[i + 1]);
                if (comparacion(keys[i + 1].getLlave(), k).equals("Menor")) {
                    i++;
                }
            }
            children[i + 1].insertNonFull(k);
        }
    }

    public void splitChild(int i, NodoArbol y) {
        NodoArbol z = new NodoArbol(y.getT(), y.isLeaf());
        z.setKeyNumber((t - 1) / 2);

        for (int j = 0; j < (t - 1) / 2; j++) {
            z.getKeys()[j] = y.getKeys()[j + t / 2];
        }

        if (!y.isLeaf()) {
            for (int j = 0; j < (t / 2); j++) {
                z.getChildren()[j] = y.getChildren()[j + t / 2];
            }
        }

        y.setKeyNumber(((t - 1) / 2));

        for (int j = keyNumber; j >= i + 1; j--) {
            children[j + 1] = children[j];
        }

        children[i + 1] = z;

        for (int j = keyNumber - 1; j >= i; j--) {
            keys[j + 1] = keys[j];
        }

        keys[i] = y.getKeys()[(t - 1) / 2];

        keyNumber++;
    }

    public NodoArbol search(String k) {
        int i = 0;
        while (i < keyNumber && comparacion(k, keys[i].getLlave()).equals("Mayor")) {
            i++;
        }
        if (keys[i].getLlave().equals(k)) {
            return this;
        }
        if (leaf) {
            return null;
        }
        return children[i].search(k);
    }

    public void traverse() {
        int i;
        for (i = 0; i < keyNumber; i++) {
            if (!leaf) {
                children[i].traverse();
            }
            System.out.print(" " + keys[i]);
        }
        if (!leaf) {
            children[i].traverse();
        }
    }

    @Override
    public String toString() {
        return "{keys=" + Arrays.toString(keys) + ", children=" + Arrays.toString(children) + '}';
    }

}
