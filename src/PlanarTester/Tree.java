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


    @Override
    public String toString() {
        String ret = "Tree: \n";
        for (TreeNode tn : nodes) {
            ret += tn.toString() + "\n";
        }
        return ret;
    }
}
