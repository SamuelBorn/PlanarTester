package UserInterface;

import GraphComponents.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
            panel.setGraph(new Graph());
            panel.repaint();
        }
        if (e.getSource().equals(loadK5)){

        }
        if (e.getSource().equals(loadK33)){

        }
        if (e.getSource().equals(loadExample1)){

        }
        if (e.getSource().equals(testPlanarConfirm)){

        }
    }
}
