package PlanarTester;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private ArrayList<TreeNode> nodes = new ArrayList<>();

    public ArrayList<TreeNode> getNodes() {
        return nodes;
    }

    public TreeNode getRoot() {
        return nodes.get(0);
    }

    public void addNode(TreeNode n) {
        nodes.add(n);
    }

    public List<Edge> getEdgeList() {
        List<Edge> edges = new ArrayList<>();
        for (TreeNode node : nodes) {
            edges.addAll(node.getEdges());
        }
        return edges;
    }

    @Override
    public String toString() {
        String ret = "Tree: \n";
        for (TreeNode tn : nodes) {
            ret += tn.toString() + "\n";
        }
        return ret;
    }
}
