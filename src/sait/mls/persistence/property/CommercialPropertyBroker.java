package sait.mls.persistence.property;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import sait.mls.exceptions.property.InvalidLegalDescriptionException;
import sait.mls.persistence.Broker;
import sait.mls.problemdomain.property.CommercialProperty;
import utility.SLL;

/**
 * Class description: A broker for CommercialProperty objects to be used by a GUI
 * @author Christian Garrovillo
 *
 */
public class CommercialPropertyBroker implements Broker
{

	private static final String SER_FILE = "res/comprop.ser";
	private static final String INPUT_FILE = "res/comprop.txt";
	private static CommercialPropertyBroker cob;
	private static CommercialProperty cprop;
	private SLL coList;
	private long maxId;
	
	/**
	 * 
	 * Method that gets called by getBroker() representing a CommercialPropertyBroker Object.
	 */
	private CommercialPropertyBroker()
	{
	}
	
	/**
	 * Method to create an instance of CommercialPropertyBroker(). Used for Singleton Pattern.
	 * @return an instance of CommercialPropertyBroker
	 * @throws InvalidLegalDescriptionException 
	 */
	public static CommercialPropertyBroker getBroker() throws InvalidLegalDescriptionException
	{
		File file = new File(SER_FILE);
		if (file.exists())
		{
			if (cob == null)
			{
				cob = new CommercialPropertyBroker();
			}
			cob.readFromSer();
			
		}
		else
		{
			if (cob == null)
			{
				cob = new CommercialPropertyBroker();
			}
			cob.loadInitialList();
			//cob.writeToSer();
			//cob.readFromSer();
		}
		return cob;
	}
	
	/**
	 * Loads an SLL consisting of CommercialPropertys based on a text file "res/comprop.txt"
	 * @throws InvalidLegalDescriptionException 
	 */
	private void loadInitialList() throws InvalidLegalDescriptionException
	{
		try
		{
			this.coList = new SLL();
			
			BufferedReader input = new BufferedReader(new FileReader(INPUT_FILE));
			String line = input.readLine();
			long id = 0L;
			while (line != null)
			{
				line = String.valueOf(++id) + ";" + line;
				cprop = new CommercialProperty(line);
				
				//System.out.println(coList.toString() + " " + coList.size());
				
				this.coList.add(cprop);
				maxId++;
				line = input.readLine();
			}
			input.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes the SLL into a file ("res/comprop.ser").
	 */
	private void writeToSer()
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SER_FILE));
			oos.writeObject(this.coList);
			oos.close();
			
			//System.out.println(coList.toString() + " " + coList.size());
			
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
			//this.coList = new SLL();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SER_FILE));
			coList = (SLL) ois.readObject();
			CommercialProperty lastNode = (CommercialProperty) coList.get(coList.size() -1);
			maxId = lastNode.getId();
			//maxId++;
			//System.out.println(coList.toString() + " " + coList.size());
			
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
	 * Method that releases resources allocated for a CommercialPropertyBroker.
	 * Sets the maxId attribute to 0L.;
	 */
	@Override
	public void closeBroker()
	{
		cob.writeToSer();
		cob = null;
		maxId = 0L;
	}

	/**
	 * Method to add a CommercialProperty to the SLL
	 * @param o - the CommercialProperty object that should be added to the SLL
	 */
	@Override
	public boolean persist(Object o)
	{
		CommercialProperty co = (CommercialProperty)o;
		if (co.getId() == 0L)
		{
			co.setId(++this.maxId);
			coList.add(co);
			return true;
		}
		else
		{
			//System.out.println(co);
			coList.set(co, (int) co.getId() -1);
			//System.out.println(coList.get((int) co.getId() -1));
			return true;
		}
	}

	/**
	 * Method that removes a CommercialProperty from the SLL
	 * @param o - the CommercialProperty object that should be removed from the SLL
	 */
	@Override
	public boolean remove(Object o)
	{
		CommercialProperty co = (CommercialProperty) o;
		int index = coList.indexOf(co);
		if (index > -1)
		{
			coList.removeAt(index);
			--maxId;
			return true;
		}
		return false;
	}

	/**
	 * Method that searches the SLL for matching items/criteria.
	 * @param o an object of type CommercialProperty that contains either a id, quadrant, or a price.
	 * @return coList2 an ArrayList that the GUI uses to display the found items
	 */
	@Override
	public List search(Object o) throws IOException
	{
		ArrayList<CommercialProperty> coList2 = new ArrayList<>();
		CommercialProperty co;
		CommercialProperty searchFor = (CommercialProperty) o;
		
		if (searchFor.getId() != 0)
		{
			//System.out.println(coList.size());
			for (int x = 0; x < coList.size(); ++x)
			{
				//System.out.println(x);
				co = (CommercialProperty) coList.get(x);
				if (co.getId() == searchFor.getId())
				{
					coList2.add(co);
				}
			}
		}
		if (searchFor.getQuadrant() != null)
		{
			//System.out.println(searchFor.getQuadrant());
			for (int x = 0; x< coList.size(); ++x)
			{
				co = (CommercialProperty) this.coList.get(x);
				
				if (co.getQuadrant().equals(searchFor.getQuadrant()))
				{
					//System.out.println(co.getQuadrant());
					coList2.add(co);
				}
			}
		}
		if (searchFor.getAskingPrice() > 0)
		{
			for (int x = 0; x< coList.size(); ++x)
			{
				co = (CommercialProperty) this.coList.get(x);
				if (co.getAskingPrice() >= searchFor.getAskingPrice())
				{
					coList2.add(co);
				}
			}
		}
		
		return coList2;
	}
	
}
