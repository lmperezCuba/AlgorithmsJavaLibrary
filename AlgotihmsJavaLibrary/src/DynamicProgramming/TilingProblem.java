package DynamicProgramming;

/**
 * Tiling Problem
 * <pre>
 * Given a "2 x n" board and tiles of size "2 x 1", count the number of ways to
 * tile the given board using the 2 x 1 tiles. A tile can either be placed
 * horizontally i.e., as a 1 x 2 tile or vertically i.e., as 2 x 1 tile.
 * See explanation at https://www.geeksforgeeks.org/tiling-problem/
 * and some variations at:
 * https://www.geeksforgeeks.org/tiling-with-dominoes/
 * https://www.geeksforgeeks.org/count-number-of-ways-to-fill-a-n-x-4-grid-using-1-x-4-tiles/
 * https://www.geeksforgeeks.org/count-number-ways-tile-floor-size-n-x-m-using-1-x-m-size-tiles/
 * For me the best explanation for all cases is founded  at: (the second one)
 * https://math.stackexchange.com/questions/664113/count-the-ways-to-fill-a-4-times-n-board-with-dominoes
 * CodeSignal Problem:
 * https://app.codesignal.com/interview-practice/task/yWF4MmhvtmzfKNWgt/description
 * </pre>
 *
 * @author lmperez
 */
public class TilingProblem {

    int MOD;

    /**
     * Constructor
     *
     * @param MOD module for the answer
     */
    public TilingProblem(int MOD) {
        this.MOD = MOD;
    }

    /**
     * Tiling 2xn and fit 1x2
     * <pre>
     *  There are just 2 ending states.
     * A    |       B   __
     *      |           __
     * So f(n) = f(n-1) + f(n-2)
     * Easy right? Fibonacci secuence!!
     * https://www.geeksforgeeks.org/tiling-problem/
     * </pre>
     *
     * @param n
     * @return the amount of combinations
     */
    public int tilingProblemClassical(int n) {
        int N = Math.max(n, 2), f[] = new int[N + 1];
        f[0] = f[1] = 1;
        for (int i = 2; i <= N; i++) {
            f[i] = f[i - 1] + f[i - 2];
            f[i] %= MOD;
        }
        return f[n];
    }

    /**
     * Given a 3 x n board, find the number of ways to fill it with 2 x 1
     * dominoes.
     * <pre>
     *  There are 5 ending states.
     * A    __      B   __      C   | |     D     __    E    __
     *      __          | |         | |         | __        | __
     *      __          | |          __         |__         | __
     *
     * f(n) = f(A) + f(B) + f(C) + f(D) + f(E),
     * ->   f(A) = f(B) = f(C) = f(n-2)
     * f(n) = 3*f(n-2) + f{D,C}(n-2) + f{E,B}(n-2),
     * ->   f{A,B,C,D,E} = f(n-2)
     * f(n) = 3*f(n-2) + f(n-2) - f{A}(n-2)
     * f(n) = 4*f(n-2) - f(n-4)
     * See https://math.stackexchange.com/questions/664113/count-the-ways-to-fill-a-4-times-n-board-with-dominoes
     * for the technique understanding, and see
     * https://www.geeksforgeeks.org/tiling-with-dominoes/ for another technique.
     * </pre>
     *
     * @param n
     * @return the amount of combinations
     */
    public int tilingWithDominoes(int n) {
        int N = Math.max(n, 3), f[] = new int[N + 1];
        f[0] = 1;
        f[1] = 0;
        f[2] = 3;
        for (int i = 3; i <= N; i++) {
            f[i] = 4 * f[i - 2];
            if (i - 4 >= 0) {
                f[n] -= f[i - 4];
            }
            f[i] %= MOD;
        }
        return f[n];
    }

    /**
     * You have a block with the dimensions 4 × n. Find the number of different
     * ways you can fill this block with smaller blocks that have the dimensions
     * 1 × 2. You are allowed to rotate the smaller blocks.
     * <pre>
     *  There are 8 ending states.
     * A  |     B   __      C   __      D  | |    E    __    F | __     G __    H __
     *    |         __          __         | |        |        | __       __     | __
     *    |         __          | |         __        |         __       | __    | __
     *    |         __          | |         __         __       __       | __     __
     *
     * f(n) = f(A) + f(B) + f(C) + f(D) + f(E) + f(F) + f(G),
     * ->   f(B) = f(C) = f(D) = f(E) = f(n-2)
     * f(n) = f(n-1) + 4*f(n-2) + f{F,A,D}(n-2) + f{G,A,C}(n-2) + f{H,E}(n-2),
     * ->   f{A,B,C,D,E,F,G,H} = f(n-2)
     * f(n) = f(n-1) + 4*f(n-2) + f{F,A,C,D,E,F,G,H}(n-2) + f{A}(n-2)
     * ->   f{B}(n-2) does not appear, it is the complement
     * f(n) = f(n-1) + 4*f(n-2) + f(n-2) - f{B}(n-2) + f{A}(n-2)
     * f(n) = f(n-1) + 5*f(n-2)          - f(n-4)    + f(n-3)
     * f(n) = f(n-1) + 5*f(n-2) + f(n-3) - f(n-4)
     * See https://math.stackexchange.com/questions/664113/count-the-ways-to-fill-a-4-times-n-board-with-dominoes
     * for the technique understanding, and see
     * https://www.geeksforgeeks.org/tiling-with-dominoes/ for another technique.
     * </pre>
     *
     * @param n
     * @return the amount of combinations
     */
    public int tiling4xn(int n) {
        int N = Math.max(n, 2), f[] = new int[N + 1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <= N; i++) {
            f[i] = f[i - 1] + 5 * f[i - 2];
            f[i] %= MOD;
            if (i - 3 >= 0) {
                f[i] += f[i - 3];
            }
            if (i - 4 >= 0) {
                f[i] -= f[i - 4];
            }
            f[i] %= MOD;
        }
        return (f[n] + MOD) % MOD;
    }

}
