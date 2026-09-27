import java.util.List;

public class StudentLinkedListTest {
    private static int checks = 0;

    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) {
            throw new AssertionError("Failed: " + message);
        }
    }

    private static Student make(String id) {
        return new Student(id, "Test " + id, "Computing", 75.0);
    }

    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();

        check(list.isEmpty(), "starts empty");
        check(list.find("missing") == null, "missing ID returns null");

        Student one = make("S-01");
        Student two = make("S-02");
        Student three = make("S-03");

        list.addLast(one);
        list.addLast(two);
        list.addLast(three);

        check(list.size() == 3, "size after adding records");
        check(list.find("s-02") == two, "case-insensitive ID find");

        Student changed =
                new Student("S-02", "Updated", "Computing", 88.0);

        check(list.replace("S-02", changed), "replace existing record");
        check(list.find("S-02") == changed, "find returns replacement");
        check(!list.replace("absent", make("S-04")),
                "missing record cannot be replaced");

        check(list.remove("S-01") == one, "remove first node");
        check(list.remove("S-03") == three, "remove last node");
        check(list.remove("not-here") == null,
                "missing record cannot be removed");
        check(list.size() == 1
                        && list.toList().equals(List.of(changed)),
                "remaining record and size");
        check(list.remove("S-02") == changed && list.isEmpty(),
                "remove final node");

        System.out.println("PASS: " + checks + " linked-list checks.");
    }
}
