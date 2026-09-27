import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/** Undirected graph of campus locations represented with adjacency lists. */
public final class CampusGraph {
    private final Map<String, String> names = new LinkedHashMap<>();
    private final Map<String, LinkedHashSet<String>> adjacency = new LinkedHashMap<>();
    private String key(String name) { return name.trim().toLowerCase(Locale.ROOT); }
    public int locationCount() { return names.size(); }
    public boolean hasLocation(String name) { return name != null && names.containsKey(key(name)); }
    public boolean addLocation(String name) {
        if (name == null || name.isBlank() || hasLocation(name)) return false;
        String k = key(name); names.put(k, name.trim()); adjacency.put(k, new LinkedHashSet<>()); return true;
    }
    public boolean removeLocation(String name) {
        if (!hasLocation(name)) return false;
        String k = key(name);
        for (Set<String> neighbors : adjacency.values()) neighbors.remove(k);
        adjacency.remove(k); names.remove(k); return true;
    }
    public boolean addRoad(String from, String to) {
        if (!hasLocation(from) || !hasLocation(to) || key(from).equals(key(to))) return false;
        String a = key(from), b = key(to);
        if (adjacency.get(a).contains(b)) return false;
        adjacency.get(a).add(b); adjacency.get(b).add(a); return true;
    }
    public boolean removeRoad(String from, String to) {
        if (!hasLocation(from) || !hasLocation(to)) return false;
        String a = key(from), b = key(to);
        if (!adjacency.get(a).contains(b)) return false;
        adjacency.get(a).remove(b); adjacency.get(b).remove(a); return true;
    }
    public List<String> locations() { return new ArrayList<>(names.values()); }
    public List<String> neighbors(String location) {
        if (!hasLocation(location)) return List.of();
        List<String> result = new ArrayList<>();
        for (String neighbor : adjacency.get(key(location))) result.add(names.get(neighbor));
        return result;
    }
    public String displayNetwork() {
        if (names.isEmpty()) return "No campus locations have been added.";
        StringBuilder out = new StringBuilder();
        for (Map.Entry<String, String> entry : names.entrySet()) {
            out.append(entry.getValue()).append(" -> ");
            List<String> adjacent = new ArrayList<>();
            for (String neighbor : adjacency.get(entry.getKey())) adjacent.add(names.get(neighbor));
            out.append(adjacent.isEmpty() ? "(no connections)" : String.join(", ", adjacent)).append('\n');
        }
        return out.toString();
    }
    public List<String> bfs(String start) {
        if (!hasLocation(start)) return List.of();
        List<String> order = new ArrayList<>(); Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new ArrayDeque<>(); String startKey = key(start);
        visited.add(startKey); queue.add(startKey);
        while (!queue.isEmpty()) {
            String current = queue.remove(); order.add(names.get(current));
            for (String next : adjacency.get(current)) if (visited.add(next)) queue.add(next);
        }
        return order;
    }
    public List<String> dfs(String start) {
        if (!hasLocation(start)) return List.of();
        List<String> order = new ArrayList<>(); Set<String> visited = new LinkedHashSet<>();
        dfsVisit(key(start), visited, order); return order;
    }
    private void dfsVisit(String current, Set<String> visited, List<String> order) {
        visited.add(current); order.add(names.get(current));
        for (String next : adjacency.get(current)) if (!visited.contains(next)) dfsVisit(next, visited, order);
    }
}
