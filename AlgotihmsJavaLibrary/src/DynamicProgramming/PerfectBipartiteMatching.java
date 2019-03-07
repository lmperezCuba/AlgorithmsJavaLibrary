package DynamicProgramming;

/**
 *
 * @author lmperez
 * @version 1.0
 * @since 1.0
 * @tags minimum weight perfect matching on a small general weighted graph
 */
public class PerfectBipartiteMatching {

    public PerfectBipartiteMatching() {
    }
    
    double [][] dist;     // Matrix of distances
    int target, n;         // All combinations. For n==8 will be (1<8)-1 
    double PerfectMatch(int bitmask, double value){
        if(bitmask==target)return value;
        double ans = Double.MAX_VALUE;
        int i, bit;
        for (i = 0; i < n; i++) {
            if((bitmask & (1 << i)) == 0){
                bit = 1 << i;
                break;
            }            
        }
        i++;
        for (; i < n; i++) {
            if((bitmask & (1 << i)) == 0){
                bit = 1 << i;
                break;
            }            
        }
        return ans;
    }
    
    //UVA - 10911 Bipartite matching
    
}
