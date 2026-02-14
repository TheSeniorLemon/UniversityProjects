public class RepasoRecursividad {
    public static void main(String args[]){
        int j= 1;
        System.out.println(recursividad(j));
        int i= 4;
        System.out.println(recursividad(i));
    }

    public static int recursividad(int num){
        if(num==0){
            return 1;
        }
        return  num*recursividad(num-1);
    }
}
