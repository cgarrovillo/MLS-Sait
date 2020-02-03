package sait.mls.exceptions.property;

/**
 * Class description: An exception that is thrown when the number of bathrooms is invalid.
 * @author Christian Garrovillo
 *
 */
public class InvalidNumberOfBathroomsException extends Exception
{
	private static final long serialVersionUID = -6312119469290986362L;

	public InvalidNumberOfBathroomsException()
	{
		super("Invalid number of Bathrooms.");
	}
	
	public InvalidNumberOfBathroomsException(String message)
	{
		super(message);
	}
}
