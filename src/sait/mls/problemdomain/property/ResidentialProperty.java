package sait.mls.problemdomain.property;

import java.util.StringTokenizer;

import sait.mls.exceptions.property.InvalidLegalDescriptionException;
import sait.mls.exceptions.property.InvalidNumberOfBathroomsException;

/**
 * Class description: Defines a ResidentialProperty object
 * @author Christian Garrovillo
 *
 */
public class ResidentialProperty extends Property
{
	private boolean active;
	private double area;
	private double bathrooms;
	private int bedrooms;
	private char garage;
	
	/**
	 * 
	 * Initializes the newly created ResidentialProperty.
	 */
	public ResidentialProperty()
	{
		this.active = true;
	}
	
	/**
	 * 
	 * Initializes the newly created ResidentialProperty.
	 * @param id
	 * @param ld
	 * @param ad
	 * @param qu
	 * @param zo
	 * @param askPrice
	 * @param comment
	 * @param area
	 * @param bathrooms
	 * @param bedrooms
	 * @param garage
	 * @throws InvalidNumberOfBathroomsException 
	 * @throws InvalidLegalDescriptionException 
	 */
	public ResidentialProperty(long id, String ld, String ad, String qu, String zo, double askPrice, String comment, double area, double bathrooms, int bedrooms, char garage) throws InvalidNumberOfBathroomsException, InvalidLegalDescriptionException
	{
		super(id,ld,ad,qu,zo,askPrice,comment);
		setArea(area);
		setBathrooms(bathrooms);
		setBedrooms(bedrooms);
		setGarage(garage);
		this.active = true;
	}

	/**
	 * 
	 * Initializes the newly created ResidentialProperty.
	 * @param line A String that contains all the information that defines a ResidentialProperty, separated by ";"s.
	 * @throws InvalidLegalDescriptionException 
	 * @throws InvalidNumberOfBathroomsException 
	 * @throws NumberFormatException 
	 */
	public ResidentialProperty(String line) throws InvalidLegalDescriptionException, NumberFormatException, InvalidNumberOfBathroomsException
	{
		StringTokenizer st = new StringTokenizer(line, ";");
		setId(Long.parseLong(st.nextToken()));
		setLegalDescription(st.nextToken());
		setAddress(st.nextToken());
		setQuadrant(st.nextToken());
		setZone(st.nextToken());
		setAskingPrice(Double.parseDouble(st.nextToken()));
		setComments(st.nextToken());
		setArea(Double.parseDouble(st.nextToken()));
		setBathrooms(Double.parseDouble(st.nextToken()));
		setBedrooms(Integer.parseInt(st.nextToken()));
		setGarage(st.nextToken().charAt(0));
		this.active = true;
	}
	
	/**
	 * Returns the area.
	 * @return the area
	 */
	public double getArea()
	{
		return area;
	}

	/**
	 * Returns the bathrooms.
	 * @return the bathrooms
	 */
	public double getBathrooms()
	{
		return bathrooms;
	}

	/**
	 * Returns the bedrooms.
	 * @return the bedrooms
	 */
	public int getBedrooms()
	{
		return bedrooms;
	}

	/**
	 * Returns the garage.
	 * @return the garage
	 */
	public char getGarage()
	{
		return garage;
	}

	/**
	 * Sets the value of area.
	 * @param area the area to set
	 */
	public void setArea(double area)
	{
		this.area = area;
	}

	/**
	 * Sets the value of bathrooms.
	 * @param bathrooms the bathrooms to set
	 * @throws InvalidNumberOfBathroomsException 
	 */
	public void setBathrooms(double bathrooms) throws InvalidNumberOfBathroomsException
	{
		this.validateBathrooms(bathrooms);
		this.bathrooms = bathrooms;
	}

	/**
	 * Sets the value of bedrooms.
	 * @param bedrooms the bedrooms to set
	 */
	public void setBedrooms(int bedrooms)
	{
		this.bedrooms = bedrooms;
	}

	/**
	 * Sets the value of garage.
	 * @param garage the garage to set
	 */
	public void setGarage(char garage)
	{
		this.garage = garage;
	}
	
	private void validateBathrooms(double bathrooms) throws InvalidNumberOfBathroomsException
	{
		//System.out.println("executed");
		double remainder = bathrooms % 1.0;
		
		//System.out.println(remainder);
		//System.out.println(bathrooms);
		
		if (bathrooms <= 0.0 ||remainder != 0.5 && remainder != 0.0)
		{
			//System.out.println("execution executed");
			throw new InvalidNumberOfBathroomsException("Invalid number of bathrooms. Bathrooms can be a full bathroom (1) or a powder room (.5)");
		}
	}
	
	
	@Override
	public String toString()
	{
		return super.toString()
				+ "\nArea: " + getArea()
				+ "\nBathrooms: " + getBathrooms()
				+ "\nBedrooms: " + getBedrooms()
				+ "\nGarage: "+ getGarage();
	}
	
}
