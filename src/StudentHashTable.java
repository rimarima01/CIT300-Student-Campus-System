import java.util.LinkedList;
import java.util.Locale;

/** Hash table using separate chaining; IDs are matched case-insensitively. */
public final class StudentHashTable {
    private LinkedList<Student>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public StudentHashTable() { buckets = (LinkedList<Student>[]) new LinkedList<?>[16]; }

    private int index(String id) {
        return Math.floorMod(id.trim().toLowerCase(Locale.ROOT).hashCode(), buckets.length);
    }
    private LinkedList<Student> bucket(int i) {
        if (buckets[i] == null) buckets[i] = new LinkedList<>();
        return buckets[i];
    }
    public int size() { return size; }

    public Student get(String id) {
        if (id == null || id.isBlank()) return null;
        LinkedList<Student> chain = buckets[index(id)];
        if (chain != null) for (Student student : chain)
            if (student.getStudentId().equalsIgnoreCase(id.trim())) return student;
        return null;
    }
    public boolean put(Student student) {
        if (get(student.getStudentId()) != null) return false;
        if ((double)(size + 1) / buckets.length > 0.75) resize();
        bucket(index(student.getStudentId())).add(student);
        size++;
        return true;
    }
    public Student remove(String id) {
        if (id == null || id.isBlank()) return null;
        LinkedList<Student> chain = buckets[index(id)];
        if (chain == null) return null;
        for (Student student : chain) {
            if (student.getStudentId().equalsIgnoreCase(id.trim())) {
                chain.remove(student); size--; return student;
            }
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Student>[] old = buckets;
        buckets = (LinkedList<Student>[]) new LinkedList<?>[old.length * 2];
        int oldSize = size; size = 0;
        for (LinkedList<Student> chain : old) if (chain != null)
            for (Student student : chain) { bucket(index(student.getStudentId())).add(student); size++; }
        if (size != oldSize) throw new IllegalStateException("Hash table resize lost records.");
    }
}
