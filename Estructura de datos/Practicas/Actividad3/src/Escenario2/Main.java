package Escenario2;
public class Main {
    public static void main(String[] args) {

        BufferNavegacion buffer = new BufferNavegacion();

        // visitar páginas
        buffer.visitarNuevaPagina("google.com");
        buffer.visitarNuevaPagina("youtube.com");
        buffer.visitarNuevaPagina("github.com");
        buffer.print(); // [ google.com ] <-> [ youtube.com ] <-> >> [ github.com ]

        // retroceder
        buffer.visitarAnteriorPagina();
        buffer.print(); // [ google.com ] <-> >> [ youtube.com ] <-> [ github.com ]

        // retroceder de nuevo
        buffer.visitarAnteriorPagina();
        buffer.print(); // >> [ google.com ] <-> [ youtube.com ] <-> [ github.com ]

        // avanzar
        buffer.visitarSiguientePagina();
        buffer.print(); // [ google.com ] <-> >> [ youtube.com ] <-> [ github.com ]

        // visitar nueva página desde punto intermedio — elimina github.com
        buffer.visitarNuevaPagina("stackoverflow.com");
        buffer.print(); // [ google.com ] <-> [ youtube.com ] <-> >> [ stackoverflow.com ]

        // página actual
        System.out.println("Página actual: " + buffer.getPaginaActual()); // stackoverflow.com

        // intentar avanzar sin páginas futuras
        buffer.visitarSiguientePagina();
        buffer.print(); // no cambia

        // intentar retroceder al inicio
        buffer.visitarAnteriorPagina();
        buffer.visitarAnteriorPagina();
        buffer.visitarAnteriorPagina(); // no pasa nada, ya está en el inicio
        buffer.print();
    }
}