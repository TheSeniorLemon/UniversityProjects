package Escenario2;
public class BufferNavegacion {
    PaginaWeb<String> paginaActual;
    int bufferSize;

    public BufferNavegacion(){
        paginaActual = null;
        bufferSize = 0;
    }

    public void visitarNuevaPagina(String url){
        PaginaWeb<String> nuevaPagina = new PaginaWeb<>(url);
        if(isEmpty()){
            paginaActual = nuevaPagina;
            bufferSize++;
            return;
        }
        // eliminar páginas futuras
        paginaActual().setNext(null);
        // enlazar nueva página
        nuevaPagina.setPrevious(paginaActual());
        paginaActual().setNext(nuevaPagina);
        paginaActual = nuevaPagina;
        bufferSize++;
    }

    public void visitarAnteriorPagina(){
        if(isEmpty() || paginaActual().getPrevious() == null) return;
        paginaActual = paginaActual().getPrevious();
    }

    public void visitarSiguientePagina(){
        if(isEmpty() || paginaActual().getNext() == null) return;
        paginaActual = paginaActual().getNext();
    }

    public String getPaginaActual(){
        if(isEmpty()) return null;
        return paginaActual().getUrl();
    }

    public void print(){
        if(isEmpty()){ System.out.println("Historial vacío"); return; }
        // ir al inicio del historial
        PaginaWeb<String> aux = paginaActual();
        while(aux.getPrevious() != null){ aux = aux.getPrevious(); }
        // imprimir desde el inicio
        while(aux != null){
            if(aux == paginaActual()) System.out.print(">> ");
            System.out.print("[ " + aux.getUrl() + " ]");
            if(aux.getNext() != null) System.out.print(" <-> ");
            aux = aux.getNext();
        }
        System.out.println();
    }

    public boolean isEmpty(){ return (paginaActual == null && bufferSize == 0); }
    public PaginaWeb<String> paginaActual(){ return paginaActual; }
    public int size(){ return bufferSize; }
}