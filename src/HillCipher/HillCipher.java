package HillCipher;

import java.io.FileNotFoundException;

public class HillCipher {
	
	static char[] finalGrid;
	
	public static void main(String[] args) throws FileNotFoundException{

		// cria um novo objeto para pegar os valores da chave e mensagem
		// baseados em arquios .txt
		Key inKey = new Key("keyFile.txt");
		Message message = new Message("textFileToEncrypt.txt",inKey);
		
		// imprime a chave
		//inKey.printKey();
		//System.out.println();

		// imprime a mensagem
		//message.printPlainText();
		//System.out.println();
		
		// executa o metodo de encriptaçao
		encrypt(inKey,message);
		System.out.println();

		// imprime o texto cifrado
		printCiphertext();
		System.out.println();
		
	}
	
	public static void encrypt(Key key, Message message) {
		// passar por mensagens convertidas divididas em partes do tamanho da chave
		// multiplique estes pedaços pela chave mod cada resultado por 26
		// resulta em uma matriz de caracteres finalGrid

		// temp armazenará os trechos da mensagem que serão multiplicados pela chave
		int[][] temp = new int[key.size][1];
		// product conterá o produto da multiplicação da matriz entre os fragmentos de chave e de mensagem
		int[][] product = new int[key.size][1];
		// instanciar o finalGrid, isso manterá nosso texto cifrado como um array do tipo char
		finalGrid = new char[message.convertedMessage.length];
		int findex = 0;

		// loop através da matriz de caracteres da mensagem
		// aqui estamos adicionando key.size a i, a cada vez
		// então obtemos o tamanho correto para multiplicar
		for (int i = 0 ; i < message.convertedMessage.length ; i = i + key.size ) {

			// preenche a matriz temp com os blocos
			for (int j = 0 ; j < key.size ; j++ ) {
				temp[j][0] = message.convertedMessage[i+j][0];
			}
			
			product = multiply(key.grid,temp);

			// pegar o product e adiciona ele ao array finalGrid
			// certificando-se de modificar cada resultado por 26 e converter de volta para uma letra
			for ( int k = 0 ; k < key.size ; k++ ) {
				finalGrid[findex] = Utility.findLetter(product[k][0] % 26);
				// we do not want the index of finalGrid to restart every iteration of the larger loop
				// so I added its own index counter
				findex++;
			}
		}
	}
	
	// retorna C = A * B
	// obtido de http://introcs.cs.princeton.edu/java/22library/Matrix.java.html
    public static int[][] multiply(int[][] A, int[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) throw new RuntimeException("matrix dimensions invalid.");
        int[][] C = new int[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += (A[i][k] * B[k][j]);
        return C;
    }
    
    public static void printCiphertext() {
    	System.out.println("Text cipher:");
		//output must be 80 char per line
    	// we start at 1 so the first line of output is 80 characters
    	// as well as all of the following lines
		for ( int i = 1 ; i < finalGrid.length+1 ; i++ ) {
			
			if ( (i % 80) == 0 && i != 0 ) {
				System.out.println(""+finalGrid[i-1]);
			}
			else {
				System.out.print(""+finalGrid[i-1]);
			}
		}
    }

}
