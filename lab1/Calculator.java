package lab1;
/**
 * The class {@code Calculator} is a console application 
 * to calculate simple mathematical expressions 
 * with decimal, binary, octonary and hexadecimal numbers 
 * using {+,-,*,/, ^} and  round brackets.
 */
import java.util.*;

public class Calculator implements ConsoleCalculator {

	/**
     * Runs the application by running the private method run().
     */
	public static void main(String[] args) {
		new Calculator().run();
	}
	/**
     * The method provides a standard console IO interface, expression spelling check  and calculations 
     * by means of class {@code Tree} variable.
     */
	private void run() {
		String s;
		System.out.println("Hi! You can calculate your expression here.\nYou can also use binary, octonary and hexadecimal numbers using prefixes bx, ox and hx, respectively. Enjoy! \nPlease, enter 'exit' to quit\n");

		while(true) {
			s=readExpression();
			if(s.equals("exit")) return;
			String str=checkSpelling(s);
			if (!str.equalsIgnoreCase("Ok")) {
				printErrorMessage(str);
				continue;
			}
			try {
				printAnswer("="+calculate(s));
			}catch(MultiExponentException e) {
				printErrorMessage("Wrong expression. Wrong operators");
			 }
		}
	}
	
	
	
	/**
     * Reads an expression from a standard console.
     */
	@Override
	public String readExpression() {
		System.out.println("Enter your expression");
		Scanner scanner=new Scanner(System.in);
		return scanner.nextLine();
	}
	/**
     * Prints an answer to a standard console.
     */
	@Override
	public void printAnswer(String s) {
		System.out.println(s);
	}
	/**
     * Prints an error message to a standard console.
     */
	@Override
	public void printErrorMessage(String s) {
		System.err.println("Wrong expression. "+s);
	}
	/**
     * Checks the expression spelling and returns "Ok" or an error message such as
     * "Wrong operators", "Wrong brackets", "Wrong number format"
     */
	@Override
	public String checkSpelling(String s) {
		Tree tree=new Tree(s);
		return tree.check();
	}


	/**
     *  Calculates the received expression and returns result. 
     *  It also repeats check of spelling for JUnit tests.
     */
	@Override	
	public String calculate(String expression) {
		Tree tree=new Tree(expression);
		String str=tree.check();
		if (!str.equalsIgnoreCase("Ok")) return "Wrong expression. "+str;
		try {
			return ""+tree.calculate();
		}catch(MultiExponentException e) {
			return "Wrong expression. Wrong operators";
			}
	}

}
