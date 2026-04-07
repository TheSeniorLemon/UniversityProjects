package Escenario1;

public class Nodo<T> {
    private final T valor;
    private Nodo<T> proximo;

    public Nodo(T valor) {
        this.valor = valor;
        this.proximo = null;
    }

    public T getValor() { return valor; }
    public Nodo<T> getProximo() { return proximo; }
    public void setProximo(Nodo<T> proximo) { this.proximo = proximo; }

    @Override
    public String toString() {
        return "Escenario1.Escenario3.Nodo{ valor=" + valor + " }";
    }
}