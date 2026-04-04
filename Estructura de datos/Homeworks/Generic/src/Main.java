public static void main(String[] args) {
    // Prueba con Strings
    Par<String> par = new Par<>("Hola", "Mundo");
    System.out.println("Antes: " + par.getFirst() + " - " + par.getSecond());

    par.intercambiarPosiciones();
    System.out.println("Después: " + par.getFirst() + " - " + par.getSecond());

    // Prueba typeComparator
    boolean resultado = par.typeComparator(par.getFirst(), par.getSecond());
    System.out.println("¿Son del mismo tipo? " + resultado);

    // Prueba con tipos diferentes
    Par<Object> par2 = new Par<>(1, "texto");
    boolean resultado2 = par2.typeComparator(par2.getFirst(), par2.getSecond());
    System.out.println("¿Integer y String son del mismo tipo? " + resultado2);
}
