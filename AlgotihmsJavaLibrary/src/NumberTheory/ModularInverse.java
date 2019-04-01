package NumberTheory;

/**
 *
 * @author lmperez
 */
public class ModularInverse {
ExtendedEuclid ee;
    public ModularInverse() {
        ee = new ExtendedEuclid();
    }
    
    /**
     * Modular Inverse
     * <pre>
     * [6] page.202
     * </pre>
     *
     * @param a value of the number to modulate
     * @param m the module
     * @param inv the modular inverse
     * @return 0 if it has not solution or 1 in other case
     */
    public long inv;

    public long modInverse1(long a, long m) {
        ee.extendedEuclid(a, m);
        if (ee.dd != 1) {
            return 0; // noSolucion
        }
        inv = (ee.x + m) % m;
        return 1;
    }
    
    public long modInverse2(long a, long m) {
        long exponent = m - 2;
        long result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * a) % m;
            }
            exponent >>= 1;
            a = (a * a) % m;
        }
        return result;
    }

    
}
