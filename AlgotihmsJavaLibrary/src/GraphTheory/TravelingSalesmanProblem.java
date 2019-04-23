package GraphTheory;

/**
 * Traveling Salesman Problem (TSP)
 * <pre>
 * [1] page.135
 * Given n cities and their pairwise distances in the form of a matrix dist of size
 * n×n, compute the cost of making a tour that starts from any city s, goes through all the
 * other n − 1 cities exactly once, and finally returns to the starting city s. [1]
 * </pre>
 *
 * @author lmperez
 */
public class TravelingSalesmanProblem {

    double[][] dist;
    int source;

    public TravelingSalesmanProblem() {
    }

    /**
     * Find the shortest weight for TSP
     *
     * @param dist matrix of distances
     * @param source initial node, must be the final node too
     * @return total weight for the route
     *
     * <h2> Code </h2>
     * <pre> <code>
     * double sol = Double.MAX_VALUE;
     * this.dist = dist;
     * this.source = source;
     * for (int i = 0; i &lt; dist.length; i++) {
     *      if (source != i) {
     *          sol = Math.min(sol, dist[source][i] + tsp(i, 1 | (1 &lt;&lt; i)));
     *      }
     * }
     * return sol;
     * </code> </pre>
     */
    public double findTSP(double[][] dist, int source) {
        double sol = Double.MAX_VALUE;
        this.dist = dist;
        this.source = source;
        for (int i = 0; i < dist.length; i++) {
            if (source != i) {
                sol = Math.min(sol, dist[source][i] + tsp(i, 1 | (1 << i)));
            }
        }
        return sol;
    }

    /**
     * Compute the shortest weighted route
     *
     * @param pos current node for the solution
     * @param mask bit mask for visited nodes
     * @return the shortest weighted route for the rest of nodes
     *
     * <h2> Code </h2>
     * <pre> <code>
     * if (mask == (1 &lt;&lt; dist.length) - 1) {
     *      return dist[pos][source];
     * }
     * double sol = Double.MAX_VALUE;
     * for (int i = 0; i &lt; dist.length; i++) {
     *      if (pos != i && (mask & (1 &lt;&lt; i)) == 0) {
     *          sol = Math.min(sol, dist[pos][i] + tsp(i, mask | (1 &lt;&lt; i)));
     *      }
     * }
     * return sol;
     * </code> </pre>
     */
    public double tsp(int pos, int mask) {
        if (mask == (1 << dist.length) - 1) {
            return dist[pos][source];
        }
        double sol = Double.MAX_VALUE;
        for (int i = 0; i < dist.length; i++) {
            if (pos != i && (mask & (1 << i)) == 0) {
                sol = Math.min(sol, dist[pos][i] + tsp(i, mask | (1 << i)));
            }
        }
        return sol;
    }

    /**
     * Test Case 1
     *
     * <h2> Code </h2>
     * <pre> <code>
     * double dist[][] = {{0, 20, 42, 35}, {20, 0, 30, 34}, {42, 30, 0, 12}, {35, 34, 12, 0}};
     * int source = 0;
     * System.out.println(findTSP(dist, source));
     * </code> </pre>
     */
    public void testCase1() {
        double dist[][] = {{0, 20, 42, 35}, {20, 0, 30, 34}, {42, 30, 0, 12}, {35, 34, 12, 0}};
        int source = 0;
        System.out.println(findTSP(dist, source));
    }

}
