package PlanarTester;

import GraphComponents.UIEdge;
import GraphComponents.UIGraph;
import GraphComponents.UINode;

import java.util.*;

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

    // needed for coloring the edges and setting the dfs index
    public Map<TreeNode, UINode> treeNodeUINodeMap;
    public Map<Edge, UIEdge> edgeUIEdgeMap;

    public List<Edge> getEdgeList() {
        List<Edge> edges = new ArrayList<>();
        for (TreeNode node : nodes) {
            edges.addAll(node.getEdges());
        }
        return edges;
    }

    public List<Edge> getNonTreeEdges() {
        return getEdgeList().stream().filter(edge -> !edge.isTreeEdge()).toList();
    }

    public void setMapping(Map<UINode, TreeNode> uiNodeTreeNodeMap, UIGraph uiGraph) {
        treeNodeUINodeMap = new HashMap<>();
        for (Map.Entry<UINode, TreeNode> uiNodeTreeNodeEntry : uiNodeTreeNodeMap.entrySet()) {
            treeNodeUINodeMap.put(uiNodeTreeNodeEntry.getValue(), uiNodeTreeNodeEntry.getKey());
        }

        edgeUIEdgeMap = new HashMap<>();
        for (Edge edge : getEdgeList()) {
            UINode nodeA = treeNodeUINodeMap.get(edge.getNodeA());
            UINode nodeB = treeNodeUINodeMap.get(edge.getNodeB());

            Optional<UIEdge> e = uiGraph
                    .getEdges()
                    .stream()
                    .filter(uiE -> (uiE.getNodeA() == nodeA && uiE.getNodeB() == nodeB) ||(uiE.getNodeA() == nodeB && uiE.getNodeB() == nodeA) )
                    .findFirst();

            edgeUIEdgeMap.put(edge, e.get());
        }
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
