package utility;

import java.io.Serializable;

/**
 * Class description: Defines a Singly Linked List Node that holds the node's element and definition for the next node.
 * @author Christian Garrovillo
 *
 */
public class SLLNode implements Serializable
{
	private static final long serialVersionUID = 556722847124104922L;
	protected Object element;
	protected SLLNode next;
	
	/*
	 * Defines an SLLNode.
	 * The argument passed is an element, and the SLLNode's element is set to this element.
	 */
	public SLLNode(Object element)
	{
		this.element = element;
	}
}
