package DivideAndConquer;

/**
 *
 * @author lmperez
 */
public class BinarySearch {

    public BinarySearch() {
    }
    
    /**
     * Binary Search
     * <pre>
     * [1], [3] page.113, [6] page.31
     * </pre>
     *
     * @param A array sorted
     * @param x element to find
     * @return position of the element or where should be find out.
     */
    public int BinarySearch(int A[], int x) {
        int m, begin = 0, end = A.length - 1;
        while (begin <= end) {
            m = (begin + end) / 2;
            if (A[m] == x) {
                if (m - 1 >= begin && A[m - 1] == x) {//Para el caso de que existan numeros repetidos tenemos que buscar la primera ocurrencia.
                    end = m - 1;
                } else {
                    return m;
                }
            } else if (A[m] < x) {
                begin = m + 1;
            } else {
                end = m - 1;
            }
        }
        return begin;//retorna la posicion en que esta y si no lo encuentra retorna donde deberia estar antes. 
    }
}
