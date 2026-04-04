package Escenario4;
public class Jugador {
    String nombre;
    Jugador next;
    Jugador previous;

    public Jugador(String nombre){
        this.nombre = nombre;
        next = null;
        previous = null;
    }

    public String getNombre(){ return nombre; }
    public Jugador getNext(){ return next; }
    public Jugador getPrevious(){ return previous; }
    public void setNext(Jugador next){ this.next = next; }
    public void setPrevious(Jugador previous){ this.previous = previous; }
}