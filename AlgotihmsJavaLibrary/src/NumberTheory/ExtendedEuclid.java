package NumberTheory;

/**
 *
 * @author lmperez
 */
public class ExtendedEuclid {

    public ExtendedEuclid() {
    }
    /**
     * Extended Euclid
     * <pre>
     * [1] page.242, [3] page.188, [6] page.204, [7] page.23
     * Suppose a housewife buys apples and oranges with cost of 8.39 SGD. An apple is 25 cents. An orange is 18 cents.
     * How many of each fruit does she buy? 25x + 18y = 839.
     * </pre>
     *
     * @param a
     * @param b
     * @return x,y
     */
    long dd, x, y;

    void extendedEuclid(long a, long b) {
        long x1;
        if (b > a) {
            x1 = a;//if b>a so I used this if condition
            a = b;// result is ok but x and y swaped
            b = x1;
        }
        if (b == 0) {
            x = 1;
            y = 0;
            dd = a;
            return;
        } // base case
        extendedEuclid(b, a % b); // similar as the original gcd
        x1 = x - (a / b) * y;
        x = y;
        y = x1;
    }
}
