
/**
 * List.java
 * @author Aurielle Jocom
 * @author Donggyu Yu
 * CIS 22C, Lab 6
 */
import java.util.NoSuchElementException;

public class List<T> {
	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	private int length;
	private Node first;
	private Node last;
	private Node iterator;

	/**** CONSTRUCTOR ****/

	/**
	 * Instantiates a new List with default values
	 * 
	 * @postcondition assign default values to length, first, last, and Iterator
	 */
	public List() {
		length = 0;
		first = null;
		last = null;
		iterator = null;
	}

	/**** COPY CONSTRUCTOR ****/

	/**
	 * Instantiates a new List by copying another List
	 * 
	 * @param original the List to make a copy of
	 * @postcondition a new List object, which is an identical but separate copy of
	 *                the List original
	 */
	public List(List<T> original) {
		if (original == null) {
			return;
		}

		if (original.length == 0) {
			length = 0;
			first = null;
			last = null;
			iterator = null;
		}

		else {
			Node temp = original.first;

			while (temp != null) {
				addLast(temp.data);
				temp = temp.next;
			}

			iterator = null;
		}
	}

	/**** MUTATORS ****/

	/**
	 * removes the element at the front of the list
	 * 
	 * @precondition the list cannot be empty
	 * @postcondition remove the first element of the list
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeFirst: " + "The list is empty. No first element to remove.");
		}

		else if (length == 1) {
			first = last = null;
		}

		else {
			if (iterator == first) {
				iterator = null;
			}

			first = first.next;
			first.prev = null;
		}

		length--;
	}

	/**
	 * removes the element at the end of the list
	 * 
	 * @precondition the list cannot be empty
	 * @postcondition removes the last element from the list
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast: " + "The list is empty. No last element to remove.");
		}

		else if (length == 1) {
			first = last = null;
		}

		else {
			if (iterator == last) {
				iterator = null;
			}

			last = last.prev;
			last.next = null;
		}

		length--;
	}

	/**
	 * Creates a new first element
	 * 
	 * @param data the data to insert at the front of the list
	 * @postcondition a new first element
	 */
	public void addFirst(T data) {
		if (length == 0) {
			first = last = new Node(data);
		}

		else {
			Node N = new Node(data);
			N.next = first;
			first.prev = N;
			first = N;
		}

		length++;
	}

	/**
	 * Creates a new last element
	 * 
	 * @param data the data to insert at the end of the list
	 * @postcondition add a new node to the end of the list
	 */
	public void addLast(T data) {
		if (length == 0) {
			first = last = new Node(data);
		}

		else {
			Node N = new Node(data);
			last.next = N;
			N.prev = last;
			last = N;
		}

		length++;
	}

	/**
	 * Places an iterator
	 * 
	 * @postcondition Places an iterator to the start of the list
	 */
	public void placeIterator() {
		iterator = first;
	}

	/**
	 * Removes the iterator
	 * 
	 * @precondition the iterator cannot be null
	 * @postcondition Iterator points to null
	 * @throws NullPointerException when precondition is violated
	 */
	public void removeIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("removeIterator: " + "Iterator is null and cannot remove node.");
		}

		else if (iterator == first) {
			removeFirst();
		}

		else if (iterator == last) {
			removeLast();
		}

		else {
			iterator.prev.next = iterator.next;
			iterator.next.prev = iterator.prev;

			length--;
		}

		iterator = null;
	}

	/**
	 * Adds an element after the node pointed by the iterator
	 * 
	 * @precondition the iterator cannot be null
	 * @postcondition insert a new element after the node pointed by the iterator
	 * @throws NullPointerException when precondition is violated
	 */
	public void addIterator(T data) throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("addIterator: " + "Iterator is null and cannot add new node.");
		}

		else if (iterator == last) {
			addLast(data);
		}

		else {
			Node N = new Node(data);
			iterator.next.prev = N;
			N.next = iterator.next;
			iterator.next = N;
			N.prev = iterator;

			length++;
		}
	}

	/**
	 * Moves the iterator up by 1 node
	 * 
	 * @precondition the iterator cannot be null
	 * @postcondition iterator pointing at the next node
	 * @throws NullPointerException when precondition is violated
	 */
	public void advanceIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("advanceIterator: " + "Iterator is null and cannot advance");
		}

		iterator = iterator.next;
	}

	/**
	 * Moves the iterator down by 1 node
	 * 
	 * @precondition the iterator cannot be null
	 * @postcondition iterator pointing at the previous node
	 * @throws NullPointerException when precondition is violated
	 */
	public void reverseIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("reverseIterator: " + "Iterator is null and cannot reverse.");
		}

		iterator = iterator.prev;
	}

	/**
	 * Advances the iterator exactly n times
	 * 
	 * @param n the number of times to advance the iterator
	 * @precondition iterator != null
	 * @precondition (n + indexIterator) <= length
	 * @throws NullPointerException      when the iterator is offEnd
	 * @throws IndexOutOfBoundsException when (iterator position + n) > length
	 */
	public void advanceNTimes(int n) throws NullPointerException, IndexOutOfBoundsException {
		if (offEnd()) {
			throw new NullPointerException("advanceNTimes: " + "iterator is offEnd");
		}

		if (n > length) {
			throw new IndexOutOfBoundsException("advanceNTimes: " + "n is greater than length. Index is out of bound.");
		}

		for (int i = 0; i < n - 1; i++) {
			advanceIterator();
		}
	}

	/**** ACCESSORS ****/

	/**
	 * Returns the value stored in the first node
	 * 
	 * @precondition the list cannot be empty
	 * @return the value stored at node first
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirst: " + "The list is empty. No first element.");
		}

		return first.data;
	}

	/**
	 * Returns the value stored in the last node
	 * 
	 * @precondition the list cannot be empty
	 * @return the value stored in the node last
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLast: " + "The list is empty. No last element.");
		}

		return last.data;
	}

	/**
	 * Returns whether the list is currently empty
	 * 
	 * @return whether the list is empty
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	/**
	 * Returns the current length of the list
	 * 
	 * @return the length of the list from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns the element currently pointed at by the iterator
	 * 
	 * @precondition the iterator cannot be null
	 * @return the value stored in the node currently pointed at by the iterator
	 * @throws NullPointerException when precondition is violated
	 */
	public T getIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("getIterator: " + "Iterator is off the end of the list.");
		}

		return iterator.data;
	}

	/**
	 * Returns whether the iterator is off the end of the list
	 * 
	 * @return whether the iterator is null
	 */
	public boolean offEnd() {
		return iterator == null;
	}

	/***
	 * Determines whether two Lists have the same data in the same order
	 * 
	 * @param L the List to compare to this List
	 * @return whether the two Lists are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		else if (!(o instanceof List)) {
			return false;
		}

		else {
			List<T> L = (List<T>) o;

			if (this.length != L.length) {
				return false;
			}

			else {
				Node temp1 = this.first;
				Node temp2 = L.first;

				while (temp1 != null) { // Lists are same length
					if (!(temp1.data.equals(temp2.data))) {
						return false;
					}

					temp1 = temp1.next;
					temp2 = temp2.next;
				}

				return true;
			}
		}
	}

	/**
	 * Searches the List for a specific element
	 * 
	 * @param data the element to search for
	 * @return the location of the element in the List from 1 to length or -1 if not
	 *         found
	 * @postcondition the position of the iterator remains unchanged
	 */
	public int linearSearch(T data) {
		placeIterator();

		for (int i = 1; i <= this.length; i++) {
			if (data.equals(this.getIterator())) {
				return i;
			}

			advanceIterator();
		}

		return -1;
	}

	/**** ADDITIONAL OPERATIONS ****/

	/**
	 * List with each value on its own line At the end of the List a new line
	 * 
	 * @return the List as a String for display
	 */
	@Override
	public String toString() {
		String result = "";
		Node temp = first;

		while (temp != null) {
			result += temp.data + " ";
			temp = temp.next;
		}

		return result;
	}

	/**
	 * Prints the numbered List
	 */
	public void printNumberedList() {
		Node temp = first;
		int i = 1;

		while (temp != null) {
			System.out.printf(i + ". " + temp.data + "\n");
			temp = temp.next;
			i++;
		}
	}
}
