package PlanarTester;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private int DFSNumber;
    private List<Edge> connectedTo = new ArrayList<>();

    public boolean isConnectedTo(TreeNode node) {
        for (Edge edge : connectedTo) {
            if (edge.nodeB.equals(node)) {
                return true;
            }
        }
        return false;
    }

    public void addTreeEdge(TreeNode nodeB) {
        connectedTo.add(new Edge(nodeB, true));
    }

    public void addNonTreeEdge(TreeNode nodeB) {
        connectedTo.add(new Edge(nodeB, false));
    }

    public List<TreeNode> getTreeEdgeNodes() {
        return connectedTo.parallelStream().filter(edge -> edge.isTreeEdge).map(edge -> edge.nodeB).toList();
    }

    public List<TreeNode> getNonTreeEdgeNodes() {
        return connectedTo.parallelStream().filter(edge -> !edge.isTreeEdge).map(edge -> edge.nodeB).toList();
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

    public class Edge {
        public TreeNode nodeB;
        public boolean isTreeEdge;

        public Edge(TreeNode nodeB, boolean isTreeEdge) {
            this.nodeB = nodeB;
            this.isTreeEdge = isTreeEdge;
        }

        @Override
        public String toString() {
            if (isTreeEdge) {
                return "t:" + nodeB.DFSNumber;
            }
            return "ø:" + nodeB.DFSNumber;
        }
    }

}

