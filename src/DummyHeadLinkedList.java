/**
 * DummyHeadLinkedList implementation of the List interface.
 * LinkedList with empty "head" node.
 * @param <T> the type of elements in this list
 */
public class DummyHeadLinkedList<T> implements List<T> {
    
    /**
     * Node class for storing elements in the linked list.
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
        
        Node() {
            this.data = null;
            this.next = null;
        }
    }
    
    private Node<T> dummyHead;
    private int size;
    
    /**
     * Constructs an empty DummyHeadLinkedList with a dummy head node.
     */
    public DummyHeadLinkedList() {
        this.dummyHead = new Node<>();
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
        Node<T> current = dummyHead;
        
        // Move to the position before the insertion point
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        // Insert the new node
        newNode.next = current.next;
        current.next = newNode;
        
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
        
        Node<T> current = dummyHead.next; // Skip dummy head
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        return current.data;
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
        
        Node<T> current = dummyHead;
        
        // Move to the position before the element to remove
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        T removed = current.next.data;
        current.next = current.next.next;
        
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
}
