package utility;

import java.io.Serializable;

/**
 * Class description: Represents a serialized Singly Linked List containing SLLNodes
 * @author Christian Garrovillo
 *
 */
public class SLL implements List, Serializable
{
	private static final long serialVersionUID = -724162139517834265L;
	//attributes
	private SLLNode head;
	private int size;
	
	
	/**
	 * Method for adding SLLNodes to the end of an SLL
	 * @param data the data to store in an SLLNode to append in a SLL
	 */
	@Override
	public void append(Object data)
	{
		SLLNode newNode = new SLLNode(data);
		
		if (isEmpty())
		{
			head = newNode;
			
		}
		else
		{
			SLLNode lastNode = getLastNode();
			lastNode.next = newNode;
			
		}
		size++;
	}

	/**
	 * Helper method that returns the last SLLNode in a SLL
	 * @return the last SLLNode
	 */
	private SLLNode getLastNode() //helper method
	{
		if (isEmpty())
		{
			return null;
		}
		SLLNode lastNode = head; //start at the head of the LinkedList
		
		//while theres a node after the current node (which is head right now)
		while(lastNode.next != null)
		{
			lastNode = lastNode.next; //set the last node as lastnode's next
			//until it reaches the last node (when next node is null)
		}
		return lastNode;
	}
	
	/**
	 * Same as append(). Adds an SLLNode to the end of the list. Uses append().
	 * @param data the data to store in an SLLNode to append in a SLL 
	 */
	@Override
	public void add(Object data)
	{
		append(data);
		
	}

	//can also insert (insert) between nodes, set modifies a node and this add
	//can insert it between
	/**
	 * Adds an SLLNode to the List based on the passed index
	 * @param data the data to store in an SLLNode to be added in a SLL
	 * @param index an integer representing where in the SLL the data will be added
	 */
	@Override
	public void add(Object data, int index) throws IndexOutOfBoundsException
	{
		//if the index is negative or greater than size
			//throw exception
		
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		//if index == size 
			//append
		if (index == size)
		{
			append(data);
		}
		
		//if index == 0
			//prepend
		if (index == 0)
		{
			prepend(data);
		}
		else
		{
			SLLNode nodeToInsert = new SLLNode(data);
			//System.out.println("node to insert: " + nodeToInsert.element);
			//System.out.println("node to insert's next: " + nodeToInsert.next);
			
			SLLNode previousNode = getNodeAt(index - 1);
			//System.out.println("previous node: " + previousNode.element);
			//System.out.println("previous node's next: " + previousNode.next.element);
			
			nodeToInsert.next = previousNode.next;
			//System.out.println("node to insert's next: " + nodeToInsert.next.element);
			previousNode.next = nodeToInsert;
			//System.out.println("previous node's next: " + previousNode.next.element);
			size++;
			
		}
		
		
		
	}

	/**
	 * Adds an SLLNode to the beginning of the SLL, after the head.
	 * @param data the data to store in an SLLNode to prepend in a SLL
	 */
	@Override
	public void prepend(Object data)
	{
		
		if (isEmpty())
		{
			append(data);
		}
		else
		{
			SLLNode newNode = new SLLNode(data);
			newNode.next = head;
			head = newNode;
			size++;
		}
	}

	/**
	 * Modifies an SLLNode in a SLL based on the index
	 * @param datatoset the new data to store in an SLLNode to replace a current SLLNode in a SLL
	 * @param index an integer representing which SLLNode in the SLL will be changed
	 */
	@Override
	public void set(Object datatoset, int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		SLLNode newNode = new SLLNode(datatoset);
		SLLNode node = getNodeAt(index);
		node.element = newNode.element;
	}

	/**
	 * Returns the size of the SLL
	 * @param size size of the SLL
	 */
	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Returns an object at a specified index in a SLL
	 * @param index an integer representing which object in the SLL will be returned
	 */
	@Override
	public Object get(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		return getNodeAt(index).element;
	}
	
	//helper
	/**
	 * Helper method that returns the SLLNode at a specified index in a SLL
	 * @param index an integer representing which SLLNode in the SLL will be returned
	 * @return node the SLLNode that gets returned
	 * @throws IndexOutOfBoundsException 
	 */
	private SLLNode getNodeAt(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		SLLNode node = head;
		int counter = 0;
		while (counter!=index)
		{
			node = node.next;
			counter++;
		}
		return node;
	}
	

	//3 JUnit classes 1 empty
	//2. not empty and has data
	//3. not empty and doesnt contain data
	/**
	 * Returns the index of an object in a SLL
	 * @param data the object that must be returned at first sign of the object
	 * @return an integer representing the index of the found object. Returns -1 if not found.
	 */
	@Override
	public int indexOf(Object data)
	{
		//if not found return -1
		//apple pineapple apple
			//returns first occurence of apple
		//n = n.next
		//check is n != null
		//is n.element = element
		//return index
		//if nodes element is == data
		int index = -1;
		SLLNode node = head;
		while ( node != null)
		{
			index++;
			if (node.element.equals(data))
			{
				return index;
			}
			node = node.next;
		}
		return -1;
	}

	/**
	 * Checks if a passed object is in the SLL.
	 * @return boolean returns true if found and vice versa
	 */
	@Override
	public boolean contains(Object data)
	{
		int index = -1;
		SLLNode node = head;
		while ( node != null)
		{
			index++;
			if (node.element.equals(data))
			{
				return true;
			}
			node = node.next;
		}
		return false;
	}

	/**
	 * Checks if the SLL is empty
	 * @return boolean returns true of the SLL is empty and vice versa
	 */
	@Override
	public boolean isEmpty()
	{
		//if head is null (which means that theres nothing after it; no list
			//return true
		//else return false
		return head == null;
	}

	/**
	 * Empties the SLL and sets the size attribute to 0
	 */
	@Override
	public void clear()
	{
		head = null;
		size = 0;
	}

	////// run by maryam
	/**
	 * Removes an SLLNode from a SLL based on the passed index
	 * @param index the integer specifying where in the SLL a SLLNode should be deleted
	 */
	@Override
	public void removeAt(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		if (size == 1)
		{
			clear();
		}
		else
		{
			if (index == 0)
			{
				head = head.next;
			}
			else
			{
				SLLNode prevNode = getNodeAt(index - 1); //should it be =SLLNode(index-1)?
				SLLNode afterPrevNode = prevNode.next;
				prevNode.next = afterPrevNode.next;
				size--;
			}
		}
	}

}
