package DynamicProgramming;

/**
 * Coin Change
 * <pre>
 * [1] page.133, [6] page.65
 * COJ Problem 2616
 * </pre>
 *
 * @author lmperez
 */
public class CoinChange {

    public int[] SOL;

    public CoinChange() {
        SOL = new int[0];
    }

    /**
     * Main method
     *
     * @param coins array of coins sorted
     * @param maxChange max value of money
     * @return DP[maxChange]: max coins
     *
     * <h2> Code </h2>
     * <pre> <code>
     * int v, DP[] = new int[maxChange+1];
     * for (int i = 0; i &lt; coins.length; i++) {
     *      for (int j = coins[i]; j &lt;= maxChange; j++) {
     *          v = DP[j - coins[i]] + 1;
     *          if (v &lt; DP[j]) {
     *              DP[j] = v;
     *          }
     *      }
     * }
     *  </code> </pre>
     */
    public int CoinChange(int coins[], int maxChange) {
        int v, DP[] = new int[maxChange + 1], MAP[] = new int[maxChange + 1], Prec[] = new int[maxChange + 1];
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= maxChange; j++) {
                v = DP[j - coins[i]] + 1;
                if (v < DP[j] || DP[j] == 0) {
                    DP[j] = v;
                    Prec[j] = j - coins[i];
                    MAP[j] = coins[i];
                }
            }
        }
        makePrecedence(DP[maxChange], MAP, Prec, maxChange);
        return DP[maxChange];
    }

    /**
     * Build the precedence
     *
     * @param size
     * @param MAP
     * @param Prec
     * @param lastpos
     * @return SOL global variable
     */
    public int[] makePrecedence(int size, int MAP[], int[] Prec, int lastpos) {
        SOL = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            SOL[i] = MAP[lastpos];
            lastpos = Prec[lastpos];
        }
        return SOL;
    }

    /**
     * Test Case 1
     * <h2> Code </h2>
     * <pre> <code>
     * int V[] = {1, 5, 6, 11}, S = 16;
     * int value = CoinChange(V, S);
     * System.out.printf("Test case 1: Change coins=%d\n", value);
     * System.out.println("Precedence:");
     * for (int i = 0; i &lt; SOL.length; i++) {
     *      System.out.print(SOL[i] + " ");
     * }
     * </code> </pre>
     */
    public void testCase1() {
        int V[] = {1, 5, 6, 11}, S = 16;
        int value = CoinChange(V, S);
        System.out.printf("Test case 1: Change coins=%d\n", value);
        System.out.println("Precedence:");
        for (int i = 0; i < SOL.length; i++) {
            System.out.print(SOL[i] + " ");
        }
    }

    /**
     * Test Case 1
     * <h2> Code </h2>
     * <pre> <code>
     * int V[] = {1, 5, 6, 11}, S = 15;
     * int value = CoinChange(V, S);
     * System.out.printf("Test case 1: Change coins=%d\n", value);
     * System.out.println("Precedence:");
     * for (int i = 0; i &lt; SOL.length; i++) {
     *      System.out.print(SOL[i] + " ");
     * }
     * </code> </pre>
     */
    public void testCase2() {
        int V[] = {1, 5, 6, 11}, S = 15;
        int value = CoinChange(V, S);
        System.out.printf("Test case 1: Change coins=%d\n", value);
        System.out.println("Precedence:");
        for (int i = 0; i < SOL.length; i++) {
            System.out.print(SOL[i] + " ");
        }
    }

}
