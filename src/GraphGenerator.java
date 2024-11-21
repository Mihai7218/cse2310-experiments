import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class GraphGenerator {
  public static void main(String[] args) {
    int[] n = {};
    for (int x : n) {
      gg(new String[] {Integer.toString(x), Integer.toString((x*(x-1))/2), Integer.toString(Integer.MAX_VALUE), "1", Integer.toString(x), "node_" + x});
    }
    int[] m = {10, 100, 500, 1000, 5000, 10000, 50000, 100000, 200000, 499500};
    for (int x : m) {
      gg(new String[] {Integer.toString(1000), Integer.toString(x), Integer.toString(Integer.MAX_VALUE), "1", "1000", "edge_" + x});
    }
  }
  public static void gg(String[] args) {
    if (args.length != 6) {
      System.err.println("Usage: java GraphGenerator <n> <m> <c> <s> <t>\n" +
          "  n = number of vertices\n" +
          "  m = number of edges\n" +
          "  c = maximum edge weight\n" +
          "  s = starting vertex\n" +
          "  t = exit vertex");
      System.exit(2);
    }

    int n = Integer.parseInt(args[0]);
    int m = Integer.parseInt(args[1]);
    int c = Integer.parseInt(args[2]);
    int s = Integer.parseInt(args[3]);
    int t = Integer.parseInt(args[4]);

    if (m > n * (n - 1) / 2) {
      System.err.println("Too many edges (or too few nodes)!");
      System.err.println("With " + n + " nodes, at most " + (n * (n - 1) / 2) + " edges are possible.");
      System.exit(2);
    }
    if (s < 1 || s > n) {
      System.err.println("The starting vertex is invalid!");
      System.err.println("It must be between 1 and n (inclusive).");
      System.exit(2);
    }
    if (t < 1 || t > n) {
      System.err.println("The exit vertex is invalid!");
      System.err.println("It must be between 1 and n (inclusive).");
      System.exit(2);
    }

    int[] edgeIndices = new int[n * (n - 1) / 2];
    for (int i = 0; i < edgeIndices.length; i++) {
      edgeIndices[i] = i;
    }
    shuffleArray(edgeIndices);
    Random rnd = new Random();

    try {
      PrintWriter pw  = new PrintWriter(args[5]);
      pw.printf("%d %d %d %d\n", n, m, s, t);
      for (int i = 0; i < m; i++) {
        int row = (int) Math.floor((1 + Math.sqrt(1 + 8 * edgeIndices[i])) / 2.); // Inverse of i = n*(n-1)/2
        int col = edgeIndices[i] - row * (row - 1) / 2;
        pw.printf("%d %d %d\n", n - row, n - col, rnd.nextInt(c));
        pw.flush();
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  // Implementing Fisher–Yates shuffle
  static void shuffleArray(int[] arr) {
    Random rnd = new Random();
    for (int i = arr.length - 1; i > 0; i--) {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      int a = arr[index];
      arr[index] = arr[i];
      arr[i] = a;
    }
  }
}
