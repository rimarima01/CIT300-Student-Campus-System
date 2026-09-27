import java.util.List;
import java.util.Scanner;

/** Entry point and menu controller for the CIT300 practical assignment. */
public final class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final StudentLinkedList students = new StudentLinkedList();
    private static final StudentHashTable studentIndex = new StudentHashTable();
    private static final StudentBST studentTree = new StudentBST();
    private static final ActionStack history = new ActionStack();
    private static final ServiceQueue requests = new ServiceQueue();
    private static final CampusGraph campus = new CampusGraph();

    private Main() { }

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println(" UNIVERSITY STUDENT RECORD & CAMPUS ROUTE MANAGEMENT SYSTEM");
        System.out.println("============================================================");
        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Select an option (1-16): ", 1, 16);
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> displayStudents();
                case 5 -> addServiceRequest();
                case 6 -> processNextRequest();
                case 7 -> displayHistory();
                case 8 -> displayStudentsSorted();
                case 9 -> searchStudent();
                case 10 -> addLocation();
                case 11 -> removeLocation();
                case 12 -> addRoad();
                case 13 -> removeRoad();
                case 14 -> displayCampus();
                case 15 -> traverseCampus();
                case 16 -> running = false;
                default -> System.out.println("Invalid option.");
            }
            if (running) pause();
        }
        System.out.println("Goodbye. Thank you for using the system.");
    }

    private static void showMenu() {
        System.out.println("\n---------------------- MAIN MENU ----------------------");
        System.out.println(" 1. Add Student Record                 9. Search Student (Hashing)");
        System.out.println(" 2. Update Student Record             10. Add Campus Location");
        System.out.println(" 3. Delete Student Record             11. Remove Campus Location");
        System.out.println(" 4. Display All Records (Linked List) 12. Add Campus Connection/Road");
        System.out.println(" 5. Add Service Request to Queue      13. Remove Campus Connection/Road");
        System.out.println(" 6. Process Next Service Request      14. Display Campus Network");
        System.out.println(" 7. Display Recent Actions (Stack)    15. Traverse Campus (BFS/DFS)");
        System.out.println(" 8. Display Students Sorted (BST)     16. Exit");
        System.out.println("-------------------------------------------------------");
        System.out.printf("Students: %d | Waiting requests: %d | Campus locations: %d%n",
                students.size(), requests.size(), campus.locationCount());
    }

    private static void addStudent() {
        System.out.println("\n-- Add Student Record --");
        String id = readRequired("Student ID: ");
        if (studentIndex.get(id) != null) { System.out.println("That student ID already exists (IDs are case-insensitive)."); return; }
        Student student = promptStudentDetails(id);
        if (student == null) return;
        students.addLast(student); studentIndex.put(student); studentTree.insert(student);
        record("Added student " + student.getStudentId());
        System.out.println("Student record added successfully.");
    }

    private static void updateStudent() {
        System.out.println("\n-- Update Student Record --");
        String id = readRequired("Enter the ID to update: ");
        Student old = studentIndex.get(id);
        if (old == null) { System.out.println("No student found with ID " + id + "."); return; }
        System.out.println("Current: " + old);
        Student replacement = promptStudentDetails(old.getStudentId());
        if (replacement == null) return;
        students.replace(old.getStudentId(), replacement);
        studentIndex.remove(old.getStudentId()); studentIndex.put(replacement);
        studentTree.remove(old.getStudentId()); studentTree.insert(replacement);
        record("Updated student " + old.getStudentId());
        System.out.println("Student record updated successfully.");
    }

    private static void deleteStudent() {
        System.out.println("\n-- Delete Student Record --");
        String id = readRequired("Enter the ID to delete: ");
        Student removed = students.remove(id);
        if (removed == null) { System.out.println("No student found with ID " + id + "."); return; }
        studentIndex.remove(removed.getStudentId()); studentTree.remove(removed.getStudentId());
        record("Deleted student " + removed.getStudentId());
        System.out.println("Deleted: " + removed);
    }

    private static Student promptStudentDetails(String id) {
        String name = readRequired("Student name: ");
        String programme = readRequired("Programme: ");
        double marks = readDouble("Marks (0-100): ", 0, 100);
        try { return new Student(id, name, programme, marks); }
        catch (IllegalArgumentException e) { System.out.println("Invalid record: " + e.getMessage()); return null; }
    }

    private static void displayStudents() {
        System.out.println("\n-- Student Records (Linked List order) --");
        printStudents(students.toList());
    }
    private static void displayStudentsSorted() {
        System.out.println("\n-- Student Records (BST in-order by Student ID) --");
        printStudents(studentTree.inOrder());
    }
    private static void printStudents(List<Student> list) {
        if (list.isEmpty()) { System.out.println("No student records to display."); return; }
        System.out.printf("%-14s | %-24s | %-20s | %6s%n", "STUDENT ID", "NAME", "PROGRAMME", "MARKS");
        System.out.println("--------------------------------------------------------------------------");
        for (Student student : list) System.out.println(student);
        System.out.println("Total records: " + list.size());
    }
    private static void searchStudent() {
        String id = readRequired("Enter Student ID to search: ");
        Student student = studentIndex.get(id);
        if (student == null) System.out.println("No student found with ID " + id + ".");
        else { System.out.println("Student found using the hash table:"); printStudents(List.of(student)); }
    }

    private static void addServiceRequest() {
        System.out.println("\n-- Add Service Request --");
        String id = readRequired("Student ID: ");
        Student student = studentIndex.get(id);
        if (student == null) { System.out.println("Student ID not found. Add the student record first."); return; }
        String description = readRequired("Request description: ");
        String request = student.getStudentId() + " (" + student.getName() + "): " + description;
        requests.enqueue(request); record("Queued service request for " + student.getStudentId());
        System.out.println("Request added to the queue. Position: " + requests.size());
    }
    private static void processNextRequest() {
        String request = requests.dequeue();
        if (request == null) { System.out.println("There are no service requests waiting."); return; }
        record("Processed service request: " + request);
        System.out.println("Now processing the oldest request:\n" + request);
    }
    private static void displayHistory() {
        System.out.println("\n-- Recent Actions (newest first; Stack) --");
        List<String> actions = history.newestFirst();
        if (actions.isEmpty()) { System.out.println("No actions recorded yet."); return; }
        int shown = Math.min(actions.size(), 20);
        for (int i = 0; i < shown; i++) System.out.printf("%2d. %s%n", i + 1, actions.get(i));
        if (actions.size() > shown) System.out.println("...and " + (actions.size() - shown) + " older action(s).");
    }
    private static void record(String action) { history.push(action); }

    private static void addLocation() {
        String name = readRequired("New campus location name: ");
        if (!campus.addLocation(name)) { System.out.println("Location is blank or already exists."); return; }
        record("Added campus location " + name.trim()); System.out.println("Campus location added.");
    }
    private static void removeLocation() {
        String name = readRequired("Campus location to remove: ");
        if (!campus.removeLocation(name)) { System.out.println("Location not found."); return; }
        record("Removed campus location " + name.trim() + " and its roads");
        System.out.println("Location removed, along with all of its connections.");
    }
    private static void addRoad() {
        String from = readRequired("First location: ");
        String to = readRequired("Second location: ");
        if (!campus.hasLocation(from) || !campus.hasLocation(to)) { System.out.println("Both locations must exist before adding a road."); return; }
        if (from.equalsIgnoreCase(to)) { System.out.println("A location cannot connect to itself."); return; }
        if (!campus.addRoad(from, to)) { System.out.println("That road already exists."); return; }
        record("Added campus road " + from.trim() + " <-> " + to.trim()); System.out.println("Undirected campus road added.");
    }
    private static void removeRoad() {
        String from = readRequired("First location: ");
        String to = readRequired("Second location: ");
        if (!campus.hasLocation(from) || !campus.hasLocation(to)) { System.out.println("Both locations must exist."); return; }
        if (!campus.removeRoad(from, to)) { System.out.println("Connection does not exist."); return; }
        record("Removed campus road " + from.trim() + " <-> " + to.trim()); System.out.println("Road removed.");
    }
    private static void displayCampus() {
        System.out.println("\n-- Campus Network (Adjacency List) --");
        System.out.print(campus.displayNetwork());
    }
    private static void traverseCampus() {
        if (campus.locationCount() == 0) { System.out.println("Add campus locations before traversing the graph."); return; }
        String start = readRequired("Traversal start location: ");
        if (!campus.hasLocation(start)) { System.out.println("Location not found. Check the displayed campus network."); return; }
        int mode = readInt("Choose 1 for BFS or 2 for DFS: ", 1, 2);
        List<String> order = mode == 1 ? campus.bfs(start) : campus.dfs(start);
        String label = mode == 1 ? "BFS" : "DFS";
        System.out.println(label + " traversal order from " + start.trim() + ": " + String.join(" -> ", order));
        if (order.size() < campus.locationCount())
            System.out.println("Note: only locations reachable from the chosen start are included.");
        record("Performed " + label + " traversal from " + start.trim());
    }

    private static String readRequired(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("Input cannot be blank. Please try again.");
        }
    }
    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (number >= min && number <= max) return number;
            } catch (NumberFormatException ignored) { /* handled below */ }
            System.out.printf("Enter a whole number from %d to %d.%n", min, max);
        }
    }
    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();
            try {
                double number = Double.parseDouble(value);
                if (Double.isFinite(number) && number >= min && number <= max) return number;
            } catch (NumberFormatException ignored) { /* handled below */ }
            System.out.printf("Enter a valid number from %.0f to %.0f.%n", min, max);
        }
    }
    private static void pause() {
        System.out.print("\nPress Enter to return to the menu...");
        INPUT.nextLine();
    }
}
