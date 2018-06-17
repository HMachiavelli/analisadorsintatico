package LexicAnalyse;

import LexicAnalyse.Contract.AnalyseContract;

public class AnalyseLogicOP extends AnalyseContract {

	public boolean analyse(String lexeme, String next) {
		boolean found = true;

		if (lexeme.matches("\\&{2}")) {
			this.tokenName = "BINOP";
		} else if (lexeme.matches("\\|{2}")) {
			this.tokenName = "BINOP";
		} else if (lexeme.matches("\\!")) {
			this.tokenName = "UNOP";
		} else {
			found = false;
		}

		return found;
	}

}
