import java.io.*;
import java.util.*;

class ProblemInstance {
    int n;
    int m;
    int s;
    int t;
    Set<Node> nodes;
    Set<Edge> edges;

    public ProblemInstance(int n, int m, int s, int t, Set<Node> nodes, Set<Edge> edges) {
        this.n = n;
        this.m = m;
        this.s = s;
        this.t = t;
        this.nodes = nodes;
        this.edges = edges;
    }
}

public class Time {
    private static ProblemInstance parseInput(Scanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        int t = sc.nextInt();
        Set<Edge> edges = new HashSet<>();
        List<Node> nodesList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nodesList.add(new Node(i));
        }
        for (int i = 1; i <= m; i++) {
            Edge e = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
            edges.add(e);
            nodesList.get(e.from-1).outgoingEdges.add(e);
        }
        sc.close();
        return new ProblemInstance(n, m, s, t, Set.copyOf(nodesList), edges);
    }

    /**
     * Method that runs and times the two methods.
     * @param args - provide the filenames as arguments to this function.
     * @throws FileNotFoundException - if any of the files is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        for (String filename : args) {
            System.out.println(filename);
            ProblemInstance pi = parseInput(new Scanner(new File(filename)));
            long startD = System.nanoTime();
            int d = Dijkstra.getMeOuttaHere(pi.n, pi.m, pi.s, pi.t, pi.nodes, pi.edges);
            long endD = System.nanoTime();
            long startFW = System.nanoTime();
            int fw = FloydWarshall.getMeOuttaHere(pi.n, pi.m, pi.s, pi.t, pi.nodes, pi.edges);
            long endFW = System.nanoTime();
            assert d == fw;
            System.out.printf("File %s: Dijkstra: %f s; Floyd-Warshall: %f s\n", filename, (endD - startD) / 1000000000.0, (endFW - startFW) / 1000000000.0);
        }
    }
}
