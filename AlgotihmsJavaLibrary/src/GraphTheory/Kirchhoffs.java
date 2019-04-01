package GraphTheory;

import Matemathics.GaussJordan;

/**
 * Kirchhoff's Theorem (Number of spanning trees)
 * <pre>
 * If a graph is a complete graph with n vertices, then total number of spanning
 * trees is n^(n-2) where n is the number of nodes in the graph. In complete graph,
 * the task is equal to counting different labeled trees with n nodes for which
 * have Cayley’s formula.
 * Total number of Spanning trees in a Cycle Graph
 * https://www.geeksforgeeks.org/total-number-of-spanning-trees-in-a-cycle-graph/
 * So, the number of spanning treess will always be equal to the number of vertices in a cycle graph.
 * What if graph is not complete?
 * Follow the given procedure :-
 * STEP 1: Create Adjacency Matrix for the given graph.
 * STEP 2: Replace all the diagonal elements with the degree of nodes. For eg. element at (1,1) position of adjacency matrix will be replaced by the degree of node 1, element at (2,2) position of adjacency matrix will be replaced by the degree of node 2, and so on.
 * STEP 3: Replace all non-diagonal 1’s with -1.
 * STEP 4: Calculate co-factor for any element.
 * STEP 5: The cofactor that you get is the total number of spanning tree for that graph.
 * https://en.wikipedia.org/wiki/Kirchhoff%27s_theorem
 * https://www.geeksforgeeks.org/total-number-spanning-trees-graph/
 * https://en.wikipedia.org/wiki/Cholesky_decomposition
 * [6] page.223
 *
 * Problems
 * https://app.codesignal.com/interview-practice/task/TJ7hGeey6JYNFjBBg/description
 * https://www.codechef.com/problems/STARROAD
 * http://www.spoj.com/problems/KPMAZE/
 * https://github.com/TomConerly/Competition-Programming/blob/master/TopCoder/Hard/RoadsOfKingdom.java
 * </pre>
 *
 * @author lmperez
 */
public class Kirchhoffs {

    public Kirchhoffs() {
    }

    /**
     * Count the number of spanning trees on a given graph.
     *
     * @param AdjMat
     * @return number of spanning trees
     * <h2> Code </h2>
     * <pre> <code>
     * int n = AdjMat.length, degree;
     * double LaplacianMatrix[][] = new double[n][n];
     * for (int i = 0; i &lt; n; i++) {
     *      degree = 0;
     *      for (int j = 0; j &lt; n; j++) {
     *              degree += AdjMat[i][j];
     *              LaplacianMatrix[i][j] = -AdjMat[i][j];
     *      }
     *      LaplacianMatrix[i][i] = degree;
     * }
     * GaussJordan gj = new GaussJordan();
     * return (int) gj.gaussJordan(LaplacianMatrix, new double[n][n], n - 1);
     * </code> </pre>
     */
    public int spanningTrees(int[][] AdjMat) {
        int n = AdjMat.length, degree;
        double LaplacianMatrix[][] = new double[n][n];
        for (int i = 0; i < n; i++) {
            degree = 0;
            for (int j = 0; j < n; j++) {
                degree += AdjMat[i][j];
                LaplacianMatrix[i][j] = -AdjMat[i][j];
            }
            LaplacianMatrix[i][i] = degree;
        }
        GaussJordan gj = new GaussJordan();
        return (int) gj.gaussJordan(LaplacianMatrix, new double[n][n], n - 1);
    }

    /**
     * Print the matrix
     *
     * @param LaplacianMatrix
     * <h2> Code </h2>
     * <pre> <code>
     *for (double[] L : LaplacianMatrix) {
     *      for (double M : L) {
     *          System.out.print(M + " ");
     *      }
     *      System.out.println();
     *}
     * </code> </pre>
     */
    public void showMatrix(double LaplacianMatrix[][]) {
        for (double[] L : LaplacianMatrix) {
            for (double M : L) {
                System.out.print(M + " ");
            }
            System.out.println();
        }
    }

    /**
     * Tess case 1
     * <h2> Code </h2>
     * <pre> <code>
     * int tree[][] = {{0, 0, 1, 1}, {0, 0, 1, 1}, {1, 1, 0, 1}, {1, 1, 1, 0}};
     *      int value = spanningTrees(tree);
     * System.out.println(value);
     * </code> </pre>
     */
    public void tessCase1() {
        int tree[][] = {{0, 0, 1, 1}, {0, 0, 1, 1}, {1, 1, 0, 1}, {1, 1, 1, 0}};
        int value = spanningTrees(tree);
        System.out.println(value);
    }

}
