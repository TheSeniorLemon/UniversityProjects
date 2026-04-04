package ListaSimple;

public class Main {
    public static void main(String[] args) {
        ListaSimplementeEnlazada<Integer> lista = new ListaSimplementeEnlazada<>();

        lista.addFirst(1);
        lista.addLast(2);
        lista.addLast(3);
        lista.addIndex(1, 99);  // 1 → 99 → 2 → 3
        lista.print();

        System.out.println(lista.getIndexValue(0)); // 1
        System.out.println(lista.getIndexValue(1)); // 99
        System.out.println(lista.indexOf(3));        // 3

        ListaSimplementeEnlazada<Integer> invertida = lista.reverse();
        System.out.println(invertida.getFirstValue()); // 3

        lista.removeFirst();
        lista.removeLast();
        lista.removeIndex(1);

        lista.print();
    }
}

