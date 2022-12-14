package PlanarTester;

import java.util.ArrayList;
import java.util.Arrays;
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
        if (!isTreeEdge()) return Arrays.asList(this);

        List<Edge> backEdges = new ArrayList<>(getNodeB().getNonTreeEdges());
        for (Edge edge : getNodeB().getTreeEdges()) {
            backEdges.addAll(edge.getBackEdges());
        }
        return backEdges;
    }

    @Override
    public String toString() {
//        return "" + nodeA.getDFSNumber();
        if (isTreeEdge) {
            return "T(" + nodeA.getDFSNumber() + ", " + nodeB.getDFSNumber() + ")";
        }
        return "X(" + nodeA.getDFSNumber() + ", " + nodeB.getDFSNumber() + ")";
    }
}
