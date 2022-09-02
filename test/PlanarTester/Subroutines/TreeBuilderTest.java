package PlanarTester.Subroutines;

import PlanarTester.Subroutines.TreeBuilder;
import PlanarTester.Tree;
import UserInterface.ExampleGraphCreator;
import org.junit.jupiter.api.Test;

class TreeBuilderTest {
    @Test
    void testPrint() {
        Tree x = TreeBuilder.buildTree(ExampleGraphCreator.getExampleGraph1());
        System.out.println(x);
        System.out.println(x.getEdgeList());
    }
}