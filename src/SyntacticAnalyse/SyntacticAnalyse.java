package SyntacticAnalyse;

import java.util.ArrayList;

import LexicAnalyse.*;

public class SyntacticAnalyse {
	private SymbolTable symbolTable;
	private Token currentToken;
	private int currentIndex;

	public SyntacticAnalyse(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
		this.currentToken = this.symbolTable.getToken(this.currentIndex);
		this.currentIndex = 0;
	}

	public void advance() {
		this.currentIndex++;
		this.currentToken = this.symbolTable.getToken(this.currentIndex);
	}

	public void consume(String t) {
		if (this.currentToken.getPattern().equals(t)) {
			this.advance();
		} else {
			this.callError();
		}
	}

	public void Program() {
		switch (this.currentToken.getPattern()) {
		case "variable":
			this.Var();
			break;
		case "function":
			this.Func();
			break;
		default:
			this.callError();
		}
	}

	public void Var() {
		switch (this.currentToken.getPattern()) {
		case "reserved_word":
			this.Type();
			this.consume("variable");

			if (this.currentToken.getPattern().equals("l_brace")) {
				this.consume("l_brace");
				this.consume("reserved_word");
				this.consume("r_brace");
			} else {
				this.callError();
			}

			this.consume("semicolon");

			break;
		default:
			this.callError();
			break;
		}
	}

	public void Func() {
		switch (this.currentToken.getPattern()) {
		case "reserved_word":
			this.consume("reserved_word");
			this.Type();
			this.consume("function");
			this.consume("l_paren");
			this.ParamList();
			this.consume("r_paren");
			this.Block();
		default:
			this.callError();
			break;
		}
	}

	public void ParamList() {
		this.Type();
		this.consume("variable");
		this.consume("l_paren");
		this.Type();
		this.consume("variable");
		this.consume("r_paren");
	}

	public void Block() {
		this.consume("l_bracket");
		this.Expr();
		this.Stmt();
		this.consume("r_bracket");
	}

	public void Stmt() {
		switch (this.currentToken.getPattern()) {
		case "reserved_word":
			this.consume("reserved_word");
			switch (this.currentToken.getPattern()) {
			case "l_paren": // if e while
				this.Expr();
				this.consume("r_paren");
				this.Block();
				if (this.currentToken.getPattern().equals("l_paren")) { // if
					this.consume("l_paren");
					this.consume("reserved_word");
					this.Block();
					this.consume("r_paren");
				}
			default: // return, break e continue
				/* if (this.Expr()) { // return
					continue;
				}
				this.consume("semicolon"); */
			}
		default:
			/* if (this.Loc()) { // loc
				this.consume("att_op");
				this.Expr();
				this.consume("semicolon");
			} else { // funccall
				this.FuncCall();
				this.consume("semicolon");
			} */
		}

	}

	public void Expr() {

	}

	public void Expr1() {

	}

	public void Type() {
		switch (this.currentToken.getPattern()) {
		case "int":
			this.consume("int");
		case "double":
			this.consume("double");
		case "bool":
			this.consume("bool");
		default:
			this.callError();
			break;
		}
	}

	public void Loc() {
		switch (this.currentToken.getPattern()) {
		case "func":
			this.consume("func");
			this.consume("l_paren");
			this.Expr();
			this.consume("r_paren");
		default:
			this.callError();
			break;
		}
	}

	public void FuncCall() {
		switch (this.currentToken.getPattern()) {
		case "func":
			this.consume("func");
			this.consume("l_paren");
			this.ArgList();
			this.consume("r_paren");
		default:
			this.callError();
			break;
		}
	}

	public void ArgList() {
		this.Expr();
		this.consume("l_paren");
		this.Expr();
		this.consume("r_paren");
	}

	public void Lit() {
		switch (this.currentToken.getPattern()) {
		case "DEC": // decimal
			this.consume("DEC");
		case "HEX": // hexadecimal
			this.consume("HEX");
		case "string": // string
			this.consume("string");
		case "bool": // string
			this.consume("bool");
		default:
			this.callError();
			break;
		}
	}

	private void callError() {
		System.out.println("Atenção! Erro de sintaxe na linha " + this.currentToken.getLine());
	}

	/*
	 * GETTERS AND SETTERS
	 */
	public SymbolTable getSymbolTable() {
		return this.symbolTable;
	}

	public void setSymbolTable(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	public Token getCurrentToken() {
		return this.currentToken;
	}

	public void setCurrentToken(Token currentToken) {
		this.currentToken = currentToken;
	}

	public int getCurrentIndex() {
		return this.currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
}
