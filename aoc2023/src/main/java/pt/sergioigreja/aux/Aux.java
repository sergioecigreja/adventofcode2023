package pt.sergioigreja.aux;

public final class Aux {

    public static void printMatrix(char[][] matrix) {
        StringBuilder sb = new StringBuilder();

        for (char[] line : matrix) {
            for (char c : line) {
                sb.append(c);
                sb.append(" ");
            }
            sb.append('\n');
            System.out.println(sb.toString());
            sb.setLength(0);
        }
    }
}
