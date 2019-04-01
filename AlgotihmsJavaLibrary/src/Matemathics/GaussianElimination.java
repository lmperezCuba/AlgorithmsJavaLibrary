package Matemathics;

import Classes.Matrix;
import java.util.LinkedList;

/**
 *
 * @author lmperez
 */
public class GaussianElimination {

    public GaussianElimination() {
    }
    double mat[][];
    double vec[];

    /**
     * Gaussian Elimination
     * <pre>
     * [1] page.346
     * </pre>
     *
     * @params N the amount of variables
     * @params mat the augmenting matrix
     * @return the solutions for X,Y and Z
     */

    double[] gaussianElimination(int N) { // O(N^3)
// input: N, Augmented Matrix Aug, output: Column vector X, the answer
        int i, j, k, l;
        double t;
        for (j = 0; j < N - 1; j++) { // the forward elimination phase
            l = j;
            for (i = j + 1; i < N; i++) // which row has largest column value
            {
                if (Math.abs(mat[i][j]) > Math.abs(mat[l][j])) {
                    l = i; // remember this row l
                }// swap this pivot row, reason: to minimize floating point error
            }
            for (k = j; k <= N; k++) {
// t is a temporary double variable
                t = mat[j][k];
                mat[j][k] = mat[l][k];
                mat[l][k] = t;
            }
            for (i = j + 1; i < N; i++) // the actual forward elimination phase
            {
                for (k = N; k >= j; k--) {
                    mat[i][k] -= mat[j][k] * mat[i][j] / mat[j][j];
                }
            }
        }
        for (j = N - 1; j >= 0; j--) { // the back substitution phase
            for (t = 0.0, k = j + 1; k < N; k++) {
                t += mat[j][k] * vec[k];
            }
            vec[j] = (mat[j][N] - t) / mat[j][j]; // the answer is here
        }
        return vec;
    }

    public double[][] augmentedMatrix(double M[][], double[] B) {
        int n = M.length;
        double A[][] = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = M[i][j];
            }
            A[i][n] = B[i];
        }
        return A;
    }

    public void showMatrix(double M[][]) {
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    /*
    void runLinearEcuations() {
        MAX_N = 3;
        //  Init
        mat = new double[MAX_N][MAX_N + 1];
        vec = new double[MAX_N];
        char C[] = {'X', 'Y', 'Z'};
        // double AM[][] = new double[MAX_N][MAX_N];
        int values[] = {1, 1, 2, 9, 2, 4, -3, 1, 3, 6, -5, 0}, current = 0;
        for (int i = 0; i < MAX_N; i++) {
            for (int j = 0; j <= MAX_N; j++) {
                mat[i][j] = values[current++];
            }
        }
        double A[] = gaussianElimination(MAX_N);
        for (int i = 0; i < MAX_N; i++) {
            System.out.println(C[i] + " = " + (int) (A[i] + 0.5));             //  X = 1, Y = 2, Z = 3 
        }
    }
    */
//     Tess case 1

    public void tessCase1() {
        int n = 4;
        double tree[][] = {{1, 0, 2, -1},
        {3, 0, 0, 5},
        {2, 1, 4, -3},
        {1, 0, 5, 0}
        };
        double[] vec = new double[n];
        double MOD = 1000000007;
        try {
            // int value = (int) gaussianElimination(n, augmentedMatrix(tree, new double[n]), vec, MOD);
            Matrix m = new Matrix(tree);
            int value = (int) m.determinant(tree);
            System.out.println(" = " + value);
            boolean sol = 30 == value;
            System.out.println("Tess Case 1: " + sol + "(" + value + ")");
        } catch (Exception ex) {
            System.err.println("ERROR " + ex.getMessage());
            ex.printStackTrace();
        }
    }
/*
    //     Tess case 2
    public void tessCase2() {
        int n = 3;
        double A[][] = {{1, 3, 1},
        {1, 1, -1},
        {3, 11, 5},};
        double B[] = {9, 1, 35};
        double[] vec = new double[n];
        double MOD = 1000000007;
        try {
            double value = gaussianElimination(n, augmentedMatrix(A, B), vec, MOD);
            int v = (int) value;
            System.out.println(" = " + v);
            boolean sol = 8 == v;
            System.out.println("Tess Case 2: " + sol + "(" + v + ")");
        } catch (Exception ex) {
            System.err.println("ERROR " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    //     Tess case 3
    public void tessCase3() {
        int n = 3;
        double A[][] = {{2, 1, -1},
        {-3, -1, 2},
        {-2, 1, 2}};
        double B[] = {8, -11, 3};
        double[] vec = new double[n];
        double MOD = 1000000007;
        try {
            double value = gaussianElimination(n, augmentedMatrix(A, B), vec, MOD);
            int v = (int) value;
            System.out.println(" = " + v);
            boolean sol = 8 == v;
            System.out.println("Tess Case 3: " + sol + "(" + v + ")");
        } catch (Exception ex) {
            System.err.println("ERROR " + ex.getMessage());
            ex.printStackTrace();
        }
    }*/

}
