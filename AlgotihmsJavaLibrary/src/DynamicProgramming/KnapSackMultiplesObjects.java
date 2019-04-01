package DynamicProgramming;

/**
 * KnapSack Multiples Objects
 * <pre>
 * [1] page.132, [2] page.205
 * </pre>
 *
 * @author lmperez
 */
public class KnapSackMultiplesObjects {

    public int[] SOL;

    public KnapSackMultiplesObjects() {
        SOL = new int[0];
    }

    /**
     * @param W array of weight
     * @param B array of profits
     * @param M capacity of KnapSak
     * @return DP array of Benefits
     *
     * <h2> Code </h2>
     * <pre> <code>
     * int DP[] = new int[M + 1];
     * for (int i = 0; i &lt; W.length; i++) {
     *      for (int j = W[i]; j &lt;= M; j++) {
     *          DP[j] = Math.max(DP[j], DP[j - W[i]] + B[i]);
     *      }
     * }
     * return DP;
     * </code> </pre>
     */
    public int[] KMO(int W[], int B[], int M) {
        int DP[] = new int[M + 1];
        for (int i = 0; i < W.length; i++) {
            for (int j = W[i]; j <= M; j++) {
                DP[j] = Math.max(DP[j], DP[j - W[i]] + B[i]);
            }
        }
        return DP;
    }

    public int[] KMOPreced(int W[], int B[], int M) {
        int DP[] = new int[M + 1], Preced[] = new int[M + 1], Mask[] = new int[M + 1], size[] = new int[M + 1];
        for (int i = 0; i < W.length; i++) {
            for (int j = W[i]; j <= M; j++) {
                if (DP[j] < DP[j - W[i]] + B[i]) {
                    Preced[j] = j - W[i];
                    Mask[j] = i;
                    DP[j] = DP[j - W[i]] + B[i];
                }
                size[j] = Math.max(size[j], size[j - W[i]] + 1);
            }
        }
        int len = size[M], last = M;
        SOL = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            SOL[i] = Mask[last];
            last = Preced[last];
        }
        return DP;
    }

    /**
     * Test Case 1
     * <h2> Code </h2>
     * <pre> <code>
     * int B[] = {100, 70, 50, 10}, W[] = {10, 4, 6, 12}, S = 12;
     * int value[] = KMOPreced(W, B, S);
     * System.out.printf("Test case 1: %d\n", value[S]);
     * System.out.println("Precedence:");
     * for (int i = 0; i &lt; SOL.length; i++) {
     *      System.out.println("Weight: " + W[SOL[i]] + ", Benefit: " + B[SOL[i]]);
     * }
     * </code> </pre>
     */
    public void testCase1() {
        int B[] = {100, 70, 50, 10}, W[] = {10, 4, 6, 12}, S = 12;
        int value[] = KMOPreced(W, B, S);
        System.out.printf("Test case 1: %d\n", value[S]);
        System.out.println("Precedence:");
        for (int i = 0; i < SOL.length; i++) {
            System.out.println("Weight: " + W[SOL[i]] + ", Benefit: " + B[SOL[i]]);
        }
        System.out.println("");
    }

}
