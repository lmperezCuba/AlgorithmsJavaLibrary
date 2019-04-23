package DynamicProgramming;

import GraphTheory.TravelingSalesmanProblem;
import java.util.Arrays;

/**
 * Traveling Salesman Maze Problem (TSMP)
 * <pre>
 * Start from 'S' and visit each '#' exactly once in any of 8 directions.
 * [7] page.57
 * UVA Problem  10944
 * </pre>
 *
 * @author lmperez
 * @see TravelingSalesmanProblem
 */
public class TravelingSalesmanMaze {

    int r6[][], m6[][], n;

    public TravelingSalesmanMaze() {
    }

    /**
     * @param bd board
     * @return the lenght for the tour
     *
     * <h2> Code </h2>
     * <pre> <code>
     * if (bd.length == 0 || bd[0].length == 0) {
     *      return 0;
     * }
     * int i, j, N = bd.length, M = bd[0].length;
     * int x[] = new int[20], y[] = new int[20];
     * r6 = new int[20][20];
     * m6 = new int[1 &lt;&lt; 17][17];
     * n = 1;
     * for (i = 0; i &lt; N; i++) {
     *      for (j = 0; j &lt; M; j++) {
     *          if (bd[i][j] == 'S') {
     *              x[0] = i;
     *              y[0] = j;
     *          }
     *          if (bd[i][j] == '#') {
     *              x[n] = i;
     *              y[n] = j;
     *              n++;
     *          }
     *      }
     * }
     * for (i = 0; i &lt; n; i++) {
     *      for (j = 0; j &lt; n; j++) {
     *          r6[i][j] = Math.max(Math.abs(x[i] - x[j]), Math.abs(y[i] - y[j]));
     *      }
     * }
     * for (int[] m61 : m6) {
     *      Arrays.fill(m61, -1);
     * }
     * return play((1 &lt;&lt; n) - 1, 0);
     * </code> </pre>
     */
    public int travelingSalesmanOnSmallGraph(char bd[][]) {
        if (bd.length == 0 || bd[0].length == 0) {
            return 0;
        }
        int i, j, N = bd.length, M = bd[0].length;
        int x[] = new int[20], y[] = new int[20];
        r6 = new int[20][20];
        m6 = new int[1 << 17][17];
        n = 1;
        for (i = 0; i < N; i++) {
            for (j = 0; j < M; j++) {
                if (bd[i][j] == 'S') {
                    x[0] = i;
                    y[0] = j;
                }
                if (bd[i][j] == '#') {
                    x[n] = i;
                    y[n] = j;
                    n++;
                }
            }
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                r6[i][j] = Math.max(Math.abs(x[i] - x[j]), Math.abs(y[i] - y[j]));
            }
        }
        for (int[] m61 : m6) {
            Arrays.fill(m61, -1);
        }
        return play((1 << n) - 1, 0);
    }

    /**
     * Find the shortest route
     *
     * @param S bit mask
     * @param s current state
     * @return the lenght of the route
     *
     * <h2> Code </h2>
     * <pre> <code>
     * if (S == (1 &lt;&lt; s)) {
     *      return r6[s][0];
     * }
     * int v = m6[S][s];
     * if (v >= 0) {
     *      return v;
     * }
     * v = 100000000;
     * for (int i = 0; i &lt; n; i++) {
     *      if (i != s) {
     *          if ((S & (1 &lt;&lt; i)) != 0) {
     *              v = Math.min(v, r6[s][i] + play(S - (1 &lt;&lt; s), i));
     *          }
     *      }
     * }
     * return v;
     * </code> </pre>
     */
    public int play(int S, int s) {
        if (S == (1 << s)) {
            return r6[s][0];
        }
        int v = m6[S][s];
        if (v >= 0) {
            return v;
        }
        v = 100000000;
        for (int i = 0; i < n; i++) {
            if (i != s) {
                if ((S & (1 << i)) != 0) {
                    v = Math.min(v, r6[s][i] + play(S - (1 << s), i));
                }
            }
        }
        return v;
    }

    /**
     * Test Case 1
     * <pre>
     *  Find the tour for
     *  S   .   .   .   .       Go from S to all # and back
     *  #   .   .   .   .       without pass over any # again.
     *  #   .   .   .   .
     *  .   .   .   .   .
     *  #   .   .   .   .
     * sol = 8
     * </pre>
     * <h2> Code </h2>
     * <pre> <code>
     *  char board[][] = {{'S', '.', '.', '.', '.'}, {'#', '.', '.', '.', '.'}, {'#', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.'}, {'#', '.', '.', '.', '.'}};
     * System.out.println(travelingSalesmanOnSmallGraph(board));
     * </code> </pre>
     */
    public void testCase1() {
        char board[][] = {{'S', '.', '.', '.', '.'}, {'#', '.', '.', '.', '.'}, {'#', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.'}, {'#', '.', '.', '.', '.'}};
        System.out.println(travelingSalesmanOnSmallGraph(board));
    }

}
