import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Repositorio<Integer> repositorio = new Repositorio<>();

        // === PRUEBA: agregar() ===
        repositorio.agregar(12);
        repositorio.agregar(11);
        repositorio.agregar(13);
        repositorio.agregar(14);

        // === PRUEBA: recorrer() ===
        System.out.println("=== Método recorrer() ===");
        repositorio.recorrer();

        // === PRUEBA: obtener() ===
        System.out.println("\n=== Método obtener(2) ===");
        System.out.println("La búsqueda es: " + repositorio.obtener(2));  // Debería ser 13

        // === PRUEBA: iterator() NORMAL (for-each) ===
        System.out.println("\n=== Iterator normal (for-each) ===");
        for (Integer elem : repositorio) {
            System.out.println("Elemento: " + elem);
        }

        // === PRUEBA: iterator() NORMAL (manual) ===
        System.out.println("\n=== Iterator normal (manual) ===");
        Iterator<Integer> itNormal = repositorio.iterator();
        while (itNormal.hasNext()) {
            System.out.println("Elemento: " + itNormal.next());
        }

        // === PRUEBA: iteratorInverso() ===
        System.out.println("\n=== Iterator inverso ===");
        Iterator<Integer> itInverso = repositorio.iteratorInverso();
        while (itInverso.hasNext()) {
            System.out.println("Elemento: " + itInverso.next());
        }

        // === PRUEBA: Múltiples iterators independientes ===
        System.out.println("\n=== Dos iterators inversos al mismo tiempo ===");
        Iterator<Integer> it1 = repositorio.iteratorInverso();
        Iterator<Integer> it2 = repositorio.iteratorInverso();

        System.out.print("Iterator 1: ");
        while (it1.hasNext()) System.out.print(it1.next() + " ");

        System.out.print("\nIterator 2: ");
        while (it2.hasNext()) System.out.print(it2.next() + " ");
        System.out.println();
    }
}