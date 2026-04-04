import java.util.HashMap;

public class PairList<K,V> {
    HashMap<K,V> lista;

    public PairList(){
        lista= new HashMap<>();
    }

    public void agregar(K clave,V valor){
        lista.put(clave,valor);
    }

    public void eliminar(K clave){
        lista.remove(clave);
    }

    public V obtener(K clave){
        return lista.get(clave);
    }

    @Override
    public String toString() {
        return "PairList{" +
                "lista=" + lista +
                '}';
    }
}
