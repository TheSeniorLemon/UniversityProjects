package Cola;

public class Nodo<T>{
    T value;
    Nodo<T> previous;

    public Nodo(T value){
        this.value=value;
        previous =null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Nodo<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Nodo<T> previous) {
        this.previous = previous;
    }
}