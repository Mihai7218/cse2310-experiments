import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FloydWarshall {
    public static int getMeOuttaHere(int n, int m, int s, int t, Set<Node> nodes, Set<Edge> edges) {
        int[][] distances = new int[n+1][n+1];
        int oo = (int) 1e9;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                distances[i][j] = oo;
            }
        }
        List<Node> nodeList = new ArrayList<>(nodes);
        for (Edge e : edges) {
            distances[e.from][e.to] = e.weight + nodeList.get(e.from-1).outgoingEdges.size();
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int distance = distances[i][k] + distances[k][j];
                    if (distance < distances[i][j]) {
                        distances[i][j] = distance;
                    }
                }
            }
        }
        return distances[s][t] != oo ? distances[s][t] : -1;
    }
}
