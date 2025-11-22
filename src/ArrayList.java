public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] array;
    private int size;
    
    public ArrayList() {
        this.array = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }
    
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (size >= array.length) {
            resize();
        }
        
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        
        array[index] = element;
        size++;
    }
    
    @Override
    public boolean add(T element) {
        add(size, element);
        return true;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) array[index];
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        T removed = (T) array[index];
        
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        
        array[size - 1] = null;
        size--;
        return removed;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    private void resize() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}
