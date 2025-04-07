/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: João Pedro Barioni Agostini
 * Numero USP: 14582163
 * Tarefa: E02
 * Data: 15/09/2023
 * 
 * Baseado nas aulas dadas e em estudos sobre recursão
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/
 
public class E02 {

    static long[] memo = new long[36]; // Vetor usado na memoização.

    public static long n_ebfR(int n) {
        if(n <= 0) {
            // Caso base para 0.
            return 1;
        }
        if(n == 1) {
            // Caso base para 1.
            return 1;
        }

        long b = 0; // b(n).

        for(int i = 0; i < n/2; i++) {
             // Somatório das permutações dos elementos de b(0) com b(n-1), b(1) com b(n-2) e etc.
            b += 2 * (n_ebfR(i) * n_ebfR(n-i-1));
        }
        if(n%2 == 1) {
            // Soma o caso b(n/2), que é ignorado pela iteração acima para ns ímpares.
            b += n_ebfR(n/2) * n_ebfR(n/2);
        }
        
        return b;
    }

    public static long n_ebfM(int n) {
        if(n <= 0) {
            // Caso base para 0.
            memo[0] = 1;
            return memo[0];
        }
        if(n == 1) {
            // Caso base para 1.
            memo[0] = 1;
            memo[1] = 1;
            return memo[1];
        }

        long b = 0; // b(n).

        n_ebfM(n-1);

        for(int i = 0; i < n/2; i++) {
            // Somatório das permutações similar à função n_ebfR, mas
            // utilizando os valores armazenados pelo método de memoização.
            b += 2 * (memo[i] * memo[n-i-1]);
        }
        if(n%2 == 1) {
            // Soma o caso b(n/2), que é ignorado pela iteração acima para ns ímpares.
            b += memo[n/2] * memo[n/2];
        }
        memo[n] = b; // Salva o valor obtido no vetor de memoização para evitar repetições desnecessárias no futuro.

        return memo[n]; // Retorna o último valor do vetor de memoização. No caso, b(n).
    }
}
