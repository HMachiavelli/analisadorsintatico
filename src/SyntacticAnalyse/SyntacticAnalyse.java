package SyntacticAnalyse;

import java.util.ArrayList;

import LexicAnalyse.*;

public class SyntacticAnalyse {
	private SymbolTable symbolTable;
	private Token currentToken;
	private int currentIndex;

	public SyntacticAnalyse(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
		this.currentIndex = 0;
		this.currentToken = this.symbolTable.getToken(this.currentIndex);
	}

	public void advance() {
		this.currentIndex++;
		this.currentToken = this.symbolTable.getToken(this.currentIndex);
	}

	public void consume(String t) {
		if (this.currentToken.getPattern().equals(t)) {
			this.advance();
		} else {
			this.callError("consume");
		}
	}

	public void analyse(){
		if (this.currentToken.getLexeme().equals("Program")) {
			this.consume("reserved_word");
			this.Program();
		} else {
			this.callError("analyse");
		}
	}

	// Program →	Var | Func
	public void Program() {
		switch (this.currentToken.getLexeme()) {
		case "def":
			this.Func();
			this.Program();
			break;

		case "int":
			this.Var();
			this.Program();
			break;

		case "bool":
			this.Var();
			this.Program();
			break;

		case "void":
			this.Var();
			this.Program();
			break;

		default:
			this.callError("program");
		}
	}

	// Var →	Type ID;
	//          Type ID [DEC];
	public void Var() {
		switch (this.currentToken.getPattern()) {
		case "reserved_word":
			this.Type();
			this.consume("variable");

			while (this.currentToken.getPattern().equals("comma")){
				this.consume("comma");
				this.consume("variable");
			}

			if (this.currentToken.getPattern().equals("l_brace")) {
				this.consume("l_brace");
				this.consume("reserved_word");
				this.consume("r_brace");
			}

			this.consume("semicolon");

			break;
			
		case "variable":
			this.consume("variable");

			while (this.currentToken.getPattern().equals("comma")){
				this.consume("comma");
				this.consume("variable");
			}

			if (this.currentToken.getPattern().equals("l_brace")) {
				this.consume("l_brace");
				this.consume("reserved_word");
				this.consume("r_brace");
			}

			this.consume("semicolon");

			break;
		default:
			this.callError("var");
			break;
		}
}

	// Func →	def Type ID(ParamList) Block
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
			break;
		default:
			this.callError("func");
			break;
		}
	}

	// ParamList → Type ID*
	public void ParamList() {
		this.Type();
		this.consume("variable");
		while (this.currentToken.getPattern().equals("comma")){
			this.consume("comma");
			this.consume("variable");
		}
	}

	// Block →	{ Var* Stmt* }
	public void Block() {
		switch (this.currentToken.getLexeme()){
			case "int":
				this.Var();
				this.Block();
				break;

			case "bool":
				this.Var();
				this.Block();
				break;

			case "void":
				this.Var();
				this.Block();
				break;

			default:
				this.Stmt();
		}
	}

	// Stmt →	Loc Expr; | FuncCall; | if(Expr) Block else Block | 
	//		    while(Expr) Block | return Expr; | break; | continue;
	public void Stmt() {
		switch (this.currentToken.getPattern()) {
			case "function": //FunCall
				// FuncCall
				this.FuncCall();
				this.consume("semicolon");

				break;
			case "variable": // loc
				this.Loc();
				this.consume("att_op");
				// adicionar if para saber se tem EXPR 
				this.Expr();
				this.consume("semicolon");
				break;

			case "reserved_word":
				this.consume("reserved_word");
				switch (this.currentToken.getPattern()) {
					case "l_paren": // if e while
						this.consume("l_paren");
						this.consume("reserved_word");
						this.Expr();
						this.consume("r_paren");
						this.Block();
						// if
						if (this.currentToken.getLexeme().equals("else")) { 
							this.consume("reserved_word");
							this.Block();
						}

						break;
					default: // return, break e continue
						if (this.currentToken.getLexeme().equals("return")) { // return
							this.consume("reserved_word");
							this.Expr();
							this.consume("semicolon");
							break;

						} else if (this.currentToken.getLexeme().equals("break")){ // break
							this.consume("reserved_word");	
							this.consume("semicolon"); 
							break;

						} else if (this.currentToken.getLexeme().equals("continue")){ // continue
							this.consume("reserved_word");	
							this.consume("semicolon");
							break;
						}	
				}
				break;
			default:
				this.callError("stmt");
				break;
		}
	}

	// Expr →	UNOP Expr Expr1 | 
	//			(Expr) Expr1 | 
	//			Loc Expr1 | 
	//			FuncCall Expr1 | 	
	//			Lit Expr1
	public void Expr() {
		switch (this.currentToken.getPattern()){
			case "UNOP":
				this.consume("UNOP");
				this.Expr();
				this.Expr1();
				break;
			case "l_paren":
				this.consume("l_paren");
				this.Expr();
				this.consume("r_paren");
				this.Expr1();
				break;
			case "function":
				this.consume("function");
				this.FuncCall();
				break;
			case "variable":
				this.consume("variable");
				this.Loc();
				break;
			case "number":
				this.consume("number");
				this.Lit();
				break;
			case "string":
				this.consume("string");
				this.Lit();
				break;
			case "bool":
				this.consume("bool");
				this.Lit();
				break;
			default:
				this.callError("expr");
				break;
		}
	}

	// Expr1 →	BINOP Expr Expr1
	public void Expr1() {
		if (this.currentToken.getPattern().equals("BINOP")){	
			this.consume("BINOP");
			this.Expr();
			this.Expr1();
		} else {
			this.callError("expr1");
		}
	}

	// Type →	int | bool | void
	public void Type() {
		switch (this.currentToken.getPattern()) {
		case "reserved_word":
			this.consume("reserved_word");
			break;
		default:
			this.callError("program");
			break;
		}
	}

	// Loc →	ID [Expr] | ID
	public void Loc() {
		switch (this.currentToken.getPattern()) {
		case "variable":
			this.consume("variable");

			if (this.currentToken.getPattern().equals("l_brace")){
				this.consume("l_brace");
				this.Expr();
				this.consume("r_brace");
			}

			break;
		default:
			this.callError("loc");
			break;
		}
	}

	// FuncCall →	ID(ArgList)
	public void FuncCall() {
		switch (this.currentToken.getPattern()) {
		case "function":
			this.consume("function");
			this.consume("l_paren");
			this.ArgList();
			this.consume("r_paren");
			break;
		default:
			this.callError("funccall");
			break;
		}
	}

	// ArgList   → Expr , Expr*
	public void ArgList(){
		this.Expr();
		while (this.currentToken.getPattern().equals("comma")){
			this.consume("comma");
			this.Expr();
		}
	}

	// Lit →	DEC | HEX | STR | true | false
	public void Lit() {
		switch (this.currentToken.getPattern()) {
		case "DEC": // decimal
			this.consume("DEC");
			break;
		case "HEX": // hexadecimal
			this.consume("HEX");
			break;
		case "string": // string
			this.consume("string");
			break;
		case "bool": // bool
			this.consume("bool");
			break;
		default:
			this.callError("lit");
			break;
		}
	}

	private void callError(String type){
		System.out.println("Atencao! Erro de sintaxe: caracter inesperado " + this.currentToken.getLexeme() + " na linha " + this.currentToken.getLine() + " [funcao: " + type +"]");
		System.exit(0);
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

	public Token getToken(int i){
		return this.symbolTable.getToken(i);
	}

	public int getCurrentIndex() {
		return this.currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
}
