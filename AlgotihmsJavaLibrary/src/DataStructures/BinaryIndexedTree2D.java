package DataStructures;

import algotihmsjavalibrary.AJL;

/**
 * Binary Indexed (Fenwick) Tree 2D
 * <pre>
 * The Fenwick Tree is a useful data structure for implementing
 * dynamic cumulative frequency tables.
 * [1] page.84, [6] page.86
 * https://www.geeksforgeeks.org/two-dimensional-binary-indexed-tree-or-fenwick-tree/
 * </pre>
 *
 * @author Gregorio Ferrer Cordova
 * @author lmperez
 * @see AJL
 * @see BinaryIndexedTree1D
 */
public class BinaryIndexedTree2D {

    public BinaryIndexedTree2D() {
    }

    int rows, cols;
    int BIT[][];

    /**
     * Update the value in the position [i, j] in O(log NM).
     *
     * @param i index x-coordinate
     * @param j index y-coordinate
     * @param value add the current value
     *
     * <h2> Code: </h2>
     * <pre><code>
     * int y;
     * while (i &lt;= rows) {
     * y = j;
     *      while (y &lt;= cols) {
     *          BIT[i][y] += value;
     *          y += LSOne(y);
     *      }
     * i += LSOne(i);
     * }
     * </code></pre>
     */
    public void update(int i, int j, int value) {
        int y;
        while (i <= rows) {
            y = j;
            while (y <= cols) {
                BIT[i][y] += value;
                y += LSOne(y);
            }
            i += LSOne(i);
        }
    }

    /**
     * Sum the cummulative values in the sub-matrix [i, j, 0, 0] in O(log NM).
     *
     * @param x index x-coordinate
     * @param y index y-coordinate
     * @return the sum of the sub-matrix
     * 
     * <h2> Code: </h2>
     * <pre><code>
     * int y;
     * while (i &lt;= rows) {
     * y = j;
     *      while (y &lt;= cols) {
     *          BIT[i][y] += value;
     *          y += LSOne(y);
     *      }
     * i += LSOne(i);
     * }
     * </code></pre>
     *
     * @see #LSOne(int)
     */
    public int sumBIT2(int x, int y) {
        int yi, sum = 0;
        while (x > 0) {
            yi = y;
            while (yi > 0) {
                sum += BIT[x][yi];
                yi -= LSOne(yi);
            }
            x -= LSOne(x);
        }
        return sum;
    }

    /**
     * Queries of the form - x1, y1, x2, y2 For example the query- {1, 1, 3, 2}
     * means the sub-matrix- y
     * <pre>
     *   y
     *   /\
     * 1  |       1 2 3 4      Sub-matrix
     * 2  |       5 3 8 1      {2,2,3,4}      --->          3 8 1
     * 3  |       4 6 7 5                                   6 7 5
     * 4  |       2 4 8 9
     *    |
     * ---|------ 1 2 3 4 ----> x
     *    |
     * Hence sum of the sub-matrix = 3+8+1+6+7+5 = 30
     * </pre>
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public int query(int x1, int y1, int x2, int y2) {
        return sumBIT2(x2, y2) - sumBIT2(x1 - 1, y2) - sumBIT2(x2, y1 - 1) + sumBIT2(x1 - 1, y1 - 1);
    }

    /**
     * <h2>Less Significant Bit|One</h2>
     * <h3>Example 1: </h3> For x = 11 base 10, x = 1011 base 2. The -x = 1001
     * base 2, so the result is 0001.
     * <h3>Example 2: </h3> For x = 6 base 10, x = 110 base 2. The -x = 010 base
     * 2, so the result is 010.
     *
     * @param x a positive integer
     * @return x & -x
     */
    public int LSOne(int x) {
        return x & -x;
    }

    /**
     * Test Case 1
     *
     * Sum all the numbers in the range [x1, y1] - [x2, y2], where x1, y1, x2
     * and y2 are indexes.
     * <h2> Code </h2>
     * <pre> <code>
     * int arr[][] = {{1, 2, 3, 4}, {5, 3, 8, 1}, {4, 6, 7, 5}, {2, 4, 8, 9}}; // the original array
     * for (int i = 0; i &lt; arr.length; i++) {
     *      for (int j = 0; j &lt; arr.length; j++) {
     *          update(i, j, arr[i][j]);
     *      }
     * }
     * </code> </pre>
     * <h2> Operations:</h2>
     * <pre>
     * Query(2, 2, 3, 4) = 30
     * Query(1, 3, 1, 4) = 7
     * Query(3, 2, 3, 2) = 6
     * </pre>
     */
    public void testCase1() {
        int arr[][] = {{1, 2, 3, 4}, {5, 3, 8, 1}, {4, 6, 7, 5}, {2, 4, 8, 9}}; // the original array
        rows = arr.length;
        cols = arr[0].length;
        BIT = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                update(i, j, arr[i - 1][j - 1]);// - query(i, j, i, j));
            }
        }
        System.out.println("Test Case 1:");
        System.out.printf("%d\n", query(2, 2, 3, 4));   //  = 30
        System.out.printf("%d\n", query(1, 3, 1, 4));   //  = 7
        System.out.printf("%d\n", query(3, 2, 3, 2));   //  = 6
    }

}
