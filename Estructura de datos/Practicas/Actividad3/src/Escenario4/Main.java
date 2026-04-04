package Escenario4;
public class Main {

    public static void main(String[] args) {

        JuegoTurnos juego = new JuegoTurnos();

        // agregar jugadores
        juego.agregarJugador("Alice");
        juego.agregarJugador("Bob");
        juego.agregarJugador("Carlos");
        juego.agregarJugador("Diana");
        juego.print(); // >> [ Alice ] <-> [ Bob ] <-> [ Carlos ] <-> [ Diana ] (circular)

        // turno actual
        System.out.println("Turno actual: " + juego.turnoActual.getNombre()); // Alice
        System.out.println("Juega antes: "  + juego.quienJugaAntes());        // Diana
        System.out.println("Juega después: "+ juego.quienJuegaDespues());     // Bob

        // siguiente turno
        juego.siguienteTurno();
        juego.print(); // [ Alice ] <-> >> [ Bob ] <-> [ Carlos ] <-> [ Diana ] (circular)

        // siguiente turno
        juego.siguienteTurno();
        juego.print(); // [ Alice ] <-> [ Bob ] <-> >> [ Carlos ] <-> [ Diana ] (circular)

        // expulsar jugador en mitad de partida
        juego.expulsarJugador("Carlos");
        juego.print(); // [ Alice ] <-> [ Bob ] <-> >> [ Diana ] (circular)

        // turno anterior
        juego.turnoAnterior();
        juego.print(); // [ Alice ] <-> >> [ Bob ] <-> [ Diana ] (circular)

        // dar la vuelta completa
        juego.siguienteTurno();
        juego.siguienteTurno();
        juego.siguienteTurno();
        juego.print(); // vuelve al mismo jugador

        // expulsar hasta quedar uno
        juego.expulsarJugador("Alice");
        juego.expulsarJugador("Bob");
        juego.print(); // >> [ Diana ] (circular)

        juego.expulsarJugador("Diana");
        juego.print(); // No hay jugadores
    }
}