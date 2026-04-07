package Escenario1;

public class ColaTurnos<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tam;

    public ColaTurnos() {
        inicio = null;
        fin = null;
        tam = 0;
    }

    // Registrar nuevo cliente al final
    public void agregar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setProximo(nuevo);
            fin = nuevo;
        }
        tam++;
    }

    // Atender al primer cliente en espera
    public void eliminar() {
        if (inicio == null) {
            System.out.println("No hay clientes en espera.");
            return;
        }
        System.out.println("Atendiendo a: " + inicio.getValor());
        inicio = inicio.getProximo();
        if (inicio == null) {
            fin = null;
        }
        tam--;
    }

    // Consultar quien es el siguiente sin atenderlo
    public T buscar() {
        if (inicio == null) return null;
        return inicio.getValor();
    }

    // Mostrar todos los turnos actuales
    public void mostrar() {
        if (inicio == null) {
            System.out.println("No hay turnos en espera.");
            return;
        }
        System.out.print("Turnos en espera: ");
        Nodo<T> tempo = inicio;
        while (tempo != null) {
            System.out.print(tempo.getValor());
            if (tempo.getProximo() != null) System.out.print(" -> ");
            tempo = tempo.getProximo();
        }
        System.out.println(" | total: " + tam);
    }

    public boolean esVacia() {
        return inicio == null;
    }

    public int getTam() {
        return tam;
    }
}