import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GraphReader {
    public static Graph readGraphFromFile(String filePath) {
        Graph graph = new Graph();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] edgeData = line.split(",");
                if (edgeData.length == 4) {
                    String source = edgeData[0];
                    String destination = edgeData[1];
                    int time = Integer.parseInt(edgeData[2]);
                    int minutes = Integer.parseInt(edgeData[3]);

                    graph.addEdge(source, destination, time, minutes);
                    graph.addEdge(destination, source, time, minutes); // For an undirected graph
                }
            }
        } catch (IOException e) {
            System.err.println("Erro de entrada/saída ao ler o arquivo: " + e.getMessage());
        }

        return graph;
    }
}
