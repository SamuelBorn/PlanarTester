package UserInterface;

import GraphComponents.UIEdge;
import GraphComponents.UIGraph;
import GraphComponents.UINode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ExampleGraphCreator {
    public static UIGraph getExampleGraph1() {
        UIGraph g = new UIGraph();
        UINode n0 = new UINode(100, 100);
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

        g.addEdge(new UIEdge(n0, n1));
        g.addEdge(new UIEdge(n1, n2));
        g.addEdge(new UIEdge(n1, n4));
        g.addEdge(new UIEdge(n0, n3));
        g.addEdge(new UIEdge(n3, n5));
        g.addEdge(new UIEdge(n4, n3));
        return g;
    }

    public static UIGraph getK5() {
        UIGraph g = new UIGraph();
        UINode n0 = new UINode(300, 100);
        UINode n1 = new UINode(100, 250);
        UINode n2 = new UINode(500, 250);
        UINode n3 = new UINode(200, 450);
        UINode n4 = new UINode(400, 450);
        List<UINode> nodes = Arrays.asList(n0, n1, n2, n3, n4);

        for (UINode node : nodes) {
            g.addNode(node);
        }

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                g.addEdge(new UIEdge(nodes.get(i), nodes.get(j)));
            }
        }

        return g;
    }

    public static UIGraph getK4() {
        UIGraph g = new UIGraph();
        UINode n0 = new UINode(100, 100);
        UINode n1 = new UINode(300, 100);
        UINode n2 = new UINode(100, 300);
        UINode n3 = new UINode(300, 300);
        n0.setName("0");
        n1.setName("1");
        n2.setName("2");
        n3.setName("3");

        List<UINode> nodes = Arrays.asList(n0, n1, n2, n3);

        for (UINode node : nodes) {
            g.addNode(node);
        }

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                g.addEdge(new UIEdge(nodes.get(i), nodes.get(j)));
            }
        }

        return g;
    }

    public static UIGraph getK33() {
        UIGraph g = new UIGraph();
        List<UINode> left = Arrays.asList(
                new UINode(100, 100),
                new UINode(100, 200),
                new UINode(100, 300));

        List<UINode> right = Arrays.asList(
                new UINode(300, 100),
                new UINode(300, 200),
                new UINode(300, 300));

        Stream.concat(left.stream(), right.stream()).forEach(g::addNode);

        for (UINode a : left) {
            for (UINode b : right) {
                g.addEdge(new UIEdge(a, b));
            }
        }
        return g;
    }

    public static UIGraph getExampleGraph2() {
        List<UINode> nodes = Arrays.asList(
                new UINode(372, 450),
                new UINode(367, 357),
                new UINode(437, 298),
                new UINode(439, 219),
                new UINode(350, 150),
                new UINode(272, 259),
                new UINode(483, 116),
                new UINode(397, 78),
                new UINode(257, 46),
                new UINode(249, 135),
                new UINode(81, 134),
                new UINode(530, 32),
                new UINode(725, 140),
                new UINode(596, 174)
        );

        List<UIEdge> edges = Arrays.asList(
                new UIEdge(nodes.get(1), nodes.get(0)),
                new UIEdge(nodes.get(2), nodes.get(1)),
                new UIEdge(nodes.get(3), nodes.get(2)),
                new UIEdge(nodes.get(4), nodes.get(3)),
                new UIEdge(nodes.get(5), nodes.get(4)),
                new UIEdge(nodes.get(6), nodes.get(4)),
                new UIEdge(nodes.get(7), nodes.get(4)),
                new UIEdge(nodes.get(8), nodes.get(7)),
                new UIEdge(nodes.get(9), nodes.get(8)),
                new UIEdge(nodes.get(10), nodes.get(9)),
                new UIEdge(nodes.get(11), nodes.get(7)),
                new UIEdge(nodes.get(12), nodes.get(11)),
                new UIEdge(nodes.get(13), nodes.get(12)),
                new UIEdge(nodes.get(3), nodes.get(6)),
                new UIEdge(nodes.get(2), nodes.get(13)),
                new UIEdge(nodes.get(11), nodes.get(13)),
                new UIEdge(nodes.get(0), nodes.get(12)),
                new UIEdge(nodes.get(1), nodes.get(5)),
                new UIEdge(nodes.get(4), nodes.get(9)),
                new UIEdge(nodes.get(8), nodes.get(10)),
                new UIEdge(nodes.get(0), nodes.get(10))
        );

        UIGraph g = new UIGraph();
        nodes.forEach(g::addNode);
        edges.forEach(g::addEdge);
        return g;
    }
}
