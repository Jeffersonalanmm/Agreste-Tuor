import java.util.*;

public class Graph {
    private Map<String, Map<String, Integer[]>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(String source, String destination, int time, int minutes) {
        adjacencyList.putIfAbsent(source, new HashMap<>());
        adjacencyList.get(source).put(destination, new Integer[]{time, minutes});
    }

    public Map<String, Integer[]> getNeighbors(String vertex) {
        return adjacencyList.getOrDefault(vertex, new HashMap<>());
    }

    public Set<String> getVertices() {
        return adjacencyList.keySet();
    }
}
