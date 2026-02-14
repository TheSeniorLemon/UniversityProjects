import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Array {
    public static void main(String[] args){
        int[] arreglo = new int[8];
        generarContenido(arreglo);
        System.out.println("Arreglo original:");
        imprimirArray(arreglo);
        ordenarArreglo(arreglo);
        System.out.println("\nArreglo ordenado:");
        imprimirArray(arreglo);

        LinkedList<Integer> lista = new LinkedList<>();
        generarLista(lista);
        System.out.println("\nLista original:");
        imprimirList(lista);
        ordenarLista(lista);
        System.out.println("\nLista ordenada:");
        imprimirList(lista);
    }

    public static void generarContenido(int[] arreglo){
        for(int i = 0; i < arreglo.length; i++){
            arreglo[i] = ((int) (Math.random()*10));
        }
    }

    public static void generarLista(LinkedList<Integer> lista){
        for(int i = 0; i < 8; i++){
            lista.add((int) (Math.random() * 10));
        }
    }

    public static void ordenarArreglo(int[] arreglo){
        Arrays.sort(arreglo);
    }

    public static void ordenarLista(LinkedList<Integer> lista){
        Collections.sort(lista);
    }

    public static void imprimirArray(int[] arreglo){
        for(int i = 0; i < arreglo.length; i++){
            System.out.print(arreglo[i] + " ");
        }
        System.out.println();
    }

    public static void imprimirList(LinkedList<Integer> lista){
        for(int i = 0; i < lista.size(); i++){
            System.out.print(lista.get(i) + " ");
        }
        System.out.println();
    }
}