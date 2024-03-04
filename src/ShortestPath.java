import java.util.*;

public class ShortestPath {
    static class Node {
        String vertex;
        int distance;

        public Node(String vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static List<Node> getShortestPath(Graph graph, String start, String destination) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousVertices = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        Set<String> visited = new HashSet<>();

        // Initialize distances
        for (String vertex : graph.getVertices()) {
            if (vertex.equals(start)) {
                distances.put(vertex, 0);
                priorityQueue.add(new Node(vertex, 0));
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
            }
            previousVertices.put(vertex, null);
        }

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            visited.add(current.vertex);

            if (current.vertex.equals(destination)) {
                break;
            }

            Map<String, Integer[]> neighbors = graph.getNeighbors(current.vertex);
            if (neighbors == null) continue; // Skip if neighbors are null

            Integer currentDistance = distances.get(current.vertex);
            if (currentDistance == null) continue; // Skip if current vertex not in distances

            for (Map.Entry<String, Integer[]> neighbor : neighbors.entrySet()) {
                Integer[] edgeData = neighbor.getValue();
                if (edgeData == null || edgeData.length != 2) continue;
                if (!visited.contains(neighbor.getKey())) {
                    int newDistance = currentDistance + edgeData[0];
                    if (newDistance < distances.getOrDefault(neighbor.getKey(), Integer.MAX_VALUE)) {
                        distances.put(neighbor.getKey(), newDistance);
                        previousVertices.put(neighbor.getKey(), current.vertex);
                        priorityQueue.add(new Node(neighbor.getKey(), newDistance));
                    }
                }
            }
        }

        List<Node> path = new ArrayList<>();
        String currentVertex = destination;
        while (currentVertex != null) {
            Integer distance = distances.get(currentVertex);
            if (distance != null) {
                path.add(new Node(currentVertex, distance));
            }
            currentVertex = previousVertices.get(currentVertex);
        }
        Collections.reverse(path);
        return path;
    }
}
