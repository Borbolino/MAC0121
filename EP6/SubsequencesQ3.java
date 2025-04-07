public class SubsequencesQ3
{
    public static long noOccurrencesFast(int[] s, int[] t) {
        int M = s.length;    // COMPRIMENTO DO VETOR s
        int N = t.length;    // COMPRIMENTO DO VETOR t

        // MATRIZ UTILIZADA PARA MEMOIZAÇÃO
        long[][] matrix = new long[N+1][M+1];

        // LAÇO DUPLO QUE ITERA A MATRIZ (COMPLEXIDADE M*N)
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= M; j++) {
                if(j == 0) matrix[i][j] = 1;        // PREENCHE A PRIMEIRA COLUNA COM 1s
                else if(i == 0) matrix[i][j] = 0;   // PREENCHE A PRIMEIRA LINHA COM 0s
                else if(t[i-1] == s[j-1]) matrix[i][j] = matrix[i-1][j] + matrix[i-1][j-1];   // CASO CARACTERES IGUAIS
                else matrix[i][j] = matrix[i-1][j]; // CASO CARACTERES DIFERENTES
            }
        }
        return matrix[N][M];  // RETORNA O ÚLTIMO VALOR DA MATRIZ - O # DE OCORRÊNCIAS DE s EM t
    }

    public static void main(String[] args)
    {
        int[] t = StdIn.readAllInts();

        int M = Integer.parseInt(args[0]);
        int[] s = new int[M];
        for (int i = 0; i < M; i++)
            s[i] = t[i];

        Stopwatch sw = new Stopwatch();
        StdOut.println(noOccurrencesFast(s, t) 
                       + " [" + sw.elapsedTime()+ " seconds]");
        sw = new Stopwatch();
        StdOut.println(SubsequenceMod2.noOccurrences(s, t)  
                       + " [" + sw.elapsedTime()+ " seconds]");
    }
}
