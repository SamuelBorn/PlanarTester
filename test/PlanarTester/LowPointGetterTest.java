package PlanarTester;

import UserInterface.ExampleGraphCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LowPointGetterTest {
    @Test
    void testLowPoints() {
        Tree x = TreeBuilder.buildTree(ExampleGraphCreator.getExampleGraph1());
        System.out.println(x);
        System.out.println(x.getEdgeList());
        System.out.println(LowPointGetter.getLowPoints(x.getEdgeList()));
    }
}