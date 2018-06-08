package LexicAnalyse;

import LexicAnalyse.Contract.AnalyseContract;

public class AnalyseVariable extends AnalyseContract {

    public boolean analyse(String lexeme, String next) {
        boolean value = lexeme.matches("^[A-Za-z]+[A-Za-z\\d]*");

        this.tokenValue = lexeme;
        if (value == true && next.equals("(")) {
            this.tokenName = "function";

            return true;
        } else if (value == true) {
            this.tokenName = "variable";

            return true;
        }

        return false;
    }
}
