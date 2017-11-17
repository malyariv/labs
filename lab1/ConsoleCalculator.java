package lab1;
/**
 * The interface {@code ConsoleCalculator} describes 
 * basic methods of the class using a standard console IO interface
 */
public interface ConsoleCalculator {
	/**
     * Reads an expression from a standard console.
     */
	String readExpression();
	/**
     * Prints an answer to a standard console.
     */
	void printAnswer(String s);
	/**
     * Prints an error message to a standard console.
     */
	void printErrorMessage(String s);
	/**
     * Checks the expression spelling.
     */
	String checkSpelling(String s);
	/**
     *  Calculates the received expression and returns result. 
     */
	String calculate(String s);
}
