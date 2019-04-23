package GraphTheory;

import Classes.Point;

/**
 * Traveling Salesman Bitonic
 * <pre>
 * [1] page.339, [7] page.56
 * Given a list of coordinates of n vertices on 2D Euclidean space that are already
 * sorted by x-coordinates (and  if tie, by y-coordinates), find a tour that starts
 * from the leftmost vertex, then goes strictly from left to right, and then upon
 * reaching the rightmost vertex, the tour goes strictly from right to left back
 * to the starting vertex. This tour behavior is called ‘bitonic’.
 * </pre>
 *
 * @author lmperez
 */
public class TravelingSalesmanBitonic {

    public TravelingSalesmanBitonic() {
    }

    Point[] Poly;
    double memo2d[][];
    int n;

    /**
     * Find the route. called with dp2(0, 0)
     * @param p1
     * @param p2
     * @return 
     */
    double bitonicTravelingSalesman(int p1, int p2) {             
        int v = 1 + Math.max(p1, p2); // this single line speeds up Bitonic TSP tour
        if (v == n - 1) {
            return Poly[p1].dist(Poly[v]) + Poly[v].dist(Poly[p2]);
        }
        if (memo2d[p1][p2] > -0.5) {
            return memo2d[p1][p2];
        }
        return memo2d[p1][p2] = Math.min(
                Poly[p1].dist(Poly[v]) + bitonicTravelingSalesman(v, p2), // extend LR path: p1->v, RL stays: p2
                Poly[v].dist(Poly[p2]) + bitonicTravelingSalesman(p1, v)); // LR stays: p1, extend RL path: p2<-v
    }

    /**
     * Test Case 1
     *
     * <pre>
     * This points are already sorted, see [1], but you can sort it with
     * compPointByX method
     * </pre>
     * <h2> Code </h2>
     * <pre> <code>
     * n = 7;
     * Poly = new Point[n];
     * Poly[0] = new Point(0, 6);
     * Poly[1] = new Point(1, 0);
     * Poly[2] = new Point(2, 3);
     * Poly[3] = new Point(5, 4);
     * Poly[4] = new Point(6, 1);
     * Poly[5] = new Point(7, 5);
     * Poly[6] = new Point(8, 2);
     * memo2d = new double[n][n];
     * for (int i = 0; i &lt; n; i++) {
     *      for (int j = 0; j &lt; n; j++) {
     *          memo2d[i][j] = -1;
     *      }
     * }
     * System.out.println(bitonicTravelingSalesman(0, 0)); // 25.584024594691332
     * </code> </pre>
     */
    public void testCase1() {
        n = 7;
        Poly = new Point[n];
        Poly[0] = new Point(0, 6);
        Poly[1] = new Point(1, 0);
        Poly[2] = new Point(2, 3);
        Poly[3] = new Point(5, 4);
        Poly[4] = new Point(6, 1);
        Poly[5] = new Point(7, 5);
        Poly[6] = new Point(8, 2);
        memo2d = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo2d[i][j] = -1;
            }
        }
        System.out.println(bitonicTravelingSalesman(0, 0)); // 25.584024594691332
    }

    /**
     * Test Case 2
     *
     * <h2> Code </h2>
     * <pre> <code>
     * n = 3;
     * Poly = new Point[n];
     * Poly[0] = new Point(1, 1);
     * Poly[1] = new Point(2, 3);
     * Poly[2] = new Point(3, 1);
     * memo2d = new double[n][n];
     * for (int i = 0; i &lt; n; i++) {
     *      for (int j = 0; j &lt; n; j++) {
     *          memo2d[i][j] = -1;
     *      }
     * }
     * System.out.println(bitonicTravelingSalesman(0, 0)); // 6.47213595499958
     * </code> </pre>
     */
    public void testCase2() {
        n = 3;
        Poly = new Point[n];
        Poly[0] = new Point(1, 1);
        Poly[1] = new Point(2, 3);
        Poly[2] = new Point(3, 1);
        memo2d = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo2d[i][j] = -1;
            }
        }
        System.out.println(bitonicTravelingSalesman(0, 0)); // 6.47213595499958
    }

    /**
     * Test Case 3
     *
     * <h2> Code </h2>
     * <pre> <code>
     * n = 4;
     * Poly[0] = new Point(1, 1);
     * Poly[1] = new Point(2, 3);
     * Poly[2] = new Point(3, 1);
     * Poly[3] = new Point(4, 2);
     * memo2d = new double[n][n];
     * for (int i = 0; i &lt; n; i++) {
     *      for (int j = 0; j &lt; n; j++) {
     *          memo2d[i][j] = -1;
     *      }
     * }
     * System.out.println(bitonicTravelingSalesman(0, 0)); // 7.8863495173726745
     * </code> </pre>
     */
    public void testCase3() {
        n = 4;
        Poly = new Point[n];
        Poly[0] = new Point(1, 1);
        Poly[1] = new Point(2, 3);
        Poly[2] = new Point(3, 1);
        Poly[3] = new Point(4, 2);
        memo2d = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo2d[i][j] = -1;
            }
        }
        System.out.println(bitonicTravelingSalesman(0, 0)); // 7.8863495173726745
    }

}
