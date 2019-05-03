/**
 * 
 * @author Christopher Marek
 * Student Number: 251034808
 * Course: Comp Sci 1027b
 * Assignment 3
 * 
 *  This class creats a Node object
 *  for our double linked listqz
 * 
 */

public class DLNode <T>{

	private T dataItem;
	private DLNode<T> next;
	private DLNode<T> previous;
	private int value;
	
	
	public DLNode(T data, int value) {
		next = null;
		previous = null;
		this.value = value;
		dataItem = data;
	}
	
	public int getValue(){
		return value;
	}
	
	public T getData() {
		return this.dataItem;
	}
	
	public DLNode<T> getNext(){
		return next;
	}
	
	public DLNode<T> getPrevious(){
		return previous;
	}
	
	public void setData(T data) {
		dataItem = data;
	}
	
	public void setNext(DLNode<T> node) {
		next = node;
	}
	
	public void setPrevious(DLNode<T> node) {
		previous = node;
	}
	
	public void setValue(int val) {
		value = val;
	}
}
