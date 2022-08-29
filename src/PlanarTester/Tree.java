package PlanarTester;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    public ArrayList<TreeNode> nodes = new ArrayList<>();

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