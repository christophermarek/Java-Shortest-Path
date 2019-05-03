
/**
 * 
 * @author Christopher Marek
 * Student Number: 251034808
 * Course: Comp Sci 1027b
 * Assignment 3
 * 
 *  This class creates a doubly linked list
 *  with the methods required in project doc
 * 
 */

public class DLList<T> implements DLListADT<T> {
	
	private DLNode<T> front;
	private DLNode<T> rear;
	private int count;
	
	/**
	 * Constructor
	 * Initializes empty list
	 */
	public DLList() {
		count = 0;
		front = null;
		rear = null;
	}
	
	/**
	 * Inserts a data item at the rear of the 
	 * Doubly linked list
	 */
	public void insert(T dataItem, int value) {
		DLNode<T> node = new DLNode<T>(dataItem, value);
		
		//check if empty
		if(rear == null) {
			rear = node;
			front = node;
			count++;
			return;
		}
		
		//check if only 1 element in list
		if(front.equals(rear)) {
			front.setNext(node);
			node.setPrevious(front);
			node.setNext(null);
			rear = node;
			count++;
			return;
		}
		
		rear.setNext(node);
		node.setPrevious(rear);
		node.setNext(null);
		rear = node;
		
		count++;
	}
	
	/**
	 * Returns the dataitem in the list with
	 * the smallest integer value associated
	 * 
	 * Also pops the smallest item from the list 
	 * 
	 * Throws EmptyListException if the list is empty
	 */
	
	public T getSmallest() throws EmptyListException {
		if(isEmpty()) {
			throw new EmptyListException("Cant get smallest. List is empty");
		}
		
		int smallest = front.getValue();
		T smallestData = null;
		
		//get smallest
		DLNode<T> check = front;
		DLNode<T> smallestNode = null; 

		for(int i = 0; i < count; i++){
			if(check.getValue() <= smallest) {
				smallest = check.getValue();
				smallestData = check.getData();
				smallestNode = check;
			}
			check = check.getNext();
		}
		
		DLNode<T> prev = smallestNode.getPrevious(); 
		DLNode<T> next = smallestNode.getNext(); 

		//if only 1 node in list
		if(next == null && prev == null) {
			smallestNode = null;
			front = null;
			rear = null;
			count--;
			return smallestData;
		}
		
		//if node is at the front
		if(prev == null) {
			next.setPrevious(null);
			front = next;
			smallestNode = null;
			count--;
			return smallestData;
		}
		//if node is at rear
		if(next == null) {
			prev.setNext(null);
			smallestNode = null;
			rear = prev;
			count--;
			return smallestData;
		}
		
		prev.setNext(next);
		next.setPrevious(prev);
		smallestNode = null;
		count --;
		
		
		return smallestData;
	}

	/**
	 * Method find and 
	 * changes a node's datavalue to the one passed in the parameters
	 * 
	 * Throws InvalidDataItemException if list is empty or no item found
	 */
	
	public void changeValue(T dataItem, int newValue) throws InvalidDataItemException {
		DLNode<T> check = front;
		
		if(this.isEmpty()) {
			throw new InvalidDataItemException("No data item found in list that matches");
		}
		
		//check if theres only 1 element in the list
		if(this.count == 1) {
			if(check.getData().equals(dataItem)) {
				check.setValue(newValue);
				return;
			}else {
				throw new InvalidDataItemException("No data item found in list that matches");
			}
		}
		
		while(check != null) {
			if(check.getData().equals(dataItem)) {
				check.setValue(newValue);
				return;
			}
			check = check.getNext();
		}
		
		throw new InvalidDataItemException("No data item found in list that matches");
	}

	/**
	 * Method finds the node that equals the data of the parameter
	 * and returns the integer value of the node
	 * 
	 * Throws InvalidDataItemException if there is no node with the associated data
	 */
	
	public int getDataValue(T dataItem) throws InvalidDataItemException {
		DLNode<T> check = front;
		
		//check if only 1 element
		if(this.count == 1) {
			return check.getValue();
		}
		
		while(check.getNext() != null) {
			if(check.getData().equals(dataItem)) {
				return check.getValue();
			}
		}
		
		throw new InvalidDataItemException("No data item found in list that matches");
		
	}
	
	/**
	 * Method prints out an integer representation of the
	 * doubly linked list
	 */
	
	public String toString() {
		
		DLNode<T> check = front;
		String str = "List: ";
		
		//loop through starting at front to get the values and data
		while(check.getNext() != null) {
			str = str + check.getData() + "," + check.getValue() + ";";
			check = check.getNext();
		}
		
		return str;
		
	}
	
	/**
	 * Method returns if the list is empty or not
	 */
	
	public boolean isEmpty() {
		return(count < 1);
	}

	/**
	 * Method returns the count of the array
	 */
	
	public int size() {
		return count;
	}

}
