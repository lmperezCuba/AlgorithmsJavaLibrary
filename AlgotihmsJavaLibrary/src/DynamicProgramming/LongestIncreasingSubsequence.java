package DynamicProgramming;

import DivideAndConquer.BinarySearch;
import java.util.Arrays;

/**
 * Longest Increasing Subsequence (LIS)
 * <pre>
 * [1] page.130, [3] page.128, [6] page.70
 * COJ Problem 1658
 * UVA Problems 111, 231, 481, 497, 10051, 10131
 * </pre>
 *
 * @author lmperez
 */
public class LongestIncreasingSubsequence {

    public LongestIncreasingSubsequence() {
    }

    /**
     * Longest Increasing Subsequence (LIS) O(n^2)
     *
     * @param LIS array of integers
     * @return SOL[res] longest subsequence size
     *
     * <h2> Code </h2>
     * <pre> <code>
     * int SOL[] = new int[LIS.length];
     * Arrays.fill(SOL, 1);
     * for (int i = 0; i &lt; SOL.length - 1; i++) {
     *      for (int j = i + 1; j &lt; SOL.length; j++) {
     *          if (LIS[j] > LIS[i] && SOL[j] &lt; SOL[i] + 1) {
     *              SOL[j] = SOL[i] + 1;
     *          }
     *      }
     * }
     * int res = 0;
     * for (int i = 0; i &lt; SOL.length; i++) {
     *      if (SOL[i] > SOL[res]) {
     *          res = i;
     *      }
     * }
     * return SOL[res];
     * </code> </pre>
     */
    int longestIncreasingSubsecuence(int[] LIS) {
        int SOL[] = new int[LIS.length];
        Arrays.fill(SOL, 1);
        for (int i = 0; i < SOL.length - 1; i++) {
            for (int j = i + 1; j < SOL.length; j++) {
                if (LIS[j] > LIS[i] && SOL[j] < SOL[i] + 1) {
                    SOL[j] = SOL[i] + 1;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < SOL.length; i++) {
            if (SOL[i] > SOL[res]) {
                res = i;
            }
        }
        return SOL[res];
    }

    /**
     * Longest Increasing Subsequence (LIS) O(n log k)
     *
     * @param LIS array of integers
     * @return SOL, SOL[0] = longest subsequence size, SOL[1:] order LIS
     *
     * <h2> Code </h2>
     * <pre> <code>
     * int A[] = new int[LIS.length + 1], Predec[] = new int[LIS.length], P[] = new int[LIS.length + 1], len = 0;
     * BinarySearch bi = new BinarySearch();
     * Arrays.fill(A, Integer.MAX_VALUE);
     * Arrays.fill(Predec, -1);
     * A[0] = Integer.MIN_VALUE;
     * for (int i = 0; i &lt; LIS.length; i++) {
     *      int pos = bi.BinarySearch(A, LIS[i]);
     *      if (A[pos] == Integer.MAX_VALUE) {
     *          len++;
     *      }
     *      if (A[pos - 1] != Integer.MIN_VALUE) {
     *          Predec[i] = P[pos - 1];
     *      }
     *      A[pos] = LIS[i];
     *      P[pos] = i;
     * }
     * // Print Precedence
     * int pos = P[len], SOL[] = new int[len + 1], k = len;
     * SOL[0] = len;
     * SOL[k--] = LIS[pos];
     * while (Predec[pos] != -1) {
     *      SOL[k--] = LIS[Predec[pos]];
     *      pos = Predec[pos];
     * }
     * return SOL;
     * </code> </pre>
     */
    int[] longestIncreasingSubsecuenceFast(int LIS[]) {
        int A[] = new int[LIS.length + 1], Predec[] = new int[LIS.length], P[] = new int[LIS.length + 1], len = 0;
        BinarySearch bi = new BinarySearch();
        Arrays.fill(A, Integer.MAX_VALUE);
        Arrays.fill(Predec, -1);
        A[0] = Integer.MIN_VALUE;
        for (int i = 0; i < LIS.length; i++) {
            int pos = bi.BinarySearch(A, LIS[i]);
            if (A[pos] == Integer.MAX_VALUE) {
                len++;
            }
            if (A[pos - 1] != Integer.MIN_VALUE) {
                Predec[i] = P[pos - 1];
            }
            A[pos] = LIS[i];
            P[pos] = i;
        }
        // Print Precedence
        int pos = P[len], SOL[] = new int[len + 1], k = len;
        SOL[0] = len;
        SOL[k--] = LIS[pos];
        while (Predec[pos] != -1) {
            SOL[k--] = LIS[Predec[pos]];
            pos = Predec[pos];
        }
        return SOL;
    }

    /**
     * Test Case 1
     * <h2> Code </h2>
     * <pre> <code>
     * int arr[] = {-7, 10, 9, 2, 3, 8, 8, 1}; // the original array
     * int value = longestIncreasingSubsecuence(arr);
     * System.out.printf("Test case 1: LIS = %d\n", value);
     * </code> </pre>
     */
    public void testCase1() {
        int arr[] = {-7, 10, 9, 2, 3, 8, 8, 1}; // the original array
        int value = longestIncreasingSubsecuence(arr);
        System.out.printf("Test case 1: LIS = %d\n", value);
    }

    /**
     * Test Case 2
     * <h2> Code </h2>
     * <pre> <code>
     * int arr[] = {-7, 10, 9, 2, 3, 8, 8, 1, 2, 3, 4}; // the original array
     * int[] value = longestIncreasingSubsecuenceFast(arr);
     * System.out.printf("Test case 2: LIS = %d\nPrecedence: ", value[0]);
     * for (int i = 1; i &lt; value.length; i++) {
     *      System.out.print(value[i] + " ");
     * }
     * </code> </pre>
     */
    public void testCase2() {
        int arr[] = {-7, 10, 9, 2, 3, 8, 8, 1, 2, 3, 4}; // the original array
        int[] value = longestIncreasingSubsecuenceFast(arr);
        System.out.printf("Test case 2: LIS = %d\nPrecedence: ", value[0]);
        for (int i = 1; i < value.length; i++) {
            System.out.print(value[i] + " ");
        }
    }

}
