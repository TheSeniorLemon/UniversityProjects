import java.util.ArrayList;

public class Par<T> {
    public T first;
    public T second;

    public Par(T first,T second){
        this.first=first;
        this.second=second;
    }

    public boolean typeComparator(T first,T second){
        boolean isEquals=false;
        if(first.getClass().getName().equals(second.getClass().getName())){
            isEquals=true;
        }
        return isEquals;
    }

    public void intercambiarPosiciones() {
        ArrayList<T> array = new ArrayList<>();
        array.add(first);
        array.add(second);
        this.first = array.get(1);
        this.second = array.get(0);
    }
    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
