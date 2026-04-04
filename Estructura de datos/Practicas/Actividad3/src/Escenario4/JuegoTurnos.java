package Escenario4;
public class JuegoTurnos {
    Jugador turnoActual;
    int size;

    public JuegoTurnos(){
        turnoActual = null;
        size = 0;
    }

    public void agregarJugador(String nombre){
        Jugador jugador = new Jugador(nombre);
        if(isEmpty()){
            jugador.setNext(jugador);
            jugador.setPrevious(jugador);
            turnoActual = jugador;
            size++;
            return;
        }
        Jugador last = turnoActual.getPrevious();
        jugador.setNext(turnoActual);
        jugador.setPrevious(last);
        last.setNext(jugador);
        turnoActual.setPrevious(jugador);
        size++;
    }

    public void siguienteTurno(){
        if(isEmpty()) return;
        turnoActual = turnoActual.getNext();
    }

    public void turnoAnterior(){
        if(isEmpty()) return;
        turnoActual = turnoActual.getPrevious();
    }

    public void expulsarJugador(String nombre){
        if(isEmpty()) return;
        if(size == 1){
            turnoActual = null;
            size--;
            return;
        }
        Jugador aux = turnoActual;
        do {
            if(aux.getNombre().equals(nombre)){
                aux.getPrevious().setNext(aux.getNext());
                aux.getNext().setPrevious(aux.getPrevious());
                if(aux == turnoActual) turnoActual = aux.getNext();
                size--;
                return;
            }
            aux = aux.getNext();
        } while(aux != turnoActual);
    }

    public String quienJugaAntes(){
        if(isEmpty()) return null;
        return turnoActual.getPrevious().getNombre();
    }

    public String quienJuegaDespues(){
        if(isEmpty()) return null;
        return turnoActual.getNext().getNombre();
    }

    public void print(){
        if(isEmpty()){ System.out.println("No hay jugadores"); return; }
        Jugador aux = turnoActual;
        do {
            if(aux == turnoActual) System.out.print(">> ");
            System.out.print("[ " + aux.getNombre() + " ]");
            if(aux.getNext() != turnoActual) System.out.print(" <-> ");
            aux = aux.getNext();
        } while(aux != turnoActual);
        System.out.println(" (circular)");
    }

    public boolean isEmpty(){ return turnoActual == null && size == 0; }
    public int size(){ return size; }
}