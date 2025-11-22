public class ListFactory {
    
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
