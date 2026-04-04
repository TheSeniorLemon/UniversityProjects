package Cola;

public class BiCola <T> extends Cola<T> {

    public BiCola(){
        super();
    }

    public void addFirst(T value){
        Nodo<T> node= new Nodo<>(value);
        node.setPrevious(queue);
        queue=node;
        size++;
    }

    public void removeLast(){
        if(isEmpty()) return;

        Nodo<T> aux=queue;
        while(aux.getPrevious().getPrevious()!= null){
            aux=aux.getPrevious();
        }
        aux.setPrevious(null);
        size--;
    }

}
