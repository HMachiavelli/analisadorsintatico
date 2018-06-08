package LexicAnalyse.Contract;

public abstract class AnalyseContract {
	public String tokenName = "";
	public String tokenValue = "";

	public abstract boolean analyse(String lexeme, String next);

	public void log()
	{
		System.out.println(String.format("[%s, %s]", this.tokenName, this.tokenValue));
	}
}
