package ListaDobleCircular;

public class Nodo<T> {

    T value;
    Nodo<T> next;
    Nodo<T> previous;

    public Nodo(T value){
        this.value=value;
        next=null;
        previous=null;
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

    public Nodo<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Nodo<T> previous) {
        this.previous = previous;
    }
}
