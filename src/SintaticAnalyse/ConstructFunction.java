package SintaticAnalyse;

import java.util.ArrayList;

import LexicAnalyse.*;


public class ConstructFunction{
	
	private Token[][] array;

	public ConstructFunction(int size) {
		this.array = new Token[size][10] ;
	}
	
	public boolean constructFunctions(SymbolTable table) {
		int k = 0;
		
		for (int i = 0; k < table.getSize(); i++) {
			
			for(int j = 0; j < array[i].length; j++) {
				if (table.getToken(k).getPattern() != "semicolon" && 
					table.getToken(k).getPattern() != "l_bracket" && 
					table.getToken(k).getPattern() != "r_bracket"	) {
						this.array[i][j] = table.getToken(k++);
				} else {
					this.array[i][j] = table.getToken(k++);
					break;
				}
					
			}
			
		}
		return false;		
	}
}
