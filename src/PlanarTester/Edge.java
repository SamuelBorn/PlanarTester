package PlanarTester;

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

    public void setNodeA(TreeNode nodeA) {
        this.nodeA = nodeA;
    }

    public TreeNode getNodeB() {
        return nodeB;
    }

    public void setNodeB(TreeNode nodeB) {
        this.nodeB = nodeB;
    }

    public boolean isTreeEdge() {
        return isTreeEdge;
    }

    public void setTreeEdge(boolean treeEdge) {
        isTreeEdge = treeEdge;
    }

    @Override
    public String toString() {
        if (isTreeEdge) {
            return "T(" + nodeA.getDFSNumber() + ", " + nodeB.getDFSNumber() + ")";
        }
        return "X(" + nodeA.getDFSNumber() + ", " + nodeB.getDFSNumber() + ")";
    }
}
