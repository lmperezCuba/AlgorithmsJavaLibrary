package DynamicProgramming;

/**
 * KnapSack 0 - 1 (Subset Sum)
 * <pre>
 * [1] page.133, [2] page.202, [3] page.130, [6] page.72
 * UVA Problem 10130
 * </pre>
 *
 * @author lmperez
 */
public class KnapSack0_1_Subset_Sum {

    public KnapSack0_1_Subset_Sum() {
    }

    /**
     * Main method
     *
     * @param W array of weight
     * @param B array of profits
     * @param M capacity of KnapSak
     * @return Mochila[N][M]: maximun profit
     * <h2> Code </h2>
     * <pre> <code>
     * int N = W.length - 1, limite;
     * int Mochila[][] = new int[N + 1][M + 1];
     * for (int i = W[0]; i &lt; M; i++) {
     *      Mochila[0][i] = W[0];
     * }
     * for (int i = 1; i &lt;= N; i++) {
     *      limite = Math.min(M, W[i]) - 1;// Avoid overflow
     *      for (int j = 1; j &lt;= limite; j++) {
     *          Mochila[i][j] = Mochila[i - 1][j];
     *      }
     *      for (int j = limite + 1; j &lt;= M; j++) {
     *          Mochila[i][j] = Math.max(Mochila[i - 1][j], B[i] + Mochila[i - 1][j - W[i]]);
     *      }
     * }
     *  </code> </pre>
     */
    public int KnapSack_01(int W[], int B[], int M) {
        int N = W.length - 1, limite;
        int Mochila[][] = new int[N + 1][M + 1];
        for (int i = W[0]; i < M; i++) {
            Mochila[0][i] = W[0];
        }
        for (int i = 1; i <= N; i++) {
            limite = Math.min(M, W[i]) - 1;// Avoid overflow
            for (int j = 1; j <= limite; j++) {
                Mochila[i][j] = Mochila[i - 1][j];
            }
            for (int j = limite + 1; j <= M; j++) {
                Mochila[i][j] = Math.max(Mochila[i - 1][j], B[i] + Mochila[i - 1][j - W[i]]);
            }
        }
        return Mochila[N][M];
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
        int B[] = {100, 70, 50, 10}, W[] = {10, 4, 6, 12}, S = 12;
        int value = KnapSack_01(W, B, S);
        System.out.printf("Test case 1: %d\n", value);
    }

    
}
