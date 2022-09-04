package UserInterface;

import GraphComponents.UIEdge;
import GraphComponents.UIGraph;
import GraphComponents.UINode;
import PlanarTester.*;
import PlanarTester.Subroutines.TreeBuilder;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class GraphMenuBar extends JMenuBar implements ActionListener {

    private GraphPanel panel;
    private JMenu graphMenu = new JMenu("Load examples  ");
    private JMenuItem loadEmpty = new JMenuItem("Load new empty graph");
    private JMenuItem loadK5 = new JMenuItem("Load K⁵");
    private JMenuItem loadK4 = new JMenuItem("Load K⁴");

    private JMenuItem loadK33 = new JMenuItem("Load K³³");

    private JMenuItem loadExample1 = new JMenuItem("Load example 1");
    private JMenuItem loadExample2 = new JMenuItem("Load example 2");

    private JMenuItem testPlanar = new JMenuItem("Check for planarity");

    public GraphMenuBar(GraphPanel panel) {
        this.panel = panel;

        graphMenu.add(loadEmpty);
        graphMenu.add(loadK5);
        graphMenu.add(loadK4);
        graphMenu.add(loadK33);
        graphMenu.add(loadExample1);
        graphMenu.add(loadExample2);
        this.add(graphMenu);

        loadEmpty.addActionListener(this);
        loadK5.addActionListener(this);
        loadK4.addActionListener(this);
        loadK33.addActionListener(this);
        loadExample1.addActionListener(this);
        loadExample2.addActionListener(this);

        this.add(testPlanar);
        testPlanar.addActionListener(this);

        this.setBackground(new Color(238, 238, 238));
        this.setBorderPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(loadEmpty)) {
            panel.setGraph(new UIGraph());
            panel.repaint();
        }
        if (e.getSource().equals(loadK5)) {
            panel.setGraph(ExampleGraphCreator.getK5());
            panel.repaint();
        }
        if (e.getSource().equals(loadK4)) {
            panel.setGraph(ExampleGraphCreator.getK4());
            panel.repaint();
        }
        if (e.getSource().equals(loadK33)) {
            panel.setGraph(ExampleGraphCreator.getK33());
            panel.repaint();
        }
        if (e.getSource().equals(loadExample1)) {
            panel.setGraph(ExampleGraphCreator.getExampleGraph2());
            panel.repaint();
        }
        if (e.getSource().equals(loadExample2)) {
            panel.setGraph(ExampleGraphCreator.getExampleGraph1());
            panel.repaint();
        }
        if (e.getSource().equals(testPlanar)) {
            if (panel.getGraph().getNodes().size() == 0) return;
            Tree t = TreeBuilder.buildTree(panel.getGraph());

            for (TreeNode node : t.getNodes()) { // set name to DFS number
                UINode uiNode = t.treeNodeUINodeMap.get(node);
                String name = node.getDFSNumber() + "";
                uiNode.setName(name);
            }
            panel.repaint();

            Map<Edge, Color> colorMap = null;
            try {
                colorMap = MainPlanarTester.testPlanar(t);
            } catch (NotPlanarException ex) {
                JOptionPane.showMessageDialog(this, "The given graph is not planar. \nNo LR bisection could be found.", "Not planar!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            for (Edge nonTreeEdge : t.getNonTreeEdges()) { // color the edges
                UIEdge uiEdge = t.edgeUIEdgeMap.get(nonTreeEdge);
                Color uiEdgeColor = colorMap.get(nonTreeEdge);

                uiEdge.setColor(uiEdgeColor);
            }
            panel.repaint();
        }
    }
}
