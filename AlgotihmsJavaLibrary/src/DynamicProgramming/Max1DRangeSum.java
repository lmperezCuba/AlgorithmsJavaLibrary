package DynamicProgramming;

/**
 * Max 1D Range Sum (Kadane’s algorithm)
 * <pre>
 * [1] page.129 , [2] page.136, [3] page.132, [6] page.21
 * UVA Problem 507
 *  For A = [ –2,11,–4,13,–5,–2 ] the solution will be 20, i = 1: j = 3
 * </pre>
 *
 * @author lmperez
 * @see Max2DRangeSum
 */
public class Max1DRangeSum {

    public Max1DRangeSum() {
    }

    /**
     *
     * @param A array of numbers
     * @return array of solutions (S). S[0] = sum of the interval, S[1] = minor
     * index, S[2] = mayor index.
     * <h2> Code </h2>
     * <pre> <code>
     * int max_prev = 0, max_aux = 0, beg = 0, end = 0, sol[] = new int[3];
     * for (int i = 0; i &lt; A.length; i++) {
     *      if (max_aux &lt;= 0) {
     *          beg = i;
     *      }
     *      max_aux = Math.max(max_aux + A[i], 0);
     *      if (max_aux &gt; max_prev) {
     *          end = i;
     *      }
     *      max_prev = Math.max(max_prev, max_aux);
     * }
     * sol[0] = max_prev;
     * sol[1] = beg;
     * sol[2] = end;
     * return sol;
     * </code> </pre>
     */
    public int[] maxSumInterval(int[] A) {
        int max_prev = 0, max_aux = 0, beg = 0, end = 0, sol[] = new int[3];
        for (int i = 0; i < A.length; i++) {
            if (max_aux <= 0) {
                beg = i;
            }
            max_aux = Math.max(max_aux + A[i], 0);
            if (max_aux > max_prev) {
                end = i;
            }
            max_prev = Math.max(max_prev, max_aux);
        }
        sol[0] = max_prev;
        sol[1] = beg;
        sol[2] = end;
        return sol;
    }

    /**
     * Test Case 1
     * <h2> Code </h2>
     * <pre> <code>
     * int arr[] = {-2, 11, -4, 13, -5, -2}; // the original array
     * int S[] = maxSumInterval(arr);
     * System.out.printf("Interval (%d,%d) = %d", S[1], S[2], S[0]);
     * </code> </pre>
     */
    public void testCase1() {
        int arr[] = {-2, 11, -4, 13, -5, -2}; // the original array
        int S[] = maxSumInterval(arr);
        System.out.printf("Interval (%d,%d) = %d\n", S[1], S[2], S[0]);
    }
    
    /**
     * Test Case 2
     * <h2> Code </h2>
     * <pre> <code>
     * int arr[] = {4, -5, 4, -3, 4, 4, -4, 4, -5}; // the original array
     * int S[] = maxSumInterval(arr);
     * System.out.printf("Interval (%d,%d) = %d", S[1], S[2], S[0]);
     * </code> </pre>
     */
    public void testCase2() {
        int arr[] = {4, -5, 4, -3, 4, 4, -4, 4, -5}; // the original array
        int S[] = maxSumInterval(arr);
        System.out.printf("Interval (%d,%d) = %d\n", S[1], S[2], S[0]);
    }
}
