package DataStructures;

import algotihmsjavalibrary.AJL;

/**
 * Union Find Disjoin Set
 * <pre>
 *
 * The Union-Find Disjoint Set (UFDS) is a data structure to model a collection
 * of disjoint sets with the ability to efficiently in ≈ O(1)—determine which
 * set an item belongs to (or to test whether two items belong to the same set)
 * and to unite two disjoint sets into one larger set.[1]
 * [1] page.77, [6] page.145, [7] page.48
 * COJ Problem 1094
 * </pre>
 *
 * @see AJL
 * @see SegmentTree
 * @see BinaryIndexedTree1D
 * @see CumulativeTable1D
 * @author lmperez
 */
public class UnionFindDisjoinSet {

    public UnionFindDisjoinSet() {
    }

    /**
     * Union-Find Disjoin-Set
     * <pre>
     * [1] page.77, [6] page.145, [7] page.48
     * Problems COJ 1094
     * </pre>
     *
     * @param N elements
     * @param M joins
     * @return ns number of sets
     * @return rank of each set number of sets
     */
    int ns;         // number of disjoin sets
    int P[], rank[], size[];

    /**
     * Initialize all variables.
     *
     * @param _size amount of items
     */
    void iniSet(int _size) {
        ns = _size;
        P = new int[_size];
        rank = new int[_size];
        size = new int[_size];
        for (int i = 0; i < _size; i++) {
            P[i] = i;
            rank[i] = size[i] = 1;
        }
    }

    /**
     * Find the parent of an item and replace all parents of all elements in the
     * process.
     *
     * @param x
     * @return the root of the set. The parent of x.
     */
    int findSet(int x) {
        return (P[x] == x) ? x : (P[x] = findSet(P[x]));
    }

    /**
     * Join two set into one.
     *
     * @param x
     * @param y
     */
    void unionSet(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);
        if (!isSameSet(x, y)) {
            ns--;
            if (rank[px] > rank[py]) {
                P[py] = px;
                size[px] += size[py];
            } else {
                P[px] = py;
                if (rank[px] == rank[py]) {
                    rank[py]++;
                }
                size[py] += size[px];
            }
        }
    }

    /**
     * Check if two items belongs to the same set.
     *
     * @param x
     * @param y
     * @return true if x and y belongs to the same set, or false otherwise.
     */
    boolean isSameSet(int x, int y) {
        return findSet(x) == findSet(y);
    }

    /**
     *
     * @return an integer with the number of disjoin sets.
     */
    int numberOfSet() {
        return ns;
    }

    /**
     * Find the parent of the set where x belongs and retrieve the size of the
     * set.
     *
     * @param x
     * @return an integer with the size of the set.
     */
    int sizeOfSet(int x) {
        return size[findSet(x)];
    }

    /**
     * Find the parent of the set where x belongs and retrieve the ranking of
     * the set.
     *
     * @param x
     * @return an integer with the ranking of the set.
     */
    int rankOfSet(int x) {
        return rank[findSet(x)];
    }
}
