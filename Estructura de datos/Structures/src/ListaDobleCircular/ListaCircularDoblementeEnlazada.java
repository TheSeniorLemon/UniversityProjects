package ListaDobleCircular;

public class ListaCircularDoblementeEnlazada<T>{
    Nodo<T> doubleList;
    int size;

    public ListaCircularDoblementeEnlazada(){
        doubleList=null;
        size=0;
    }

    public void addFirst(T value){
        Nodo<T> nodo=new Nodo<>(value);
        if(isEmpty()){
            nodo.setNext(nodo);
            nodo.setPrevious(nodo);
            doubleList = nodo;
            size++;
            return;
        }

        Nodo<T> aux=doubleList();

        nodo.setNext(aux);
        nodo.setPrevious(aux.getPrevious());
        aux.getPrevious().setNext(nodo);
        aux.setPrevious(nodo);
        doubleList=nodo;
        size++;
    }

    public void addLast(T value){
        if(isEmpty()){ addFirst(value); return; }

        Nodo<T> nodo = new Nodo<>(value);
        Nodo<T> last = doubleList().getPrevious();

        nodo.setPrevious(last);
        nodo.setNext(doubleList());
        last.setNext(nodo);
        doubleList().setPrevious(nodo);
        size++;
    }

    public void addIndex(int index, T value){
        if(index<0 || index> size()-1){return;}
        if(index==0){addFirst(value); return;}
        if(index==(size()-1)){addLast(value); return;}

        Nodo<T> nodo= new Nodo<>(value);
        Nodo<T> aux=doubleList();
        int count=0;

        while(count<(index-1)){
            aux=aux.getNext();
            count++;
        }

        nodo.setPrevious(aux);
        nodo.setNext((aux.getNext()));
        aux.getNext().setPrevious(nodo);
        aux.setNext(nodo);
        size++;
    }

    public void removeFirst(){
        if(isEmpty()) return;
        if(size == 1){ doubleList = null; size--; return; }

        Nodo<T> last = doubleList().getPrevious();

        doubleList = doubleList().getNext();
        last.setNext(doubleList());
        doubleList().setPrevious(last);
        size--;
    }

    public void removeLast(){
        if(isEmpty()){return;}

        if(size==1){doubleList=null;return;}

        Nodo<T> last=doubleList().getPrevious();

        last.getPrevious().setNext(doubleList());
        doubleList().setPrevious(last.getPrevious());
        size--;
    }

    public void removeIndex(int index){
        if(index<0 || index>size()){return;}
        if(index==0){removeFirst();return;}
        if(index==(size()-1)){removeLast();return;}

        Nodo<T> aux= doubleList();
        int count=0;

        while((index-1)>count){
            aux = aux.getNext();
            count++;
        }

        aux.getNext().getNext().setPrevious(aux);
        aux.setNext(aux.getNext().getNext());
        size--;
    }

    public T getFirstValue() {if(isEmpty()){return null;}return doubleList().getValue();}

    public T getLastValue() {if(isEmpty()){return null;}return doubleList().getPrevious().getValue();}

    public T getValueIndex(int index) {
        if (index < 0 || index > (size() - 1)) return null;
        if (index == 0) return getFirstValue();
        if (index == (size() - 1)) return getLastValue();

        Nodo<T> aux = doubleList();
        int count = 0;

        while (index >= count) {
            aux = aux.getNext();
            count++;
        }
        return aux.getValue();
    }

    public ListaCircularDoblementeEnlazada<T> reverse(){
        if(isEmpty()){return null;}
        if(size()==1){return this;}

        ListaCircularDoblementeEnlazada<T> inverted=new ListaCircularDoblementeEnlazada<>();
        Nodo<T> actual= doubleList();

        do {
            inverted.addFirst(actual.getValue());
            actual = actual.getNext();
        } while(actual != doubleList());
        return inverted;
    }

    public int indexOf(T value) {
        if (isEmpty()) return -1;

        Nodo<T> aux = doubleList();
        int index = 0;

        do {
            if(aux.getValue().equals(value)) return index;

            aux = aux.getNext();
            index++;
        } while(aux != doubleList());
        return -1;
    }

    public void print(){
        if(isEmpty()){ System.out.println("The double list is empty"); return;}

        System.out.print("First => ");
        Nodo<T> aux= doubleList();
        do {
            System.out.print("[ " + aux.getValue() + " ]");
            if(aux.getNext() != doubleList()) System.out.print(" <-> ");
            aux = aux.getNext();
        } while(aux != doubleList());
        System.out.println("(Cicle) <= Last");
    }

    public boolean isEmpty(){return (doubleList()==null && size()==0);}

    public Nodo<T> doubleList(){return doubleList;}

    public int size(){return size;}

}
