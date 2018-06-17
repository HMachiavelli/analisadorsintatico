import java.io.IOException;
import LexicAnalyse.*;
import SyntacticAnalyse.SyntacticAnalyse;

public class Main {

    public static void main(String[] args) {
        String content = "";

        final String path = "codeNave";

        try {
            content = new FileHandler().read(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        SymbolTable tableToken = new SymbolTable();
        LexicAnalyse lexicAnalyse = new LexicAnalyse();

        tableToken = lexicAnalyse.analyse(content);

        SyntacticAnalyse syntaticAnalyse = new SyntacticAnalyse(tableToken);

        System.out.println(tableToken.toString());
        syntaticAnalyse.analyse();

    }
}
