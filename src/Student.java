import java.util.Locale;

/** Immutable student record used by all student indexes. */
public final class Student {
    private final String studentId;
    private final String name;
    private final String programme;
    private final double marks;

    public Student(String studentId, String name, String programme, double marks) {
        if (studentId == null || studentId.isBlank()) throw new IllegalArgumentException("Student ID cannot be empty.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty.");
        if (programme == null || programme.isBlank()) throw new IllegalArgumentException("Programme cannot be empty.");
        if (!Double.isFinite(marks) || marks < 0 || marks > 100) throw new IllegalArgumentException("Marks must be between 0 and 100.");
        this.studentId = studentId.trim();
        this.name = name.trim();
        this.programme = programme.trim();
        this.marks = marks;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getProgramme() { return programme; }
    public double getMarks() { return marks; }
    public String key() { return studentId.toLowerCase(Locale.ROOT); }

    @Override public String toString() {
        return String.format(Locale.ROOT, "%-14s | %-24s | %-20s | %6.2f", studentId, name, programme, marks);
    }
}
