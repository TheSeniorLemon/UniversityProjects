package Escenario3;

public class Main {
    public static void main(String[] args) {
        ListaReproduccion<String> lista = new ListaReproduccion<>();

        System.out.println("=== LISTA DE REPRODUCCION CONTINUA ===\n");

        // Agregar canciones
        lista.agregar("Cancion A");
        lista.agregar("Cancion B");
        lista.agregar("Cancion C");
        lista.agregar("Cancion D");
        lista.mostrar();
        System.out.println("Reproduciendo ahora: " + lista.buscar());
        System.out.println();

        // Avanzar por las canciones
        lista.avanzar();
        lista.avanzar();
        lista.mostrar();
        System.out.println();

        // Llegar al final y volver al inicio automaticamente
        lista.avanzar();
        lista.avanzar(); // vuelve a Cancion A
        lista.mostrar();
        System.out.println();

        // Eliminar una cancion que no es la actual
        lista.eliminar("Cancion B");
        lista.mostrar();
        System.out.println();

        // Eliminar la cancion actual — debe avanzar a la siguiente
        System.out.println("Actual antes de eliminar: " + lista.buscar());
        lista.eliminar("Cancion A");
        System.out.println("Actual despues de eliminar: " + lista.buscar());
        lista.mostrar();
        System.out.println();

        // Intentar eliminar cancion inexistente
        lista.eliminar("Cancion Z");
        System.out.println();

        // Vaciar lista
        lista.eliminar("Cancion C");
        lista.eliminar("Cancion D");
        lista.mostrar();
        lista.avanzar(); // no debe explotar
    }
}