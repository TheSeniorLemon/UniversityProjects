package Escenario2;

public class PaginaWeb<T> {

    T value;
    PaginaWeb<T> next;
    PaginaWeb<T> previous;

    public PaginaWeb(T value){
        this.value=value;
        next=null;
        previous=null;
    }

    public T getUrl() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public PaginaWeb<T> getNext() {
        return next;
    }

    public void setNext(PaginaWeb<T> next) {
        this.next = next;
    }

    public PaginaWeb<T> getPrevious() {
        return previous;
    }

    public void setPrevious(PaginaWeb<T> previous) {
        this.previous = previous;
    }
}
