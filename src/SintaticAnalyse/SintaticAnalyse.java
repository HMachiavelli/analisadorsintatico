package SintaticAnalyse;

import java.util.ArrayList;

import LexicAnalyse.*;

public class SintaticAnalyse{
    private SymbolTable symbolTable;
    private Token currentToken;
    private int currentIndex;

	public SintaticAnalyse(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
		this.currentToken = this.symbolTable.getToken(this.currentIndex).getPattern();
		this.currentIndex = 0;
	}

	public void advance(){
		this.currentIndex++;
		this.currentToken = this.symbolTable.getToken(this.currentIndex).getPattern();
	}

	public void consume(String t){
		if (this.currentToken.getPattern().equals(t))
			this.advance();
		else
			error(); 
	}
	
	public void Program(){
		switch(this.currentToken.getPattern()){
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

	public void Var(){
		switch (this.currentToken.getPattern()){
			case "reserved_word":
				this.Type();
				this.consume("variable");

				if(this.currentToken.getPattern().equals("l_brace")){
					this.consume("l_brace");
					this.consume("reserved_word");
					this.consume("r_brace");
				}else{
					this.callError();
				}

				this.consume("semicolon");

				break;
			default:
				this.callError();
				break;
		}
	}

	public void Func(){
		switch (this.currentToken.getPattern()){
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

	public void ParamList(){

	}

	public void Block(){

	}

	public void Stmt(){

	}

	public void Expr(){

	}

	public void Expr1(){

	}

	public void Type(){

	}

	public void Loc(){

	}

	public void FuncCall(){

	}

	public void Lit(){

	}

	private void callError(){
		system.out.println("Atenção! Erro de sintaxe na linha " + this.currentToken.getLine().toString());
	}

	
	/*
	 * GETTERS AND SETTERS
	 */
    public SymbolTable getSymbolTable()
	{
		return this.symbolTable;
	}

	public void setSymbolTable(SymbolTable symbolTable)
	{
		this.symbolTable = symbolTable;
	}

	public Token getCurrentToken()
	{
		return this.currentToken;
	}

	public void setCurrentToken(Token currentToken)
	{
		this.currentToken = currentToken;
	}

	public int getCurrentIndex()
	{
		return this.currentIndex;
	}

	public void setCurrentIndex(int currentIndex)
	{
		this.currentIndex = currentIndex;
	}
}
