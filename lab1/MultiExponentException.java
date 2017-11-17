package lab1;

/**
 * The class {@code MultiExponentException} extends RuntimeException
 * It indicates a multiple usage of an exponent operator (^) in a row
 * as 2^2^2 
 */

public class MultiExponentException extends RuntimeException{

	public MultiExponentException() {
		super();
	}

	public MultiExponentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MultiExponentException(String message, Throwable cause) {
		super(message, cause);
	}

	public MultiExponentException(String message) {
		super(message);
	}

	public MultiExponentException(Throwable cause) {
		super(cause);
	}

}
