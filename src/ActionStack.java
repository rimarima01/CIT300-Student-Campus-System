import java.util.ArrayList;
import java.util.List;

/** LIFO history stack implemented with linked nodes. */
public final class ActionStack {
    private static final class Node { String value; Node next; Node(String value, Node next) { this.value = value; this.next = next; } }
    private Node top;
    private int size;
    public void push(String action) { top = new Node(action, top); size++; }
    public String pop() {
        if (top == null) return null;
        String value = top.value; top = top.next; size--; return value;
    }
    public boolean isEmpty() { return top == null; }
    public List<String> newestFirst() {
        List<String> result = new ArrayList<>();
        for (Node n = top; n != null; n = n.next) result.add(n.value);
        return result;
    }
    public int size() { return size; }
}
