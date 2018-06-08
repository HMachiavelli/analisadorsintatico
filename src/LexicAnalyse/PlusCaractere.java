package LexicAnalyse;

public class PlusCaractere{
	
	// caracter especial que será adicionado 
	public char caracter = '$';
	
	public String addCaracter(String text) {	
		// retorno da funcao
		String linha = "";
		
		// faz split e retira espacos em branco da entrada da funcao
		String textsplit[] = new String[text.length()];
		textsplit = text.trim().split("( )|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))|(?<=;)|(?=;)|(?<=,)|(?=,)|(?<=\\{)|(?=\\})|(?<=\\*)|(?=\\*)|(?<=\\[)|(?<=\\])|(?=\\[)|(?=\\])");	
		
		// variavel para auxiliar laco a seguir
		boolean searchString = false;
		
		// laco para adicionar o caracter especial entre os lexemas
		for (int i = 0; i < textsplit.length; i++) {
			
			// se achar " (indica o inicio de uma string) entao nao adiciona caracter entre a string
			if (textsplit[i].matches("(\".*)") || searchString) {
				searchString = true;
				// concatena o lexema ao retorno 
				linha += textsplit[i] +" ";
				// quando acha o fim da string cai fora deste if e continua adicionado caracter 
				if (textsplit[i].matches("(.*\")")) {
					linha += caracter;
					searchString = false;
				}
				
			// concatena o lexema ao retorno com caracter especial	
			} else { 
				linha += textsplit[i] + caracter;
			}
		}
		return linha;
	}

}
