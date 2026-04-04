import java.util.*;

public class ListaTareas {
    PriorityQueue<Tarea<String,Integer,Date>> tareas = new PriorityQueue<>(
            Comparator.comparingInt((Tarea<String, Integer, Date> t) -> t.getPrioridad())
                    .thenComparing(Tarea::getFechaVencimiento)
    );

    public static void main(String[] args){
        ListaTareas lista = new ListaTareas();
        lista.agregarTarea("Estudiar Java", 1, new Date(System.currentTimeMillis() + 86400000));
        lista.agregarTarea("Comprar comida", 5, new Date(System.currentTimeMillis() + 172800000));
        lista.agregarTarea("Pagar servicios", 10, new Date(System.currentTimeMillis() + 259200000));
        lista.agregarTarea("Tarea vencida", 3, new Date(System.currentTimeMillis() - 86400000));

        System.out.println("\n=== Todas las tareas por prioridad ===");
        lista.mostrarPorPrioridad();

        System.out.println("\n=== Tareas con prioridad 5 ===");
        lista.obtenerPorPrioridad(5);

        System.out.println("\n=== Todas las tareas por fecha ===");
        lista.mostrarPorFecha();

        System.out.println("\nCantidad de tareas: " + lista.getCantidadTareas());
    }

    public void agregarTarea(String descripcion, int prioridad, Date fechaVencimiento){
        if(prioridad < 0 || prioridad > 10){
            System.out.println("Error, la prioridad debe estar ennumerada entre 0 y 10");
            return;
        }
        if(fechaVencimiento == null || fechaVencimiento.before(new Date())){
            System.out.println("Error, la fecha de vencimiento no puede ser en el pasado.");
            return;
        }
        Tarea<String, Integer, Date> e = new Tarea<>(descripcion, prioridad, fechaVencimiento);
        tareas.offer(e);
        System.out.println("Tarea agregada exitosamente");
    }

    public void mostrarPorPrioridad(){
        PriorityQueue<Tarea<String,Integer,Date>> copia = new PriorityQueue<>(tareas);
        while(!copia.isEmpty()){
            System.out.println(copia.poll().toString());
        }
    }

    public void obtenerPorPrioridad(int prioridad){
        for(Tarea<String,Integer,Date> t : tareas){
            if(t.getPrioridad() == prioridad){
                System.out.println(t.toString());
            }
        }
    }

    public void mostrarPorFecha(){
        List<Tarea<String,Integer,Date>> lista = new ArrayList<>(tareas);
        lista.sort(Comparator.comparing(Tarea::getFechaVencimiento));
        lista.forEach(t -> System.out.println(t.toString()));
    }

    public PriorityQueue<Tarea<String,Integer,Date>> getTareas(){
        return tareas;
    }

    public void setTareas(PriorityQueue<Tarea<String,Integer,Date>> tareas){
        this.tareas = tareas;
    }

    public int getCantidadTareas(){
        return tareas.size();
    }
}