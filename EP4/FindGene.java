/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: João Pedro Barioni Agostini
 * Numero USP: 14582163
 * Tarefa: E04
 * Data: 07/11/2023
 * 
 * Baseado nas aulas dadas e nos algoritmos providenciados. O programa
 * FindGene.java, ao invés de iterar a string genoma n! vezes, itera
 * a string uma única vez, e apenas cria mais "ramos de iteração"
 * quando é encontrado um gene do tipo start. Assim, são evitadas
 * incontáveis iterações que derrubam a eficiência do algoritmo vertiginosamente.
 * Diferentemente do programa GeneFindImproved.java, todos os genes encontrados
 * aqui, pois não há saltos na iteração primária da string.
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/


public class FindGene {
	
	public static String getStr() {
        String genome = StdIn.readString();
		return genome;	
	}
	
	public static int nextNull(String [] arr) {
		for(int i = 0; i< arr.length; i++) {
			if(arr[i] == null) return i;
		}
		return 0; }
	
	public static void print(String[] arr) {
		for(int i = 0; i < nextNull(arr); i++) System.out.println(arr[i]);
	}

	public static void main(String[] args) {
		String genome = getStr();
		int geneCount = 0;

		for(int i = 0; i < genome.length() - 5; i++) {
			String codon = genome.substring(i, i+3);
			if(codon.equals("ATG")) {
				for(int j = i; j < genome.length() - 2; j+=3) {
					if(genome.substring(j, j+3).equals("TAA") || genome.substring(j, j+3).equals("TAG") || genome.substring(j, j+3).equals("TGA")) {
						geneCount++;
						if(args.length == 0) System.out.println(genome.substring(i, j+3));
						j = genome.length();
						} } 
				i += 2; } }
		if(args.length > 0) System.out.println(geneCount);
	}
}
