public class Tarea<T , S, U> {

    T descripcion;
    S prioridad;
    U fechaVencimiento;

    public Tarea(T descripcion, S prioridad, U fechaVencimiento) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaVencimiento = fechaVencimiento;
    }

    public T getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(T descripcion) {
        this.descripcion = descripcion;
    }

    public S getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(S prioridad) {
        this.prioridad = prioridad;
    }

    public U getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(U fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "descripcion=" + descripcion +
                ", prioridad=" + prioridad +
                ", fechaVencimiento=" + fechaVencimiento +
                '}';
    }
}
