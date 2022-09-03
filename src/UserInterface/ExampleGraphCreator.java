package UserInterface;

import GraphComponents.UIEdge;
import GraphComponents.UIGraph;
import GraphComponents.UINode;

public class ExampleGraphCreator {
    public static UIGraph getExampleGraph1(){
        UIGraph g = new UIGraph();
        UINode n0 = new UINode(100,100);
        UINode n1 = new UINode(100, 200);
        UINode n2 = new UINode(100, 300);
        UINode n3 = new UINode(200, 200);
        UINode n4 = new UINode(200, 300);
        UINode n5 = new UINode(300, 300);
//        n0.setName("0");
//        n1.setName("1");
//        n2.setName("2");
//        n3.setName("4");
//        n4.setName("3");
//        n5.setName("5");
        g.addNode(n0);
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);

        g.addEdge(new UIEdge(n0,n1));
        g.addEdge(new UIEdge(n1,n2));
        g.addEdge(new UIEdge(n1,n4));
        g.addEdge(new UIEdge(n0, n3));
        g.addEdge(new UIEdge(n3, n5));
        g.addEdge(new UIEdge(n4,n3));
        return g;
    }
}
