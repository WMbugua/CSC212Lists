package edu.smith.cs.csc212.adtr.real;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.errors.BadIndexError;
import edu.smith.cs.csc212.adtr.errors.TODOErr;

public class SinglyLinkedList<T> extends ListADT<T> {
	/**
	 * The start of this list.
	 * Node is defined at the bottom of this file.
	 */
	Node<T> start;
	
	@Override
	public T removeFront() {
		checkNotEmpty();
		T tValue = this.start.value;
		this.start = this.start.next;
		return tValue;
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		Node<T> lastInlist = null;
		for (Node<T> current = this.start; current != null; current= current.next) {
			if (this.start.next==null) {
				lastInlist = this.start;
				this.start = null;
			}
			else if (current.next.next==null) {
				lastInlist = current.next;
				current.next = null;
			}
		}
		return lastInlist.value;
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		int count = 0;
		Node<T> tIndex = null;
		if (index==0) {
			return this.removeFront();
		}
		for (Node<T> current = this.start; current!=null; current = current.next) {
			if (count+1==index) {
				tIndex = current.next;
				current.next = current.next.next;
			}
			count++;
		}
		return tIndex.value;
	}

	@Override
	public void addFront(T item) {
		this.start = new Node<T>(item, start);
	}

	@Override
	public void addBack(T item) {
		Node<T> lastInlist = null;
		
		if (this.start == null) {
			this.start = new Node<T>(item, start);
		}
		else {
			for (Node<T> current = this.start; current!=null; current = current.next) {
			lastInlist = current;
			}
			lastInlist.next = new Node<T> (item, null);
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
			for (Node<T> current = this.start; current!=null; current = current.next) {
				if (count+1==index) {
					current.next = new Node<T>(item, current.next);
					return;
				}
			count++;
			}
		}
		throw new BadIndexError(index);
	}
	
	
	
	@Override
	public T getFront() {
		checkNotEmpty();
		return this.start.value;
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		Node<T> lastInlist = null;
		for (Node<T> current = this.start; current!=null; current = current.next) {
			lastInlist = current;
		}
		return lastInlist.value;
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			if (at++ == index) {
				return n.value;
			}
		}
		throw new BadIndexError(index);
	}
	

	@Override
	public void setIndex(int index, T value) {
		checkNotEmpty();
		int count = 0;
		for (Node<T> current = this.start; current!=null; current = current.next) {
			if (count==index) {
				current.value = value;
				return;
			}
			count++;
		}
		
		throw new BadIndexError(index);
	}

	@Override
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of SinglyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}

}
