/******************************************************************************
 *  Compilation:  javac DoublingTest.java
 *  Execution:    java DoublingTest
 * 
 * $ java-introcs DoublingTest 0 512 2023
 * Algorithm: N^3
 *    size    time  ratio
 *     512    0.02   0.85
 *    1024    0.16   7.23
 *    2048    1.25   7.84
 * $ cat output0.out 
 * 15
 * 11
 * 83
 * 500
 * $ java-introcs DoublingTest 1 512 2023
 * Algorithm: N^2\log N
 *    size    time  ratio
 *     512    0.00   0.60
 *    1024    0.01   2.67
 *    2048    0.04   5.25
 *    4096    0.20   4.81
 *    8192    0.95   4.70
 * $ cat output1.out 
 * 15
 * 11
 * 83
 * 500
 * 4226
 * 34621
 * $ 
 ******************************************************************************/

public class DoublingTest {

    // For outputting the number of triples that add up to zero
    // (for sanity check)
    // See https://introcs.cs.princeton.edu/java/31datatype/
    public static Out results;

    public static double timeTrial(int n, int alg) {
	int[] a;
	Stopwatch s;
	
	switch (alg) {
	case 0: 
	    a = RandomInts.randomSeq(n, 1000000);
	    s = new Stopwatch();
	    results.println(ThreeSum.count(a));
	    return s.elapsedTime();
	case 1:
	    a = RandomInts.randomSeq(n, 1000000);
	    s = new Stopwatch();
	    results.println(ThreeSumBinary.count(a));
	    return s.elapsedTime();
	case 2:
	    a = RandomInts.randomSeq(n, 1000000);
	    s = new Stopwatch();
	    results.println(ThreeSumBinaryDups.count(a));
	    return s.elapsedTime();
	case 3:
	    System.exit(0);
	    // a = RandomInts.randomSeq(n, 1000000);
	    // s = new Stopwatch();
	    // results.println(ThreeSumQuad.count(a));
	    // return s.elapsedTime();
	    return 0.0;
	default:
	    System.exit(0);
	}
	return 0.0;
    }

    public static void main(String[] args) {
	int alg = Integer.parseInt(args[0]);
	String[] option = {"N^3", "N^2\\log N", "N^2\\log N (allowing duplicates)", "N^2"};
	int N0 = Integer.parseInt(args[1]);
	long seed = Long.parseLong(args[2]);
	StdRandom.setSeed(seed);
	results = new Out("output" + alg + ".out");

        StdOut.printf("Algorithm: %s\n", option[alg]);
        StdOut.printf("%7s %7s %6s\n", "size", "time", "ratio");
        double previous = timeTrial(N0, alg);
        for (int n = N0; true; n += n) {
	    double current = timeTrial(n, alg);
	    StdOut.printf("%7d %7.2f %6.2f\n", n, current, current / previous);
	    previous = current;
        }
    }
}

