package sait.mls.persistence.property;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import sait.mls.exceptions.property.InvalidLegalDescriptionException;
import sait.mls.exceptions.property.InvalidNumberOfBathroomsException;
import sait.mls.persistence.Broker;
import sait.mls.problemdomain.property.CommercialProperty;
import sait.mls.problemdomain.property.ResidentialProperty;
import utility.SLL;

/**
 * Class description: A broker for ResidentialProperty objects to be used by a GUI
 * @author Christian Garrovillo
 *
 */
public class ResidentialPropertyBroker implements Broker
{
	private static final String SER_FILE = "res/resprop.ser";
	private static final String INPUT_FILE = "res/resprop.txt";
	private static ResidentialPropertyBroker rb;
	private static ResidentialProperty rprop;
	private SLL rList;
	private long maxId;
	
	/**
	 * 
	 * Method that gets called by getBroker() representing a ResidentialPropertyBroker Object.
	 */
	private ResidentialPropertyBroker()
	{
		
	}
	
	/**
	 * Method to create an instance of ResidentialPropertyBroker(). Used for Singleton Pattern.
	 * @return an instance of ResidentailPropertyBroker
	 * @throws InvalidNumberOfBathroomsException 
	 * @throws InvalidLegalDescriptionException 
	 * @throws NumberFormatException 
	 */
	public static ResidentialPropertyBroker getBroker() throws NumberFormatException, InvalidLegalDescriptionException, InvalidNumberOfBathroomsException
	{
		File file = new File(SER_FILE);
		if (file.exists())
		{
			if (rb == null)
			{
				rb = new ResidentialPropertyBroker();
			}
			rb.readFromSer();
			
		}
		else
		{
			if (rb == null)
			{
				rb = new ResidentialPropertyBroker();
			}
			rb.loadInitialList();
		}
		return rb;
	}
	
	/**
	 * Loads an SLL consisting of ResidentialPropertys based on a text file "res/resprop.txt"
	 * @throws InvalidNumberOfBathroomsException 
	 * @throws InvalidLegalDescriptionException 
	 * @throws NumberFormatException 
	 */
	private void loadInitialList() throws NumberFormatException, InvalidLegalDescriptionException, InvalidNumberOfBathroomsException
	{
		try
		{
			this.rList = new SLL();
			
			BufferedReader input = new BufferedReader(new FileReader(INPUT_FILE));
			String line = input.readLine();
			long id = 0L;
			while (line != null)
			{
				line = String.valueOf(++id) + ";" + line;
				rprop = new ResidentialProperty(line);
				rList.add(rprop);
				maxId++;
				line = input.readLine();
			}
			input.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes the SLL into a file ("res/resrop.ser").
	 */
	private void writeToSer()
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SER_FILE));
			oos.writeObject(rList);
			oos.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to read from the .ser file and load its content objects to 
	 * a singly linked list
	 */
	private void readFromSer()
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SER_FILE));
			rList = (SLL) ois.readObject();
			ResidentialProperty lastNode = (ResidentialProperty) rList.get(rList.size() - 1);
			maxId = lastNode.getId();
			//System.out.println(maxId);
			//maxId++;
			//System.out.println(maxId);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that releases resources allocated for a ResidentialPropertyBroker.
	 * Sets the maxId attribute to 0L.;
	 */
	@Override
	public void closeBroker()
	{
		rb.writeToSer();
		rb = null;
		maxId = 0L;
	}

	/**
	 * Method to add a ResidentailProperty to the SLL
	 * @param o - the ResidentialProperty object that should be added to the SLL
	 */
	@Override
	public boolean persist(Object o)
	{
		ResidentialProperty r = (ResidentialProperty) o;
		if (r.getId() == 0L)
		{
			r.setId(++this.maxId);
			rList.add(r);
			return true;
		}
		else
		{
			rList.set(r, (int) r.getId() - 1);
			return true;
		}
	}

	/**
	 * Method that removes a ResidentialProperty from the SLL
	 * @param o - the ResidentialProperty object that should be removed from the SLL
	 */
	@Override
	public boolean remove(Object o)
	{
		ResidentialProperty r = (ResidentialProperty) o;
		int index = rList.indexOf(r);
		if (index > -1)
		{
			rList.removeAt(index);
			--maxId;
			return true;
		}
		return false;
	}

	/**
	 * Method that searches the SLL for matching items/criteria.
	 * @param o an object of type ResidentialProperty that contains either a id, quadrant, or a price.
	 * @return rList2 an ArrayList that the GUI uses to display the found items
	 */
	@Override
	public List search(Object o) throws IOException
	{
		ArrayList<ResidentialProperty> rList2 = new ArrayList<>();
		ResidentialProperty r;
		ResidentialProperty searchFor = (ResidentialProperty) o;
		
		if (searchFor.getId() != 0)
		{
			//System.out.println(coList.size());
			for (int x = 0; x < rList.size(); ++x)
			{
				//System.out.println(x);
				r = (ResidentialProperty) rList.get(x);
				if (r.getId() == searchFor.getId())
				{
					rList2.add(r);
				}
			}
		}
		if (searchFor.getQuadrant() != null)
		{
			//System.out.println(searchFor.getQuadrant());
			for (int x = 0; x< rList.size(); ++x)
			{
				r = (ResidentialProperty) rList.get(x);
				if (r.getQuadrant().equals(searchFor.getQuadrant()))
				{
					//System.out.println(co.getQuadrant());
					rList2.add(r);
				}
			}
		}
		if (searchFor.getAskingPrice() > 0)
		{
			for (int x = 0; x< rList.size(); ++x)
			{
				r = (ResidentialProperty) rList.get(x);
				if (r.getAskingPrice() >= searchFor.getAskingPrice())
				{
					rList2.add(r);
				}
			}
		}
		
		return rList2;
	}

}
