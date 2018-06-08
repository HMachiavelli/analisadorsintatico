package LexicAnalyse;

import LexicAnalyse.Contract.AnalyseContract;

public class AnalyseString extends AnalyseContract {

	public boolean analyse(String lexeme, String next) {
        boolean found = true;

		if (lexeme.trim().matches("\"(.+)\"")) {
            this.tokenName = "string";
            this.tokenValue = lexeme;
		} else {
			found = false;
		}

		return found;
	}

	public String replaceSpaces(String lexeme, String separator) {
		String ret;

		ret = lexeme.replaceAll(" ", separator);
		return ret;
	}
}
