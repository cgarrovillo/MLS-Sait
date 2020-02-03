package sait.mls.problemdomain.property;

import java.io.Serializable;

import sait.mls.exceptions.property.InvalidLegalDescriptionException;

/**
 * Class description: 
 * @author Christian Garrovillo
 *
 */
public abstract class Property implements Serializable
{
	private static final long serialVersionUID = 8255507675708997787L;
	long id;
	String legalDescription;
	String address;
	String quadrant;
	String zone;
	double askingPrice;
	String comments;
	
	public Property()
	{
		
	}
	
	public Property(long id, String ld, String addr, String qua, String zo, double askPrice, String comment) throws InvalidLegalDescriptionException
	{
		setId(id);
		setLegalDescription(ld);
		setAddress(addr);
		setQuadrant(qua);
		setZone(zo);
		setAskingPrice(askPrice);
		setComments(comment);
	}

	/**
	 * Returns the id.
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * Returns the legalDescription.
	 * @return the legalDescription
	 */
	public String getLegalDescription()
	{
		return legalDescription;
	}

	/**
	 * Returns the address.
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * Returns the quadrant.
	 * @return the quadrant
	 */
	public String getQuadrant()
	{
		return quadrant;
	}

	/**
	 * Returns the zone.
	 * @return the zone
	 */
	public String getZone()
	{
		return zone;
	}

	/**
	 * Returns the askingPrice.
	 * @return the askingPrice
	 */
	public double getAskingPrice()
	{
		return askingPrice;
	}

	/**
	 * Returns the comments.
	 * @return the comments
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * Sets the value of id.
	 * @param id the id to set
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * Sets the value of legalDescription.
	 * @param legalDescription the legalDescription to set
	 * @throws InvalidLegalDescriptionException 
	 */
	public void setLegalDescription(String legalDescription) throws InvalidLegalDescriptionException
	{
		this.validateLegal(legalDescription);
		this.legalDescription = legalDescription;
	}

	/**
	 * Sets the value of address.
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * Sets the value of quadrant.
	 * @param quadrant the quadrant to set
	 */
	public void setQuadrant(String quadrant)
	{
		this.quadrant = quadrant;
	}

	/**
	 * Sets the value of zone.
	 * @param zone the zone to set
	 */
	public void setZone(String zone)
	{
		this.zone = zone;
	}

	/**
	 * Sets the value of askingPrice.
	 * @param askingPrice the askingPrice to set
	 */
	public void setAskingPrice(double askingPrice)
	{
		this.askingPrice = askingPrice;
	}

	/**
	 * Sets the value of comments.
	 * @param comments the comments to set
	 */
	public void setComments(String comments)
	{
		this.comments = comments;
	}
	
	public boolean equals(Object o)
	{
		Property p = (Property) o;
		return id == p.getId();
	}
	
	private void validateLegal(String legal) throws InvalidLegalDescriptionException
	{
		String[] split = legal.split("[A-Z || [-]]");
        if (split.length == 3) 
        {
        	//System.out.println("executed");
            int left = Integer.parseInt(split[0]);
            int middle = Integer.parseInt(split[1]);
            int right = Integer.parseInt(split[2]);
            //System.out.println(left);
            //System.out.println(middle);
            //System.out.println(right);
            
            
            if (left < 0 || left >= 10000 || middle < 0 || middle >= 10000 || right < 0 || right >= 100) 
            {
            	//System.out.println("exception executed");
                throw new InvalidLegalDescriptionException("Invalid Legal Description: " + legal + " Input must have a format of [0-9999][A-Z][0-9999][-][0-99].");
            }
         }
	}
	
	@Override
	public String toString()
	{
		return "\nID: " + getId()
				+ "\nLegal Description: " + getLegalDescription()
				+ "\nAddress: " + getAddress()
				+ "\nQuadrant: " + getQuadrant()
				+ "\nZone: " + getZone()
				+ "\nAsking Price: " + getAskingPrice()
				+ "\nComments: " + getComments()
				+ "\n\n";
	}
}
