package PlanarTester;

import PlanarTester.Subroutines.TreeBuilder;
import UserInterface.ExampleGraphCreator;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainPlanarTesterTest {
    @Test
    void testMain() {
        Tree t = TreeBuilder.buildTree(ExampleGraphCreator.getK4());
        Map<Edge, Color> x = MainPlanarTester.testPlanar(t);
    }
}