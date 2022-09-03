package PlanarTester.Subroutines;

import PlanarTester.Conflict;
import PlanarTester.Edge;
import PlanarTester.NotPlanarException;
import Util.NestedListUtils;
import Util.Tuple;

import java.util.*;

public class EqualityConflictMerger {

    public static List<Tuple<List<Edge>, List<Edge>>> getMergedGraph(Map<Edge, List<Edge>> edgeComponentMap, List<Conflict> inequalityConflicts) {
        List<Tuple<List<Edge>, List<Edge>>> mergedGraph = new ArrayList<>();

        for (Conflict inequalityConflict : inequalityConflicts) {
            List<Edge> node1 = edgeComponentMap.get(inequalityConflict.getEdge1());
            List<Edge> node2 = edgeComponentMap.get(inequalityConflict.getEdge2());

            if (node1 == node2) throw new NotPlanarException("graph is not bipartite");

            mergedGraph.add(new Tuple<>(node1, node2));
        }

        return mergedGraph;
    }

    /**
     * Maps every Edge to a component.
     * A component is a list of edges connected by equality Conflicts.
     */
    public static Map<Edge, List<Edge>> getEdgeComponentMap(List<Edge> edges, List<Conflict> equalityConflicts) {
        List<List<Edge>> existingComponents = new ArrayList<>();
        Map<Edge, List<Edge>> mapping = new HashMap<>();

        for (Edge e : edges) {
            List<Edge> existingComponent = NestedListUtils.getContainingList(e, existingComponents);
            if (existingComponent != null) {
                mapping.put(e, existingComponent);
            } else {
                mapping.put(e, getConnectedComponent(e, equalityConflicts));
            }
        }
        return mapping;
    }

    public static List<Edge> getConnectedComponent(Edge e, List<Conflict> equalityConflicts) {
        return getConnectedComponentRecursive(e, equalityConflicts, new ArrayList<>());
    }

    private static List<Edge> getConnectedComponentRecursive(Edge e, List<Conflict> equalityConflicts, List<Edge> visited) {
        List<Edge> adj = getAdjacentEdges(e, equalityConflicts, visited);
        if (adj.isEmpty()) {
            return Arrays.asList(e);
        }

        visited.addAll(adj);
        for (Edge edge : adj) {
            visited.addAll(getConnectedComponentRecursive(edge, equalityConflicts, visited));
        }
        return new ArrayList<>(new HashSet<>(visited));
    }

    public static List<Edge> getAdjacentEdges(Edge e, List<Conflict> equalityConflicts, List<Edge> visited) {
        List<Edge> adjacentEdges = new ArrayList<>();
        for (Conflict equalityConflict : equalityConflicts) {
            Edge adjacent = null;
            if (equalityConflict.getEdge1() == e) {
                adjacent = equalityConflict.getEdge2();
            }
            if (equalityConflict.getEdge2() == e) {
                adjacent = equalityConflict.getEdge1();
            }
            if (adjacent != null && !visited.contains(adjacent)) {
                adjacentEdges.add(adjacent);
            }
        }
        return adjacentEdges;
    }
}
