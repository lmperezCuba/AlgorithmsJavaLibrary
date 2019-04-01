package Backtracking;

/**
 * N Queens Chess (Classical)
 * <pre>
 * Find the amount of valid positions for n queens in a nxn chess board.
 * [1] page.99
 * </pre>
 *
 * @author lmperez
 * @see AJL
 */
public class N_QueensChessClassical {

    public N_QueensChessClassical() {
    }

    int NMAX = 100;
    int a_EQueens[] = new int[NMAX], count_solutions, n_EQueens;
    boolean val;

    /**
     * Main methods. Count all the possibles permutations.
     *
     * @param n number of queens
     * @return count_solutions
     *
     * <h2> Code </h2>
     * <pre> <code>
     * n_EQueens = n;
     * count_solutions = 0;
     * next_moved(1);
     * </code> </pre>
     *
     * @see #next_moved(int)
     */
    public int n_Queens(int n) {
        n_EQueens = n;
        count_solutions = 0;
        next_moved(1);
        return count_solutions;
    }

    /**
     * Check that don't exist colisions.
     *
     * @param row for the new queen
     * @param col for the new queen
     * @return true if it is a valid position or false otherwise.
     *
     * <h2> Code </h2>
     * <pre> <code>
     * for (int i = 1; i &lt; col; i++) {
     *      if (a_EQueens[i] == row || (Math.abs(row - a_EQueens[i]) == Math.abs(col - i))) {
     *          return false;
     *      }
     * }
     * </code> </pre>
     */
    public boolean can_move(int row, int col) {
        for (int i = 1; i < col; i++) {
            if (a_EQueens[i] == row || (Math.abs(row - a_EQueens[i]) == Math.abs(col - i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Count a new step and look for the next queen valid position.
     *
     * @param k k-th queen to search
     *
     * <h2> Code </h2>
     * <pre> <code>
     * if (k > n_EQueens) {
     *      count_solutions++;
     *      //printSolution();
     *      return;
     * }
     * for (int i = 1; i &lt;= n_EQueens; i++) {
     *      if (can_move(i, k)) {
     *          a_EQueens[k] = i;
     *          next_moved(k + 1);
     *      }
     * }
     * </code> </pre>
     *
     * @see #can_move(int, int)
     */
    public void next_moved(int k) {
        if (k > n_EQueens) {
            count_solutions++;
            //printSolution();
            return;
        }
        for (int i = 1; i <= n_EQueens; i++) {
            if (can_move(i, k)) {
                a_EQueens[k] = i;
                next_moved(k + 1);
            }
        }
    }

    public void printSolution() {
        for (int i = 1; i <= n_EQueens; i++) {
            System.out.print(a_EQueens[i] + " ");
        }
        System.out.println("");
    }

    /**
     * Test Case 1
     *
     * Show the the amount of valid position for n queens from 1 to 14.
     * <h2> Code </h2>
     * <pre> <code>
     * for (int i = 1; i &lt;= 14; i++) {
     *      System.out.println(n_Queens(i));
     * }
     * </code> </pre>
     * <h2> Solutions:</h2>
     * <pre>
     * For 1 queen:     1
     * For 2 queens:    0
     * For 3 queens:    0
     * For 4 queens:    2
     * For 5 queens:    10
     * For 6 queens:    4
     * For 7 queens:    40
     * For 8 queens:    92
     * For 9 queens:    352
     * For 10 queens:   724
     * For 11 queens:   2680
     * For 12 queens:   14200
     * For 13 queens:   73712
     * For 14 queens:   365596
     * </pre>
     *
     * @see #n_Queens(int)
     */
    public void testCase1() {
        for (int i = 1; i <= 14; i++) {
            System.out.println(n_Queens(i));
        }
    }

}
