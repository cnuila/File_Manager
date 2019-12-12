public class LinkedList {

    ListNode inicio = null;
    ListNode tmp = new ListNode();
    int size = 0;

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
        } else if (posicion > 1 && posicion < size) {
            for (int i = 0; i < posicion - 1; i++) {
                tmp = tmp.getSiguiente();
            }
            tmp.getSiguiente().setAnterior(newNode);
            newNode.setSiguiente(tmp.getSiguiente());
            newNode.setAnterior(tmp);
            tmp.setSiguiente(newNode);
            size++;
        } else if (posicion == size + 1) {
            ListNode temporal = new ListNode();
            temporal.setData(dato);
            temporal.setSiguiente(temporal);
            for (int i = 0; i < posicion - 1; i++) {
                temporal = temporal.getSiguiente();
            }
            temporal.setSiguiente(newNode);
            newNode.setAnterior(temporal);
            size++;
        } else {
            System.out.println("No se agrego el elemento, Ingrese una posicion valida");
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
        } else if (posicion > 1 && posicion < size) {
            tmp = inicio;
            for (int i = 0; i < posicion; i++) {
                tmp = tmp.getSiguiente();
            }
            tmp.getSiguiente().setAnterior(tmp.getAnterior());
            tmp.getAnterior().setSiguiente(tmp.getSiguiente());
            tmp.setSiguiente(null);
            tmp.setAnterior(null);
            size = size - 1;
        } else if (posicion == size) {
            for (int i = 0; i < size; i++) {
                tmp = tmp.getSiguiente();
            }
            tmp.getAnterior().setSiguiente(null);
            tmp.setAnterior(null);
            size = size - 1;
        } else {
            System.out.println("No se borro ningun elemento");
        }
    }

    //Devuelve la posicion del dato a buscar
    public int buscar(Llave dato) {
        tmp = inicio;
        int pos = -1;
        for (int i = 0; i < size; i++) {
            if (tmp.getData().getLlave().equals(dato.getLlave())) {
                pos = i;
            } else {
                tmp = tmp.getSiguiente();
            }
        }
        if (pos == -1) {
            System.out.println("no se encontro la Llave buscada");
        }
        return pos;
    }

    //Verifica si la lista esta vacia
    public boolean vacia() {
        return inicio == null;
    }

    //Devuelve el dato en la posicion mandada
    public String elementoPosicion(int posicion) {
        if (posicion >= 1 && posicion <= size) {
            tmp = inicio;
            for (int i = 0; i < posicion; i++) {
                tmp = tmp.getSiguiente();
            }
            return tmp.getData().getLlave();
        } else {
            System.out.println("Ingreso una posicion incorrecta");
            return null;
        }
    }

    //Devuelve el dato siguiente a la posicion ingresada
    public String ObtenerSiguiente(int posicion) {
        if (posicion >= 1 && posicion < size) {
            tmp = inicio;
            for (int i = 0; i < posicion; i++) {
                tmp = tmp.getSiguiente();
            }
            return tmp.getData().getLlave();
        } else {
            System.out.println("Ingreso una posicion incorrecta");
            return null;
        }
    }

    //Devuelve el dato anterior a la posicion ingresada
    public String ObtenerAnterior(int posicion) {
        if (posicion > 1 && posicion < size) {
            tmp = inicio;
            for (int i = 0; i < posicion; i++) {
                tmp = tmp.getSiguiente();
            }
            return tmp.getData().getLlave();
        } else {
            System.out.println("Ingreso una posicion incorrecta");
            return null;
        }
    }

    //Vacia la lista
    public void Anula() {
        if (inicio != null) {
            inicio = null;
            System.out.println("La lista se vacio correctamente");
        } else {
            System.out.println("La lista ya esta vacia");
        }
    }
}
