package lab1;
/**
 * The class {@code MathParser} is a utility class 
 * which provides parsing of simple mathematical expressions
 * with decimal, binary, octonary and hexadecimal numbers
 * and standard operators (+,-,*,/,^)   
 */
import java.util.*;

public class MathParser {
	/**
	 * Since class {@code MathParser} is a utility class 
	 * only with static methods so default constructor is private 
	 * to prevent generation of objects   
	 */
	private MathParser(){}
	
	/**
	 * Returns true if string starts with plus
	 */
	public static boolean startsWithPlus(String e) {
		return e.charAt(0)=='+';
	}
	/**
	 * Returns true if string starts with minus
	 */	
	public static boolean startsWithMinus(String e) {
		return e.charAt(0)=='-';
		
	}
	/**
	 * Parses the expressions and returns operands as a list of String  
	 * @param expression is expression to be parsed  
	 */
	public static List<String> getOperandsStr(String expression) {
		String[] operandStr=expression.split("\\+|-|\\*|\\/|\\(|\\)|\\^");
		List<String> listOfOperands=new ArrayList<>();
		for (String s:operandStr) {
			if (s.length()!=0) listOfOperands.add(s);
		}
		return listOfOperands;
	}
	
	/**
	 * Parses the expressions and returns operands as an array of double
	 * @param expression is expression to be parsed 
	 */
	public static double[] getOperands(String expression) {
		List<String> listOfOperands=getOperandsStr(expression);
		double[] operandNum=new double[listOfOperands.size()];
		for (int i=0; i<listOfOperands.size();i++) {
			String s=listOfOperands.get(i);
			String prefix=s.length()>2? s.substring(0, 2):"";
			switch(prefix) {
				case "":operandNum[i]=Double.valueOf(s); break;
				case "bx": 
					s=s.substring(2,s.length()); 
					operandNum[i]=(double) Integer.parseUnsignedInt(s, 2); 
					break;
				case "ox": 
					s=s.substring(2,s.length()); 
					operandNum[i]=(double) Integer.parseUnsignedInt(s, 8); 
					break;
				case "hx": 
					s=s.substring(2,s.length()); 
					operandNum[i]=(double) Integer.parseUnsignedInt(s, 16); 
					break;
				default: operandNum[i]=Double.valueOf(s); break;
				
			}
						
		}
		return operandNum;
	}	
	
	/**
	 * Parses the expressions and returns operators as a list of String 
	 * @param expression is expression to be parsed 
	 */
	public static List<String> getOperatorsStr(String expression) {
		String[] operators=expression.split("\\w|\\(|\\)");
		List<String> listOfOperators=new ArrayList<>();
		for (String s:operators) {
			if (s.length()==1&&!s.equals(".")) listOfOperators.add(s);
		}
		return listOfOperators;
	}
	
	/**
	 * Finds all entries of symbol in string and returns their positions in a array of integers
	 */
	public static int[] findSymbolIndices(char c, String expression) {
		int number=0;
		for (char ch:expression.toCharArray()) if(ch==c) number++;
		int[] indices=new int[number];
		int index=0;
		for (int i=0;i<indices.length;i++) {
			indices[i]=expression.indexOf(c, index);
			index=indices[i]+1;
		}
		return indices;
	}
	
	
}
