package src.exceptions;
import java.io.IOException;

public class ArgsParseException extends IOException {
	
	/**
	 * Every throw of this exception should result in printing/logging its message to the user
	 * and termination of the program.
	 */
	    private static final long serialVersionUID = 1L;

	    public ArgsParseException(String message) {
	        super(message);
	    }

}
