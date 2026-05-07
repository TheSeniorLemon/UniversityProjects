package ListaDoble;

public class ListaDoblementeEnlazada<T> {
    Nodo<T> doubleList;
    int size;

    public ListaDoblementeEnlazada() {
        doubleList = null;
        size = 0;
    }

    public void addFirst(T value) {
        Nodo<T> nodo = new Nodo<>(value);
        if (!isEmpty()) {
            nodo.setNext(doubleList);
            doubleList.setPrevious(nodo);
        }
        doubleList = nodo;
        size++;
    }

    public void addLast(T value) {
        if (isEmpty()) { addFirst(value); return; }

        Nodo<T> nodo = new Nodo<>(value);
        Nodo<T> aux = doubleList;

        while (aux.getNext() != null) {
            aux = aux.getNext();
        }

        aux.setNext(nodo);
        nodo.setPrevious(aux);
        size++;
    }

    public void addIndex(int index, T value) {
        if (index < 0 || index > size()) return;
        if (index == 0) { addFirst(value); return; }
        if (index == size()) { addLast(value); return; }

        Nodo<T> nodo = new Nodo<>(value);
        Nodo<T> aux = doubleList;
        int count = 0;

        while (count < index - 1) {
            aux = aux.getNext();
            count++;
        }

        nodo.setNext(aux.getNext());
        nodo.setPrevious(aux);
        if (aux.getNext() != null) aux.getNext().setPrevious(nodo);
        aux.setNext(nodo);
        size++;
    }

    public void removeFirst() {
        if (isEmpty()) return;
        if (size == 1) { doubleList = null; size--; return; }

        doubleList = doubleList.getNext();
        doubleList.setPrevious(null);
        size--;
    }

    public void removeLast() {
        if (isEmpty()) return;
        if (size == 1) { doubleList = null; size--; return; }

        Nodo<T> aux = doubleList;
        while (aux.getNext() != null) {
            aux = aux.getNext();
        }

        aux.getPrevious().setNext(null);
        size--;
    }

    public void removeIndex(int index) {
        if (index < 0 || index >= size()) return;
        if (index == 0) { removeFirst(); return; }
        if (index == size() - 1) { removeLast(); return; }

        Nodo<T> aux = doubleList;
        int count = 0;

        while (count < index) {
            aux = aux.getNext();
            count++;
        }

        aux.getPrevious().setNext(aux.getNext());
        aux.getNext().setPrevious(aux.getPrevious());
        size--;
    }

    public T getFirstValue() {
        if (isEmpty()) return null;
        return doubleList.getValue();
    }

    public T getLastValue() {
        if (isEmpty()) return null;
        Nodo<T> aux = doubleList;
        while (aux.getNext() != null) aux = aux.getNext();
        return aux.getValue();
    }

    public T getValueIndex(int index) {
        if (index < 0 || index >= size()) return null;
        if (index == 0) return getFirstValue();
        if (index == size() - 1) return getLastValue();

        Nodo<T> aux = doubleList;
        int count = 0;

        while (count < index) {
            aux = aux.getNext();
            count++;
        }
        return aux.getValue();
    }

    public ListaDoblementeEnlazada<T> reverse() {
        if (isEmpty()) return null;
        if (size() == 1) return this;

        ListaDoblementeEnlazada<T> inverted = new ListaDoblementeEnlazada<>();
        Nodo<T> aux = doubleList;

        while (aux != null) {
            inverted.addFirst(aux.getValue());
            aux = aux.getNext();
        }
        return inverted;
    }

    public int indexOf(T value) {
        if (isEmpty()) return -1;

        Nodo<T> aux = doubleList;
        int index = 0;

        while (aux != null) {
            if (aux.getValue().equals(value)) return index;
            aux = aux.getNext();
            index++;
        }
        return -1;
    }

    public void print() {
        if (isEmpty()) { System.out.println("The double list is empty"); return; }

        System.out.print("First => ");
        Nodo<T> aux = doubleList;

        while (aux != null) {
            System.out.print("[ " + aux.getValue() + " ]");
            if (aux.getNext() != null) System.out.print(" <-> ");
            aux = aux.getNext();
        }
        System.out.println(" <= Last");
    }

    public boolean isEmpty() { return doubleList == null && size == 0; }

    public Nodo<T> doubleList() { return doubleList; }

    public int size() { return size; }
}