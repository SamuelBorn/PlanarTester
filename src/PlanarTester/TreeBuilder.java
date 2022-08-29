package PlanarTester;

import GraphComponents.Graph;
import GraphComponents.Node;
import UserInterface.ExampleGraphCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeBuilder {
    private static int depth;  // global dfs number, needed for recursion

    public static Tree buildTree(Graph g) {
        List<Node> discovered = new ArrayList<>();
        depth = 0;
        Map<Node, TreeNode> map = new HashMap<>();
        Tree tree = new Tree();
        for (Node node : g.getNodes()) {
            TreeNode tn = new TreeNode();
            tree.addNode(tn);
            map.put(node, tn);
        }
        DFS(g, map, discovered, g.getNodes().get(0));
        return tree;
    }

    public static void DFS(Graph g, Map<Node, TreeNode> map, List<Node> discovered, Node current) {
        discovered.add(current);
        TreeNode currentTN = map.get(current);
        currentTN.setDFSNumber(depth++);

        for (Node adjacent : g.getAdjacentNodes(current)) {
            TreeNode adjacentTN = map.get(adjacent);

            if (adjacentTN.isConnectedTo(currentTN)) continue;  // don't walk back

            if (!discovered.contains(adjacent)) {
                currentTN.addTreeEdge(adjacentTN);
                DFS(g, map, discovered, adjacent);
            } else {
                currentTN.addNonTreeEdge(adjacentTN);
            }
        }
    }

    public static void main(String[] args) {
        Tree x = TreeBuilder.buildTree(ExampleGraphCreator.getExampleGraph1());
        System.out.println(x);
    }
}

