import java.util.Arrays;

public class Recursivity {

    public static void main(String[] args){
        int[] array = {1, 2, 3, 4, 5};
        System.out.println("=== Recorrer un arreglo ===");
        traverseArray(array);
        System.out.println("\n=== Terminó de recorrer un arreglo ===\n");

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("=== Recorrer una matriz ===");
        traverseMatrixChatGpt(matrix,matrix[0].length);
        System.out.println("\n=== Terminó de recorrer la matriz ===\n");

        System.out.println("=== Recorrer la diagonal principal ===");
        traverseMainDiagonal(matrix);
        System.out.println("\n=== Terminó de recorrer la diagonal principal ===");
    }

    public static void traverseArray(int[] array){
        if(array.length == 0){
            return;
        }
        if(array.length == 1){
            System.out.print(array[0] + "\t");
            return;
        }

        int middle = array.length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[array.length - middle];

        System.arraycopy(array, 0, leftArray, 0, middle);
        System.arraycopy(array, middle, rightArray, 0, array.length - middle);

        traverseArray(leftArray);
        traverseArray(rightArray);
    }

    public static void traverseMatrix(int[][] matrix, int cols) {
        if (matrix.length == 0) {
            return;
        }
        if (matrix.length == 1) {
            traverseArray(matrix[0]);  // recursión individual de la lista
            return;
        }

        int middle = matrix.length / 2;
        int[][] topHalf = new int[middle][cols];

        int[][] bottomHalf = new int[matrix.length - middle][cols];

        System.arraycopy(matrix, 0, topHalf, 0, middle);
        System.arraycopy(matrix, middle, bottomHalf, 0, matrix.length - middle);

        traverseMatrix(topHalf, cols);
        traverseMatrix(bottomHalf, cols);
    }

    public static void traverseMatrixChatGpt(int[][] matrix, int col){
        if(matrix.length == 0){
            return;
        }
        if(matrix.length == 1){
            traverseArray(matrix[0]);
            return;
        }

        int middle = matrix.length / 2;
        int[][] leftMatrix = new int[middle][col];
        int[][] rightMatrix = new int[matrix.length - middle][col];

        for (int i = 0; i < middle; i++) {
            leftMatrix[i] = Arrays.copyOf(matrix[i], col);
        }
        for (int i = 0; i < matrix.length - middle; i++) {
            rightMatrix[i] = Arrays.copyOf(matrix[middle + i], col);
        }

        traverseMatrixChatGpt(leftMatrix, col);
        traverseMatrixChatGpt(rightMatrix, col);
    }

    public static void traverseMainDiagonal(int[][] matrix){
        if(matrix.length == 0 || matrix.length != matrix[0].length){
            return;
        }
        traverseDiagonalRecursive(matrix, 0, matrix.length - 1);
    }

    private static void traverseDiagonalRecursive(int[][] matrix, int start, int end){
        if(start > end){
            return;
        }
        if(start == end){
            System.out.print(matrix[start][start] + "\t");
            return;
        }

        int mid = start + (end - start) / 2;
        traverseDiagonalRecursive(matrix, start, mid);
        traverseDiagonalRecursive(matrix, mid + 1, end);
    }
}