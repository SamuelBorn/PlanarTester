package UserInterface;

import GraphComponents.Edge;
import GraphComponents.Graph;
import GraphComponents.Node;

import java.util.List;

public class ExampleGraphCreator {
    public static Graph getExampleGraph1(){
        Graph g = new Graph();
        Node n1 = new Node(100,100);
        Node n2 = new Node(100, 200);
        Node n3 = new Node(100, 300);
        Node n4 = new Node(200, 200);
        Node n5 = new Node(200, 300);
        Node n6 = new Node(300, 300);
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);
        g.addNode(n6);

        g.addEdge(new Edge(n1,n2));
        g.addEdge(new Edge(n2,n3));
        g.addEdge(new Edge(n2,n5));
        g.addEdge(new Edge(n1, n4));
        g.addEdge(new Edge(n4, n6));
        g.addEdge(new Edge(n5,n4));
        return g;
    }
}
