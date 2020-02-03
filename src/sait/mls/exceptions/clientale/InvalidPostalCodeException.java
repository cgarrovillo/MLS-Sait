package sait.mls.exceptions.clientale;

/**
 * Class description: 
 * @author Christian Garrovillo
 *
 */
public class InvalidPostalCodeException extends Exception 
{
    public InvalidPostalCodeException() 
    {
        super("Invalid Postal Code.");
    }

    public InvalidPostalCodeException(String msg) 
    {
        super(msg);
    }
}