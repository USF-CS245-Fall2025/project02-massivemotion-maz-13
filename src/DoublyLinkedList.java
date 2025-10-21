/**
 * DoublyLinkedList implementation of the List interface.
 * Linked list containing Node with next and prev pointers.
 * @param <T> the type of elements in this list
 */
public class DoublyLinkedList<T> implements List<T> {
    
    /**
     * Node class for storing elements in the doubly-linked list.
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;
        
        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    /**
     * Constructs an empty DoublyLinkedList.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Adds the specified element at the specified position in this list.
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node<T> newNode = new Node<>(element);
        
        if (size == 0) {
            // First element
            head = tail = newNode;
        } else if (index == 0) {
            // Insert at beginning
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (index == size) {
            // Insert at end
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            // Insert in middle
            Node<T> current = getNodeAt(index);
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        
        size++;
    }
    
    /**
     * Appends the specified element to the end of this list.
     * @param element element to be appended to this list
     * @return true (as specified by Collection.add)
     */
    @Override
    public boolean add(T element) {
        add(size, element);
        return true;
    }
    
    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        return getNodeAt(index).data;
    }
    
    /**
     * Removes the element at the specified position in this list.
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node<T> nodeToRemove = getNodeAt(index);
        T removed = nodeToRemove.data;
        
        if (size == 1) {
            // Only one element
            head = tail = null;
        } else if (nodeToRemove == head) {
            // Remove from beginning
            head = head.next;
            head.prev = null;
        } else if (nodeToRemove == tail) {
            // Remove from end
            tail = tail.prev;
            tail.next = null;
        } else {
            // Remove from middle
            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;
        }
        
        size--;
        return removed;
    }
    
    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Helper method to get the node at a specific index.
     * @param index the index of the node to return
     * @return the node at the specified index
     */
    private Node<T> getNodeAt(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}
