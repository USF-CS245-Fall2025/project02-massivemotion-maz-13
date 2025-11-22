public class DummyHeadLinkedList<T> implements List<T> {
    
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
    
    public DummyHeadLinkedList() {
        this.dummyHead = new Node<>();
        this.size = 0;
    }
    
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node<T> newNode = new Node<>(element);
        Node<T> current = dummyHead;
        
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        newNode.next = current.next;
        current.next = newNode;
        
        size++;
    }
    
    @Override
    public boolean add(T element) {
        add(size, element);
        return true;
    }
    
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node<T> current = dummyHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        return current.data;
    }
    
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node<T> current = dummyHead;
        
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        T removed = current.next.data;
        current.next = current.next.next;
        
        size--;
        return removed;
    }
    
    @Override
    public int size() {
        return size;
    }
}
