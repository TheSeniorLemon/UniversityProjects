import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Repositorio<T> implements Iterable<T> {
    List<T> objetos;

    public Repositorio(){
        objetos= new ArrayList<>();
    }

    public void agregar(T elemento){
        objetos.add(elemento);
    }

    public T obtener(int indice){
        return objetos.get(indice);
    }

    @Override
    public Iterator<T> iterator(){
        return new Myiterator<T>(objetos);
    }

    public void recorrer(){
        for(T objeto: this){
            System.out.println(objeto);
        }
    }

    public Iterator<T> iteratorInverso(){
        return new IteratorInverso();
    }

    private class IteratorInverso implements Iterator<T> {
        int index=objetos.size()-1;

        @Override
        public boolean hasNext(){
            return 0<=index;
        }

        @Override
        public T next(){
            if(!hasNext()){
                throw new NoSuchElementException("Matraz");
            }
            return objetos.get(index--);
        }

    }

}
