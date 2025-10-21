/**
 * Factory class for creating List implementations based on configuration.
 */
public class ListFactory {
    
    /**
     * Creates a new List implementation based on the specified type.
     * @param listType the type of list to create ("arraylist", "single", "double", "dummyhead")
     * @param <T> the type of elements in the list
     * @return a new List implementation
     * @throws IllegalArgumentException if the list type is not recognized
     */
    public static <T> List<T> createList(String listType) {
        switch (listType.toLowerCase()) {
            case "arraylist":
                return new ArrayList<>();
            case "single":
                return new LinkedList<>();
            case "double":
                return new DoublyLinkedList<>();
            case "dummyhead":
                return new DummyHeadLinkedList<>();
            default:
                throw new IllegalArgumentException("Unknown list type: " + listType);
        }
    }
}
