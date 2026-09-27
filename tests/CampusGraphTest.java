import java.util.List;
public class CampusGraphTest {
private static int checks = 0;
private static void check(boolean condition, 
String message) {
checks++;
if (!condition) throw new AssertionError("Failed: " 
+ message);
}
public static void main(String[] args) {
CampusGraph graph = new CampusGraph();
check(graph.addLocation("Gate"), "add Gate");
check(graph.addLocation("Library"), "add 
Library");
check(graph.addLocation("Lab"), "add Lab");
check(graph.addLocation("Remote"), "add 
disconnected location");
check(!graph.addLocation("Gate"), "reject 
duplicate location");
check(graph.addRoad("Gate", "Library"), "add 
Gate-Library road");
check(graph.addRoad("Library", "Lab"), "add 
Library-Lab road");
check(!graph.addRoad("Gate", "Library"), "reject 
duplicate road");
check(!graph.addRoad("Gate", "Gate"), "reject 
self-connection");
check(!graph.addRoad("Gate", "Missing"), "reject 
missing location");
check(graph.neighbors("Library").equals(List.of("
Gate", "Lab")),
"adjacency-list neighbors");
check(graph.bfs("Gate").equals(List.of("Gate", 
"Library", "Lab")),
"BFS visits reachable locations");
check(graph.dfs("Gate").equals(List.of("Gate", 
"Library", "Lab")),
"DFS visits connected locations");
check(graph.removeRoad("Gate", "Library"), 
"remove existing road");
check(!graph.removeRoad("Gate", "Library"), 
"missing road rejected");
check(graph.removeLocation("Library"), "remove 
location");
check(!graph.hasLocation("Library") && 
graph.locationCount() == 3,
"location and its roads removed");
System.out.println("PASS: " + checks + " graph 
checks.");
}
}
