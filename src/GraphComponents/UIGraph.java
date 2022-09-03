package GraphComponents;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIGraph {
    private final List<UINode> UINodes;
    private final List<UIEdge> edges;

    public UIGraph() {
        this.UINodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public List<UINode> getNodes() {
        return UINodes;
    }

    public List<UIEdge> getEdges() {
        return edges;
    }

    public void addNode(UINode n) {
        UINodes.add(n);
    }

    public void addEdge(UIEdge e) {
        edges.add(e);
    }

    public void removeNode(UINode n) {
        removeAttachedEdges(n);
        UINodes.remove(n);
    }

    public void removeAttachedEdges(UINode n) {
        edges.removeIf(edge -> n.equals(edge.getNodeA()) || n.equals(edge.getNodeB()));
    }

    public void removeEdge(UIEdge e) {
        edges.remove(e);
    }

    public boolean isEdge(UINode UINodeA, UINode UINodeB){
        return edges.stream().anyMatch(edge -> edge.hasEndpoint(UINodeA) && edge.hasEndpoint(UINodeB));
    }

    public List<UINode> getAdjacentNodes(UINode n) {
        List<UINode> UINodes = new ArrayList<>();
        for (UIEdge edge : edges) {
            if (n.equals(edge.getNodeA())) {
                UINodes.add(edge.getNodeB());
            } else if (n.equals(edge.getNodeB())) {
                UINodes.add(edge.getNodeA());
            }
        }
        return UINodes;
    }

    public UINode findNodeUnderCursor(int mx, int my) {
        for (UINode UINode : UINodes) {
            if (UINode.isUnderCursor(mx, my)) {
                return UINode;
            }
        }
        return null;
    }

    public UIEdge findEdgeUnderCursor(int mx, int my) {
        for (UIEdge edge : edges) {
            if (edge.isUnderCursor(mx, my)) {
                return edge;
            }
        }
        return null;
    }

    public void moveGraph(int dx, int dy) {
        for (UINode UINode : UINodes) {
            UINode.move(dx, dy);
        }
    }

    public void draw(Graphics g) {
        for (UIEdge edge : getEdges()) {
            edge.draw(g);
        }

        for (UINode UINode : getNodes()) {
            UINode.draw(g);
        }
    }

    @Override
    public String toString() {
        return "UIGraph{" +
                "edges=" + edges +
                '}';
    }
}
