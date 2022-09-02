package PlanarTester.Subroutines;

import PlanarTester.Edge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LowPointGetter {
    public static Map<Edge, Integer> getLowPoints(List<Edge> edges) {
        Map<Edge, Integer> lowPoints = new HashMap<>();

        for (Edge edge : edges) {
            lowPoints.put(edge, getLowPoint(edge));
        }

        return lowPoints;
    }

    private static int getLowPoint(Edge e) {
        if (!e.isTreeEdge()) return e.getNodeB().getDFSNumber();

        int lowPoint = e.getNodeB().getDFSNumber();
        for (Edge edge : e.getNodeB().getEdges()) {
            int temp = getLowPoint(edge);
            if (temp < lowPoint) {
                lowPoint = temp;
            }
        }
        return lowPoint;
    }
}
