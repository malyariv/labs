package lab1;
/**
 * The class {@code Calculator} is a console application 
 * to calculate simple mathematical expressions 
 * with decimal, binary, octonary and hexadecimal numbers 
 * using {+,-,*,/} and  round brackets.
 */
import java.util.*;

public class Calculator {

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
		Tree tree;
		Scanner scanner=new Scanner(System.in);
		System.out.println("Hi! You can calculate your expression here.\nYou can also use binary, octonary and hexadecimal numbers using prefixes bx, ox and hx, respectively. Enjoy! \nPlease, enter 'exit' to quit\n");
		while(true) {
			System.out.println("Enter your expression");
			String s=scanner.nextLine();
			if(s.equals("exit")) return;
			
			tree=new Tree(s);
			String str=tree.check();
			if (!str.equalsIgnoreCase("Ok")) {
				System.out.println("Wrong expression. "+str);
				continue;
			}
			System.out.println("="+tree.calculate());
		}
	}
	/**
     * The following method duplicates calculation ability of method run() and returns number result for JUnit tests.
     */
	public double calculate(String expression) {
		Tree tree =new Tree(expression);
		tree.check();
		return tree.calculate();
	}

}
