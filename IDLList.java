import java.util.ArrayList;

public class IDLList<E> {
	//Data Fields
	/** The First Node in the IDLL */
	private Node<E> head;
	/** The Last Node in the IDLL */
	private Node<E> tail;
	/** The Size of the IDLL */
	private int size;
	/** The Array that will store the Nodes with an index */
	private ArrayList<Node<E>> indicies = new ArrayList<Node<E>>();
	
	//The class that will represents the nodes themselves
	private class Node<E> extends IDLList<Object> {
		//Data Fields
		/** The Data that will be contained in the Node */
		E data;
		/** The Node that is After the Current Node */
		Node<E> next;
		/** The Node that is Before the Current Node */
		Node<E> prev;
		
		//Constructors
		/**
		 * Constructs a Node with the data being equal to elem
		 * @param elem The data that the Node will hold
		 */
		public Node(E elem) {
			Node<E> newNode = new Node<E>(elem);
			newNode.data = elem;
		}
		
		/**
		 * Constructs a Node with the data being equal to elem
		 * and the next Node being equal to next and the 
		 * previous Node being equal to prev
		 * @param elem The Data that the Node will hold
		 * @param prev The previous Node
		 * @param next The next Node
		 */
		public Node(E elem, Node<E> prev, Node<E> next) {
			Node<E> newNode = new Node<E>(elem);
			newNode.data = elem;
			newNode.next = next;
			newNode.prev = prev;
		}
	}
	
	// Constructors
	/**
	 * Constructs an IDLL that has two Nodes that contain nothing 
	 * one being the Head and one being the Tail
	 */
	public void IDDList() {
		head = new Node<E>(null);
		tail = new Node<E>(null);
		size = 0;
		head.next = tail;
		tail.prev = head;
		indicies.add(head);
		indicies.add(tail);
	}
	
	//Operations
	/**
	 * Adds a new Node at the given index containing the given elem
	 * @param index The index at which the new Node will be added
	 * @param elem What the new Node will contain
	 * @return True if it was added successfully, false if it was not
	 */
	public boolean add(int index, E elem) {
		if (index < size){
			Node<E> newNode = new Node<E>(elem,indicies.get(index),indicies.get(index+1));
			indicies.get(index).next = newNode;
			indicies.get(index).next.prev = newNode;
			indicies.add(index,newNode);
			size++;
			return true;
		}
		else if(index == 0) {
			add(elem);
			return true;
		}
		else if(index == size-1){
			append(elem);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Adds a new Node to the beginning of the IDLL
	 * @param elem What the Node will contain
	 * @return True if it was added successfully, false if it was not
	 */
	public boolean add(E elem) {
		Node<E> newNode = new Node<E>(elem, null, head);
		indicies.add(0,newNode);
		head.prev = newNode;
		head = newNode;
		size++;
		return true;
	}
	
	/**
	 * Adds a new Node to the end of the IDLL
	 * @param elem What the Node will contain
	 * @return True if it was added successfully, false if it was not
	 */
	public boolean append(E elem) {
		Node<E> newNode = new Node<E>(elem, tail, null);
		indicies.add(newNode);
		tail.next = newNode;
		tail = newNode;
		size++;
		return true;
		}
	
	/**
	 * Gets the data from the Node at the given index
	 * @param index The position in the IDLL that the data is desired
	 * @return The data held in the Node at the given index
	 */
	public E get(int index) {
		return indicies.get(index).data;
	}
	
	/**
	 * Gets the data from the first Node in the IDLL
	 * @return The data held in the Node at the beginning of the IDLL
	 */
	public E getHead() {
		return head.data;
	}
	
	/**
	 * Gets the data from the last Node in the IDLL
	 * @return The data held in the Node at the end of the IDLL
	 */
	public E getTail() {
		return tail.data;
	}
	
	/**
	 * Returns the size of the IDLL
	 * @return size of the IDLL
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes the first Node of the IDLL
	 * @return The data in the Node that was removed
	 */
	public E remove() {
		E result = head.data;
		indicies.get(1).prev = null;
		indicies.remove(0);
		head = indicies.get(0);
		size--;
		return result;
	}
	
	/**
	 * Removes the last Node of the IDLL
	 * @return The data in the Node that was removed
	 */
	public E removeLast(){
		E result = tail.data;
		indicies.get(size-2).next = null;
		indicies.remove(size-1);
		tail = indicies.get(size-1);
		size--;
		return result;
	}
	
	/**
	 * Removes the Node at the given index
	 * @param index The place where the desired Node is in the IDLL
	 * @return The data in the Node that was removed
	 */
	public E remove(int index) {
		E result = indicies.get(index).data;
		if (index < size){
			indicies.get(index-1).next = indicies.get(index+1);
			indicies.get(index+1).prev = indicies.get(index-1);
			size--;
		}
		else if (index == 0) {
			remove();
		}
		else if (index == size-1){
			removeLast();
		}
		else{
			return null;
		}
		return result;
	}
	
	/**
	 * Removes the first Node that contains the given elem
	 * @param elem What the Node will contain if it is removed
	 * @return True if a node was removed, False if nothing was removed
	 */
	public boolean remove(E elem) {
		for (int i=0; i<size;i++){
			if (indicies.get(i).data == elem){
				remove(i);
			}
			else{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns a string representation of the IDLL
	 */
	public String toString(){
		String result = new String();
		for (int i=0; i<size;i++){
			result+=indicies.get(i).data;
		}
		return result;
	}
	
	public static void main(String[] args) {
	}
}
