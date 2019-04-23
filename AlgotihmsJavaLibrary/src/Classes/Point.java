package Classes;

/**
 *
 * @author lmperez
 */
public class Point implements Comparable<Point> {

    double x, y;
    char sortBy;

    public Point() {
        sortBy = 'x';
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        sortBy = 'x';
    }

    /**
     * Euclidean distance
     * <pre>
     * Sort the points by x-axis. If you can sort it by y-axis change "sortBy='y'".
     *  hypot(dx, dy) returns sqrt(dx * dx + dy * dy)
     * </pre>
     *
     * @param p point to compute the distance
     * @return the euclidian distance
     *
     * <h2> Code </h2>
     * <pre> <code>
     * return Math.hypot(p1.x - p2.x, p1.y - p2.y);
     * </code> </pre>
     */
    public double dist(Point p) {
        return Math.hypot(x - p.x, y - p.y);
    }

    /**
     * Override the compareTo method for Comparable interface.
     *
     * @param o point to compare
     * @return the comparison
     *
     * <h2> Code </h2>
     * <pre> <code>
     * if (Character.toLowerCase(sortBy) == 'x') {
     *      return compareByX(o);
     * }
     * return compareByY(o);
     * </code> </pre>
     */
    @Override
    public int compareTo(Point o) {
        if (Character.toLowerCase(sortBy) == 'x') {
            return compareByX(o);
        }
        return compareByY(o);
    }

    /**
     *
     * @param o point to compare
     * @return the comparison
     *
     * <h2> Code </h2>
     * <pre> <code>
     * if (x &lt; o.x) {
     *      return -1;
     * }
     * if (x > o.x) {
     *      return 1;
     * }
     * if (y &lt; o.y) {
     *      return -1;
     * }
     * if (y > o.y) {
     *      return 1;
     * }
     * return 0;
     * </code> </pre>
     */
    public int compareByX(Point o) {
        if (x < o.x) {
            return -1;
        }
        if (x > o.x) {
            return 1;
        }
        if (y < o.y) {
            return -1;
        }
        if (y > o.y) {
            return 1;
        }
        return 0;
    }

    /**
     *
     * @param o point to compare
     * @return the comparison
     * <h2> Code </h2>
     * <pre> <code>
     * if (x &lt; o.x) {
     *      return -1;
     * }
     * if (x > o.x) {
     *      return 1;
     * }
     * if (y &lt; o.y) {
     *      return -1;
     * }
     * if (y > o.y) {
     *      return 1;
     * }
     * return 0;
     * </code> </pre>
     */
    public int compareByY(Point o) {
        if (x < o.x) {
            return -1;
        }
        if (x > o.x) {
            return 1;
        }
        if (y < o.y) {
            return -1;
        }
        if (y > o.y) {
            return 1;
        }
        return 0;
    }

}
