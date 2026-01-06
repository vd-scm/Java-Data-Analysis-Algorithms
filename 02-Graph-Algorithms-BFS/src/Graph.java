import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// class Graph implements undirected graph with BFS traversal
public class Graph<E> {
    private Node<E>[] nodes;

    // non-default constructor; initialize graph with given values and edges
    public Graph(E[] values, int[][] edges) {
        nodes = new Node[values.length];
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new Node<>(values[i]);
        }
        for (int[] edge : edges) {
            nodes[edge[0]].addEdge(edge[1]);
            nodes[edge[1]].addEdge(edge[0]); // undirected
        }
    }

    // BFS traversal from given node index
    public LinkedList<E> breadthFirstSearchOrder(int root) {
        LinkedList<E> result = new LinkedList<>(); // traversal order
        Queue<Integer> queue = new LinkedList<>(); // BFS traversal queue

        queue.add(root);
        nodes[root].setVisited(true);

        while (!queue.isEmpty()) {
            int v = queue.poll(); // remove and return first element in queue
            result.add(nodes[v].getValue()); // store node's value in result

            // traverse all neighboring nodes
            for (int neighbor : nodes[v].getEdges()) {
                if (!nodes[neighbor].isVisited()) { // visits unvisited nodes
                    queue.add(neighbor);
                    nodes[neighbor].setVisited(true);
                }
            }
        }

        // reset visited status to all nodes
        for (Node<E> node : nodes) {
            node.setVisited(false);
        }
        return result;
    }

    public String toString() {
        return " Graph{nodes=" + Arrays.toString(nodes) + "}";
    }
}