package algotihmsjavalibrary;

import DynamicProgramming.CoinChange;
import DynamicProgramming.KnapSack0_1_Subset_Sum;
import DynamicProgramming.KnapSackMultiplesObjects;

/**
 * AlgorithmsJavaLibrary
 *
 * <pre>
 * ====================             version 1.0             ====================
 * This is just a library for production usage.
 * https://github.com/lmperezCuba/AlgorithmsJavaLibrary
 * +10 Classical Solutions (CS)
 * ====================                 Usage               ====================
 * <code>
 * 1. In the index each category belongs to one package and each algorithm
 *    belogs to one class.
 * 2. Just start using the class do you want
 * </code>
 * ====================                 Contact             ====================
 * Ing. Luis Manuel Pérez Batista
 * luismanuelp1992@gmail.com
 * (+53) 53855092
 * (Cuba)
 * https://www.linkedin.com/in/lmperezCuba/
 * Profiles
 * - COJ username - (lmperez)
 * - Codefight username - (lmperez)
 * - HackerRank username - (lmperez)
 *
 * ====================             More                ====================
 * - I strongly recommend you to study from GeeksforGeeks
 * (https://www.geeksforgeeks.org)
 * - If you find any bug please contact me, thanks.
 * - I need some help for translate this material into JAVA 11+, Kotlin and Groovy.
 *
 * ====================         Bibliography            ====================
 *
 * - [1] Competitive Programming 3, 2013, (Steven Halim, Felix Halim)
 * - [2] Análisis y diseño de algoritmos (Antonio Vallecillo - Univ. Málaga)
 * - [3] Art of Programming Contest (Ahmed Shamsul Arefin - UVA)
 * - [4] MIT Press Introduction to Algorithms (2nd Edition)
 * - [5] Springer The Algorithm Design Manual (Steve S. Skieva - 2nd Edition 2010)
 * - [6] Competitive Programmer’s Handbook (Antti Laaksonen Draft - July 8, 2017)
 * - [7] The Hitchhiker’s Guide to the Programming Contests (Nite Nimajneb)
 *
 *
 * ====================             Tips                ====================
 * 1. Use Ctrl + F to find the algorithms
 * 2. The NEW algorithms are identified by three asterics (***)
 * 3. The FIXED algorithms are identified by three plus characters(+++)
 * =========================================================================
 * Index (14 Algorithms)
 *
 * =========================================================================
 * 2.Backtracking (1)
 * - N Queens Chess (Classical)                                         ***
 * =========================================================================
 * 3.Data Structure (5)
 * - Binary Indexed (Fenwick) Tree 1D                                   ***
 * - Binary Indexed (Fenwick) Tree 2D                                   ***
 * - RMQ (Range Min|Max Query)                                          ***
 * - Segment Tree (Range Min|Max|Sum Query)                             ***
 * - Union-Find Disjoin-Set                                             ***
 * ========================================================================
 * 4. Dynamic Programming (6)
 * - Coin Change                                                        ***
 * - KnapSack 0 - 1 (Subset Sum)                                        ***
 * - KnapSack Multiples Objects                                         ***
 * - LongestIncreasingSubsequence                                       ***
 * - Max 1D Range Sum (Kadane’s algorithm)                              ***
 * - Max 2D Range Sum                                                   ***
 * ========================================================================
 * 5.Graph Theory (1)
 * - Kirchhoffs                                                         ***
 * ========================================================================
 * 6.Matemathics (1)
 * - Gauss-Jordan                                                       ***
 * 
 * </pre>
 *
 * @version 1.0
 * @author lmperez
 */
public class AJL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Try your code here
        // If you want to test some algorithm just create a new object an press F6.
        KnapSackMultiplesObjects k = new KnapSackMultiplesObjects();
        k.testCase1();
    }

}
