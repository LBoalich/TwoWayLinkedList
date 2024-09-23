import java.util.*;

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size = 0; // Number of elements in the list
    
    /** Create an empty list */
    public TwoWayLinkedList() {
    }

    /** Create a list from an array of objects */
    public TwoWayLinkedList(E[] objects) {
        for (E e : objects)
            add(e);
    }

    /** Return the head element in the list */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
          } else {
            return tail.element;
          }   
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        if (head == null) {
            head = newNode; // head points to the new node
            head.previous = null; // head node so no node to link
            head.next = null;  // only no so no node to link
            tail = head;  // only node so tail and head the same
        }
        else {
            newNode.next = head; // link the new node with the head
            head = newNode; // head points to the new node
            (head.next).previous = head; // link the old head to new head
            head.previous = null; // head node so no node to link
        }
        size++; // Increase list size

        if (tail == null) // The new node is the only node in list
            tail = head;
        
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node for e

        if (tail == null) {
            head = tail = newNode; // The only node in list
        }
        else {
            tail.next = newNode; // Link the new node with the last node
            newNode.previous = tail;  // Link the new tails previous to the old tail
            tail = newNode; // tail now points to the last node
        }

        size++; // Increase size
    }

    @Override /** Add a new element at the specified index
    * in this list. The index of the head element is 0 */
    public void add(int index, E e) {
        if (index == 0) addFirst(e); // Insert first
        else if (index >= size) addLast(e); // Insert last
        else { // Insert in the middle
            Node<E> current = head;
            for (int i = 1; i < index; i++)
                current = current.next;
            Node<E> temp = current.next;
            current.next = temp.previous = new Node<>(e); // Set current next and temp previous to new node
            (current.next).previous = current; // Set new nodes previous to current node
            (current.next).next = temp;
            size++;
        }
    }

    /** Remove the head node and
    * return the object that is contained in the removed node. */
    public E removeFirst() {
        if (size == 0) return null; // Nothing to delete
        else {
            Node<E> temp = head; // Keep the first node temporarily
            head = head.next; // Move head to point to next node
            head.previous = null; // Set new head previous to null
            size--; // Reduce size by 1
            if (head == null) tail = null; // List becomes empty
            return temp.element; // Return the deleted element
        }
    } 

    /** Remove the last node and
    * return the object that is contained in the removed node. */
    public E removeLast() { 
        if (size == 0 || size == 1) {
            return removeFirst();
        }
        else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }

            E temp = tail.element;
            tail = current;
            tail.next = null;
            size--;
            return temp;
        }        
    }
   
    @Override /** Remove the element at the specified position in this
    * list. Return the element that was removed from the list. */
    public E remove(int index) {
        if (index < 0 || index >= size) return null; // Out of range
        else if (index == 0) return removeFirst(); // Remove first
        else if (index == size - 1) return removeLast(); // Remove last
        else {
            Node<E> previous = head;

            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            (current.next).previous = previous; // Set the node after the removed nodes previous to the node before the removed node
            size--;
            return current.element;
        }  
    }

    @Override /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
  
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
          result.append(current.element);
          current = current.next;
          if (current != null) {
            result.append(", "); // Separate two elements with a comma
          } else {
            result.append("]"); // Insert the closing ] in the string
          }
        }
        return result.toString();
    }

    @Override /** Clear the list */
    public void clear() {
        size = 0;
        head = tail = null;
    }
   
    @Override /** Return true if this list contains the element e */
    public boolean contains(Object e) {
        System.out.println("Implementation left as an exercise");
        return true;
    }

    @Override /** Return the element at the specified index */
    public E get(int index) {
        System.out.println("Implementation left as an exercise");
        return null;
    }

    @Override /** Return the index of the first matching element in
    * this list. Return −1 if no match. */
    public int indexOf(Object e) {
        System.out.println("Implementation left as an exercise");
        return 0;
    }

    @Override /** Return the index of the last matching element in
    * this list. Return −1 if no match. */
    public int lastIndexOf(E e) {
        System.out.println("Implementation left as an exercise");
        return 0;
    }

    @Override /** Replace the element at the specified position
    * in this list with the specified element. */
    public E set(int index, E e) {
        System.out.println("Implementation left as an exercise");
        return null;
    }
  
    @Override /** Override iterator() defined in Iterable */ 
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }
   
    private class LinkedListIterator implements ListIterator<E> {
        private Node<E> current = head; // Current index

        public LinkedListIterator() {
        }
        
        public LinkedListIterator(int index) {
          if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
              + size);
          for (int nextIndex = 0; nextIndex < index; nextIndex++)
            current = current.next;
        }

        public void setLast() {
            current = tail;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override
        public void remove() {
            System.out.println("Implementation left as an exercise");
        }


        @Override
        public void add(E e) {
            System.out.println("Implementation left as an exercise");
        }

        @Override
        public boolean hasPrevious() {
            return current != null;
        }

        @Override
        public int nextIndex() {
          System.out.println("Implementation left as an exercise");
          return 0;
        }

        @Override
        public E previous() {
          E e = current.element;
          current = current.previous;
          return e;
        }

        @Override
        public int previousIndex() {
          System.out.println("Implementation left as an exercise");
          return 0;
        }

        @Override
        public void set(E e) {
          current.element = e; // TODO Auto-generated method stub
        }
    }
   
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E o) {
            element = o;
        }
    }

    @Override /** Return the number of elements in this list */
    public int size() {
        return size;
    }

    public ListIterator<E> listIterator() {
        return new LinkedListIterator(); 
    }
    
    public ListIterator<E> listIterator(int index) {
        return new LinkedListIterator(index); 
    }
}





    
    





    

   

 

   

  




 
