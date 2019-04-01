package Classes;

import NumberTheory.ModularInverse;


/**
 * https://github.com/TomConerly/Competition-Programming/blob/master/TopCoder/Hard/RoadsOfKingdom.java
 *
 * @author lmperez
 */
public class Matrix {

    int rows, cols;
    double M[][];
    final double EPSILON = 1e-10;
    ModularInverse mi;

    public Matrix(double[][] M) {
        this.rows = M.length;
        this.cols = M[0].length;
        this.M = M;
        mi = new ModularInverse();
    }

    public double determinant(double[][] matrix) {
        int N = matrix.length;
        double det = 1;
        for (int i = 0; i < N; ++i) {
            double pivotElement = matrix[i][i];
            int pivotRow = i;
            for (int row = i + 1; row < N; ++row) {
                if (Math.abs(matrix[row][i]) > Math.abs(pivotElement)) {
                    pivotElement = matrix[row][i];
                    pivotRow = row;
                }
            }
            if (pivotElement <= EPSILON) {
                return 0.0;
            }
            if (pivotRow != i) {
                switchRows(matrix[i], matrix[pivotRow]);
                det *= -1.0;
            }
            det *= pivotElement;
            for (int row = i + 1; row < N; ++row) {
                for (int col = i + 1; col < N; ++col) {
                    matrix[row][col] -= matrix[row][i] * matrix[i][col] / pivotElement;
                }
            }
        }
        return det;
    }

    public long determinant(long[][] matrix, long MOD) {
       long result = 1;
        for (int i = 0; i < matrix.length-1; i++) {
            long modInverse = mi.modInverse2(matrix[i][i], MOD);
            result = (result * modInverse) % MOD;
            for (int j = i; j < matrix.length; j++) {
                matrix[i][j] = (modInverse * matrix[i][j]) % MOD;
            }
            for (int ii = i+1; ii < matrix.length; ii++) {
                if (matrix[ii][i] == 0) continue;
                long multiple = MOD-matrix[ii][i];
                for (int jj = i; jj < matrix.length; jj++) matrix[ii][jj] = (matrix[ii][jj] + matrix[i][jj] * multiple) % MOD;
            }
        }
        return (mi.modInverse2(result, MOD)*matrix.length)%MOD;
    }

    

    public void swap(double a, double b) {
        double c = a;
        a = b;
        b = c;
    }

    public String toString() {
        String out = rows + "," + cols + "\n";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                out = out + M[i][j] + ",";
            }
            out = out + "\n";
        }
        return out;
    }

    private void subRow(int j, int i, double d) {
        for (int k = 0; k < cols; k++) {
            M[j][k] -= M[i][k] * d;
        }
    }

    @SuppressWarnings("unused")
    public void scaleRow(int r, double f) {
        for (int i = 0; i < cols; i++) {
            M[r][i] *= f;
        }
    }

    public void switchRows(long[] a, long[] b) {
        long[] temp = a;
        a = b;
        b = temp;
    }

    public void switchRows(double[] a, double[] b) {
        double[] temp = a;
        a = b;
        b = temp;
    }

    public Matrix copy() {
        Matrix copy = new Matrix(new double[rows][cols]);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                copy.M[i][j] = M[i][j];
            }
        }
        return copy;
    }

}
