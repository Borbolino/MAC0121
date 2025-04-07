/*
 * $ java-introcs NEBF 21
 * Versão recursiva memoizada:
 * b(0) = 1    [0.0s]
 * b(1) = 1    [0.0s]
 * b(2) = 2    [0.0s]
 * b(3) = 5    [0.0s]
 * b(4) = 14    [0.0s]
 * [...]
 * b(21) = 24466267020    [0.0s]
 * 
 * Versão recursiva simples:
 * b(0) = 1    [0.0s]
 * b(1) = 1    [0.0s]
 * b(2) = 2    [0.0s]
 * b(3) = 5    [0.0s]
 * b(4) = 14    [0.0s]
 * [...]
 * b(21) = 24466267020    [18.806s]
 * $
 */

public class NEBF
{
    public static void main(String[] args)
    {
	int N = Integer.parseInt(args[0]);

	StdOut.println("Versão recursiva memoizada:");	
	for (int i = 0; i <= N; i++) {
	    Stopwatch sw = new Stopwatch();
	    long M = E02.n_ebfM(i);
	    double t = sw.elapsedTime();
	    StdOut.println("b(" + i + ") = " + M + "    [" + t + "s]");
	}

	StdOut.println("\nVersão recursiva simples:");
	for (int i = 0; i <= N; i++) {
	    Stopwatch sw = new Stopwatch();
	    long M = E02.n_ebfR(i);
	    double t = sw.elapsedTime();
	    StdOut.println("b(" + i + ") = " + M + "    [" + t + "s]");
	}

    }
}
