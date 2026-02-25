import java.time.LocalDate;

class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private String categoria;
    private LocalDate fechaIngreso;

    public Producto(String codigo, String nombre, double precio,
                    String categoria, LocalDate fechaIngreso) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaIngreso = fechaIngreso;
    }

    public String getCodigo() { return codigo; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " - $" + String.format("%.2f", precio);
    }
}