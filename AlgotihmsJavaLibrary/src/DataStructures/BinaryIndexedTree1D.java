package DataStructures;

import algotihmsjavalibrary.AJL;

/**
 * Binary Indexed (Fenwick) Tree 1D
 * <pre>
 * The Fenwick Tree is a useful data structure for implementing
 * dynamic cumulative frequency tables. You can sum the amount of repetitions of
 * the numbers in the range [i, j] where i and j are values, update(value, 1)
 * that minds 1 repetition of the value.
 * Also you can find the sum of all numbers in the range [i, j] where i and j are
 * indexes, update(i, value), there will be only one repetition of each index.
 * You can use a cummulative table if you array do not update.
 * [1] page.84, [6] page.86
 * https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/#_=_
 * </pre>
 *
 * @author lmperez
 * @see AJL
 * @see BinaryIndexedTree2D
 */
public class BinaryIndexedTree1D {

    public BinaryIndexedTree1D() {
    }

    /**
     * The array is 1 base.
     */
    public int ft[];

    /**
     * Sum the amount of numbers in the range [1, b]. How many 1s + 2s + 3s +
     * ... + bs;
     * <p>
     * <b>Note: Does not sum all <i>values</i> in the range [1, b]. If you want
     * to know the sum of all numbers in the range [1, b] use a cumulative table
     * instead.</b></p>
     *
     * @param b the value to compute
     * @return sum
     * <h3>Code:</h3>
     * <pre>
     * <code>
     *int sum = 0;
     * for (; b > 0; b -= LSOne(b)) {
     *      sum += ft[b];
     * }
     * </code>
     * </pre>
     *
     * @see BinaryIndexedTree1D#LSOne(int)
     */
    public int rsq(int b) {
        int sum = 0;
        for (; b > 0; b -= LSOne(b)) {
            sum += ft[b];
        }
        return sum;
    }

    /**
     * Sum the amount of numbers in the range [A, B]. How many As + ... + Bs.
     * <p>
     * <b>Example: </b> For {2, 4, 2, 6, 7, 3, 6, 1} and [2,6] return 5.
     * </p>
     *
     * @param a the lower bound
     * @param b the upper bound
     * @return <code>rsq(b) - (a == 1 ? 0 : rsq(a - 1));</code>
     * @see BinaryIndexedTree1D#rsq(int)
     */
    public int sum(int a, int b) { // returns RSQ(a, b)
        return rsq(b) - (a == 1 ? 0 : rsq(a - 1));
    }

    /**
     * Update the frecuencies for k value v times more.
     *
     * @param k value for the update
     * @param v aumented repetitions
     * <h2> Code </h2>
     * <pre> <code>
     * for (; k &lt; ft.length; k += LSOne(k)) {
     *      ft[k] += v;
     * }
     * </code> </pre>
     *
     * @see BinaryIndexedTree1D#LSOne(int)
     */
    public void update(int k, int v) {
        for (; k < ft.length; k += LSOne(k)) {
            ft[k] += v;
        }
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
     * Sum the repetitions of numbers in the range [i, j], where i and j are
     * values.
     * <h2> Code </h2>
     * <pre> <code>
     * arr = {2, 4, 5, 5, 6, 6, 6, 7, 7, 8, 9}; // the original array
     *  MAXN = 10;  // Max value of the array
     *  ft = new int[MAXN + 1];
     *  for (int i = 0; i &lt; arr.length; i++) {
     *      update(arr[i], 1);// this is O(k log n)
     *  }
     * </code> </pre>
     * <h2> Operations:</h2>
     * <pre>
     * sum(1, 1)        // 0 => ft[1] = 0
     * sum(1, 2)        // 1 => ft[2] = 1
     * sum(1, 6)        // 7 => ft[6] + ft[4] = 5 + 2 = 7
     * sum(1, 10)       // 11 => ft[10] + ft[8] = 1 + 10 = 11
     * sum(3, 6)        // 6 => rsq(1, 6) - rsq(1, 2) = 7 - 1
     * update(5, 2)     // update demo
     * sum(1, 10)       // now 13
     * </pre>
     */
    public void testCase1() {
        int arr[] = {2, 4, 5, 5, 6, 6, 6, 7, 7, 8, 9}; // the original array
        int MAXN = 10;
        ft = new int[MAXN];
        for (int i = 0; i < arr.length; i++) {
            update(arr[i], 1);// this is O(k log n)
        }
        System.out.printf("%d\n", sum(1, 1));
        System.out.printf("%d\n", sum(1, 2));
        System.out.printf("%d\n", sum(1, 6));
        System.out.printf("%d\n", sum(1, 10));
        System.out.printf("%d\n", sum(3, 6));
        update(5, 2);
        System.out.printf("%d\n", sum(1, 10));
    }

    /**
     * Test Case 2
     *
     * Sum all the numbers in the range [i, j], where i and j are indexes.
     * <h2> Code </h2>
     * <pre> <code>
     * arr = {2, 4, 5, 5, 6, 6, 6, 7, 7, 8, 9}; // the original array
     *  MAXN = 10;  // Max value of the array
     *  ft = new int[MAXN + 1];
     *  for (int i = 0; i &lt; arr.length; i++) {
     *      update(arr[i], 1);// this is O(k log n)
     *  }
     * </code> </pre>
     * <h2> Operations:</h2>
     * <pre>
     * sum(1, 6)        // 12 => ft[6] + ft[4] = 5 + 7 = 12
     * update(5, 2)
     * sum(1, 6)        // 18 => ft[6] + ft[4] = 5 + 13 = 18
     * </pre>
     */
    public void testCase2() {
        int arr[] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9}; // the original array
        int MAXN = arr.length + 1;
        ft = new int[MAXN];
        for (int i = 0; i < arr.length; i++) {
            update(i + 1, arr[i]);// this is O(k log n)
        }
        System.out.printf("%d\n", sum(1, 6));
        update(3, 6);
        System.out.printf("%d\n", sum(1, 6));
    }

}
