package UserInterface;

import GraphComponents.UIEdge;
import GraphComponents.Graph;
import GraphComponents.UINode;

public class ExampleGraphCreator {
    public static Graph getExampleGraph1(){
        Graph g = new Graph();
        UINode n1 = new UINode(100,100);
        UINode n2 = new UINode(100, 200);
        UINode n3 = new UINode(100, 300);
        UINode n4 = new UINode(200, 200);
        UINode n5 = new UINode(200, 300);
        UINode n6 = new UINode(300, 300);
        n1.setName("1");
        n2.setName("2");
        n3.setName("3");
        n4.setName("4");
        n5.setName("5");
        n6.setName("6");
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);
        g.addNode(n6);

        g.addEdge(new UIEdge(n1,n2));
        g.addEdge(new UIEdge(n2,n3));
        g.addEdge(new UIEdge(n2,n5));
        g.addEdge(new UIEdge(n1, n4));
        g.addEdge(new UIEdge(n4, n6));
        g.addEdge(new UIEdge(n5,n4));
        return g;
    }
}
