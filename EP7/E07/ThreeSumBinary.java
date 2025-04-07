/******************************************************************************
 *  Compilation:  javac ThreeSumBinary.java
 *  Execution:    java ThreeSumBinary < input.txt
 *
 *  A program with n^2 log n running time. Read in n integers
 *  and counts the number of triples that sum to exactly 0.
 *
 *  Limitations
 *  -----------
 *     - we ignore integer overflow
 *     - doesn't handle case when input has duplicates
 *
 ******************************************************************************/

import java.util.Arrays;

public class ThreeSumBinary {

    // print distinct triples (i, j, k) such that a[i] + a[j] + a[k] = 0
    public static void printAll(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k >= 0 && k > j) {
                    StdOut.println(a[i] + " " + a[j] + " " + a[k]);
                }
            }
        }
    } 

    // return number of distinct triples (i, j, k) such that a[i] + a[j] + a[k] = 0
    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k >= 0 && k > j) {
                    count++;
                }
            }
        }
        return count;
    } 

    public static void main(String[] args)  { 
        int[] a = StdIn.readAllInts();
	int count = count(a);
        StdOut.println(count);
        if (count < 30) printAll(a);
    }
} 
