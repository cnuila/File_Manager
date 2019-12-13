
public class LinkedList {

    ListNode inicio = null;
    ListNode tmp = new ListNode();
    int size = 0;

    public LinkedList() {

    }

    //Agrega elemento a la lista en la posicion ingresada
    public void inserta(Llave dato, int posicion) {
        tmp = inicio;
        ListNode newNode = new ListNode();
        newNode.setData(dato);
        if (inicio == null) {
            inicio = newNode;
            size++;
        } else if (posicion == 1) {
            tmp.setAnterior(newNode);
            newNode.setSiguiente(tmp);
            size++;
        } else if (posicion == size + 1) {
            for (int i = 1; i <= posicion - 2; i++) {
                tmp = tmp.getSiguiente();
            }
            tmp.setSiguiente(newNode);
            newNode.setAnterior(tmp);
            size++;
        } else if (posicion > 1 && posicion <= size) {
            for (int i = 1; i <= posicion - 1; i++) {
                tmp = tmp.getSiguiente();
            }
            newNode.setSiguiente(tmp);
            newNode.setAnterior(tmp.getAnterior());
            tmp.getAnterior().setSiguiente(newNode);
            tmp.setAnterior(newNode);
            size++;
        }
    }

    //Elimina el elemento en la posicion ingresada
    public void borrarElemento(int posicion) {
        tmp = inicio;
        if (posicion == 1) {
            if (size > 1) {
                tmp = inicio.getSiguiente();
                tmp.getAnterior().setSiguiente(null);
                tmp.setAnterior(null);
                inicio = tmp;
            } else {
                inicio = null;
            }
            size = size - 1;
        } else if (posicion == size) {
            for (int i = 1; i <= size - 1; i++) {
                tmp = tmp.getSiguiente();
            }
            tmp.getAnterior().setSiguiente(null);
            tmp.setAnterior(null);
            size = size - 1;
        } else if (posicion > 1 && posicion < size) {
            for (int i = 1; i <= posicion - 1; i++) {
                tmp = tmp.getSiguiente();
            }
            tmp.getSiguiente().setAnterior(tmp.getAnterior());
            tmp.getAnterior().setSiguiente(tmp.getSiguiente());
            tmp.setSiguiente(null);
            tmp.setAnterior(null);
            size = size - 1;
        }
    }

    //Verifica si la lista esta vacia
    public boolean vacia() {
        return inicio == null;
    }

    //Devuelve el dato en la posicion mandada
    public Llave elementoPosicion(int posicion) {
        if (posicion >= 1 && posicion <= size) {
            tmp = inicio;
//            if (posicion == size) {
//                for (int i = 1; i <=posicion - 1; i++) {
//                    tmp = tmp.getSiguiente();
//                }
//            }
                for (int i = 1; i <=posicion-1; i++) {
                    tmp = tmp.getSiguiente();
                }
            return tmp.getData();
        } else {
            return null;
        }
    }

    //Devuelve el dato siguiente a la posicion ingresada
    public String ObtenerSiguiente(int posicion) {
        if (posicion >= 1 && posicion < size) {
            tmp = inicio;
            for (int i = 1; i <= posicion; i++) {
                tmp = tmp.getSiguiente();
            }
            return tmp.getData().getLlave();
        } else {
            return null;
        }
    }

    //Devuelve el dato anterior a la posicion ingresada
    public String ObtenerAnterior(int posicion) {
        if (posicion > 1 && posicion < size) {
            tmp = inicio;
            for (int i = 1; i <= posicion - 2; i++) {
                tmp = tmp.getSiguiente();
            }
            return tmp.getData().getLlave();
        } else {
            return null;
        }
    }
}
