package Cola;
public class Main {
    public static void main(String[] args) {

        // ===== COLA =====
        System.out.println("===== COLA =====");
        Cola<Integer> cola = new Cola<Integer>();
        cola.addLast(1);
        cola.addLast(2);
        cola.addLast(3);
        cola.addLast(4);
        cola.print();

        System.out.println("Peek: " + cola.peek());
        System.out.println("Contains 3: " + cola.contains(3));
        System.out.println("Contains 9: " + cola.contains(9));
        cola.removeFirst();
        cola.print();

        Cola<Integer> invertida = cola.reverse();
        invertida.print();

        System.out.println("Size: " + cola.size());
        cola.removeFirst();
        cola.removeFirst();
        cola.removeFirst();
        cola.print();

        // ===== BICOLA =====
        System.out.println("\n===== BICOLA =====");
        BiCola<Integer> bicola = new BiCola<Integer>();
        bicola.addLast(2);
        bicola.addLast(3);
        bicola.addLast(4);
        bicola.print();

        // addFirst - agregar al frente
        bicola.addFirst(1);
        bicola.print();

        // removeLast - eliminar por el fondo
        bicola.removeLast();
        bicola.print();

        // removeFirst - heredado de Cola
        bicola.removeFirst();
        bicola.print();

        // metodos heredados de Cola
        System.out.println("Peek: " + bicola.peek());
        System.out.println("Contains 2: " + bicola.contains(2));
        System.out.println("Size: " + bicola.size());
    }
}