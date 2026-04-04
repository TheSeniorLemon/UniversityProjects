import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Myiterator<T> implements Iterator<T>{
    private int index=0;
    private boolean puedeEliminar= false;
    List<T> objetos;

    public Myiterator(List<T> objetos){
        this.objetos= objetos;
    }

    @Override
    public boolean hasNext(){
        return index < objetos.size();
    }

    @Override
    public T next(){
        if(!hasNext()){
            throw new NoSuchElementException("Matraz");
        }
        puedeEliminar=true;
        return objetos.get(index++);
    }

}
