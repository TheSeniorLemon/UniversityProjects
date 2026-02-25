public class Main {
    public static void main(String[] args) {
        RegistroVentasDiarias registro = new RegistroVentasDiarias();

        // Registrar ventas en orden específico
        System.out.println("=== REGISTRANDO VENTAS ===\n");
        registro.registroVentasDiarias(new Venta( "Laptop", 2, 1500.00));
        registro.registroVentasDiarias(new Venta( "Mouse", 5, 25.50));
        registro.registroVentasDiarias(new Venta( "Teclado", 3, 45.00));
        registro.registroVentasDiarias(new Venta( "Monitor", 1, 300.00));
        registro.registroVentasDiarias(new Venta( "USB", 10, 10.00));

        // REQUISITO 2: Consultar producto
        System.out.println("\n=== CONSULTAR PRODUCTO POR CÓDIGO ===");
        Venta consultada = registro.consultarProductoCodigoHM("PROD-002");
        if (consultada != null) {
            System.out.println("Producto encontrado: " + consultada);
        }

        // REQUISITO 3: Mostrar todas las ventas
        registro.mostrarTodasLasVentas();

        // REQUISITO 4: Mostrar ordenadas por código
        registro.mostrarVentasOrdenadasPorCodigo();

        // REQUISITO 5: Mostrar en orden de registro
        registro.mostrarVentasOrdenRegistro();

        // Comparar comportamiento
        registro.compararComportamiento();

        // Total de ventas
        System.out.println("\n💰 TOTAL DE VENTAS: $" + registro.calcularTotalVentas());
    }
}