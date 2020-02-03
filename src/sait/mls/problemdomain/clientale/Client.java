package sait.mls.problemdomain.clientale;

import java.util.StringTokenizer;

import sait.mls.exceptions.clientale.InvalidClientTypeException;
import sait.mls.exceptions.clientale.InvalidPhoneNumberException;
import sait.mls.exceptions.clientale.InvalidPostalCodeException;

/**
 * Class description: A Client object representing client data.
 * @author Christian Garrovillo
 *
 */
public class Client
{
	private boolean active;
	private long id; //8
	private String firstname; //22
	private String lastname; //22
	private String address; //52 (50 + 2 overhead)
	private String postal; //9
	private String phone; //14
	private char type; //1
	public static final int SIZE = 130;
	
	/**
	 * 
	 * Initializes the newly created Client.
	 */
	public Client()
	{
		this.active = true;
	}
	
	/**
	 * 
	 * Initializes the newly created Client.
	 * @param id the Client's id number
	 * @param fn the Client's first name
	 * @param ln the Client's last name
	 * @param ad the client's address
	 * @param post the client's postal code
	 * @param pho the client's phone number
	 * @param type the client type: R - Residential, C - Commercial
	 */
	public Client(long id, String fn, String ln, String ad, String post, String pho, char type) throws InvalidPostalCodeException, InvalidPhoneNumberException, InvalidClientTypeException
	{
		setClientID(id);
		setFirstName(fn);
		setLastName(ln);
		setAddress(ad);
		setPostalCode(postal);
		setPhoneNumber(phone);
		setClientType(type);
		active = true;
	}
	
	/**
	 * 
	 * Initializes a Client object with initial values 
	 * @param line
	 */
	public Client(String line)
	{
		StringTokenizer st = new StringTokenizer(line, ";");
		setClientID(Long.parseLong(st.nextToken()));
		setFirstName(st.nextToken());
		setLastName(st.nextToken());
		setAddress(st.nextToken());
		try
		{
			setPostalCode(st.nextToken());
		} catch (InvalidPostalCodeException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			setPhoneNumber(st.nextToken());
		} catch (InvalidPhoneNumberException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			setClientType(st.nextToken().charAt(0));
		} catch (InvalidClientTypeException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.active = true;
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if (this.id == ((Client)o).getClientID())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Returns the client's address
	 * @return Address in type String
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * Returns the client's ID
	 * @return ID in type Long
	 */
	public long getClientID()
	{
		return id;
	}
	
	/**
	 * Returns the client type of the client
	 * @return Type in type char: R-Residential C-Commercial
	 */
	public char getClientType()
	{
		return type;
	}
	
	/**
	 * Returns the client's First name
	 * @return firstname in type String
	 */
	public String getFirstName()
	{
		return firstname;
	}
	
	/**
	 * Returns the client's Last Name
	 * @return lastname in type String
	 */
	public String getLastName()
	{
		return lastname;
	}
	
	/**
	 * Returns the client's phone number
	 * @return phone number in type String
	 */
	public String getPhoneNumber()
	{
		return phone;
	}
	
	/**
	 * Returns the client's postal code
	 * @return postal code in type String
	 */
	public String getPostalCode()
	{
		return postal;
	}
	
	/**
	 * Returns the boolean for the Client's active state
	 * @return active a boolean representing true if Client is active and vice versa
	 */
	public boolean isActive()
	{
		return active;
	}
	
	/**
	 * Sets the client's status
	 * @param active boolean to pass to this method
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	/**
	 * Sets the client's Address
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	/**
	 * Sets the client's ID
	 * @param id the id to set
	 */
	public void setClientID(long id)
	{
		if (id > 0)
			this.id = id;
	}
	
	/**
	 * Sets the client's type
	 * @param type the type to set
	 * @throws InvalidClientTypeException
	 */
	public void setClientType(char type) throws InvalidClientTypeException
	{
		this.type = type;
	}
	
	/**
	 * Sets the client's first name
	 * @param firstname the first name to set
	 */
	public void setFirstName(String firstname)
	{
		this.firstname = firstname;
	}
	
	/**
	 * Sets the client's last Name
	 * @param lastname the last name to set
	 */
	public void setLastName(String lastname)
	{
		this.lastname = lastname;
	}
	
	/**
	 * Sets the client's phone number
	 * @param phone phone number to set
	 * @throws InvalidPhoneNumberException
	 */
	public void setPhoneNumber(String phone) throws InvalidPhoneNumberException
	{
		String st1 = phone;
		String st2 = "(\\d{3}-\\d{3}-\\d{4})";
		if (!st1.matches(st2))
		{
			throw new InvalidPhoneNumberException();
		}
		this.phone = phone;
	}
	
	/**
	 * Sets the client's postal code
	 * @param postal the postal code to set
	 * @throws InvalidPostalCodeException
	 */
	public void setPostalCode(String postal) throws InvalidPostalCodeException
	{
		String string1 = postal;
		String match = "[A-Z][0-9][A-Z]\\s[0-9][A-Z][0-9]";
		if (!string1.matches(match))
		{
			throw new InvalidPostalCodeException();
		}
		this.postal = postal;
	}
	
	public String toString()
	{
		return "\nActive: " + this.active
				+ "\nID:" + this.id
				+ "\nName: " + this.firstname + " " + this.lastname
				+ "\nAddress: " + this.address
				+ "\nPostal Code: " + this.postal
				+ "\nPhone: " + this.phone
				+ "\nType: " + this.type;
	}
	
}
