package HillCipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Key {
	
	int size;
	int[][] grid;
	
	public Key(String keyFile) throws FileNotFoundException {
		
		// abre o arquivo para ser lido
		Scanner scanner = new Scanner(new File(keyFile));

		// obtem tamanho da matriz
		// será sempre uma matriz quadrada
		this.size = scanner.nextInt();
				
		// aloca na matriz grid
		this.grid = new int[size][size];
				
			// intera pelas linhas
		for( int row = 0 ; row < this.size ; row++ ){
			// intera pelas colunas
			for( int column = 0; column < this.size ; column++ ){
				// ler cada valor
				this.grid[row][column] = scanner.nextInt();
			}
		}
				
		scanner.close();
	}
	
	public void printKey() {
		// faz a impressao da matriz chave
		System.out.println("key matrix:\n");
		for( int row = 0 ; row < this.size ; row++ ){
			for( int column = 0; column < this.size ; column++ ){
				if ( column == this.size-1 ) {
					System.out.println(this.grid[row][column]);
				}
				else {
					System.out.print(this.grid[row][column]+" ");
				}
			}
		}
	}
	
}
