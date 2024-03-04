import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "teste.txt"; // Modificação do caminho do arquivo
        Graph graph;

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("Arquivo não encontrado: " + filePath);
            return;
        }

        graph = GraphReader.readGraphFromFile(filePath);

        String startCity = "Aguas Belas";
        String destinationCity = "Garanhuns";

        if (graph != null) {
            List<ShortestPath.Node> shortestPathNodes = ShortestPath.getShortestPath(graph, startCity, destinationCity);

            if (shortestPathNodes != null && !shortestPathNodes.isEmpty()) {
                int totalDistance = 0;
                int totalMinutes = 0; // Alteração aqui para calcular o tempo total
                for (int i = 0; i < shortestPathNodes.size() - 1; i++) {
                    String currentCity = shortestPathNodes.get(i).vertex;
                    String nextCity = shortestPathNodes.get(i + 1).vertex;
                    Integer[] edgeData = graph.getNeighbors(currentCity).get(nextCity);
                    totalDistance += edgeData[0];
                    totalMinutes += edgeData[1]; // Acumulando o tempo em minutos
                    System.out.println(currentCity + " -> " + nextCity + " (Distância: " + edgeData[0] + " km, Tempo: " + edgeData[1] + " minutos)");
                }
                // Convertendo o tempo total de minutos para horas e minutos
                int totalHours = totalMinutes / 60;
                totalMinutes %= 60;
                System.out.println("O tempo total da rota mais curta entre " + startCity + " e " + destinationCity + " é: " + totalHours + " horas e " + totalMinutes + " minutos");
                System.out.println("Distância total percorrida: " + totalDistance + " km");
            } else {
                System.out.println("Não há rota entre " + startCity + " e " + destinationCity);
            }
        } else {
            System.out.println("O grafo não foi inicializado corretamente. Verifique se o arquivo foi lido corretamente.");
        }
    }
}
