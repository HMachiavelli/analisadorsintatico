package LexicAnalyse;

import LexicAnalyse.Contract.AnalyseContract;

public class AnalyseIgnored extends AnalyseContract {

	public boolean analyse(String lexeme, String next) {
		boolean found = true;

		if (lexeme.matches("//.*$")) {
			this.tokenName = "comment";
		} else if (lexeme.matches("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)")) {
			this.tokenName = "comment";
		} else {
			found = false;
		}

		return found;
	}
	
	public String removeComments(String lexeme) {
		String ret;
		
		ret = lexeme.replaceAll("//.*$", "");
		ret = ret.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", "");

		return ret;
	}
}
