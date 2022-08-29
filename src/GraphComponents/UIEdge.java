package GraphComponents;

import java.awt.*;

public class UIEdge {
    private UINode UINodeA;
    private UINode UINodeB;
    private Color color = Color.BLACK;

    public UIEdge(UINode UINodeA, UINode UINodeB) {
        this.UINodeA = UINodeA;
        this.UINodeB = UINodeB;
    }

    public UINode getNodeA() {
        return UINodeA;
    }

    public UINode getNodeB() {
        return UINodeB;
    }

    public boolean hasEndpoint(UINode n) {
        return UINodeA == n || UINodeB == n;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics g) {
        int xa = UINodeA.getX();
        int ya = UINodeA.getY();
        int xb = UINodeB.getX();
        int yb = UINodeB.getY();

        g.setColor(color);
        g.drawLine(xa, ya, xb, yb);
    }

    public boolean isUnderCursor(int mx, int my) {
        if (mx < Math.min(UINodeA.getX(), UINodeB.getX()) ||
                mx > Math.max(UINodeA.getX(), UINodeB.getX()) ||
                my < Math.min(UINodeA.getY(), UINodeB.getY()) ||
                my > Math.max(UINodeA.getY(), UINodeB.getY())) {
            return false;
        }

        int A = UINodeB.getY() - UINodeA.getY();
        int B = UINodeB.getX() - UINodeA.getX();

        double distance = Math.abs(A * mx - B * my + UINodeB.getX() * UINodeA.getY() - UINodeB.getY() * UINodeA.getX()) / Math.sqrt(A * A + B * B);
        return distance <= 5;
    }
}
