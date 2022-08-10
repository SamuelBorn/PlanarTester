package GraphComponents;

import java.awt.*;

public class Node {
    private int x;
    private int y;
    private int r = 20;
    private String name = "";

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x-r, y-r, r+r, r+r);
        g.setColor(Color.BLACK);
        g.drawOval(x-r, y-r, r+r, r+r);

        FontMetrics fm = g.getFontMetrics();
        int tx = x - fm.stringWidth(name)/2;
        int ty = y - fm.getHeight()/2 + fm.getAscent();
        g.drawString(name, tx, ty);
    }

    public boolean isUnderCursor(int mx, int my) {
        int a = x - mx;
        int b = y - my;
        return a*a + b*b <= r*r;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
