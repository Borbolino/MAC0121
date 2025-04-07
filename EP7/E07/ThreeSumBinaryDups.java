/******************************************************************************
 *  Compilation:  javac ThreeSumBinaryDups.java
 *  Execution:    java ThreeSumBinaryDups < input.txt
 *
 *  A program with n^2 log n running time. Read in n integers
 *  and counts the number of triples that sum to exactly 0.  Allow
 *  duplicates in input.
 *
 *  Limitations
 *  -----------
 *     - we ignore integer overflow
 *
 ******************************************************************************/

import java.util.Arrays;

public class ThreeSumBinaryDups {

    // print distinct triples (i, j, k) such that a[i] + a[j] + a[k] = 0
    public static void printAll(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
		int kMax = BinarySearchDeluxe.searchMax(a, -(a[i] + a[j]));
		if (kMax - j > 0) {
		    int kMin = BinarySearchDeluxe.searchMin(a, -(a[i] + a[j]));
		    int k = Math.max(j + 1, kMin);
		    while (k <= kMax) 
			StdOut.println(a[i] + " " + a[j] + " " + a[k++]);
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
		int kMax = BinarySearchDeluxe.searchMax(a, -(a[i] + a[j]));
		if (kMax - j > 0) {
		    int kMin = BinarySearchDeluxe.searchMin(a, -(a[i] + a[j]));
		    count += Math.min(kMax - j, kMax - kMin + 1);
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
