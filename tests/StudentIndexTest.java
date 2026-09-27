import java.util.List;

public class StudentIndexTest {
    private static int checks = 0;

    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) throw new AssertionError("Failed: " + message);
    }

    private static Student make(String id) {
        return new Student(id, "Student " + id, "Computing", 70.0);
    }

    public static void main(String[] args) {
        StudentHashTable hash = new StudentHashTable();
        Student a = make("S-20");
        Student b = make("S-10");
        Student c = make("S-30");

        check(hash.put(a) && hash.put(b) && hash.put(c), "hash inserts");
        check(!hash.put(make("s-20")), "hash rejects duplicate ID");
        check(hash.get("s-10") == b, "case-insensitive hash search");
        check(hash.remove("S-20") == a, "hash removes record");
        check(hash.get("S-20") == null && hash.size() == 2,
                "hash reflects removal");

        StudentHashTable growingHash = new StudentHashTable();
        for (int i = 0; i < 80; i++) growingHash.put(make("ID-" + i));
        check(growingHash.size() == 80, "hash size after growth");
        check(growingHash.get("id-79") != null, "search after resizing");

        StudentBST tree = new StudentBST();
        tree.insert(a);
        tree.insert(b);
        tree.insert(c);
        check(tree.search("s-30") == c, "BST search");
        check(tree.inOrder().stream().map(Student::getStudentId).toList()
                .equals(List.of("S-10", "S-20", "S-30")), "BST sorted order");
        tree.remove("S-20");
        check(tree.search("S-20") == null && tree.inOrder().size() == 2,
                "BST deletion");

        System.out.println("PASS: " + checks + " hash/BST checks.");
    }
}