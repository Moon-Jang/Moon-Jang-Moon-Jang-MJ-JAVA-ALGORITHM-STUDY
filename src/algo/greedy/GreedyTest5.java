package algo.greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class GreedyTest5 {

    public void run() {
        final int n1 = 5;
        final int n2 = 5;
        final int n3 = 4;
        final int n4 = 5;
        final int n5 = 6;
        final int n6 = 5;
        final int n7 = 5;
        final int n8 = 5;
        final int n9 = 5;
         /*
        1.5 [[0, 1, 5], [1, 2, 3], [2, 3, 3], [3, 1, 2], [3, 0, 4], [2, 4, 6], [4, 0, 7]] 15
        2.5 [[0, 1, 1], [3, 4, 1], [1, 2, 2], [2, 3, 4]] 8
        3.4 [[0, 1, 5], [1, 2, 3], [2, 3, 3], [1, 3, 2], [0, 3, 4]] 9
        4.5 [[0, 1, 1], [3, 1, 1], [0, 2, 2], [0, 3, 2], [0, 4, 100]] 104
        5.6 [[0, 1, 5], [0, 3, 2], [0, 4, 3], [1, 4, 1], [3, 4, 10], [1, 2, 2], [2, 5, 3], [4, 5, 4]] 11
        6.5 [[0, 1, 1], [2, 3, 1], [3, 4, 2], [1, 2, 2], [0, 4, 100]] 6
        7.5 [[0, 1, 1], [0, 4, 5], [2, 4, 1], [2, 3, 1], [3, 4, 1]] 8
        8.5 [[0, 1, 1], [0, 2, 2], [0, 3, 3], [0, 4, 4], [1, 3, 1]] 8
        */
        final int[][] cost1 = {{0, 1, 5}, {1, 2, 3}, {2, 3, 3}, {3, 1, 2}, {3, 0, 4}, {2, 4, 6}, {4, 0, 7}};
        final int[][] cost3 = {{0, 1, 5}, {1, 2, 3}, {2, 3, 3}, {1, 3, 2}, {0, 3, 4}};
        final int[][] cost4 = {{0, 1, 1}, {3, 1, 1}, {0, 2, 2}, {0, 3, 2}, {0, 4, 100}};
        final int[][] cost8 = {{0, 1, 1}, {0, 2, 2}, {0, 3, 3}, {0, 4, 4}, {1, 3, 1}};
        final int[][] cost9 = {{0, 1, 1}, {2, 3, 1}, {3, 4, 2}, {1, 2, 2}, {0, 4, 100}};
        int result = solution(n1, cost1);
        System.out.println("result: " + result);
    }


    public int solution(int n, int[][] costs) {
        Graph graph = new Graph(n, costs);
        Set<Node> nodes = graph.getNodes();
        graph.connectAll();
        return graph.getTotalWeight();
    }

    class Graph {
        private Set<Node> nodes;
        private List<Edge> edges;
        private int totalWeight;

        public Graph(int size, int[][] costs) {
            this.nodes = new HashSet<>();
            this.totalWeight = 0;

            for (int i = 0; i < size; i++) {
                this.nodes.add(new Node(i));
            }

            this.edges = Arrays.stream(costs).map(m -> new Edge(this.nodes, m[0], m[1], m[2]))
                    .collect(Collectors.toList());
            this.edges.sort((a, b) -> a.compareTo(b));
        }

        public void connectAll() {
            final int successCnt = nodes.size() - 1;
            int cnt = 0;

            for (Edge edge : edges) {
                if (successCnt == cnt)
                    break;
                boolean connectResult = connect(edge.getA(), edge.getB());
                if (!connectResult)
                    continue;
                totalWeight += edge.getWeight();
                cnt++;
            }
        }

        private boolean connect(Node a, Node b) {
            Node parentOfA = findParent(a);
            Node parentOfB = findParent(b);

            if (parentOfA == parentOfB) {
                return false;
            }
            if (parentOfB.getIdx() > parentOfA.getIdx()) {
                parentOfB.setParent(parentOfA);
                return true;
            }

            parentOfA.setParent(parentOfB);
            return true;
        }

        private Node findParent(Node node) {
            if (node.hasParent()) {
                node.setParent(findParent(node.getParent()));
                return node.getParent();
            }
            return node.getParent();
        }

        public Set<Node> getNodes() {
            return nodes;
        }

        public int getTotalWeight() {
            return totalWeight;
        }

        public Node getNode(int idx) {
            for (Node node : nodes) {
                if (node.getIdx() == idx) {
                    return node;
                }
            }
            return null;
        }


    }

    class Node {
        private Node parent;
        private final int idx;

        public Node(int idx) {
            this.idx = idx;
            this.parent = this;
        }

        public Node getParent() {
            return parent;
        }

        public int getIdx() {
            return idx;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        private boolean hasParent() {
            if (parent == this) {
                return false;
            }
            return true;
        }

    }

    class Edge implements Comparable<Edge>{
        private final Node a;
        private final Node b;
        private final int weight;

        public Edge(Set<Node> nodes, int i, int j, int weight) {
            this.a = getNode(nodes, i);
            this.b = getNode(nodes, j);
            this.weight = weight;
        }

        public Node getNode(Set<Node> nodes, int idx) {
            for (Node node : nodes) {
                if (node.getIdx() == idx) {
                    return node;
                }
            }
            return null;
        }

        public Node getA() {
            return a;
        }

        public Node getB() {
            return b;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.getWeight();
        }
    }
}
