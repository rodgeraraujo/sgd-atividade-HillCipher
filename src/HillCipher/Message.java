package HillCipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Message {
	
	String fullMessage;
	String strFinalMessage;
	char[] finalMessage;
	int[][] convertedMessage;
	char[] cipherMessage;
	

	public Message(String inFile, Key key) throws FileNotFoundException {
		// configura duas variáveis de string locais para uso neste método
		String scanMessage = "";
		String result = "";

		// abre o arquivo para ser lido e salve a mensagem
		Scanner scanner = new Scanner(new File(inFile));
		while (scanner.hasNextLine()) {
			scanMessage += scanner.nextLine();
		}

		// faz o replace, e remoe tudo, menos letras e coloca todas em minúsculas
		result = scanMessage.replaceAll("[^\\p{Alpha}]+","").replaceAll("\\s+", "+").toLowerCase();
		this.strFinalMessage = result;
		
		int pad = result.length() % key.size;
		// descobre se a mensagem precisa de preenchimento ou não
		// se isso acontecer, e necessario instanciar uma versão maior do que apenas o resultado.
		if ( pad != 0 ) {
			this.finalMessage = new char[result.length()+pad];
			// aqui continua adicionando um padding até que não seja mais necessário
			while (result.length() % key.size != 0) {
				result += 'x';
			}
		}
		else {
			this.finalMessage = new char[result.length()];
		}
		// define nossa variável de classe strFinal com essa nova string finalizada
		this.strFinalMessage = result;

		// converte a  string final em um array do tipo char
		this.finalMessage = result.toCharArray();
		// cria matriz de conversão cheia de representação de índice de letras
		convertMessage();
		
		scanner.close();
	}
	
	
	public void printPlainText() {

		System.out.println("message:\n");
		// a saída deve ser de 80 caracteres por linha
		for ( int i = 1 ; i < this.finalMessage.length ; i++ ) {
			
			if ( (i % 80) == 0 && i != 0 ) {
				System.out.println(""+this.finalMessage[i-1]);
			}
			else {
				System.out.print(""+this.finalMessage[i-1]);
			}

		}
	}

	public void convertMessage() {
		
		this.convertedMessage = new int[this.finalMessage.length][1];
		// simplesmente percorre a mensagem e troca o char pelo seu índice correspondente
		for ( int i = 0 ; i < finalMessage.length ; i++ ) {
			this.convertedMessage[i][0] = Utility.findAlphaIndex(this.finalMessage[i]);
		}
	}
	
    public void printCipherText() {

		System.out.println("Ciphertext:\n");
		// a saída deve ser de 80 caracteres por linha
		for ( int i = 1 ; i < this.cipherMessage.length+1 ; i++ ) {
			
			if ( (i % 80) == 0 && i != 0 ) {
				System.out.println(""+this.cipherMessage[i-1]);
			}
			else {
				System.out.print(""+this.cipherMessage[i-1]);
			}
		}
	}
}
