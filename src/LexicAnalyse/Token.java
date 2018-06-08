package LexicAnalyse;

public class Token {

	private String lexeme;
	private String pattern;
	private int line;
    private int colum;
    private String name;
    private String value;
    private String lineFile; // temp

	public Token(String lexeme) {
		super();
		this.lexeme = lexeme;
	}

	public String getLexeme() {
		return lexeme;
	}

	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColum() {
		return colum;
	}

	public void setColum(int colum) {
		this.colum = colum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLineFile() {
		return lineFile;
	}

	public void setLineFile(String lineFile) {
		this.lineFile = lineFile;
	}
	
	public String toString() {
		String object = getLexeme() + ": " + getPattern();
		return object;	
	}
}
