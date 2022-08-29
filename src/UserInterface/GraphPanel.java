package UserInterface;

import GraphComponents.Graph;
import GraphComponents.UINode;
import GraphComponents.UIEdge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    private Graph graph;
    private boolean mouseLeftButton = false;
    private boolean mouseRightButton = false;
    private int mouseX;
    private int mouseY;
    private UINode UINodeUnderCursor;
    private UIEdge edgeUnderCursor;
    private UINode edgeUINodeA;

    public GraphPanel(Graph graph) {
        this.graph = graph;

        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);

        setFocusable(true);
        requestFocus();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graph.draw(g);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mouseRightButton) {
            moveGraphDrag(e.getX(), e.getY());
        } else {
            setMouseCursor(e);
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        mouseLeftButton = e.getButton() == MouseEvent.BUTTON1;
        mouseRightButton = e.getButton() == MouseEvent.BUTTON3;
        setMouseCursor(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseLeftButton = false;
            if (UINodeUnderCursor == null && edgeUnderCursor == null) {
                graph.addNode(new UINode(e.getX(), e.getY()));
                edgeUINodeA = null;
            } else if (UINodeUnderCursor != null && edgeUINodeA == null) {
                edgeUINodeA = UINodeUnderCursor;
            } else if (UINodeUnderCursor != null && edgeUINodeA != null && edgeUINodeA != UINodeUnderCursor) {
                if (!graph.isEdge(UINodeUnderCursor, edgeUINodeA)) {
                    graph.addEdge(new UIEdge(UINodeUnderCursor, edgeUINodeA));
                }
                edgeUINodeA = null;
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            mouseRightButton = false;
        }

        setMouseCursor(e);
        repaint();
    }


    private void moveGraphDrag(int mx, int my) {
        int dx = mx - mouseX;
        int dy = my - mouseY;

        if (UINodeUnderCursor != null) {
            UINodeUnderCursor.move(dx, dy);
        } else {
            graph.moveGraph(dx, dy);
        }

        mouseX = mx;
        mouseY = my;
        repaint();
    }

    public void setMouseCursor(MouseEvent e) {
        int mouseCursor;
        if (mouseRightButton) {
            mouseCursor = Cursor.MOVE_CURSOR;
        } else if (edgeUINodeA != null) {
            mouseCursor = Cursor.WAIT_CURSOR;
        } else {
            mouseCursor = Cursor.DEFAULT_CURSOR;
        }
        setCursor(Cursor.getPredefinedCursor(mouseCursor));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (e != null) {
            UINodeUnderCursor = graph.findNodeUnderCursor(e.getX(), e.getY());
            if (UINodeUnderCursor == null) {
                edgeUnderCursor = graph.findEdgeUnderCursor(e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'x') {
            if (UINodeUnderCursor != null) {
                graph.removeNode(UINodeUnderCursor);
            } else if (edgeUnderCursor != null) {
                graph.removeEdge(edgeUnderCursor);
            }
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setGraph(Graph graph){
        this.graph = graph;
    }
}
