package ListaDobleCircular;

public class Main {

    public static void main(String[] args) {

        ListaCircularDoblementeEnlazada<Integer> lista = new ListaCircularDoblementeEnlazada<>();

        // addFirst y addLast
        lista.addFirst(3);
        lista.addFirst(2);
        lista.addFirst(1);
        lista.addLast(4);
        lista.addLast(5);
        lista.print(); // First => [ 1 ] <-> [ 2 ] <-> [ 3 ] <-> [ 4 ] <-> [ 5 ] <= Last

        // addIndex
        lista.addIndex(2, 99);
        lista.print(); // First => [ 1 ] <-> [ 2 ] <-> [ 99 ] <-> [ 3 ] <-> [ 4 ] <-> [ 5 ] <= Last

        // getFirstValue y getLastValue
        System.out.println("First: " + lista.getFirstValue()); // 1
        System.out.println("Last: "  + lista.getLastValue());  // 5

        // getValueIndex
        System.out.println("Index 2: " + lista.getValueIndex(2)); // 99

        // indexOf
        System.out.println("indexOf 99: " + lista.indexOf(99)); // 2
        System.out.println("indexOf 10: " + lista.indexOf(10)); // -1

        // removeFirst
        lista.removeFirst();
        lista.print(); // First => [ 2 ] <-> [ 99 ] <-> [ 3 ] <-> [ 4 ] <-> [ 5 ] <= Last

        // removeLast
        lista.removeLast();
        lista.print(); // First => [ 2 ] <-> [ 99 ] <-> [ 3 ] <-> [ 4 ] <= Last

        // removeIndex
        lista.removeIndex(1);
        lista.print(); // First => [ 2 ] <-> [ 3 ] <-> [ 4 ] <= Last

        // reverse
        ListaCircularDoblementeEnlazada<Integer> invertida = lista.reverse();
        invertida.print(); // First => [ 4 ] <-> [ 3 ] <-> [ 2 ] <= Last

        // size
        System.out.println("Size: " + lista.size()); // 3

        // vaciar
        lista.removeFirst();
        lista.removeFirst();
        lista.removeFirst();
        lista.print(); // The list is empty
    }

}
