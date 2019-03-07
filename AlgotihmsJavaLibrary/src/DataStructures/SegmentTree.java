package DataStructures;

import algotihmsjavalibrary.AJL;

/**
 * Segment Tree
 * <pre>
 * Is a data structure which can efficiently answer dynamic25 range queries.
 * One such range query is the problem of finding the index of the minimum
 * element in an array within range [i..j].
 * [1] page.80, [6] page.89, [6] page.257
 * Problems COJ 1651, 2395
 * </pre>
 *
 * @author lmperez
 * @see AJL
 * @see RMQ
 * @see BinaryIndexedTree1D
 */
public final class SegmentTree {

    public SegmentTree() {
    }

    int N, Q;
    int treeA[];

    /**
     * Initialize the atributes class
     *
     * @param A array of elements
     * @param type 0 for min, 1 for max and 2 for sum.
     *
     * <h2>Code:</h2>
     * <pre>
     * <code>
     * treeA = new int[4 * A.length];
     * create(0, A.length - 1, 1, A, type);
     * </code>
     * </pre>
     *
     * @see SegmentTree#create(int, int, int, int[], int)
     */
    void init(int A[], int type) {
        treeA = new int[4 * A.length];
        create(0, A.length - 1, 1, A, type);
    }

    /**
     * Build the tree of nodes.
     *
     * @param a minimun range index
     * @param b maximum range index
     * @param node value of the node for the range [a,b]
     * @param A array of elements
     * @param type 0 for min, 1 for max and 2 for sum
     *
     * <pre><code>
     * if (a == b) {
     *      treeA[node] = A[a];
     *      return;
     * }
     * create(a, (a + b) / 2, 2 * node, A, type);
     * create((a + b) / 2 + 1, b, 2 * node + 1, A, type);
     * switch (type) {
     *      case 0:
     *          treeA[node] = Math.min(treeA[2 * node], treeA[2 * node + 1]);
     *          break;
     *      case 1:
     *          treeA[node] = Math.max(treeA[2 * node], treeA[2 * node + 1]);
     *          break;
     *      case 2:
     *          treeA[node] = treeA[2 * node] + treeA[2 * node + 1];
     *          break;
     * }
     * </code></pre>
     */
    void create(int a, int b, int node, int A[], int type) {
        if (a == b) {
            treeA[node] = A[a];
            return;
        }
        create(a, (a + b) / 2, 2 * node, A, type);
        create((a + b) / 2 + 1, b, 2 * node + 1, A, type);
        switch (type) {
            case 0:
                treeA[node] = Math.min(treeA[2 * node], treeA[2 * node + 1]);
                break;
            case 1:
                treeA[node] = Math.max(treeA[2 * node], treeA[2 * node + 1]);
                break;
            case 2:
                treeA[node] = treeA[2 * node] + treeA[2 * node + 1];
                break;
        }
    }

    /**
     * Make a query to the segment tree.
     *
     * @param a minimun query index
     * @param b maximun query index
     * @param ini minimun global query index
     * @param fin maximun global query index
     * @param node value of the node for the range [a,b]
     * @param type 0 for min, 1 for max and 2 for sum
     * @return the min|max|sum of the range [a,b].
     *
     * <h2>Code: </h2>
     * <pre><code>
     * if (b &lt; ini || a > fin) {
     *      return -1;
     * }
     * if (a >= ini && b &lt;= fin) {
     *      return treeA[node];
     * }
     * int A1 = query(a, (a + b) / 2, ini, fin, 2 * node, type);
     * int A2 = query((a + b) / 2 + 1, b, ini, fin, 2 * node + 1, type);
     * if (A1 == -1) {
     *      return A2;
     * }
     * if (A2 == -1) {
     *      return A1;
     * }
     * switch (type) {
     *      case 0:
     *          return Math.min(A1, A2);
     *      case 1:
     *          return Math.max(A1, A2);
     *      case 2:
     *          return A1 + A2;
     * }
     * </code></pre>
     */
    int query(int a, int b, int ini, int fin, int node, int type) {
        if (b < ini || a > fin) {
            return -1;
        }
        if (a >= ini && b <= fin) {
            return treeA[node];
        }
        int A1 = query(a, (a + b) / 2, ini, fin, 2 * node, type);
        int A2 = query((a + b) / 2 + 1, b, ini, fin, 2 * node + 1, type);
        if (A1 == -1) {
            return A2;
        }
        if (A2 == -1) {
            return A1;
        }
        switch (type) {
            case 0:
                return Math.min(A1, A2);
            case 1:
                return Math.max(A1, A2);
            case 2:
                return A1 + A2;
        }
        return 0;
    }

    /**
     * Test Case 1 for Developers Print the minimun of the ranges
     *
     * <h2>Code: </h2>
     * <pre><code>
     * int arr[] = {18, 17, 13, 19, 15, 11, 20}; // the original array
     * init(arr, 0);
     * <h3>Queries: </h3>
     * Query(1, 3) = 13, Query(5, 6) = 11
     * </code></pre>
     *
     * @see SegmentTree#init(int[], int)
     * @see SegmentTree#query(int, int, int, int, int, int)
     */
    public void testCase1() {
        int arr[] = {18, 17, 13, 19, 15, 11, 20}; // the original array
        init(arr, 0); // find the minimun
        System.out.printf("Query(1, 3) = %d\n", query(0, arr.length - 1, 1, 3, 1, 0)); // answer = index 13
        System.out.printf("Query(5, 6) = %d\n", query(0, arr.length - 1, 5, 6, 1, 0)); // answer = index 11
    }

    /**
     * Test Case 2 for Developers Print the max of the ranges
     *
     * <h2>Code: </h2>
     * <pre><code>
     * int arr[] = {18, 17, 13, 19, 15, 11, 20}; // the original array
     * init(arr, 1);
     * <h3>Queries: </h3>
     * Query(1, 3) = 19, Query(5, 6) = 20
     * </code></pre>
     *
     * @see SegmentTree#init(int[], int)
     * @see SegmentTree#query(int, int, int, int, int, int)
     */
    public void testCase2() {
        int arr[] = {18, 17, 13, 19, 15, 11, 20}; // the original array
        init(arr, 1); // find the maximun
        System.out.printf("Query(1, 3) = %d\n", query(0, arr.length - 1, 1, 3, 1, 1)); // answer = index 19
        System.out.printf("Query(5, 6) = %d\n", query(0, arr.length - 1, 5, 6, 1, 1)); // answer = index 20
    }

    /**
     * Test Case 3 for Developers Print the sum of the ranges
     *
     * <h2>Code: </h2>
     * <pre><code>
     * int arr[] = {18, 17, 13, 19, 15, 11, 20}; // the original array
     * init(arr, 2);
     * <h3>Queries: </h3>
     * Query(1, 3) = 49, Query(5, 6) = 31
     * </code></pre>
     *
     * @see SegmentTree#init(int[], int)
     * @see SegmentTree#query(int, int, int, int, int, int)
     */
    public void testCase3() {
        int arr[] = {18, 17, 13, 19, 15, 11, 20}; // the original array
        init(arr, 2); // find the maximun
        System.out.printf("Query(1, 3) = %d\n", query(0, arr.length - 1, 1, 3, 1, 2)); // answer = index 49
        System.out.printf("Query(5, 6) = %d\n", query(0, arr.length - 1, 5, 6, 1, 2)); // answer = index 31
    }
}
