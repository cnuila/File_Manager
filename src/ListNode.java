public class ListNode {

    ListNode anterior = null;
    ListNode siguiente = null;
    Llave data = null;

    public ListNode getAnterior() {
        return anterior;
    }

    public void setAnterior(ListNode anterior) {
        this.anterior = anterior;
    }

    public ListNode getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(ListNode siguiente) {
        this.siguiente = siguiente;
    }

    public Llave getData() {
        return data;
    }

    public void setData(Llave data) {
        this.data = data;
    }

}
