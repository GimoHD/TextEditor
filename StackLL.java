/**
 * Implements a Stack based on a Linked List
 *
 * @param <T> type of the elements on the stack
 */
public class StackLL<T> implements Stack<T> {
    private final int maxSize;
    private LinkedList<T> ll;

    /**
     * Creates an empty stack
     */
    public StackLL() {
        ll = new LinkedList<>();
        this.maxSize = -1;
    }

    public StackLL(int maxSize) {
        ll = new LinkedList<>();
        this.maxSize = maxSize;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void reset() {
        ll = new LinkedList<>();
    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public void push(T element) {
        ll.prepend(element);
        if (maxSize > 0) {
            ll.removeOverFlow(maxSize);
        }

    }

    @Override
    public T pop() {
        if (size() == 0) throw new StackEmptyException();
        T element = ll.first();
        ll = ll.tail();
        return element;
    }

    @Override
    public T top() {
        return ll.first();
    }

}
