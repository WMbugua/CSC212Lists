package edu.smith.cs.csc212.adtr.real;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.errors.BadIndexError;
import edu.smith.cs.csc212.adtr.errors.TODOErr;
//.import edu.smith.cs.csc212.adtr.real.SinglyLinkedList.Node;
//import edu.smith.cs.csc212.adtr.real.SinglyLinkedList.Node;


public class DoublyLinkedList<T> extends ListADT<T> {
	private Node<T> start;
	private Node<T> end;
	
	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}
	

	@Override
	public T removeFront() {
		checkNotEmpty();
		T initialStartValue = this.start.value;
		this.start = this.start.after;
		if (this.start == null) {
			this.end = null;
		} else {
			this.start.before = null;
		}
		return initialStartValue;
		//throw new TODOErr();
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		T LValue = this.end.value;
		this.end = this.end.before;
		if (this.end==null) {
			this.start = null;
		}
		else {
			end.after = null;
		}
		return LValue;
		//throw new TODOErr();
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		int count = 0;
		if (index==0) {
			return this.removeFront();
		}
		else if (index == this.size()-1) {
			Node<T> currentEnd = this.end;
			this.end = this.end.before;
			this.end.after = null;
			return currentEnd.value;
		}
		else {
			for (Node<T> current = this.start; current!=null; current = current.after) {
				if (count==index) {
					Node<T> previousNode = current.before;
					Node<T> currentAfter= current.after;
					previousNode.after = currentAfter;
					currentAfter.before = previousNode;
					return current.value;
				}
			count++;
			}
		}
		throw new BadIndexError(index);

		//throw new TODOErr();
	}

	@Override
	public void addFront(T item) {
		Node<T> initialStart = this.start;
		this.start = new Node<T> (item);
		start.after = initialStart;
		start.before = null;
		if (initialStart==null) {
			this.end = this.start;
		}
		else {
			initialStart.before = start;
		}
		//throw new TODOErr();
	}

	@Override
	public void addBack(T item) {
		if (end == null) {
			start = end = new Node<T>(item);
		} else {
			Node<T> secondLast = end;
			end = new Node<T>(item);
			end.before = secondLast;
			secondLast.after = end;
		}
	}

	@Override
	public void addIndex(int index, T item) {
		int count = 0;
		if (index==0) {
			addFront(item);
			return;
		}
		else {
			for (Node<T> current = this.start; current!=null; current = current.after) {
				if (count+1==index) {
					Node<T> oldAfter = current.after;
					Node<T> newAfter = new Node<T>(item);
					current.after = newAfter;
					newAfter.after = oldAfter;
					newAfter.before = current;
					if (oldAfter==null) {
						this.end = newAfter;
					}
					else {
						oldAfter.before = newAfter;
					}
					return;
				}
			count++;
			}
		}
		throw new BadIndexError(index);

		//throw new TODOErr();
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		return this.start.value;
		//throw new TODOErr();
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		return this.end.value;
		//throw new TODOErr();
	}
	
	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at++ == index) {
				return n.value;
			}
		}
		throw new BadIndexError(index);
		//throw new TODOErr();
	}
	
	public void setIndex(int index, T value) {
		checkNotEmpty();
		int count = 0;
		for (Node<T> current = this.start; current!=null; current = current.after) {
			if (count==index) {
				current.value = value;
				return;
			}
			count++;
		}
		
		throw new BadIndexError(index);
		//throw new TODOErr();
	}

	@Override
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			count++;
		}
		return count;
		//throw new TODOErr();
	}

	@Override
	public boolean isEmpty() {
		return this.start==null && this.end==null;
		//throw new TODOErr();
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
}
