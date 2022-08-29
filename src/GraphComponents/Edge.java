package GraphComponents;

import java.awt.*;

public class Edge {
    private Node nodeA;
    private Node nodeB;
    private Color color = Color.BLACK;

    public Edge(Node nodeA, Node nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public Node getNodeA() {
        return nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }

    public boolean hasEndpoint(Node n) {
        return nodeA == n || nodeB == n;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics g) {
        int xa = nodeA.getX();
        int ya = nodeA.getY();
        int xb = nodeB.getX();
        int yb = nodeB.getY();

        g.setColor(color);
        g.drawLine(xa, ya, xb, yb);
    }

    public boolean isUnderCursor(int mx, int my) {
        if (mx < Math.min(nodeA.getX(), nodeB.getX()) ||
                mx > Math.max(nodeA.getX(), nodeB.getX()) ||
                my < Math.min(nodeA.getY(), nodeB.getY()) ||
                my > Math.max(nodeA.getY(), nodeB.getY())) {
            return false;
        }

        int A = nodeB.getY() - nodeA.getY();
        int B = nodeB.getX() - nodeA.getX();

        double distance = Math.abs(A * mx - B * my + nodeB.getX() * nodeA.getY() - nodeB.getY() * nodeA.getX()) / Math.sqrt(A * A + B * B);
        return distance <= 5;
    }
}
