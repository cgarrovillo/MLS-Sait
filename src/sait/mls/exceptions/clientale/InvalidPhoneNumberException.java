package sait.mls.exceptions.clientale;

/**
 * Class description: 
 * @author Christian Garrovillo
 *
 */
public class InvalidPhoneNumberException extends Exception 
{
    public InvalidPhoneNumberException() {
        super("Invalid Phone Number.");
    }

    public InvalidPhoneNumberException(String message) 
    {
        super(message);
    }
}