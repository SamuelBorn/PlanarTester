package PlanarTester;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private int DFSNumber;
    private List<Edge> edges = new ArrayList<>();

    public TreeNode() {}

    public TreeNode(int DFSNumber) {
        this.DFSNumber = DFSNumber;
    }

    public boolean isConnectedTo(TreeNode node) {
        for (Edge edge : edges) {
            if (edge.getNodeB().equals(node)) {
                return true;
            }
        }
        return false;
    }

    public void addTreeEdge(TreeNode nodeB) {
        edges.add(new Edge(this, nodeB, true));
    }

    public void addNonTreeEdge(TreeNode nodeB) {
        edges.add(new Edge(this, nodeB, false));
    }

    public List<Edge> getTreeEdges() {
        return edges.parallelStream().filter(edge -> edge.isTreeEdge()).toList();
    }

    public List<Edge> getNonTreeEdges() {
        return edges.parallelStream().filter(edge -> !edge.isTreeEdge()).toList();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getDFSNumber() {
        return DFSNumber;
    }

    public void setDFSNumber(int DFSNumber) {
        this.DFSNumber = DFSNumber;
    }

    @Override
    public String toString() {
        return "#" + DFSNumber + ", con=" + edges + "";
    }
}

