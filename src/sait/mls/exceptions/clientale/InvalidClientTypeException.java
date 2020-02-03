package sait.mls.exceptions.clientale;

/**
 * Class description: 
 * @author Christian Garrovillo
 *
 */
public class InvalidClientTypeException extends Exception 
{
    public InvalidClientTypeException() 
    {
        super("Invalid Client Type.");
    }

    public InvalidClientTypeException(String message) 
    {
        super(message);
    }
}
