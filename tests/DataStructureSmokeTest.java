import java.util.List;

/** Lightweight dependency-free checks; run with: java -cp out DataStructureSmokeTest */
public final class DataStructureSmokeTest {
    private static int checks;
    private DataStructureSmokeTest() { }
    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) throw new AssertionError("Check failed: " + message);
    }
    private static Student student(String id, String name, double marks) {
        return new Student(id, name, "Computing", marks);
    }
    public static void main(String[] args) {
        Student a = student("S-20", "Amina", 85.5);
        Student b = student("S-10", "Bilal", 72);
        Student c = student("S-30", "Cara", 94);

        StudentLinkedList list = new StudentLinkedList();
        list.addLast(a); list.addLast(b); list.addLast(c);
        check(list.size() == 3 && list.find("s-10") == b, "linked-list add/find");
        Student updated = student("S-10", "Bilal Updated", 75);
        check(list.replace("S-10", updated) && list.find("s-10") == updated, "linked-list replace");
        check(list.remove("S-20") == a && list.size() == 2, "linked-list remove");

        StudentHashTable hash = new StudentHashTable();
        check(hash.put(a) && hash.put(b) && hash.put(c), "hash insertion");
        check(!hash.put(student("s-20", "Duplicate", 1)), "hash duplicate rejection");
        check(hash.get("s-10") == b && hash.remove("S-20") == a && hash.size() == 2, "hash lookup/remove");

        StudentBST bst = new StudentBST();
        bst.insert(a); bst.insert(b); bst.insert(c);
        check(bst.search("s-30") == c, "BST search");
        check(bst.inOrder().stream().map(Student::getStudentId).toList().equals(List.of("S-10", "S-20", "S-30")), "BST in-order sort");
        bst.remove("S-20");
        check(bst.search("S-20") == null && bst.inOrder().size() == 2, "BST deletion with two children");

        ActionStack stack = new ActionStack();
        stack.push("first"); stack.push("second");
        check("second".equals(stack.pop()) && "first".equals(stack.pop()) && stack.isEmpty(), "stack LIFO");
        ServiceQueue queue = new ServiceQueue();
        queue.enqueue("first"); queue.enqueue("second");
        check("first".equals(queue.dequeue()) && "second".equals(queue.dequeue()) && queue.isEmpty(), "queue FIFO");

        CampusGraph graph = new CampusGraph();
        check(graph.addLocation("Gate") && graph.addLocation("Library") && graph.addLocation("Lab"), "graph locations");
        check(!graph.addLocation("gate"), "graph case-insensitive duplicate");
        check(graph.addRoad("Gate", "Library") && graph.addRoad("Library", "Lab"), "graph roads");
        check(!graph.addRoad("Gate", "Unknown") && !graph.addRoad("Gate", "Gate"), "graph invalid-road handling");
        check(graph.bfs("Gate").equals(List.of("Gate", "Library", "Lab")), "graph BFS");
        check(graph.dfs("Gate").equals(List.of("Gate", "Library", "Lab")), "graph DFS");
        check(graph.removeRoad("Gate", "Library") && graph.bfs("Gate").equals(List.of("Gate")), "graph remove road");
        check(graph.removeLocation("Library") && graph.locationCount() == 2, "graph remove location");

        boolean rejectedMarks = false;
        try { student("BAD", "Invalid", 101); } catch (IllegalArgumentException expected) { rejectedMarks = true; }
        check(rejectedMarks, "marks range validation");
        System.out.println("PASS: " + checks + " smoke checks completed.");
    }
}
