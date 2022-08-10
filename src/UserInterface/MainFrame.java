package UserInterface;

import GraphComponents.Graph;

import javax.swing.*;

public class MainFrame extends JFrame {
    GraphPanel graphPanel = new GraphPanel(new Graph());

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

        //addActionListeners();
        //createMenuBar();

        setVisible(true);

        //showInstruction();

        //graphPanel.deserializeGraph(AUTOSAVE_FILE);

    }
}
