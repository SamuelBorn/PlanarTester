package PlanarTester;

import java.util.List;

public class Edge {
    private TreeNode nodeA;
    private TreeNode nodeB;
    private boolean isTreeEdge;

    public Edge(TreeNode nodeA, TreeNode nodeB, boolean isTreeEdge) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.isTreeEdge = isTreeEdge;
    }

    public TreeNode getNodeA() {
        return nodeA;
    }

    public TreeNode getNodeB() {
        return nodeB;
    }

    public boolean isTreeEdge() {
        return isTreeEdge;
    }

    public List<Edge> getBackEdges() {
        List<Edge> backEdges = getNodeB().getNonTreeEdges();
        for (Edge edge : getNodeB().getTreeEdges()) {
            backEdges.addAll(edge.getBackEdges());
        }
        return backEdges;
    }

    @Override
    public String toString() {
        if (isTreeEdge) {
            return "T(" + nodeA.getDFSNumber() + ", " + nodeB.getDFSNumber() + ")";
        }
        return "X(" + nodeA.getDFSNumber() + ", " + nodeB.getDFSNumber() + ")";
    }
}
