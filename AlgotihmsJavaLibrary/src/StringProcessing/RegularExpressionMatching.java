package StringProcessing;

import java.util.BitSet;

/**
 * Regular Expression Matching
 * <p>
 * Implement regular expression matching with support for '.' and '*', given the
 * following guidelines: '.' Matches any single character. '*' Matches zero or
 * more of the element that comes before it.
 *
 * The matching should cover the entire input string s. If the pattern p matches
 * the input string s, return true, otherwise return false.
 * </p>
 * <pre>
 * Codesignal Problem:
 * https://app.codesignal.com/interview-practice/task/Sx8ndFtwEyCRRqF7q
 * </pre>
 *
 * @author lmperez
 */
public class RegularExpressionMatching {

    public RegularExpressionMatching() {
    }

    /**
     *
     * @param s text
     * @param p pattern
     * @return true if it matches or false otherwise.
     */
    boolean regularExpressionMatching(String s, String p) {
        int m = s.length(), n = p.length();
        BitSet dp = new BitSet(n + 1);
        for (int i = 0; i <= m; i++) {
            boolean pre = dp.get(0);
            dp.set(0, i == 0);
            for (int j = 1; j <= n; j++) {
                boolean temp = dp.get(j);
                if (p.charAt(j - 1) == '*') {
                    dp.set(j, dp.get(j - 2) || (i != 0 && dp.get(j) && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')));
                } else {
                    dp.set(j, i != 0 && pre && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'));
                }
                pre = temp;
            }
        }
        return dp.get(n);
    }

}
