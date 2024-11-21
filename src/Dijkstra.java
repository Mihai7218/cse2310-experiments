import java.util.*;


class Dijkstra {

    /**
     * @param n the number of nodes
     * @param m the number of edges
     * @param s the starting node (1 <= s <= n)
     * @param t the ending node (1 <= t <= n)
     * @param nodes the set of n nodes in the graph.
     * @param edges the set of edges of the graph, with endpoints labelled between 1 and n
     *     inclusive.
     * @return the time required to get out, or -1 if it is not possible to get out.
     */
    public static int getMeOuttaHere(int n, int m, int s, int t, Set<Node> nodes, Set<Edge> edges) {
        List<Node> nodeList = new ArrayList<>(nodes);
        int oo = 2000000000;
        Map<Integer, Integer> map = new HashMap<>(n);

        for (int i = 1; i <= n; i++) {
            map.put(i, oo);
        }
        map.put(s, 0);

        PriorityQueue<PQNode> pq = new PriorityQueue<>(n);

        pq.add(new PQNode(s, 0));

        while (!pq.isEmpty()) {
            PQNode currentPQ = pq.poll();
            Node current = nodeList.get(currentPQ.node-1);
            int w = currentPQ.weight;
            // if (current.id == t) break;
            if (current.marked) continue;
            current.marked = true;
            for (Edge outEdge : current.outgoingEdges) {
                Node next = nodeList.get(outEdge.to-1);
                int edgeWeight = w + outEdge.weight + nodeList.get(outEdge.from-1).outgoingEdges.size();
                if (map.get(next.id) > edgeWeight) {
                    map.put(next.id, edgeWeight);
                    pq.add(new PQNode(next.id, edgeWeight));
                }
            }
        }

        if (map.get(t) == oo)
            return -1;
        return map.get(t);
    }
}
