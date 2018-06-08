package LexicAnalyse;

import java.util.HashMap;
import java.util.Map;

import LexicAnalyse.Contract.AnalyseContract;

public class AnalyseReservedWord extends AnalyseContract {

    // Reconhecer a sentenca de entrada
    public boolean analyse(String lexeme, String next) {
        if (lexeme.equals("void")        || lexeme.equals("int") ||
        	lexeme.equals("double")      || lexeme.equals("bool") ||
        	lexeme.equals("string")      || lexeme.equals("class") ||
        	lexeme.equals("interface")   || lexeme.equals("null") ||
        	lexeme.equals("this")        || lexeme.equals("extends") ||
        	lexeme.equals("implements")  || lexeme.equals("for") ||
        	lexeme.equals("while")       || lexeme.equals("if") ||
        	lexeme.equals("else")        || lexeme.equals("return") ||
        	lexeme.equals("break")       || lexeme.equals("new") ||
        	lexeme.equals("NewArray")    || lexeme.equals("Print") || 
        	lexeme.equals("ReadInteger") || lexeme.equals("ReadLine")) {
        		this.tokenName = "reserved_word";
        		this.tokenValue = lexeme;
        		return true;
        }
	
        return false;
    }
}
