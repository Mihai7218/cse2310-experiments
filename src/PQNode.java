public class PQNode implements Comparable<PQNode> {
    int node;
    int weight;

    public PQNode(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(PQNode other) {
        int blah = this.weight - other.weight;
        if (blah == 0) {
            return this.node - other.node;
        }
        return blah;
    }
}
