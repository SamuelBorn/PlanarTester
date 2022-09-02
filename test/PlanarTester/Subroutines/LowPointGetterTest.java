package PlanarTester.Subroutines;

import PlanarTester.Subroutines.LowPointGetter;
import PlanarTester.Subroutines.TreeBuilder;
import PlanarTester.Tree;
import UserInterface.ExampleGraphCreator;
import org.junit.jupiter.api.Test;

class LowPointGetterTest {
    @Test
    void testLowPoints() {
        Tree x = TreeBuilder.buildTree(ExampleGraphCreator.getExampleGraph1());
        System.out.println(x);
        System.out.println(x.getEdgeList());
        System.out.println(LowPointGetter.getLowPoints(x.getEdgeList()));
    }
}