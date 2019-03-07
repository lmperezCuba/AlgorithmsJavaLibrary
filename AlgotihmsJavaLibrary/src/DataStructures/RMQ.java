package DataStructures;

import algotihmsjavalibrary.AJL;

/**
 * RMQ
 * <pre>
 * Find the max|min of a range of elements for a fixed array. Really the program
 * just can find the min, so if you need yo find the max, you need to
 * multiply all elements by -1 and then find the minimun (The max will be
 * that result multiplied by -1).
 * [1] page.80, [7] page.48
 *
 * COJ Problems  1651, 2395
 * </pre>
 *
 * @see AJL
 * @see SegmentTree
 * @see BinaryIndexedTree1D
 * @author lmperez
 */
public final class RMQ {

    public RMQ() {
    }

    int MAXN;
    int M[][], q, a, b, c;

    int A[], N;

    /**
     * Create the RMQ.
     *
     * @param B arrays of elements
     * @param MAXN Greater value in the array
     * @param bits <code>Math.log10(MAXN)/Math.log10(2) + k</code>. Where k is a
     * number in the range [1..5] for avoid overload.
     *
     * <h2>Code:</h2>
     * <pre>
     * <code>
     * this.MAXN = MAXN;
     * M = new int[MAXN][bits];
     * N = B.length;
     * A = B;
     * int i, j;
     * //initialize M for the intervals with length 1
     * for (i = 0; i &lt; N; i++) {
     *      M[i][0] = i;
     * }
     * //compute values from smaller to bigger intervals
     * for (j = 1; (1 &lt;&lt; j) &lt;= N; j++) {
     *      for (i = 0; i + (1 &lt;&lt; j) - 1 &lt; N; i++) {
     *          if (A[M[i][j - 1]] &lt; A[M[i + (1 &lt;&lt; (j - 1))][j - 1]]) {
     *              M[i][j] = M[i][j - 1];
     *          } else {
     *              M[i][j] = M[i + (1 &lt;&lt; (j - 1))][j - 1];
     *          }
     *      }
     * }
     * </code>
     * </pre>
     */
    public void init(int B[], int MAXN, int bits) {
        this.MAXN = MAXN;
        M = new int[MAXN][bits];
        N = B.length;
        A = B;
        int i, j;
        //initialize M for the intervals with length 1
        for (i = 0; i < N; i++) {
            M[i][0] = i;
        }
        //compute values from smaller to bigger intervals
        for (j = 1; (1 << j) <= N; j++) {
            for (i = 0; i + (1 << j) - 1 < N; i++) {
                if (A[M[i][j - 1]] < A[M[i + (1 << (j - 1))][j - 1]]) {
                    M[i][j] = M[i][j - 1];
                } else {
                    M[i][j] = M[i + (1 << (j - 1))][j - 1];
                }
            }
        }
    }

    /**
     * Once we have these values preprocessed, let's show how we can use them to
     * calculate RMQA(i, j). The idea is to select two blocks that entirely
     * cover the interval [i..j] and find the minimum between them. Let k =
     * [log(j - i + 1)].
     *
     * @param i leftbound index
     * @param j upperbound index
     * @return The minimin value in the range [i, j]. Math.min(A[M[i][k]], A[M[j
     * - (1 &lt;&lt; k) + 1][k]]);
     *
     * <h2>Code:</h2>
     * <pre><code>
     * if (i == j) {
     *      return A[i];
     * }
     * int k = (int) (Math.log10(j - i + 1) / Math.log10(2.0));
     * </code></pre>
     */
    public int Query(int i, int j) {
        if (i == j) {
            return A[i];
        }
        int k = (int) (Math.log10(j - i + 1) / Math.log10(2.0));
        return Math.min(A[M[i][k]], A[M[j - (1 << k) + 1][k]]);
    }

    /**
     * Test Case 1 for Developers Print the minimun of the ranges
     *
     * <h2>Code: </h2>
     * <pre><code>
     * int arr[] = {18, 17, 13, 19, 15, 11, 20}; // the original array
     * init(arr, 21, 5);
     * </code>
     * <h3> Queries: </h3>
     * RMQ(1, 3) = 13,
     * RMQ(4, 6) = 11
     * </pre>
     * @see RMQ#init(int[], int, int) 
     * @see RMQ#Query(int, int) 
     */
    public void testCase1() {
        int arr[] = {18, 17, 13, 19, 15, 11, 20}; // the original array
        init(arr, 21, 5); // find the minimun
        System.out.printf("RMQ(1, 3) = %d\n", Query(1, 3)); // answer = index 13
        System.out.printf("RMQ(4, 6) = %d\n", Query(4, 6)); // answer = index 11
    }

    /**
     * Test Case 2 for Developers Print the maximun of the ranges
     * 
     * <h2>Code: </h2>
     * <pre><code>
     * int arr[] = {18, 17, 13, 19, 15, 11, 2000000}; // the original array
     * for (int i = 0; i &lt; arr.length; i++) {
            arr[i] *= -1;
        }
     * init(arr, 2000001, 20);
     * </code>
     * <h3> Queries: </h3>
     * RMQ(0, 6) = 19,
     * RMQ(4, 6) = 2000000
     * </pre>
     * @see RMQ#init(int[], int, int) 
     * @see RMQ#Query(int, int) 
     */
    public void testCase2() {
        int arr[] = {18, 17, 13, 19, 15, 11, 2000000}; // the original array
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= -1;
        }
        init(arr, 2000001, 20); // find the max
        System.out.printf("RMQ(0, 6) = %d\n", Query(1, 3) * -1); // answer = 19
        System.out.printf("RMQ(4, 6) = %d\n", Query(4, 6) * -1); // answer = 2000000
    }
}
