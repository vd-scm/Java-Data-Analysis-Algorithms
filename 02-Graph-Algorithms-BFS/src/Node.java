import java.util.ArrayList;

// class Node; a node in the graph
public class Node<E> {
    private E value;
    private boolean visited;
    private ArrayList<Integer> edges;

    // non-default constructor; initialize nodes and empty edge list
    public Node(E value) {
        this.value = value;
        this.visited = false;
        this.edges = new ArrayList<>();
    }

    // setter
    public void setValue(E value) {
        this.value = value;
    }

    // getter
    public E getValue() {
        return value;
    }

    // check if node is visited
    public boolean isVisited() {
        return visited;
    }

    // sets node visited status
    public void setVisited(boolean visited) {
        this.visited = visited;

    }

    // add a line edge from a node to another node and store its index
    public void addEdge(int e) {
        edges.add(e);
    }

    // return list of connected nodes
    public ArrayList<Integer> getEdges() {
        return edges;
    }

    public String toString() {
        return "Node{value=" + value + ", visited=" + visited + ", edges=" + edges + "}";
    }
}
