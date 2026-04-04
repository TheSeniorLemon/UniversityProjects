package ListaSimple;

public class ListaSimplementeEnlazada<T> {
    Nodo<T> list;
    int size;

    public ListaSimplementeEnlazada() {
        list = null;
        size = 0;
    }

    public void addFirst(T value) {
        Nodo<T> node = new Nodo<>(value);
        node.setTl(list);
        list = node;
        size++;
    }

    public void addLast(T value) {
        if (isEmpty()) {
            addFirst(value);
            return;
        }
        Nodo<T> node = new Nodo<>(value);

        Nodo<T> aux = list;
        while (aux.getTl() != null) {
            aux = aux.getTl();
        }
        aux.setTl(node);
        size++;
    }

    public void addIndex(int index, T value) {
        if (index < 0 || index > (size - 1)) return;
        if (index == 0) {
            addFirst(value);
            return;
        }
        if (index == (size - 1)) {
            addLast(value);
            return;
        }

        Nodo<T> nodo = new Nodo<>(value);
        Nodo<T> aux = list;

        int count = 0;
        while ((index-1) > count) {
            aux = aux.getTl();
            count++;
        }
        nodo.setTl(aux.getTl());
        aux.setTl(nodo);
        size++;
    }

    public void removeFirst() {
        if (isEmpty()) return;

        list = list.getTl();
        size--;
    }

    public void removeLast() {
        if (isEmpty()) return;

        if (size == 1) {
            list = null;
            size--;
            return;
        }

        Nodo<T> aux = list;
        while (aux.getTl().getTl() != null) {
            aux = aux.getTl();
        }
        aux.setTl(null);
        size--;
    }

    public void removeIndex(int index) {
        if (index < 0 || index > (size - 1)) return;
        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == (size - 1)) {
            removeLast();
            return;
        }

        Nodo<T> aux = list;
        int count = 0;

        while ((index - 1) > count) {
            aux = aux.getTl();
            count++;
        }
        aux.setTl(aux.getTl().getTl());
        size--;
    }

    public T getFirstValue() {
        return list.getValue();
    }

    public T getLastValue() {
        Nodo<T> aux = list;
        while (aux.getTl() != null) {
            aux = aux.getTl();
        }
        return aux.getValue();
    }

    public T getIndexValue(int index) {
        if (index < 0 || index > (size - 1)) return null;
        if (index == 0) return getFirstValue();
        if (index == (size - 1)) return getLastValue();

        Nodo<T> aux = list;
        int count = 0;

        while (index > count) {
            aux = aux.getTl();
            count++;
        }
        return aux.getValue();
    }

    public ListaSimplementeEnlazada<T> reverse(){
        if(isEmpty()) return null;
        if(size == 1) return this;

        ListaSimplementeEnlazada<T> inverted = new ListaSimplementeEnlazada<>();
        Nodo<T> actual = list;

        while(actual != null){
            inverted.addFirst(actual.getValue());
            actual = actual.getTl();
        }
        return inverted;
    }

    public int indexOf(T value) {
        if (isEmpty()) return -1;

        Nodo<T> aux = list;
        int index = 0;

        while (aux != null) {
            if (aux.getValue().equals(value)) return index;

            aux = aux.getTl();
            index++;
        }
        return -1;
    }

    public void print(){
        if(isEmpty()){ System.out.println("The list is empty"); return;}

        System.out.print("First => <<");
        Nodo<T> aux= list;
        while(aux!=null){
            System.out.print("[ "+ aux.getValue() +" ]");
            if (aux.getTl()!=null) System.out.print("->");
            aux=aux.getTl();
        }
        System.out.println(">> <= Last");
    }

    public boolean isEmpty() {
        return (list == null && size == 0);
    }

    public int size() {return size;}

    public Nodo<T> list(){return list;}
}
