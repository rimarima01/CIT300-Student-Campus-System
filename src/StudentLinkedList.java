import java.util.ArrayList;
import java.util.List;

/** Singly linked list, maintained as the primary student-record store. */
public final class StudentLinkedList {
    private static final class Node {
        Student student;
        Node next;
        Node(Student student) { this.student = student; }
    }
    private Node head;
    private Node tail;
    private int size;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void addLast(Student student) {
        Node node = new Node(student);
        if (head == null) head = tail = node;
        else { tail.next = node; tail = node; }
        size++;
    }

    public Student find(String id) {
        for (Node n = head; n != null; n = n.next)
            if (n.student.getStudentId().equalsIgnoreCase(id.trim())) return n.student;
        return null;
    }

    public boolean replace(String id, Student replacement) {
        for (Node n = head; n != null; n = n.next) {
            if (n.student.getStudentId().equalsIgnoreCase(id.trim())) { n.student = replacement; return true; }
        }
        return false;
    }

    public Student remove(String id) {
        Node previous = null;
        for (Node current = head; current != null; current = current.next) {
            if (current.student.getStudentId().equalsIgnoreCase(id.trim())) {
                if (previous == null) head = current.next; else previous.next = current.next;
                if (current == tail) tail = previous;
                size--;
                if (size == 0) head = tail = null;
                return current.student;
            }
            previous = current;
        }
        return null;
    }

    public List<Student> toList() {
        List<Student> result = new ArrayList<>(size);
        for (Node n = head; n != null; n = n.next) result.add(n.student);
        return result;
    }
}
