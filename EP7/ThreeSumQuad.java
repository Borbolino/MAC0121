/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: João Pedro Barioni Agostini
 * Numero USP: 14582163
 * Tarefa: E04
 * Data: 10/12/2023
 * 
 * Baseado nos algoritmos apresentados em aula e no exercício teórico 9.
 * O algoritmo presente nesse arquivo itera um array ordenado e, para cada
 * iteração, utiliza o método apresentado no teórico 9 (modificado para permitir
 * repetições), com as variáveis lo e hi, de complexidade de ordem N. Como executa
 * o algoritmo uma vez para cada iteração, resulta numa complexidade de ordem N^2. 
 * 
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/


import java.util.Arrays;    // IMPLEMENTAÇÃO DE UM ALGORITMO DE ORDENAÇÃO

public class ThreeSumQuad {

    public static int count(int[] a, int x) {
        int N = a.length;
        int lo = 0, hi = N - 1;
        int t = 0;
        int repeatLo = 0;       // CONTA REPETIÇÕES DE a[lo]
        int repeatHi = 0;       // CONTA AS REPETIÇÕES DE a[hi]
        int combination = 1;    // RESULTADO DA COMBINAÇÃO DAS REPETIÇÕES DE a[lo] E a[hi]
        
        for(int j = 0; j < N-2; j++) {
            while(lo < hi) {
                if(lo == j) lo++;   // EVITA UTILIZAR O MESMO ELEMENTO EM UMA MESMA TRINCA
                if(hi == j) hi--;   // ...
                if(a[j] + a[hi] + a[lo] == x) {
                    repeatLo = 0;       // RESETA AS VARIÁVEIS A CADA LOOP
                    repeatHi = 0;       // ...
                    combination = 1;    //...
                    
                    if(a[lo] == a[hi]) {    // CASO ESPECIAL ONDE DEVE-SE CALCULAR UM COEFICIENTE BINOMIAL
                    	int loToHi = hi - lo + 1;
                        combination = (loToHi * loToHi - loToHi) / 2;   // N ESCOLHE 2 == SOMA DA PA DE 1 A N-1
                        t += combination;
                        break;
                    }

                    // LAÇO QUE CHECA REPETIÇÕES DE a[lo]
                    for(int i = 0; i < N; i++) {
                        if(lo+repeatLo+1 == hi) break;  // EVITA REPETIR O MESMO ELEMENTO EM UMA MESMA TRINCA
                        if(lo < N-1 && a[lo+repeatLo] == a[lo+repeatLo+1]) repeatLo++;  // INCREMENTA CONTADOR DE REPETIÇÃO
                        else break;
                    }
                    lo += repeatLo; // PULA OS VALORES REPETIDOS JÁ LIDOS (MANTENDO A COMPLEXIDADE N^2)

                    // LAÇO QUE CHECA REPETIÇÕES DE a[hi]
                    for(int i = 0; i < N; i++) {
                        if(hi-repeatHi-1 == lo) break;  // EVITA REPETIR O MESMO ELEMENTO EM UMA MESMA TRINCA
                        if(hi > 0 && a[hi-repeatHi] == a[hi-repeatHi-1]) repeatHi++;    // INCREMENTA CONTADOR DE REPETIÇÃO
                        else break;
                    }
                    hi -= repeatHi; // PULA OS VALORES REPETIDOS JÁ CONHECIDOS (MANTENDO A COMPLEXIDADE N^2)

                    combination = (1 + repeatHi) * (1 + repeatLo);  // CALCULA A COMBINAÇÃO DOS ELEMENTOS REPETIDOS

                    t += combination;   // INCREMENTA O VALOR DA COMBINAÇÃO EM t (SE NÃO HÁ REPETIÇÕES, EQUIVALE A t++)
                    hi--; lo++;
                    continue;
                }
                if(a[j] + a[hi] + a[lo] < x) lo++;
                else hi--;
            }
            lo = j+1;   // NÃO ITERA NOVAMENTE OS ELEMENTOS JÁ ITERADOS (<= j)
            hi = N-1;
        }
        return t;
    }

    public static void main(String[] args) {
        int[] a = StdIn.readAllInts();  // LÊ UMA SEQUÊNCIA DE INTEIROS
        Arrays.sort(a); // ORDENA O ARRAY EM ORDEM CRESCENTE

        System.out.println(count(a, 0));    // PRINTA O RESULTADO
    }
}
