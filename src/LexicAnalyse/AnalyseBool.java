package LexicAnalyse;

import LexicAnalyse.Contract.AnalyseContract;

public class AnalyseBool extends AnalyseContract {

	public boolean analyse(String lexeme, String next) {
		boolean found = true;

		this.tokenValue = lexeme;
		if (lexeme.matches("true")) {
			this.tokenName = "bool";
		} else if (lexeme.isequals("false")) {
			this.tokenName = "bool";
		} else {
			found = false;
		}

		return found;
	}
}