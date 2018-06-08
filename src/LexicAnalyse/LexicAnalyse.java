package LexicAnalyse;

import java.util.Iterator;

import LexicAnalyse.Contract.AnalyseContract;

public class LexicAnalyse {
	
	//lista de analisaddores
	private AnalyseList<AnalyseContract> list;
	//lista de tokens
	private SymbolTable tokenTable;
    //numeros
    private AnalyseNumber num;  
    //variaveis
    private AnalyseVariable var; 
    //palavras reservadas
    private AnalyseReservedWord deterministic; 
    //operadores relacionais
    private AnalyseRelationalOP relational; 
    //operadores aritmeticos
    private AnalyseArithOP arith; 
    //operadores logicos
    private AnalyseLogicOP logic; 
    //strings
    private AnalyseString string; 
    //comentários
    private AnalyseIgnored ignored; 
    //demais caracteres
    private AnalyseCaracter caracter; 
    //adiciona caracter
    private PlusCaractere addCaracter; 
    
    public LexicAnalyse() {
    	this.num = new AnalyseNumber();
    	this.var = new AnalyseVariable();
    	this.deterministic = new AnalyseReservedWord();
    	this.relational = new AnalyseRelationalOP();
    	this.arith = new AnalyseArithOP();
    	this.logic = new AnalyseLogicOP();
    	this.string = new AnalyseString();
    	this.ignored = new AnalyseIgnored();
    	this.caracter = new AnalyseCaracter();
    	this.addCaracter = new PlusCaractere();
    	this.tokenTable = new SymbolTable();
    	this.list = new AnalyseList<AnalyseContract>();
    	this.list.add(num);
    	this.list.add(deterministic);
    	this.list.add(var);
    	this.list.add(relational);
    	this.list.add(arith);
    	this.list.add(logic);
    	this.list.add(string);
    	this.list.add(ignored);
    	this.list.add(caracter);
    }
    
    public SymbolTable analyse(String code) {
    	String content = ignored.removeComments(code);
    	
    	String lines[] = content.split("\\r?\\n");
    	
    	for (int j = 0; j < lines.length; j++) {
            lines[j] = addCaracter.addCaracter(lines[j]);
        }
    	
    	for (int lineIdx = 0, columnIdx = 0; lineIdx < lines.length; lineIdx++, columnIdx = 0) {
            String[] line = lines[lineIdx].split("(\\$)");

            String[] linesTmp = new String[line.length];
            int k = 0;

            for (String column : line) {
                if (!column.isEmpty()) {
                    linesTmp[k++] = column;
                }
            }

            line = linesTmp;

            for (String column : line) {
                if (column == null || column.isEmpty()) continue;

                Token token = new Token(column);
                token.setLine(lineIdx);
                token.setColum(columnIdx);
                token.setLineFile(String.join(" ", line));

                Iterator<AnalyseContract> it = list.getList().iterator();

                while (it.hasNext()) {
                    AnalyseContract analyser = it.next();

                    String next = (columnIdx == line.length - 1 ? "" : line[(columnIdx + 1)]);
                    if (analyser.analyse(token.getLexeme(), next)) {
                        
                        token.setPattern(analyser.tokenName); //seta o tipo

                        tokenTable.add(token); // adiciona o token na tabela de simbolos

                        columnIdx++;

                        break; // para no primeiro reconhecer só
                    }
                }
            }
        }
    	
    	return tokenTable;
    }
    
}
