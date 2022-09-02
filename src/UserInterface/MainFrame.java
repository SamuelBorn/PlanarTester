package UserInterface;

import GraphComponents.UIGraph;

import javax.swing.*;

public class MainFrame extends JFrame {
    private GraphPanel graphPanel = new GraphPanel(new UIGraph());
    private GraphMenuBar graphMenuBar = new GraphMenuBar(graphPanel);


    public static void main(String[] args) {
        new MainFrame();
    }

    private MainFrame() {
        super("PlanarTester");
        setSize(800, 600);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(graphPanel);
        setJMenuBar(graphMenuBar);

        setVisible(true);
    }
}
