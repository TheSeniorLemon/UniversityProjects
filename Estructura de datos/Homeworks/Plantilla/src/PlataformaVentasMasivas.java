import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class PlataformaVentasMasivas {
    // ESTRUCTURA PRINCIPAL: LinkedList (por inserción rápida al inicio)
    private LinkedList<Producto> productos;

    public PlataformaVentasMasivas() {
        this.productos = new LinkedList<>();
    }

    // 1. BUSCAR POR CÓDIGO (MUY FRECUENTE)
    // Complejidad: O(n) - Búsqueda lineal
    public Producto buscarPorCodigo(String codigo) {
        long inicio = System.nanoTime();

        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                long fin = System.nanoTime();
                System.out.println("Tiempo búsqueda: " + (fin - inicio) + " ns");
                return p;
            }
        }

        long fin = System.nanoTime();
        System.out.println("Tiempo búsqueda: " + (fin - inicio) + " ns (no encontrado)");
        return null;
    }

    // 2. INSERTAR AL INICIO (MILES POR HORA)
    // Complejidad: O(1) - ¡CRÍTICO!
    public void insertarProducto(Producto producto) {
        productos.addFirst(producto);  // O(1) en LinkedList
    }

    // Insertar múltiples productos
    public void insertarProductosMasivos(List<Producto> nuevosProductos) {
        for (Producto p : nuevosProductos) {
            insertarProducto(p);
        }
    }

    // 3. ORDENAR POR PRECIO
    // Complejidad: O(n log n)
    // Estrategia: Convertir a ArrayList, ordenar, y volver a LinkedList
    public void ordenarPorPrecio() {
        long inicio = System.nanoTime();

        // Convertir a ArrayList para mejor rendimiento en ordenamiento
        ArrayList<Producto> listaTemporal = new ArrayList<>(productos);

        // Ordenar ArrayList
        listaTemporal.sort(Comparator.comparing(Producto::getPrecio));

        // Limpiar y reconstruir LinkedList
        productos.clear();
        productos.addAll(listaTemporal);

        long fin = System.nanoTime();
        System.out.println("Tiempo ordenamiento: " + (fin - inicio) / 1_000_000 + " ms");
    }

    // Obtener lista ordenada sin modificar la original
    public LinkedList<Producto> obtenerOrdenadosPorPrecio() {
        ArrayList<Producto> temporal = new ArrayList<>(productos);
        temporal.sort(Comparator.comparing(Producto::getPrecio));
        return new LinkedList<>(temporal);
    }

    // 4. FILTRAR POR CATEGORÍA
    // Complejidad: O(n)
    public LinkedList<Producto> filtrarPorCategoria(String categoria) {
        LinkedList<Producto> filtrados = new LinkedList<>();

        for (Producto p : productos) {
            if (p.getCategoria().equals(categoria)) {
                filtrados.add(p);
            }
        }

        return filtrados;
    }

    // Método auxiliar para obtener tamaño
    public int size() {
        return productos.size();
    }

    // Método para mostrar todos los productos
    public void mostrarProductos() {
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    // PRUEBAS DE RENDIMIENTO (FASE 4)
    public void probarRendimiento(int tamanio) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PRUEBA CON " + tamanio + " PRODUCTOS");
        System.out.println("=".repeat(60));

        long memoriaInicial = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long inicioTotal = System.currentTimeMillis();

        // Generar e insertar productos
        long inicioInsercion = System.nanoTime();
        for (int i = 0; i < tamanio; i++) {
            Producto p = new Producto(
                    "COD" + i,
                    "Producto " + i,
                    Math.random() * 10000,
                    "CAT" + (i % 10),
                    LocalDate.now()
            );
            insertarProducto(p);
        }
        long finInsercion = System.nanoTime();
        System.out.println("✓ Inserción de " + tamanio + " productos: " +
                (finInsercion - inicioInsercion) / 1_000_000 + " ms");

        // Probar búsqueda
        long inicioBusqueda = System.nanoTime();
        Producto encontrado = buscarPorCodigo("COD" + (tamanio / 2));
        long finBusqueda = System.nanoTime();
        System.out.println("✓ Búsqueda: " + (finBusqueda - inicioBusqueda) / 1_000_000 + " ms");

        // Probar ordenamiento
        long inicioOrden = System.nanoTime();
        ordenarPorPrecio();
        long finOrden = System.nanoTime();
        System.out.println("✓ Ordenamiento: " + (finOrden - inicioOrden) / 1_000_000 + " ms");

        // Probar filtrado
        long inicioFiltro = System.nanoTime();
        LinkedList<Producto> filtrados = filtrarPorCategoria("CAT0");
        long finFiltro = System.nanoTime();
        System.out.println("✓ Filtrado (CAT0): " + (finFiltro - inicioFiltro) / 1_000_000 +
                " ms - Encontrados: " + filtrados.size());

        long memoriaFinal = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long finTotal = System.currentTimeMillis();

        System.out.println("\n📊 RESUMEN:");
        System.out.println("   Tiempo total: " + (finTotal - inicioTotal) + " ms");
        System.out.println("   Memoria usada: " + (memoriaFinal - memoriaInicial) / 1024 + " KB");
        System.out.println("   Total productos: " + productos.size());
    }

    public static void main(String[] args) {
        PlataformaVentasMasivas plataforma = new PlataformaVentasMasivas();

        System.out.println("FASE 4 - IMPLEMENTACIÓN Y MEDICIÓN REAL");
        System.out.println("Estructura: LinkedList (con conversión a ArrayList para ordenar)");

        // Pruebas con diferentes tamaños
        int[] tamanios = {100, 1000, 10000, 100000};

        for (int tamanio : tamanios) {
            plataforma.probarRendimiento(tamanio);
            plataforma = new PlataformaVentasMasivas(); // Reset para siguiente prueba
        }

        // Ejemplo de uso
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EJEMPLO DE USO");
        System.out.println("=".repeat(60));

        PlataformaVentasMasivas demo = new PlataformaVentasMasivas();

        // Insertar productos
        demo.insertarProducto(new Producto("P001", "Laptop", 1500.00, "Electrónica", LocalDate.now()));
        demo.insertarProducto(new Producto("P002", "Mouse", 25.50, "Electrónica", LocalDate.now()));
        demo.insertarProducto(new Producto("P003", "Teclado", 45.00, "Electrónica", LocalDate.now()));
        demo.insertarProducto(new Producto("P004", "Silla", 120.00, "Muebles", LocalDate.now()));

        System.out.println("\nProductos insertados (al inicio):");
        demo.mostrarProductos();

        System.out.println("\nBuscar P002:");
        System.out.println(demo.buscarPorCodigo("P002"));

        System.out.println("\nOrdenar por precio:");
        demo.ordenarPorPrecio();
        demo.mostrarProductos();

        System.out.println("\nFiltrar por Electrónica:");
        LinkedList<Producto> electronicos = demo.filtrarPorCategoria("Electrónica");
        for (Producto p : electronicos) {
            System.out.println(p);
        }
    }
}