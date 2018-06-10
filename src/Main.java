import java.io.IOException;
import java.util.Iterator;

import LexicAnalyse.*;
import LexicAnalyse.Contract.AnalyseContract;
import SintaticAnalyse.*;

public class Main {

    public static void main(String[] args) {
    	
        SymbolTable tableToken = new SymbolTable();
        LexicAnalyse lexicAnalyse = new LexicAnalyse(); 
        
        String content = "";
    	
    	final String path = "code.txt";

        try {
            content = new FileHandler().read(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        tableToken = lexicAnalyse.analyse(content);
        
        System.out.println(tableToken.toString());
        
        
    }
}
