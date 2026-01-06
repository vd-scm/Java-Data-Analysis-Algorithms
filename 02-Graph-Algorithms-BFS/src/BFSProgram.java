//
// Project: Breadth First Traversal
// Author: Vince David M. Muego
//

import java.util.*;

// BFS traversal on graph; create two graphs: 1- character nodes 2- city names
// starting from different nodes and prints traversal order. BFS visits each nodes and all neighbors
public class BFSProgram {
    public static void main(String[] args) {
        Character[] values1 = {'S', 'A', 'B', 'C', 'D'};
        int[][] edges1 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {2, 4}, {3, 4}};
        String[] values2 = {"Seattle", "San Francisco", "Los Angeles",
                "Denver", "Kansas City", "Chicago", "Boston", "New York",
                "Atlanta", "Miami", "Dallas", "Houston"};
        int[][] edges2 =  {
                {0, 1}, {0, 3}, {0, 5},
                {1, 0}, {1, 2}, {1, 3},
                {2, 1}, {2, 3}, {2, 4}, {2, 10},
                {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
                {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
                {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
                {6, 5}, {6, 7},
                {7, 4}, {7, 5}, {7, 6}, {7, 8},
                {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
                {9, 8}, {9, 11},
                {10, 2}, {10, 4}, {10, 8}, {10, 11},
                {11, 8}, {11, 9}, {11, 10}
        };
        Graph<Character> g1 = new Graph<>(values1, edges1);
        Graph<String> g2 = new Graph<>(values2, edges2);
        System.out.println(g1.breadthFirstSearchOrder(0));
        System.out.println(g1.breadthFirstSearchOrder(4));
        System.out.println(g1.breadthFirstSearchOrder(3));
        System.out.println(g2.breadthFirstSearchOrder(0));
        System.out.println(g2.breadthFirstSearchOrder(5));
        System.out.println(g2.breadthFirstSearchOrder(9));
    }
}

/*
[paste output here]
[S, A, B, C, D]
[D, A, B, C, S]
[C, S, D, A, B]
[Seattle, San Francisco, Denver, Chicago, Los Angeles, Kansas City, Boston, New York, Dallas, Atlanta, Houston, Miami]
[Chicago, Seattle, Denver, Kansas City, Boston, New York, San Francisco, Los Angeles, Atlanta, Dallas, Miami, Houston]
[Miami, Atlanta, Houston, Kansas City, New York, Dallas, Los Angeles, Denver, Chicago, Boston, San Francisco, Seattle]

Process finished with exit code 0
*/