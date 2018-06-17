package LexicAnalyse;

import LexicAnalyse.Contract.AnalyseContract;

public class AnalyseArithOP extends AnalyseContract {

	public boolean analyse(String lexeme, String next) {
		boolean found = true;

		if (lexeme.matches("\\+")) {
			this.tokenName = "BINOP";
			this.tokenValue = "+";
		} else if (lexeme.matches("\\-")) {
			this.tokenName = "BINOP";
			this.tokenValue = "-";
		} else if (lexeme.matches("\\/")) {
			this.tokenName = "BINOP";
			this.tokenValue = "/";
		} else if (lexeme.matches("\\*")) {
			this.tokenName = "BINOP";
			this.tokenValue = "*";
		} else if (lexeme.matches("\\%")) {
			this.tokenName = "BINOP";
			this.tokenValue = "%";
		} else {
			found = false;
		}

		return found;
	}

}
