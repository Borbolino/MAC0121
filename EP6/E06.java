/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: João Pedro Barioni Agostini
 * Numero USP: 14582163
 * Tarefa: E04
 * Data: 10/12/2023
 * 
 * Baseado nas aulas dadas e nos algoritmos providenciados. O Programa executa
 * uma versão memoizada do código noOcurrences(s, t). Ao invés de utilizar
 * recursão, calcula o número de ocorrências para cada combinação de sufixos
 * de M e N (subsequências de M e N truncadas). Assim, agiliza o processo pois
 * não realiza cálculos desnecessários (que já foram calculados anteriormente).
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class E06 {
    
    public static int noOcurrencesFast(String M, String N) {
        int lM = M.length();    // COMPRIMENTO DA STRING M
        int lN = N.length();    // COMPRIMENTO DA STRING N

        // MATRIZ UTILIZADA PARA MEMOIZAÇÃO
        int[][] matrix = new int[lM+1][lN+1];

        // LAÇO DUPLO QUE ITERA A MATRIZ (COMPLEXIDADE M*N)
        for(int i = 0; i <= lM; i++) {
            for(int j = 0; j <= lN; j++) {
                if(j == 0) matrix[i][j] = 1;        // PREENCHE A PRIMEIRA COLUNA COM 1s
                else if(i == 0) matrix[i][j] = 0;   // PREENCHE A PRIMEIRA LINHA COM 0s
                else if(M.charAt(i-1) == N.charAt(j-1)) matrix[i][j] = matrix[i-1][j] + matrix[i-1][j-1];   // CASO CARACTERES IGUAIS
                else matrix[i][j] = matrix[i-1][j]; // CASO CARACTERES DIFERENTES
            }
        }
        return matrix[lM][lN];  // RETORNA O ÚLTIMO VALOR DA MATRIZ - O # DE OCORRÊNCIAS DE N EM M
    }

    public static void main(String[] args) {
        System.out.println(noOcurrencesFast("1122333", "123"));
    }
    
}
