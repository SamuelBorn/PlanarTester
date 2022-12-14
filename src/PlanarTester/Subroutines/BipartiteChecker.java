package PlanarTester.Subroutines;

import PlanarTester.Edge;
import PlanarTester.NotPlanarException;
import Util.NestedListUtils;
import Util.Tuple;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BipartiteChecker {
    /**
     * Maps every edge to a color.
     * If there does not exist a Color mapping null is returned.
     */
    public static Map<Edge, Color> getColorMapping(List<Tuple<List<Edge>, List<Edge>>> graph, List<Edge> nonTreeEdges, Map<Edge, List<Edge>> componentMap) {
        Map<Edge, Color> edgeColorMap = new HashMap<>();

        for (Edge nonTreeEdge : nonTreeEdges) {
            if (edgeColorMap.containsKey(nonTreeEdge)) continue;

            List<Edge> component = componentMap.get(nonTreeEdge);
            getColorMappingRec(component, new ArrayList<>(), Color.BLUE, edgeColorMap, graph);
        }

        return edgeColorMap;
    }

    private static void getColorMappingRec(List<Edge> current, List<List<Edge>> visited, Color c, Map<Edge, Color> edgeColorMap, List<Tuple<List<Edge>, List<Edge>>> graph) {
        current.forEach(e -> edgeColorMap.put(e, c));
        visited.add(current);

        for (Tuple<List<Edge>, List<Edge>> tuple : graph) {
            List<Edge> adjacent = null;
            if (current.equals(tuple.getX())) adjacent = tuple.getY();
            if (current.equals(tuple.getY())) adjacent = tuple.getX();
            if (adjacent == null) continue;

            if (edgeColorMap.get(adjacent.get(0)) == c) throw new NotPlanarException("graph is not bipartite");
            if (visited.contains(adjacent)) continue;

            getColorMappingRec(adjacent, visited, getOpposingColor(c), edgeColorMap, graph);
        }
    }

    private static Color getOpposingColor(Color c) {
        if (c == Color.BLUE) return Color.RED;
        return Color.BLUE;
    }
}
