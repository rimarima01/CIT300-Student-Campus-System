import java.util.ArrayList;
import java.util.List;

/** FIFO queue for student service requests. */
public final class ServiceQueue {
    private static final class Node { String request; Node next; Node(String request) { this.request = request; } }
    private Node front, rear;
    private int size;
    public void enqueue(String request) {
        Node node = new Node(request);
        if (rear == null) front = rear = node;
        else { rear.next = node; rear = node; }
        size++;
    }
    public String dequeue() {
        if (front == null) return null;
        String request = front.request; front = front.next; size--;
        if (front == null) rear = null;
        return request;
    }
    public boolean isEmpty() { return front == null; }
    public int size() { return size; }
    public List<String> toList() {
        List<String> result = new ArrayList<>();
        for (Node n = front; n != null; n = n.next) result.add(n.request);
        return result;
    }
}
