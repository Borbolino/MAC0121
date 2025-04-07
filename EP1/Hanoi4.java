/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: João Pedro Barioni Agostini
 * Numero USP: 14582163
 * Tarefa: E01
 * Data: 16/09/2023
 * 
 * Baseado nas aulas dadas e em estudos sobre recursão e o problema de Hanói
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

import java.lang.Math;

public class Hanoi4 {

    static int[] text = new int[99999999]; // Vetor que guarda os movimentos para serem impressos.
    static int movimentos = 0;

    public static void main(String args[]) {
        hanoi4(Integer.parseInt(args[0]), 0, 3, 2, 1);
        if(args.length > 1) {
            // Imprime os movimentos se há mais de um argumento na linha de comando.
            System.out.println(movimentos + " moves");
        }
        for(int i = 0; i < movimentos * 2 - 1; i += 2) {
            // Imprime o vetor da forma especificada.
            System.out.print(text[i] + " " + text[i+1] + "  ");
        }
        System.out.println(); // Quebra de linha.
    }

    public static void hanoi3(int n, int ajuste, int ti, int tx, int tf) {
        if(n <= 0) {
            // Caso base.
            return;
        }

        hanoi3(n-1, ajuste, ti, tf, tx);
        // Guarda os movimentos em ordem no vetor.
        text[movimentos*2] = n + ajuste;
        text[movimentos*2 + 1] = tf;
        movimentos++; // Conta a quantidade de movimentos utilizada para resolver o problema.
        hanoi3(n-1, ajuste, tx, ti, tf);
        return;
    }

    public static void hanoi4(int n, int ti, int tx1, int tx2, int tf) {
        if(n <= 0) {
            // Caso base para 0.
            return;
        }
        if(n == 1) {
            // Caso base para 1.
            // Guarda os movimentos em ordem no vetor.
            text[movimentos*2] = n;
            text[movimentos*2 + 1] = tf;
            movimentos++; // Conta a quantidade de movimentos utilizada para resolver o problema.
            return;
        }

        /*
         * Cálculo do k-ótimo para resolver o problema Hanói4.
         * 
         * Para resolver a torre de Hanói com 4 torres, a estratégia
         * mais otimizada envolve escolher um número k e aplicar o algoritmo
         * hanoi4 para n-k blocos, aplicar hanoi3 para k blocos e aplicar
         * hanoi4 novamente sobre os n-k blocos iniciais.
         * 
         * k pode ser definido como o maior inteiro <= n tal que o
         * somatório de 1 a k seja <= n. Porém, mais eficientemente, podemos
         * implementar a fórmula seguinte para encontrar o mesmo resultado.
         */
        
        int k = (int) (Math.sqrt(8*n+1)-1)/2;

        // Aplicação do algoritmo explicado acima.
        hanoi4(n-k, ti, tx1, tf, tx2);
        hanoi3(k, n-k, ti, tx1, tf);
        hanoi4(n-k, tx2, ti, tx1, tf);

        return;
    }
    
}
