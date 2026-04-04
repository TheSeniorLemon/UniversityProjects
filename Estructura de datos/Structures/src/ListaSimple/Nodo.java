package ListaSimple;

public class Nodo <T>{
    T value;
    Nodo<T> tl;

    public Nodo(T value){
        this.value=value;
        tl=null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Nodo<T> getTl() {
        return tl;
    }

    public void setTl(Nodo<T> tl) {
        this.tl = tl;
    }
}
