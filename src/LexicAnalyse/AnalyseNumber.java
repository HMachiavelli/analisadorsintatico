package LexicAnalyse;

import LexicAnalyse.Contract.AnalyseContract;

public class AnalyseNumber extends AnalyseContract {
	public boolean analyse(String lexeme, String next) {
		boolean found = true;
        boolean value = lexeme.matches("\\d{0,5}");

		if (value == true) {
			this.tokenName = "number";
			this.tokenValue = "int";
		} else {
			value = lexeme.matches("\\d+\\.\\d+");

			if (value == true) {
				this.tokenName = "number";
				this.tokenValue = "double";
			} else {
				found = false;
			}
		}

		return found;
	}

}
