package ListaSimpleNoGeneric;

public class ListaSimplementeEnlazada {
    private Nodo inicial;
    private int tam;

    public ListaSimplementeEnlazada() {
        inicial = null;
        tam = 0;
    }

    public boolean insertar(int valor) {
        Nodo nuevo = new Nodo(valor);

        if(inicial == null && tam == 0) {
            inicial = nuevo;
            tam++;
            return true;
        }
            Nodo tempo = inicial;
            while(tempo.getProximo() != null) {
                tempo = tempo.getProximo();
            }
            tempo.setProximo(nuevo);
            tam++;
            return true;

    }

    @Override
    public String toString() {
        return "ListaSimplementeEnlazada{" +
                "inicial = " + inicial +
                '}';
    }

    public Nodo getInicial() {
        return inicial;
    }

    public void setInicial(Nodo inicial) {
        this.inicial = inicial;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }
}
