package Escenario1;

public class Main {
    public static void main(String[] args) {
        ColaTurnos<String> cola = new ColaTurnos<>();

        System.out.println("=== SISTEMA DE TURNOS - PANADERIA ===\n");

        // Llegan clientes
        cola.agregar("Cliente 1 - Juan");
        cola.agregar("Cliente 2 - Maria");
        cola.agregar("Cliente 3 - Carlos");
        cola.agregar("Cliente 4 - Ana");
        cola.mostrar();

        System.out.println();

        // Consultar siguiente
        System.out.println("Siguiente en ser atendido: " + cola.buscar());
        System.out.println();

        // Atender clientes
        cola.eliminar();
        cola.mostrar();
        System.out.println();

        cola.eliminar();
        cola.mostrar();
        System.out.println();

        // Llega un nuevo cliente mientras se atiende
        cola.agregar("Cliente 5 - Luis");
        System.out.println("Nuevo cliente registrado.");
        cola.mostrar();
        System.out.println();

        // Atender el resto
        while (!cola.esVacia()) {
            cola.eliminar();
        }

        System.out.println();
        cola.mostrar(); // sin clientes
        cola.eliminar(); // no debe explotar
    }
}