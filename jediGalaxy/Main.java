package jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        fillMatrix(rows, cols, matrix);

        String command = scanner.nextLine();
        long sum = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] ivoCoordinate = Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] evilCoordinate = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int evilRow = evilCoordinate[0];
            int evilCol = evilCoordinate[1];

            while (evilRow >= 0 && evilCol >= 0) {
                setEvilSpotTo0(matrix, evilRow, evilCol);
                evilRow--;
                evilCol--;
            }

            int ivoRow = ivoCoordinate[0];
            int ivoCol = ivoCoordinate[1];

            while (ivoRow >= 0 && ivoCol < matrix[1].length) {
                sum = sumDiagonals(matrix, sum, ivoRow, ivoCol);

                ivoCol++;
                ivoRow--;
            }

            command = scanner.nextLine();
        }

        System.out.println(sum);


    }

    private static long sumDiagonals(int[][] matrix, long sum, int ivoRow, int ivoCol) {
        if (ivoRow < matrix.length && ivoCol >= 0 && ivoCol < matrix[0].length) {
            sum += matrix[ivoRow][ivoCol];
        }
        return sum;
    }

    private static void setEvilSpotTo0(int[][] matrix, int evilRow, int evilCol) {
        if (evilRow < matrix.length && evilCol < matrix[0].length) {
            matrix[evilRow][evilCol] = 0;
        }
    }

    private static void fillMatrix(int rows, int cols, int[][] matrix) {
        int value = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = value++;
            }
        }
    }
}
