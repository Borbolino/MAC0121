public class SubsequenceMod2
{
    public static long noOccurrences(int[] s, int[] t) {
        return noOccurrences(s, 0, t, 0);
    }

    public static long noOccurrences(int[] s, int i, int[] t, int j) {
        int M = s.length;
        int N = t.length;
        if (i == M) return 1;
        if (j == N) return 0;

        if (s[i] == t[j])
            return noOccurrences(s, i + 1, t, j + 1)
                   + noOccurrences(s, i, t, j + 1);
        return noOccurrences(s, i, t, j + 1);
    }

    public static void show(int[] v) {
        for (int i = 0; i < v.length; i++)
            StdOut.print(v[i]);
        StdOut.println();
    }

    public static void main(String[] args)
    {
        int[] NUSP = StdIn.readAllInts();

        for (int i = 0; i < NUSP.length; i++)
            NUSP[i] = NUSP[i] % 2;
        show(NUSP);

        int[] s1 = new int[1];
        s1[0] = NUSP[0];
        StdOut.println(noOccurrences(s1, NUSP));

        int[] s3 = new int[3];
        for (int i = 0; i < 3; i++)
            s3[i] = NUSP[i];
        show(s3);
        StdOut.println(noOccurrences(s3, NUSP));
    }
}
