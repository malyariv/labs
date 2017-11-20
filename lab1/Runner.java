package lab1;
/**
 * The class {@code Runner} is a console application 
 * to calculate simple mathematical expressions 
 * with decimal, binary, octonary and hexadecimal numbers 
 * using {+,-,*,/, ^} and  round brackets.
 */
public class Runner {
	/**
     * Runs the application. It uses class {@code Calculator} 
     * implementing interface {@code ConsoleCalculator}.
     * The method provides a standard console IO interface, 
     * expression spelling check  and calculations.
     */
	public static void main(String[] args) {
		ConsoleCalculator calc=new Calculator();
		
		System.out.println("Hi! You can calculate your expression here.\nYou can also use binary, octonary and hexadecimal numbers using prefixes bx, ox and hx, respectively. Enjoy! \nPlease, enter 'exit' to quit\n");
		while(true) {
			String s=calc.readExpression();
			if(s.equals("exit")) return;
			String str=calc.checkSpelling(s);
			if (!str.equalsIgnoreCase("Ok")) {
				calc.printErrorMessage(str);
				try {
					Thread.sleep(100);
				}catch(InterruptedException e) {}
				continue;
			}
			try {
				calc.printAnswer("="+calc.calculate(s));
			}catch(MultiExponentException e) {
				calc.printErrorMessage("Wrong expression. Wrong operators");
			 }
		}		

	}

}
