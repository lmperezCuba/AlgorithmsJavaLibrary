package DynamicProgramming;

/**
 * Max Sum Interval (Kadane’s algorithm)
 * <pre>
 * [1] page.129
 * UVA Problem 108
 * </pre>
 *
 * @author lmperez
 * @see Max1DRangeSum
 */
public class Max2DRangeSum {

    public Max2DRangeSum() {
    }

    /**
     * Find the submatrix with the max range sum.
     *
     * @param A matrix of values
     * @return tuple sol.
     * <h2> Code </h2>
     * <pre> <code>
     * int[] sol = new int[5];
     * build(A);
     * int n = A.length, maxSubRect = Integer.MIN_VALUE; // the lowest possible value for this problem
     * for (int i = 0; i &lt; n; i++) {
     *      for (int j = 0; j &lt; n; j++) // start coordinate
     *      {
     *          for (int k = i; k &lt; n; k++) {
     *              for (int l = j; l &lt; n; l++) { // end coord
     *                  int subRect = A[k][l]; // sum of all items from (0, 0) to (k, l): O(1)
     *                  if (i > 0) {
     *                      subRect -= A[i - 1][l]; // O(1)
     *                  }
     *                  if (j > 0) {
     *                      subRect -= A[k][j - 1]; // O(1)
     *                  }
     *                  if (i > 0 && j > 0) {
     *                      subRect += A[i - 1][j - 1]; // O(1)
     *                  }
     *                  if (subRect > maxSubRect) {
     *                      maxSubRect = subRect;
     *                      make(i, j, k, l, maxSubRect, sol);
     *                  }
     *              }
     *          }
     *      }
     * }
     * return sol;
     * </code> </pre>
     *
     * @see Max2DRangeSum#build(int[][])
     */
    public int[] maxSumInterval(int[][] A) {
        int[] sol = new int[5];
        build(A);
        int n = A.length, maxSubRect = Integer.MIN_VALUE; // the lowest possible value for this problem
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) // start coordinate
            {
                for (int k = i; k < n; k++) {
                    for (int l = j; l < n; l++) { // end coord
                        int subRect = A[k][l]; // sum of all items from (0, 0) to (k, l): O(1)
                        if (i > 0) {
                            subRect -= A[i - 1][l]; // O(1)
                        }
                        if (j > 0) {
                            subRect -= A[k][j - 1]; // O(1)
                        }
                        if (i > 0 && j > 0) {
                            subRect += A[i - 1][j - 1]; // O(1)
                        }
                        if (subRect > maxSubRect) {
                            maxSubRect = subRect;
                            make(i, j, k, l, maxSubRect, sol);
                        }
                    }
                }
            }
        }
        return sol;
    }

    /**
     * Buil the dynamic table for the solution
     *
     * @param A matrix of integers
     *
     * <h2> Code </h2>
     * <pre> <code>
     * int n = A.length;
     * for (int i = 0; i &lt; n; i++) {
     *      for (int j = 0; j &lt; n; j++) {
     *          if (i &gt; 0) {
     *              A[i][j] += A[i - 1][j]; // if possible, add from top
     *          }
     *          if (j &gt; 0) {
     *              A[i][j] += A[i][j - 1]; // if possible, add from left
     *          }
     *          if (i &gt; 0 && j &gt; 0) {
     *              A[i][j] -= A[i - 1][j - 1]; // avoid double count
     *          }
     *      }
     * }
     * </code> </pre>
     *
     * @see Max2DRangeSum#make(int, int, int, int, int, int[])
     * @see Max2DRangeSum#maxSumInterval(int[][])
     */
    public void build(int[][] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    A[i][j] += A[i - 1][j]; // if possible, add from top
                }
                if (j > 0) {
                    A[i][j] += A[i][j - 1]; // if possible, add from left
                }
                if (i > 0 && j > 0) {
                    A[i][j] -= A[i - 1][j - 1]; // avoid double count
                }
            }
        }
    }

    /**
     * Make a tuple with the solution values
     *
     * @param x0 = A[0]
     * @param y0 = A[1]
     * @param x1 = A[2]
     * @param y1 = A[3]
     * @param value = A[4]
     * @param A array to return by reference
     * @see Max2DRangeSum#make(int, int, int, int, int, int[])
     * @see Max2DRangeSum#maxSumInterval(int[][])
     */
    public void make(int x0, int y0, int x1, int y1, int value, int[] A) {
        A[0] = x0;
        A[1] = y0;
        A[2] = x1;
        A[3] = y1;
        A[4] = value;
    }

    /**
     * Test Case 1
     * <h2> Code </h2>
     * <pre> <code>
     * int arr[][] = {{0, -2, -7, 0}, {9, 2, -6, 2}, {-4, 1, -4, 1}, {-1, 8, 0, -2}}; // the original matrix
     * int S[] = maxSumInterval(arr);
     * </code> </pre>
     */
    public void testCase1() {
        int arr[][] = {{0, -2, -7, 0}, {9, 2, -6, 2}, {-4, 1, -4, 1}, {-1, 8, 0, -2}}; // the original array
        int S[] = maxSumInterval(arr);
        System.out.printf("Interval (%d,%d),(%d,%d) = %d\n", S[0], S[1], S[2], S[3], S[4]);
    }
}
