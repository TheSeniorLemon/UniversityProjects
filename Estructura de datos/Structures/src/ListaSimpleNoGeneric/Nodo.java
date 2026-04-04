package ListaSimpleNoGeneric;

public class Nodo {
    private int valor;
    private Nodo proximo;

    public Nodo(int valor) {
        this.valor = valor;
        proximo = null;
    }

    @Override
    public String toString() {
        return "Nodo {" +
                "valor = " + valor +
                ", proximo = " + proximo +
                '}';
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getProximo() {
        return proximo;
    }

    public void setProximo(Nodo proximo) {
        this.proximo = proximo;
    }
}
