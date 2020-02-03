package sait.mls.persistence.clientale;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sait.mls.exceptions.clientale.InvalidClientTypeException;
import sait.mls.exceptions.clientale.InvalidPhoneNumberException;
import sait.mls.exceptions.clientale.InvalidPostalCodeException;
import sait.mls.persistence.Broker;
import sait.mls.problemdomain.clientale.Client;

/**
 *
 * Class description: A broker for Clients to be used by the MLS GUI using JDBC. 
 * @author Christian Garrovillo
 *
 */
public class ClientBroker implements Broker
{
	private static final String TEXT_FILE = "res/clients.txt";
	private static Connection conn;
	private static ClientBroker cb;
	private int maxId;
	
	private static final String USER = "CPRG250";
	private static final String PASSWORD = "password";
	private static final String INSERT_STMT =	"INSERT INTO Clients"
												+ "(cliID, "
												+ "fName, "
												+ "lName, "
												+ "addres, "
												+ "postalCode, "
												+ "phoneNumber, "
												+ "cType" +
												"values (?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE Clients SET "
												+ "fName = ?, "
												+ "lName = ?, "
												+ "address  = ?, "
												+ "postal = ?, "
												+ "phone = ?,"
												+ "type = ? "
												+ "WHERE cliID = ?";
	private static final String DELETE_STMT = "DELETE FROM Clients WHERE cliID = ?";
	private static final String SEL_ID_STMT = "SELECT * FROM Clients WHERE cliID = ?";
	private static final String SEL_LN_STMT = "SELECT * FROM Clients WHERE lName = ?";
	private static final String SEL_TP_STMT = "SELECT * FROM Clients WHERE cType = ?";
	
	/**
	 * 
	 * A method called when getBroker() is called. Starts an instance of ClientBroker.
	 * @throws IOException
	 */
	private ClientBroker() throws IOException
	{
	}
	
	/**
	 * Initializes the newly created ClientBroker;
	 * @return the broker
	 * @throws IOException
	 */
	public static ClientBroker getBroker() throws IOException
	{
		if (cb == null)
		{
			cb = new ClientBroker();
			cb.setConnection();
			//check if table exists
			//create table
			if (!cb.tableExists())
			{
				cb.createTable();
				cb.loadFromTxt();
			}
		}
		return cb;
	}
	
	/**
	 * Starts a connection with the JDBC driver for Oracle 12c Database
	 */
	private void setConnection()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SAIT", USER, PASSWORD);
			
			// MYSQL: ****** 
			//Class.forName("com.mysql.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost/javaa6?", "root", "46718682");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("Error connecting to database");
			System.out.println("SQL Exception: " + e.getMessage());
			System.out.println("SQL State: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
	
	/**
	 * Checks to see if a Clients table exists in the database
	 * @return boolean that is true for existance and false if it doesn't exist.
	 */
	private boolean tableExists()
	{
		boolean exists = false;
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			String selQuer = "SELECT * FROM Clients";
			stmt.executeQuery(selQuer);
			exists = true;
		} catch (SQLException e)
		{
		}
		try
		{
			if (stmt!=null)
			{
				stmt.close();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return exists;
	}
	
	/**
	 * Method that creates an empty Clients table with constraints
	 */
	private void createTable()
	{
		try
		{
			String create = "CREATE TABLE Clients" + 
							"(cliID NUMBER CONSTRAINT CLI_ID_NN NOT NULL," +
							"fName VARCHAR2(30) CLI_FNAME_NN NOT NULL," +
							"lName VARCHAR2(30) CLI_LNAME_NN NOT NULL," + 
							"address VARCHAR2(50) CLI_EM_NN NOT NULL," + 
							"postalCode CHAR(7) CLI_PC_NN NOT NULL," +
							"phoneNumber CHAR(12) CLI_PN_NN_NOT NULL," +
							"cType CHAR(1) CLI_TP_NN NOT NULL," + 
							"CONSTRAINT CLI_PK PRIMARY KEY (cliID))";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(create);
			stmt.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that loads the created Clients table with data from res/clients.txt
	 */
	private void loadFromTxt()
	{
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(TEXT_FILE));
			String line = input.readLine();
			
			while (line != null)
			{
				Client c = new Client(line);
				//insert
				line = input.readLine();
			}
			input.close();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that inserts (adds) a Client to the Clients table
	 * @param c the Client to be added to the database
	 * @return boolean a boolean that returns true if successful and false if not
	 */
	private boolean insert(Client c)
	{
		try
		{
			PreparedStatement insertStat = conn.prepareStatement(INSERT_STMT);
			
			insertStat.setInt(1, (int) c.getClientID());
			insertStat.setString(2, c.getFirstName());
			insertStat.setString(3, c.getLastName());
			insertStat.setString(4, c.getAddress());
			insertStat.setString(5, c.getPostalCode());
			insertStat.setString(6, c.getPhoneNumber());
			insertStat.setString(7, Character.toString(c.getClientType()));
			
			int rows = insertStat.executeUpdate();
			insertStat.close();
			maxId++;
			return rows == 1;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * A Method that updates (modifies) a Client that already exists in the Clients table based on their ID
	 * @param c the Client with the updated parameters to update in the database
	 * @return boolean a boolean that returns true if successful and false if not
	 */
	private boolean update(Client c)
	{
		try
		{
			PreparedStatement upStat = conn.prepareStatement(UPDATE_STMT);
			upStat.setString(1, c.getFirstName());
			upStat.setString(2, c.getLastName());
			upStat.setString(3, c.getAddress());
			upStat.setString(4, c.getPostalCode());
			upStat.setString(5,c.getPhoneNumber());
			upStat.setString(6, Character.toString(c.getClientType()));
			upStat.setInt(7, (int) c.getClientID());
			
			int rows = upStat.executeUpdate();
			return rows == 1;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	

	
	/**
	 * A method that closes the broker and closes the connection with the driver 
	 */
	@Override
	public void closeBroker()
	{
		try
		{
			conn.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * A method responsible for updating or inserting Clients to the Clients table.
	 * Determines if the Client passed into it is to be added or to be updated in the table.
	 */
	@Override
	public boolean persist(Object o)
	{
		Client c = (Client) o;
		
		if (c.getClientID() == 0L)
		{
			c.setClientID(++maxId);
			insert(c);
			return true;
		}
		else
		{
			update(c);
			return true;
		}
	}

	/**
	 * A method that removes a Client from the Clients table by using the DELETE statement.
	 */
	@Override
	public boolean remove(Object o)
	{
		Client c = (Client) o;
		try
		{
			PreparedStatement remStat = conn.prepareStatement(DELETE_STMT);
			remStat.setInt(1, (int) c.getClientID());
			int rows = remStat.executeUpdate();
			return rows == 1;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * A method responsible for handling the searches of Clients in the Clients table, based
	 * on the parameters of a Client object passed into it.
	 */
	@Override
	public List search(Object o) throws IOException
	{
		Client c = (Client) o;
		ArrayList<Client> cList = new ArrayList<Client>();
		
		if (c.getClientID() != 0L)
		{
			return selID(cList, (int) c.getClientID());
		}
		if (c.getLastName() != null)
		{
			return selLN(cList, c.getLastName());
		}
		if (c.getClientType() != '\u0000')
		{
			return selTP(cList,c.getClientType());
		}
		return cList;
	}
	
	/**
	 * Support method that uses an ID to search for a Client in the Clients table.
	 * @param cList2 the List to add found Clients into
	 * @param ID the ID to search for
	 * @return cList2 the List of Clients with the matching ID
	 */
	private ArrayList<Client> selID(ArrayList<Client> cList2, int ID)
	{
		try
		{
			PreparedStatement selStmt = conn.prepareStatement(SEL_ID_STMT);
			selStmt.setInt(1, ID);
			
			ResultSet result = selStmt.executeQuery();
			
			while (result.next())
			{
				Client c = new Client(
						result.getInt(1),
						result.getString(2),
						result.getString(3),
						result.getString(4),
						result.getString(5),
						result.getString(6),
						result.getString(7).charAt(0)
						);
				cList2.add(c);
			}
			result.close();
			selStmt.close();
		} catch (SQLException e)
		{
			
			e.printStackTrace();
		} catch (InvalidPostalCodeException e)
		{
			
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e)
		{
			
			e.printStackTrace();
		} catch (InvalidClientTypeException e)
		{
			
			e.printStackTrace();
		}
		return cList2;
	}
	
	/**
	 * Support method that uses a lastName to search for a Client in the Clients table.
	 * @param cList2 the List to add found Clients into
	 * @param last the lastName to search for
	 * @return cList2 the List of Clients with the matching lastName
	 */
	private ArrayList<Client> selLN(ArrayList<Client> cList2, String last)
	{
		try
		{
			PreparedStatement selStmt = conn.prepareStatement(SEL_LN_STMT);
			selStmt.setString(1, last);
			
			ResultSet result = selStmt.executeQuery();
			
			while (result.next())
			{
				Client c = new Client(
						result.getInt(1),
						result.getString(2),
						result.getString(3),
						result.getString(4),
						result.getString(5),
						result.getString(6),
						result.getString(7).charAt(0)
						);
				cList2.add(c);
			}
			result.close();
			selStmt.close();
			
		} catch (SQLException e)
		{
			
			e.printStackTrace();
		} catch (InvalidPostalCodeException e)
		{
			
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e)
		{
			
			e.printStackTrace();
		} catch (InvalidClientTypeException e)
		{
			
			e.printStackTrace();
		}
		return cList2;
	}
	
	/**
	 * Support method that uses a client type to search for a Client in the Clients table.
	 * @param cList2 the List to add found Clients into
	 * @param type the type to search for
	 * @return cList2 the List of Clients with the matching type
	 */
	private ArrayList<Client> selTP(ArrayList<Client> cList2,char type)
	{
		try
		{
			PreparedStatement selStmt = conn.prepareStatement(SEL_TP_STMT);
			selStmt.setString(1, Character.toString(type));
			
			ResultSet result = selStmt.executeQuery();
			char searType = Character.toUpperCase(type);
			if (searType != 'R')
			{
				throw new InvalidClientTypeException();
			}
			if (searType != 'C')
			{
				throw new InvalidClientTypeException();
			}
			
			while (result.next())
			{
				Client c = new Client(
						result.getInt(1),
						result.getString(2),
						result.getString(3),
						result.getString(4),
						result.getString(5),
						result.getString(6),
						result.getString(7).charAt(0)
						);
				cList2.add(c);
			}
			result.close();
			selStmt.close();
			
		} catch (SQLException e)
		{
			
			e.printStackTrace();
		} catch (InvalidPostalCodeException e)
		{
			
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e)
		{
			
			e.printStackTrace();
		} catch (InvalidClientTypeException e)
		{
			
			e.printStackTrace();
		}
		return cList2;
	}
}
