/******************************************************************************
 *  Compilation:  javac BinarySearchDeluxe.java
 *  Execution:    java BinarySearchDeluxe wordlist.txt < input.txt
 *  Data files:   https://introcs.cs.princeton.edu/java/42sort/emails.txt
 *                https://introcs.cs.princeton.edu/java/42sort/allowlist.txt
 *
 *  Read in an alphabetical list of words from the given file.
 *  Then prompt the user to enter words. The program reports which
 *  words are *not* in the wordlist.
 * 
 *  % java BinarySearchDeluxe allowlist.txt < emails.html
 *  marvin@spam
 *  mallory@spam
 *  eve@airport
 *
 * ADDITIONS by yk: 
 * 
 * - searchMin(): smallest index i with the key
 * - searchMax(): largest index i with the key
 * 
 ******************************************************************************/

import java.util.Arrays;

public class BinarySearchDeluxe {

    // return the index of the key in the sorted array a[]; -1 if not found
    public static int search(String key, String[] a) {
        return search(key, a, 0, a.length);
    }
    public static int search(String key, String[] a, int lo, int hi) {
        // possible key indices in [lo, hi)
        if (hi <= lo) return -1;
        int mid = lo + (hi - lo) / 2;
        int cmp = a[mid].compareTo(key);
        if      (cmp > 0) return search(key, a, lo, mid);
        else if (cmp < 0) return search(key, a, mid+1, hi);
        else              return mid;
    }

    // additional methods to find the smallest and largest i with a[i] = x
    // Have to clean them up a bit
    public static int searchMin(int[] a, int x) {
	return searchMin(a, x, 0, a.length - 1);
    }
    
    public static int searchMin(int[] a, int x, int lo, int hi) {
        // possible key indices in [lo, hi]
        if (hi <= lo) {
	    if (x == a[hi])
		return hi;
	    else
		return -1;
	} 
        int mid = lo + (hi - lo) / 2;
        int cmp = a[mid] - x;
        if (cmp >= 0) // x <= a[mid]
	    return searchMin(a, x, lo, mid);
        else          // x > a[mid]
	    return searchMin(a, x, mid + 1, hi);
    }

    public static int searchMax(int[] a, int x) {
	return searchMax(a, x, 0, a.length - 1);
    }
    
    public static int searchMax(int[] a, int x, int lo, int hi) {
        // possible key indices in [lo, hi]
        if (hi <= lo) {
	    if (x == a[hi])
		return hi;
	    else
		return -1;
	} 
        int mid = lo + (hi - lo + 1) / 2;
        int cmp = a[mid] - x;
        if (cmp > 0)  // x < a[mid]
	    return searchMax(a, x, lo, mid - 1);
        else          // x >= a[mid]
	    return searchMax(a, x, mid, hi);
    }

    // allowlist, exception filter
    public static void main(String[] args) {
        In in = new In(args[0]);
        String s = in.readAll();
        String[] words = s.split("\\s+");
        System.err.println("Done reading words");

        // sort the words (if needed)
        Arrays.sort(words);
        System.err.println("Done sorting words");

        // prompt user to enter a word and check if it's there
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (search(key, words) < 0) StdOut.println(key);
        }
    }
}

