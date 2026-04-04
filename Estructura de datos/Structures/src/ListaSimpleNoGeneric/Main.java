package ListaSimpleNoGeneric;

public class Main {
    public static void main(String[] args) {
        ListaSimplementeEnlazada lista = new ListaSimplementeEnlazada();
        lista.insertar(10);
        lista.insertar(20);
        lista.insertar(30);

        System.out.println(lista);

    }
}