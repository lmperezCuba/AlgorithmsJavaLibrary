package NumberTheory;

/**
 * Modular Inverse
 * <pre>
 * [6] page.202
 * CodeSignal Problem
 * https://app.codesignal.com/interview-practice/task/tNajEfDckCgSvrjQp
 * </pre>
 *
 * @author lmperez
 */
public class ModularInverse {

    ExtendedEuclid ee;

    public ModularInverse() {
        ee = new ExtendedEuclid();
    }

    public long inv;

    /**
     * Recursive version
     *
     * @param a value of the number to modulate
     * @param m the module
     * @param inv the modular inverse
     * @return 0 if it has not solution or 1 in other case
     */
    public long modInverse1(long a, long m) {
        ee.extendedEuclid(a + m, m);
        if (ee.dd != 1) {
            return 0; // noSolucion
        }
        inv = (ee.x + m) % m;
        return 1;
    }

    /**
     * Linear version
     *
     * @param n value of the number to modulate
     * @param m the module
     * @return the modular inverse or -1 if it has not solution
     */
    public long modInverse2(long n, long m) {
        long t, i = 0, j = 1, k = m;
        while (n != 0) {
            t = i - j * (m / n);
            i = j;
            j = t;
            t = m % n;
            m = n;
            n = t;
        }
        return (m != 1) ? -1 : (i + k) % k;
    }

}
