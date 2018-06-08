package LexicAnalyse;

import LexicAnalyse.Contract.AnalyseContract;

public class AnalyseArithOP extends AnalyseContract {

	public boolean analyse(String lexeme, String next) {
		boolean found = true;

		if (lexeme.matches("\\+")) {
			this.tokenName = "Arith_Op";
			this.tokenValue = "+";
		} else if (lexeme.matches("\\-")) {
			this.tokenName = "Arith_Op";
			this.tokenValue = "-";
		} else if (lexeme.matches("\\/")) {
			this.tokenName = "Arith_Op";
			this.tokenValue = "/";
		} else if (lexeme.matches("\\*")) {
			this.tokenName = "Char_Asterisk";
			this.tokenValue = "*";
		} else if (lexeme.matches("\\%")) {
			this.tokenName = "Arith_Op";
			this.tokenValue = "%";
		} else {
			found = false;
		}

		return found;
	}

}
