package ListaSimpleCircular;

public class Nodo <T>{
    T value;
    Nodo<T> next;

    public Nodo(T valor){
        this.value =valor;
        next=null;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "valor=" + value +
                ", next=" + next +
                '}';
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Nodo<T> getNext() {
        return next;
    }

    public void setNext(Nodo<T> next) {
        this.next = next;
    }
}
