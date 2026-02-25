import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class RegistroVentasDiarias {
    LinkedHashMap<String,Venta> ventasLHM = new LinkedHashMap<>();
    HashMap<String,Venta> ventasHM = new HashMap<>();
    TreeMap<String,Venta> ventasTM = new TreeMap<>();

    // =================================================================================================================
    // REQUISITO 1: Registrar ventas usando el código del producto como identificador
    // =================================================================================================================
    public void registroVentasDiarias(Venta venta) {
        ventasLHM.put(venta.getCode(), venta);
        ventasHM.put(venta.getCode(), venta);
        ventasTM.put(venta.getCode(), venta);
    }

    // =================================================================================================================
    // REQUISITO 2: Consultar la información de un producto por su código
    // =================================================================================================================
    public Venta consultarProductoCodigoHM(String codigo) {
        return ventasHM.get(codigo);
    }

    public Venta consultarProductoCodigoLHM(String codigo) {
        return ventasLHM.get(codigo);
    }

    public Venta consultarProductoCodigoTM(String codigo) {
        return ventasTM.get(codigo);
    }

    // =================================================================================================================
    // REQUISITO 3: Mostrar todas las ventas registradas
    // =================================================================================================================
    public void mostrarTodasLasVentas() {
        System.out.println("\n=== TODAS LAS VENTAS REGISTRADAS (HashMap) ===");
        System.out.println("Total de ventas: " + ventasHM.size());
        System.out.println("--------------------------------------------------");

        for(Map.Entry<String, Venta> entry : ventasHM.entrySet()) {
            Venta venta = entry.getValue();
            System.out.println("Código: " + venta.getCode());
            System.out.println("  Producto: " + venta.getNombreProducto());
            System.out.println("  Cantidad: " + venta.getCantidad());
            System.out.println("  Valor Total: $" + venta.getValorTotal());
            System.out.println("--------------------------------------------------");
        }
    }

    // =================================================================================================================
    // REQUISITO 4: Mostrar las ventas ordenadas por código de producto (TreeMap)
    // =================================================================================================================
    public void mostrarVentasOrdenadasPorCodigo() {
        System.out.println("\n=== VENTAS ORDENADAS POR CÓDIGO (TreeMap) ===");
        System.out.println("--------------------------------------------------");

        for(Map.Entry<String, Venta> entry : ventasTM.entrySet()) {
            Venta venta = entry.getValue();
            System.out.println("Código: " + venta.getCode());
            System.out.println("  Producto: " + venta.getNombreProducto());
            System.out.println("  Cantidad: " + venta.getCantidad());
            System.out.println("  Valor Total: $" + venta.getValorTotal());
            System.out.println("--------------------------------------------------");
        }
    }

    // =================================================================================================================
    // REQUISITO 5: Mostrar las ventas en el orden exacto en que fueron registradas (LinkedHashMap)
    // =================================================================================================================
    public void mostrarVentasOrdenRegistro() {
        System.out.println("\n=== VENTAS EN ORDEN DE REGISTRO (LinkedHashMap) ===");
        System.out.println("--------------------------------------------------");

        int numeroRegistro = 1;
        for(Map.Entry<String, Venta> entry : ventasLHM.entrySet()) {
            Venta venta = entry.getValue();
            System.out.println("Registro #" + numeroRegistro++);
            System.out.println("  Código: " + venta.getCode());
            System.out.println("  Producto: " + venta.getNombreProducto());
            System.out.println("  Cantidad: " + venta.getCantidad());
            System.out.println("  Valor Total: $" + venta.getValorTotal());

            System.out.println("--------------------------------------------------");
        }
    }

    // =================================================================================================================
    // Métodos adicionales útiles
    // =================================================================================================================
    public void compararComportamiento(){
        System.out.println("\n" + "=".repeat(80));
        System.out.println("COMPARACIÓN DE COMPORTAMIENTO DE LOS 3 MAPAS");
        System.out.println("=".repeat(80));

        System.out.println("\n1. HASHMAP - No mantiene ningún orden");
        System.out.println("-".repeat(60));
        System.out.println("   Keys: " + ventasHM.keySet());

        System.out.println("\n2. LINKEDHASHMAP - Mantiene orden de inserción");
        System.out.println("-".repeat(60));
        System.out.println("   Keys: " + ventasLHM.keySet());

        System.out.println("\n3. TREEMAP - Ordena automáticamente por clave (ascendente)");
        System.out.println("-".repeat(60));
        System.out.println("   Keys: " + ventasTM.keySet());
    }

    public double calcularTotalVentas(){
        double total = 0;
        for(Venta venta : ventasHM.values()){
            total += venta.getValorTotal();
        }
        return total;
    }
}