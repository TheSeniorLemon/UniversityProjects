public class main {
    static void main(String args[]) {
        PairList pairlist= new PairList();
        pairlist.agregar(12,10);

        System.out.println(pairlist);
        pairlist.eliminar(12);
        System.out.println(pairlist);
    }
}
