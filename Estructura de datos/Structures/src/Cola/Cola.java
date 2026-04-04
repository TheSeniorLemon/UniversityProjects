package Cola;

public class Cola <T>{

    Nodo<T> queue;
    int size;

    public Cola(){
        queue= null;
        size=0;
    }

    public void addLast(T value){
        Nodo<T> node = new Nodo<>(value);
        if(isEmpty()){ queue = node; size++; return; }
        Nodo<T> aux = queue;
        while(aux.getPrevious() != null){
            aux = aux.getPrevious();
        }
        aux.setPrevious(node);
        size++;
    }

    public void removeFirst(){
        if(isEmpty()) return;

        if(size==1){queue=null; size--; return;}

        queue=queue.getPrevious();
        size--;
    }

    public T peek(){
        if(isEmpty()) return null;
        return queue.getValue();
    }

    public boolean contains(T value){
        if(isEmpty()){return false;}

        Nodo<T> aux=queue;
        while(aux.getPrevious()!=null){ if(aux.getValue().equals(value)) return true; aux=aux.getPrevious();}
        return false;
    }

    public Cola<T> reverse(){
        if(isEmpty())return null;
        if(size==1) { Cola<T> aux = new Cola<T>(); aux.addLast(queue.getValue()); return aux;};

        Cola<T> inverted=new Cola<T>();
        Nodo<T> actual = queue;

        while(actual != null){
            inverted.addLast(actual.getValue());
            actual=actual.getPrevious();
        }
    return inverted;
    }

    public void print(){
        if(isEmpty()){ System.out.println("The queue is empty"); return; }

        System.out.print("First => ");
        Nodo<T> aux = queue;
        while(aux != null){
            System.out.print("[ " + aux.getValue() + " ]");
            if(aux.getPrevious() != null) System.out.print(" -> ");
            aux = aux.getPrevious();
        }
        System.out.println(" <= Last");
    }

    public boolean isEmpty(){return (queue==null && size==0);}

    public int size(){return size;}
}
