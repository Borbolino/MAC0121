/*
 * $ java-introcs Generate 40 122
 * TGGTTGGTCTATATAATCTGGAGCGGTATTCCGACATCAT
 * $ java-introcs Generate 80 122122
 * TCGTCAGCAGATCACGCCTCCTCACGTTTTATGCACCGCTCCGGATCTTGCGGAGGTCCAACGAAGCTAGGGTTGCTATG
 * $ 
 */

public class Generate
{
    public static void main(String[] args)
    {
        int N = Integer.parseInt(args[0]);
	long seed = Long.parseLong(args[1]);
	StdRandom.setSeed(seed);
	char[] a = {'A', 'C', 'T', 'G'};
        for (int i = 0; i < N; i++) 
            StdOut.print(a[StdRandom.uniformInt(4)]);
	StdOut.println();
    }
}
