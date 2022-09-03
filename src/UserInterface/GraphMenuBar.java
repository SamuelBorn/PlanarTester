package UserInterface;

import GraphComponents.UIEdge;
import GraphComponents.UIGraph;
import GraphComponents.UINode;
import PlanarTester.Edge;
import PlanarTester.MainPlanarTester;
import PlanarTester.Subroutines.TreeBuilder;
import PlanarTester.Tree;
import PlanarTester.TreeNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class GraphMenuBar extends JMenuBar implements ActionListener {

    private GraphPanel panel;
    private JMenu graphMenu = new JMenu("Load examples  ");
    private JMenuItem loadEmpty = new JMenuItem("Load new empty graph");
    private JMenuItem loadK5 = new JMenuItem("Load K³³");
    private JMenuItem loadK33 = new JMenuItem("Load K⁵");

    private JMenuItem loadExample1 = new JMenuItem("Load example 1");
    private JMenu testPlanar = new JMenu("Check for planarity");
    private JMenuItem testPlanarConfirm = new JMenuItem("Perform check");

    public GraphMenuBar(GraphPanel panel) {
        this.panel = panel;

        graphMenu.add(loadEmpty);
        graphMenu.add(loadK5);
        graphMenu.add(loadK33);
        graphMenu.add(loadExample1);
        this.add(graphMenu);

        testPlanar.add(testPlanarConfirm);
        this.add(testPlanar);

        loadEmpty.addActionListener(this);
        loadK5.addActionListener(this);
        loadK33.addActionListener(this);
        loadExample1.addActionListener(this);

        testPlanarConfirm.addActionListener(this);

        this.setBackground(new Color(238, 238, 238));
        this.setBorderPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(loadEmpty)){
            panel.setGraph(new UIGraph());
            panel.repaint();
        }
        if (e.getSource().equals(loadK5)){

        }
        if (e.getSource().equals(loadK33)){

        }
        if (e.getSource().equals(loadExample1)){
            panel.setGraph(ExampleGraphCreator.getExampleGraph1());
            panel.repaint();
        }
        if (e.getSource().equals(testPlanarConfirm)){
            Tree t = TreeBuilder.buildTree(panel.getGraph());
            Map<Edge, Color> colorMap = MainPlanarTester.testPlanar(t);

            for (Edge nonTreeEdge : t.getNonTreeEdges()) { // color the edges
                UIEdge uiEdge = t.edgeUIEdgeMap.get(nonTreeEdge);
                Color uiEdgeColor = colorMap.get(nonTreeEdge);

                uiEdge.setColor(uiEdgeColor);
            }

            for (TreeNode node : t.getNodes()) { // set name to DFS number
                UINode uiNode = t.treeNodeUINodeMap.get(node);
                String name = node.getDFSNumber() + "";

                uiNode.setName(name);
            }
            panel.repaint();
        }
    }
}
