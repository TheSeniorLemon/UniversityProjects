package Escenario3;

public class ListaReproduccion<T> {
    private Nodo<T> inicio;
    private Nodo<T> ultimo;
    private Nodo<T> actual;
    private int tam;

    public ListaReproduccion() {
        inicio = null;
        ultimo = null;
        actual = null;
        tam = 0;
    }

    // Agregar cancion al final de la lista
    public void agregar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);

        if (inicio == null) {
            inicio = nuevo;
            ultimo = nuevo;
            nuevo.setProximo(inicio);
            actual = inicio;
        } else {
            ultimo.setProximo(nuevo);
            nuevo.setProximo(inicio); // cierre circular
            ultimo = nuevo;
        }
        tam++;
    }

    // Avanzar a la siguiente cancion — nunca llega a null
    public void avanzar() {
        if (actual == null) {
            System.out.println("La lista esta vacia.");
            return;
        }
        actual = actual.getProximo();
        System.out.println("Reproduciendo: " + actual.getValor());
    }

    // Mostrar cancion actual
    public T buscar() {
        if (actual == null) return null;
        return actual.getValor();
    }

    // Eliminar cancion por valor
    public void eliminar(T valor) {
        if (inicio == null) {
            System.out.println("La lista esta vacia.");
            return;
        }

        // Caso: unica cancion
        if (tam == 1) {
            if (inicio.getValor().equals(valor)) {
                inicio = null;
                ultimo = null;
                actual = null;
                tam--;
                System.out.println("Eliminada: " + valor);
            } else {
                System.out.println("Cancion no encontrada: " + valor);
            }
            return;
        }

        Nodo<T> tempo = inicio;
        Nodo<T> anterior = ultimo; // anterior al inicio en lista circular

        do {
            if (tempo.getValor().equals(valor)) {
                // Si se elimina la cancion actual, avanzar primero
                if (tempo == actual) {
                    actual = tempo.getProximo();
                }
                anterior.setProximo(tempo.getProximo());

                // Actualizar inicio o ultimo si corresponde
                if (tempo == inicio) {
                    inicio = tempo.getProximo();
                }
                if (tempo == ultimo) {
                    ultimo = anterior;
                }
                tam--;
                System.out.println("Eliminada: " + valor);
                return;
            }
            anterior = tempo;
            tempo = tempo.getProximo();
        } while (tempo != inicio);

        System.out.println("Cancion no encontrada: " + valor);
    }

    // Mostrar toda la secuencia marcando la cancion actual
    public void mostrar() {
        if (inicio == null) {
            System.out.println("Lista de reproduccion vacia.");
            return;
        }

        System.out.print("Lista: ");
        Nodo<T> tempo = inicio;
        do {
            if (tempo == actual) {
                System.out.print("[" + tempo.getValor() + "]");
            } else {
                System.out.print(tempo.getValor());
            }
            tempo = tempo.getProximo();
            if (tempo != inicio) System.out.print(" -> ");
        } while (tempo != inicio);
        System.out.println(" -> (circular) | tam=" + tam);
    }

    public boolean esVacia() {
        return inicio == null;
    }

    public int getTam() {
        return tam;
    }
}