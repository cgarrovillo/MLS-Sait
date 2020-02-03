package utility;

/**
 * 
 * Class description: A high level representation of managing a list of elements.
 *
 * @author Nick Hamnett
 *
 */
public interface List {

	/**
	 * Adds to the end of the list.
	 * @param data Data to append.
	 */
	void append(Object data);

	/**
	 * Adds a new element to the list.
	 * @param data Data to store inside element.
	 */
	void add(Object data);

	/**
	 * Adds a new element at a specific position.
	 * @param data Data that element is to contain.
	 * @param index Index to add new element at.
	 * @throws IndexOutOfBoundsException Thrown if index is negative or past the size of the list.
	 */
	void add(Object data, int index) throws IndexOutOfBoundsException;

	/**
	 * Prepends (adds to beginning) data to the list.
	 * @param data Data to store inside element.
	 */
	void prepend(Object data);

	/**
	 * Replaces the data  at index.
	 * @param data Data to replace.
	 * @param index Index of element to replace.
	 * @throws IndexOutOfBoundsException Thrown if index is negative or larger than size - 1 of list.
	 */
	void set(Object data, int index) throws IndexOutOfBoundsException;

	/**
	 * Gets the number of elements in the list.
	 * @return Size of list (0 meaning empty)
	 */
	int size();

	/**
	 * Gets the data at the specified index.
	 * @param index Index of element to get.
	 * @return Data in element or null if it was not found.
	 * @throws IndexOutOfBoundsException Thrown if index is negative or larger than size - 1 of the list.
	 */
	Object get(int index) throws IndexOutOfBoundsException;

	/**
	 * Gets the first index of element containing data.
	 * @param data Data object to find the first index of.
	 * @return First of index of element with matching data or -1 if not found.
	 */
	int indexOf(Object data);

	/**
	 * Go through elements and check if we have one with data.
	 * @param data Data object to search for.
	 * @return True if element exists with value.
	 */
	boolean contains(Object data);

	/**
	 * Checks if the list is empty.
	 * @return True if it is empty.
	 */
	boolean isEmpty();

	/**
	 * Clears the list.
	 */
	void clear();

	/**
	 * Removes element at index from list, reducing the size.
	 * @param index Index of element to remove.
	 * @throws IndexOutOfBoundsException Thrown if index is negative or past the size - 1.
	 */
	void removeAt(int index) throws IndexOutOfBoundsException;

}