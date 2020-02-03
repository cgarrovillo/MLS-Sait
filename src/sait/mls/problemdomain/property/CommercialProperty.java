package sait.mls.problemdomain.property;

import java.io.Serializable;
import java.util.StringTokenizer;

import sait.mls.exceptions.property.InvalidLegalDescriptionException;

/**
 * Class description: Defines a CommercialProperty Object
 * @author Christian Garrovillo
 *
 */
public class CommercialProperty extends Property implements Serializable
{
	private static final long serialVersionUID = -7257448135478501941L;
	private boolean active;
	private String type;
	private int floors;
	
	/**
	 * 
	 * Initializes the newly created CommercialProperty.
	 */
	public CommercialProperty()
	{
		this.active = true;
	}
	
	/**
	 * 
	 * Initializes the newly created CommercialProperty.
	 * @param id
	 * @param ld
	 * @param ad
	 * @param qu
	 * @param zo
	 * @param askPrice
	 * @param comments
	 * @param type
	 * @param floors
	 * @throws InvalidLegalDescriptionException 
	 */
	public CommercialProperty(long id, String ld, String ad, String qu, String zo, double askPrice, String comments, String type, int floors) throws InvalidLegalDescriptionException
	{
		super(id,ld,ad,qu,zo,askPrice,comments);
		setType(type);
		setNoFloors(floors);
		this.active = true;
	}
	
	/**
	 * 
	 * Initializes the newly created CommercialProperty.
	 * @param line A String that contains the information that defines a CommercialProperty separated by ";"s.
	 * @throws InvalidLegalDescriptionException 
	 */
	public CommercialProperty(String line) throws InvalidLegalDescriptionException
	{
		StringTokenizer st = new StringTokenizer(line, ";");
		setId(Long.parseLong(st.nextToken()));
		setLegalDescription(st.nextToken());
		setAddress(st.nextToken());
		setQuadrant(st.nextToken());
		setZone(st.nextToken());
		setAskingPrice(Double.parseDouble(st.nextToken()));
		setComments(st.nextToken());
		setType(st.nextToken());
		setNoFloors(Integer.parseInt(st.nextToken()));
		this.active = true;
	}

	/**
	 * Returns the type.
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Returns the floors.
	 * @return the floors
	 */
	public int getNoFloors()
	{
		return floors;
	}

	/**
	 * Sets the value of type.
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Sets the value of floors.
	 * @param floors the floors to set
	 */
	public void setNoFloors(int floors)
	{
		this.floors = floors;
	}
	
	
	
	@Override
	public String toString()
	{
		return super.toString()
				+ "\nType: " + getType()
				+ "\nNo. Floors: " + getNoFloors();
	}
	
}
