/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: João Pedro Barioni Agostini
 * Numero USP: 14582163
 * Tarefa: E03
 * Data: 12/11/2023
 * 
 * Baseado nas aulas dadas e em estudos sobre recursão. O programa Divide.java
 * gera permutações de um vetor de valores recebido e, quando encontra uma divisão
 * justa, retorna a divisão. Utiliza o mecanismo de backtracking. Quando é
 * encontrada uma permutação que não gerará um resultado favorável, retorna para o
 * nível de recursão anterior. Ou seja, quando a soma dos elementos de dado grupo
 * é maior que o soma/3 + soma%3, retorna.
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class Divide
{
    private static boolean verbose;
    private static int best = 0; // best value so far
    private static int[] b;  // best division so far
	private static boolean optimal = false;
	private static int modCap = 0;
    
    public static void enumerate(int[] val, int[] a, int k, int sum) {
		if (max(val, a) > ((sum/3) + (sum%3)) || modCap > sum%3) return;
		if (k == a.length) {
			int t = min(val, a);
			if (t > best) {
				best = t;
				copy(b, a);
				if (best >= sum/3) optimal = true;
			}
			return;
		}

		//for(int i = k; i < a.length; i++) {
			int A = sumIndex(val, a, 1);
			int B = sumIndex(val, a, 2);
			int C = sumIndex(val, a, 3);
			if(A < sum/3) {
				a[k] = 1;
				A = sumIndex(val, a, 1);
				if(A > sum/3) modCap += A - sum/3;
				enumerate(val, a, k + 1, sum);
				if(A > sum/3) modCap -= A - sum/3;
				a[k] = 0;
				if(optimal) return;
			}
			if(B < sum/3) {
				a[k] = 2;
				B = sumIndex(val, a, 2);
				if(B > sum/3) modCap += B - sum/3;
				enumerate(val, a, k  + 1, sum);
				if(B > sum/3) modCap -= B - sum/3;
				a[k] = 0;
				if(optimal) return;
			}
			if(C < sum/3) {
				a[k] = 3;
				C = sumIndex(val, a, 3);
				if(C > sum/3) modCap += C - sum/3;
				enumerate(val, a, k  + 1, sum);
				if(C > sum/3) modCap -= C - sum/3;
				a[k] = 0;
				if(optimal) return;
			} }

    public static int scheduleBrute(int[] val, int sum) {
		int[] a = new int[val.length];
		b = new int[val.length];
		enumerate(val, a, 0, sum);
		return best;
    }

    public static int min(int[] val, int[] a) {
		int N = a.length;
		int A = 0, B = 0, C = 0;
		for (int i = 0; i < N; i++)
			switch (a[i]) {
			case 0: break;
			case 1: A += val[i]; break;
			case 2: B += val[i]; break;
			case 3: C += val[i]; break;
			} 
		return Math.min(Math.min(A, B), C);
    }

	public static int max(int[] val, int[] a) {
		int N = a.length;
		int A = 0, B = 0, C = 0;
		for (int i = 0; i < N; i++)
			switch (a[i]) {
			case 0: break;
			case 1: A += val[i]; break;
			case 2: B += val[i]; break;
			case 3: C += val[i]; break;
			} 
		return Math.max(Math.max(A, B), C);
    }

    public static int sum(int[] val) {
		int N = val.length, S = 0; 
		for (int i = 0; i < N; i++) S += val[i];
		return S;
    }

	public static int sumIndex(int[] val, int a[], int index) {
		int N = val.length, S = 0;
		for (int i = 0; i < N; i++)
			if(a[i] == index) S += val[i];
		return S;
	}

    public static void show(int[] a, int[] val) {
		for (int i = 1; i < 4; i++) 
			show(a, val, i);
    }

    public static void show(int[] a, int[] val, int i) {    
		int N = a.length, s;
		StdOut.print( (i-1) + ": ");
		s = 0;
		for (int j = 0; j < N; j++) {
			if (a[j] == i) {
				s += val[j];
				StdOut.print(val[j] + " ");
			} 
		}
		StdOut.println("(sum: " + s + ")");
    }

    public static void copy(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) a[i] = b[i];
    }

	public static void exchange(int[] arr, int i, int j) {
        int x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
        return;
    }

	public static void quixort(int[] arr, int left, int right) {
    	if(left < right) {
	    	int pivot = arr[right];
	    	int i = left - 1;
	    	
	    	for(int j = left; j < right; j++) {
	    		if(arr[j] > pivot) {
	    			i++;
	    			exchange(arr, i, j);
	    		}
	    	}
	    	exchange(arr, right, i+1);
	    	
	    	int index = i + 1;
	    	quixort(arr, left, index - 1);
	    	quixort(arr, index + 1, right);
    	}
    }
    
    public static void main(String[] args)
    {
		verbose = args.length > 0;
		int[] val = StdIn.readAllInts();
		quixort(val, 0, val.length-1);
		int sum = sum(val);	
		int opt = scheduleBrute(val, sum);
		if(!optimal) {
			for(int i = 0; i < b.length; i++) {
				b[i] = 1;
			}
		}
		if (opt >= sum / 3)
			StdOut.println("There is a solution");
		else 
			StdOut.println("There is no solution");
		if (verbose && optimal) { 
			StdOut.println("Optimal value: " + opt
				+ " (sum: " + sum + " / want: " + sum/3
				+ " / sum mod 3 = " + sum % 3 +")");
			if (verbose) show(b, val);
		}
		if (verbose && !optimal) { 
			StdOut.println("Best I found (not necessarily optimal): " + opt
				+ " (sum: " + sum + " / want: " + sum/3
				+ " / sum mod 3 = " + sum % 3 +")");
			if (verbose) show(b, val);
		}
    }
}
