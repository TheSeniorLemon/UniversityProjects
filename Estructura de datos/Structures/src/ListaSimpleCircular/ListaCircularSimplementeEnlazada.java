package ListaSimpleCircular;

public class ListaCircularSimplementeEnlazada<T>{
    Nodo<T> list;
    int size;

    public ListaCircularSimplementeEnlazada(){
        list=null;
        size=0;
    }

    public void addFirst(T value){
        Nodo<T> node= new Nodo<>(value);

        if(isEmpty()){
            node.setNext(node);
            list=node;
            size++;
            return;
        }
        Nodo<T> aux=list();

        while(aux.getNext() !=list()){
            aux=aux.getNext();
        }
        aux.setNext(node);
        node.setNext(list());
        list = node;
        size++;
    }

    public void addLast(T value){
        if(isEmpty()){addFirst(value); return;}

        Nodo<T> node= new Nodo<>(value);
        Nodo<T> aux= list();

        while(aux.getNext() != list()){
            aux=aux.getNext();
        }
        aux.setNext(node);
        node.setNext(list());
        size++;
    }

    public void addIndex(int index, T value){
        if(index<0 || index > (size()-1)){return;}
        if(index==0){addFirst(value);return;}
        if(index==(size()-1)){addLast(value);return;}

        Nodo<T> node=new Nodo<>(value);
        Nodo<T> aux=list();
        int count=0;

        while(count<(index-1)){
            aux=aux.getNext();
            count++;
        }

        node.setNext(aux.getNext().getNext());
        aux.setNext(node);
        size++;
    }

    public void removeFirst(){
        Nodo<T> aux=list();
        while(aux.getNext()!=list()){
            aux=aux.getNext();
        }
        aux.setNext(list().getNext());
        list=list().getNext();
        size--;
    }

    public void removeLast(){
        if(isEmpty()) return;
        if(size()==1){list().setNext(null);size--;return;}

        Nodo<T> aux=list();

        while(aux.getNext().getNext()!=list()){
            aux=aux.getNext();
        }

        aux.getNext().setNext(null);
        aux.setNext(list());
        size--;
    }

    public void removeIndex(int index){
        if(index < 0 || index > (size()-1)) return;
        if(index == 0){removeFirst();return;}
        if(index==size()-1){removeLast();return;}

        Nodo<T> aux=list();
        int count=0;

        while((index-1)>count){
            aux=aux.getNext();
            count++;
        }
        aux.setNext(aux.getNext().getNext());
        size--;
    }

    public T getFirstValue(){
        if(isEmpty()) return null;

        return list().getValue();
    }

    public T getLastValue(){
        if(isEmpty()) return null;

        if(size()==1) return getFirstValue();

        Nodo<T> aux=list();

        while(aux.getNext()!=list()){
            aux= aux.getNext();
        }
        return aux.getValue();
    }

    public T getValueIndex(int index){
        if(index<0 || index>size()){return null;}
        if(index==0){return getFirstValue();}
        if(index==size()-1){return getLastValue();}

        Nodo<T> aux=list();
        int count=0;

        while(count<=index){
            aux = aux.getNext();
            count++;
        }
        return aux.getValue();
    }

    public ListaCircularSimplementeEnlazada<T> reverse(){
        if(isEmpty()) return null;

        if(size() == 1){
            ListaCircularSimplementeEnlazada<T> aux = new ListaCircularSimplementeEnlazada<>();
            aux.addFirst(list().getValue());
            return aux;
        }

        ListaCircularSimplementeEnlazada<T> inverted = new ListaCircularSimplementeEnlazada<>();
        Nodo<T> actual = list();

        do {
            inverted.addFirst(actual.getValue());
            actual = actual.getNext();
        } while(actual != list());

        return inverted;
    }

    public int indexOf(T value){
        if(isEmpty()) return -1;

        Nodo<T> aux = list();
        int index = 0;

        do {
            if(aux.getValue().equals(value)) return index;
            aux = aux.getNext();
            index++;
        } while(aux != list());
        return -1;
    }

    public void print(){
        if(isEmpty()){ System.out.println("The list is empty"); return; }

        System.out.print("First => ");
        Nodo<T> aux = list();

        do {
            System.out.print("[ " + aux.getValue() + " ]");
            if(aux.getNext() != list()) System.out.print(" -> ");
            aux = aux.getNext();
        } while(aux != list());

        System.out.println("(Cicle) <= Last");
    }

    public boolean isEmpty(){
        return size()==0 && list()==null;
    }

    public int size() {return size;}

    public Nodo<T> list(){return list;}

}