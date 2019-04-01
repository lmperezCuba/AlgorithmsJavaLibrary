package Matemathics;

/**
 * Gauss-Jordan elimination with full pivoting.
 * <pre>
 * Uses:
 * (1) solving systems of linear equations (AX=B)
 * (2) inverting matrices (AX=I)
 * (3) computing determinants of square matrices
 *
 * Running time: O(nˆ3)
 *
 * INPUT: a[][] = an nxn matrix
 * b[][] = an nxm matrix
 *
 * OUTPUT: X = an nxm matrix (stored in b[][])
 * Aˆ{-1} = an nxn matrix (stored in a[][])
 * returns determinant of a[][]
 * </pre>
 *
 * @author lmperez
 */
public class GaussJordan {

    final double EPSILON = 1e-10;

    public GaussJordan() {
    }

    /**
     * Gaussian Elimination version
     *
     * @param a an nxn matrix
     * @param b an nxm matrix
     * @param n size of a
     * @return determinant of a[][], an nxm matrix (stored in b[][]), Aˆ{-1} =
     * an nxn matrix (stored in a[][])
     *
     * <h2> Code </h2>
     * <pre> <code>
     * int m = b[0].length;
     * int[] irow = new int[n], icol = new int[n], ipiv = new int[n];
     * double det = 1;
     * for (int i = 0; i &lt; n; i++) {
     *      int pj = -1, pk = -1;
     *      for (int j = 0; j &lt; n; j++) {
     *          if (ipiv[j] == 0) {
     *              for (int k = 0; k &lt; n; k++) {
     *                  if (ipiv[k] == 0) {
     *                      if (pj == -1 || Math.abs(a[j][k]) > Math.abs(a[pj][pk])) {
     *                          pj = j;
     *                          pk = k;
     *                      }
     *                  }
     *              }
     *          }
     *      }
     *      if (Math.abs(a[pj][pk]) &lt; EPSILON) {
     *          System.out.println("Matrix is singular.");
     *      }
     *      ipiv[pk]++;
     *      swap(a[pj], a[pk]);
     *      swap(b[pj], b[pk]);
     *      if (pj != pk) {
     *          det *= -1;
     *      }
     *      irow[i] = pj;
     *      icol[i] = pk;
     *      double c = 1.0 / a[pk][pk];
     *      det *= a[pk][pk];
     *      a[pk][pk] = 1.0;
     *      for (int p = 0; p &lt; n; p++) {
     *          a[pk][p] *= c;
     *      }
     *      for (int p = 0; p &lt; m; p++) {
     *          b[pk][p] *= c;
     *      }
     *      for (int p = 0; p &lt; n; p++) {
     *          if (p != pk) {
     *              c = a[p][pk];
     *              a[p][pk] = 0;
     *              for (int q = 0; q &lt; n; q++) {
     *                  a[p][q] -= a[pk][q] * c;
     *              }
     *              for (int q = 0; q &lt; m; q++) {
     *                  b[p][q] -= b[pk][q] * c;
     *              }
     *          }
     *      }
     *  }
     *  for (int p = n - 1; p &gt;= 0; p--) {
     *      if (irow[p] != icol[p]) {
     *          for (int k = 0; k &lt; n; k++) {
     *              swap(a[k][irow[p]], a[k][icol[p]]);
     *          }
     *      }
     *  }
     * return det;
     * </code> </pre>
     */
    public double gaussJordan(double[][] a, double[][] b, int n) {
        int m = b[0].length;
        int[] irow = new int[n], icol = new int[n], ipiv = new int[n];
        double det = 1;
        for (int i = 0; i < n; i++) {
            int pj = -1, pk = -1;
            for (int j = 0; j < n; j++) {
                if (ipiv[j] == 0) {
                    for (int k = 0; k < n; k++) {
                        if (ipiv[k] == 0) {
                            // if(pj != -1)       System.out.println(Math.abs(a[j][k]) + " - " + a[pj][pk]);
                            if (pj == -1 || Math.abs(a[j][k]) > Math.abs(a[pj][pk])) {

                                pj = j;
                                pk = k;
                            }
                        }
                    }
                }
            }
            if (Math.abs(a[pj][pk]) < EPSILON) {
                System.out.println("Matrix is singular.");
            }
            ipiv[pk]++;
            swap(a[pj], a[pk]);
            swap(b[pj], b[pk]);
            if (pj != pk) {
                det *= -1;
            }
            irow[i] = pj;
            icol[i] = pk;
            double c = 1.0 / a[pk][pk];
            det *= a[pk][pk];
            a[pk][pk] = 1.0;
            for (int p = 0; p < n; p++) {
                a[pk][p] *= c;
            }
            for (int p = 0; p < m; p++) {
                b[pk][p] *= c;
            }
            for (int p = 0; p < n; p++) {
                if (p != pk) {
                    c = a[p][pk];
                    a[p][pk] = 0;
                    for (int q = 0; q < n; q++) {
                        a[p][q] -= a[pk][q] * c;
                    }
                    for (int q = 0; q < m; q++) {
                        b[p][q] -= b[pk][q] * c;
                    }
                }
            }
        }
        for (int p = n - 1; p >= 0; p--) {
            if (irow[p] != icol[p]) {
                for (int k = 0; k < n; k++) {
                    swap(a[k][irow[p]], a[k][icol[p]]);
                }
            }
        }
        return det;
    }

    /**
     * Swap array a with array b
     *
     * @param a
     * @param b
     */
    public void swap(double[] a, double[] b) {
        double[] temp = new double[a.length];
        System.arraycopy(a, 0, temp, 0, a.length);
        System.arraycopy(b, 0, a, 0, a.length);
        System.arraycopy(temp, 0, b, 0, a.length);
    }

    /**
     * Swap value a with value b
     *
     * @param a
     * @param b
     */
    public void swap(double a, double b) {
        double c = a;
        a = b;
        b = c;
    }

    /**
     * Tess case 1
     *
     * <h2> Code </h2>
     * <pre> <code>
     * double a[][] = {{1, 2, 3, 4}, {1, 0, 1, 0}, {5, 3, 2, 4}, {6, 1, 4, 6}};
     * double[][] b = {{1, 2}, {4, 3}, {5, 6}, {8, 7}};
     * int value = (int) gaussJordan(a, b, a.length);
     * boolean sol = 60 == value;
     * System.out.println("Tess Case 1: " + sol + "(" + value + ")");
     * </code> </pre>
     */
    public void tessCase1() {
        double a[][] = {{1, 2, 3, 4}, {1, 0, 1, 0}, {5, 3, 2, 4}, {6, 1, 4, 6}};
        double[][] b = {{1, 2}, {4, 3}, {5, 6}, {8, 7}};
        int value = (int) gaussJordan(a, b, a.length);
        boolean sol = 60 == value;
        System.out.println("Tess Case 1: " + sol + "(" + value + ")");
    }
}
