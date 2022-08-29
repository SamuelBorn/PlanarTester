package PlanarTester;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private int DFSNumber;
    private List<Edge> connectedTo = new ArrayList<>();

    public boolean isConnectedTo(TreeNode node) {
        for (Edge edge : connectedTo) {
            if (edge.getNodeB().equals(node)) {
                return true;
            }
        }
        return false;
    }

    public void addTreeEdge(TreeNode nodeB) {
        connectedTo.add(new Edge(this, nodeB, true));
    }

    public void addNonTreeEdge(TreeNode nodeB) {
        connectedTo.add(new Edge(this, nodeB, false));
    }

    public List<TreeNode> getTreeEdgeNodes() {
        return connectedTo.parallelStream().filter(edge -> edge.isTreeEdge()).map(edge -> edge.getNodeB()).toList();
    }

    public List<TreeNode> getNonTreeEdgeNodes() {
        return connectedTo.parallelStream().filter(edge -> !edge.isTreeEdge()).map(edge -> edge.getNodeB()).toList();
    }

    public int getDFSNumber() {
        return DFSNumber;
    }

    public void setDFSNumber(int DFSNumber) {
        this.DFSNumber = DFSNumber;
    }

    @Override
    public String toString() {
        return "#" + DFSNumber + ", con=" + connectedTo + "";
    }
}

