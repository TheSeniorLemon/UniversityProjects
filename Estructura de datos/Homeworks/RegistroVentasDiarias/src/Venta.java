public class Venta {
    String codeProducto, nombreProducto;
    int cantidad;
    double valorTotal;

    public Venta(String nombreProducto, int cantidad, double valorTotal) {
        codeProducto= Math.random()*100+"";
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.valorTotal = valorTotal;
    }

    public String getCode() {
        return codeProducto;
    }
    public void setCode(String code) {
        this.codeProducto = code;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Venta= \nCódigo de venta: " + this.codeProducto + " \nCantidad: " + this.cantidad + " \nNombre del producto: " + nombreProducto + " \nValor total: " + valorTotal;
    }
}
