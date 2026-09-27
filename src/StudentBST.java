import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Binary search tree keyed by student ID (case-insensitive). */
public final class StudentBST {
    private static final class Node {
        Student student;
        Node left, right;
        Node(Student student) { this.student = student; }
    }
    private Node root;

    public void insert(Student student) { root = insert(root, student); }
    private Node insert(Node node, Student student) {
        if (node == null) return new Node(student);
        int c = compare(student.getStudentId(), node.student.getStudentId());
        if (c < 0) node.left = insert(node.left, student);
        else if (c > 0) node.right = insert(node.right, student);
        else throw new IllegalArgumentException("Duplicate student ID: " + student.getStudentId());
        return node;
    }
    public Student search(String id) {
        if (id == null) return null;
        Node current = root;
        while (current != null) {
            int c = compare(id, current.student.getStudentId());
            if (c == 0) return current.student;
            current = c < 0 ? current.left : current.right;
        }
        return null;
    }
    public void remove(String id) { root = remove(root, id); }
    private Node remove(Node node, String id) {
        if (node == null) return null;
        int c = compare(id, node.student.getStudentId());
        if (c < 0) node.left = remove(node.left, id);
        else if (c > 0) node.right = remove(node.right, id);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node successor = minimum(node.right);
            node.student = successor.student;
            node.right = remove(node.right, successor.student.getStudentId());
        }
        return node;
    }
    private Node minimum(Node node) { while (node.left != null) node = node.left; return node; }
    public List<Student> inOrder() { List<Student> result = new ArrayList<>(); inOrder(root, result); return result; }
    private void inOrder(Node node, List<Student> result) {
        if (node == null) return;
        inOrder(node.left, result); result.add(node.student); inOrder(node.right, result);
    }
    private int compare(String a, String b) { return a.trim().toLowerCase(Locale.ROOT).compareTo(b.trim().toLowerCase(Locale.ROOT)); }
}
