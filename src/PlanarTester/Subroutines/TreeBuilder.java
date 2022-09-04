package PlanarTester.Subroutines;

import GraphComponents.UIGraph;
import GraphComponents.UINode;
import PlanarTester.Tree;
import PlanarTester.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeBuilder {
    private static int depth;  // global dfs number, needed for recursion

    public static Tree buildTree(UIGraph g) {
        List<UINode> discovered = new ArrayList<>();
        depth = 0;
        Map<UINode, TreeNode> map = new HashMap<>();
        Tree tree = new Tree();
        for (UINode UINode : g.getNodes()) {
            TreeNode tn = new TreeNode();
            tree.addNode(tn);
            map.put(UINode, tn);
        }
        for (UINode node : g.getNodes()) {
            if (discovered.contains(node)) {
                continue;
            }
            DFS(g, map, discovered, node);
        }
        tree.setMapping(map, g);
        return tree;
    }

    public static void DFS(UIGraph g, Map<UINode, TreeNode> map, List<UINode> discovered, UINode current) {
        discovered.add(current);
        TreeNode currentTN = map.get(current);
        currentTN.setDFSNumber(depth++);

        for (UINode adjacent : g.getAdjacentNodes(current)) {
            TreeNode adjacentTN = map.get(adjacent);

            if (adjacentTN.isConnectedTo(currentTN))
                continue;  // isConnectedTo operates on the tree built so far → don't walk back

            if (!discovered.contains(adjacent)) {
                currentTN.addTreeEdge(adjacentTN);
                DFS(g, map, discovered, adjacent);
            } else {
                currentTN.addNonTreeEdge(adjacentTN);
            }
        }
    }
}

