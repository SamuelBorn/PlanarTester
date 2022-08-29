package GraphComponents;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> nodes;
    private List<Edge> edges;

    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addNode(Node n) {
        nodes.add(n);
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public void removeNode(Node n) {
        removeAttachedEdges(n);
        nodes.remove(n);
    }

    public void removeAttachedEdges(Node n) {
        edges.removeIf(edge -> n.equals(edge.getNodeA()) || n.equals(edge.getNodeB()));
    }

    public void removeEdge(Edge e) {
        edges.remove(e);
    }

    public boolean isEdge(Node nodeA, Node nodeB){
        return edges.stream().filter(edge -> edge.hasEndpoint(nodeA) && edge.hasEndpoint(nodeB)).findAny().isPresent();
    }

    public List<Node> getAdjacentNodes(Node n) {
        List<Node> nodes = new ArrayList<>();
        for (Edge edge : edges) {
            if (n.equals(edge.getNodeA())) {
                nodes.add(edge.getNodeB());
            } else if (n.equals(edge.getNodeB())) {
                nodes.add(edge.getNodeA());
            }
        }
        return nodes;
    }

    public Node findNodeUnderCursor(int mx, int my) {
        for (Node node : nodes) {
            if (node.isUnderCursor(mx, my)) {
                return node;
            }
        }
        return null;
    }

    public Edge findEdgeUnderCursor(int mx, int my) {
        for (Edge edge : edges) {
            if (edge.isUnderCursor(mx, my)) {
                return edge;
            }
        }
        return null;
    }

    public void moveGraph(int dx, int dy) {
        for (Node node : nodes) {
            node.move(dx, dy);
        }
    }

    public void draw(Graphics g) {
        for (Edge edge : getEdges()) {
            edge.draw(g);
        }

        for (Node node : getNodes()) {
            node.draw(g);
        }
    }
}
