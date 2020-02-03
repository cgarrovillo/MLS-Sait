package sait.mls.exceptions.property;

/**
 * Class description: An exception that is thrown when a Legal Description is not valid
 * @author Christian Garrovillo
 *
 */
public class InvalidLegalDescriptionException extends Exception
{
	private static final long serialVersionUID = 9011842589585419074L;

	public InvalidLegalDescriptionException()
	{
		super("Invalid Legal Description.");
	}
	
	public InvalidLegalDescriptionException(String message)
	{
		super(message);
	}

}
