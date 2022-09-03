package PlanarTester;

import GraphComponents.UIGraph;
import PlanarTester.Subroutines.*;
import UserInterface.ExampleGraphCreator;
import Util.Tuple;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class MainPlanarTester {
    /**
     * Returns a color mapping for the edges of the graph.
     * If the Graph is not planar the NotPlanarException is thrown.
     */
    public static Map<Edge, Color> testPlanar(Tree t) {
        Map<Edge, Integer> lowPoints = LowPointGetter.getLowPoints(t.getEdgeList());

        List<Conflict> conflicts = ConflictGetter.getConflicts(t, lowPoints);
        List<Conflict> equalityConflicts = conflicts.stream().filter(c -> c.getType() == Conflict.ConflictType.EQUALITY).toList();
        List<Conflict> inequalityConflicts = conflicts.stream().filter(c -> c.getType() == Conflict.ConflictType.INEQUALITY).toList();

        System.out.println(t);

        Map<Edge, List<Edge>> edgeComponentMap = EqualityConflictMerger.getEdgeComponentMap(t.getEdgeList(), equalityConflicts);
        List<Tuple<List<Edge>, List<Edge>>> mergedGraph = EqualityConflictMerger.getMergedGraph(edgeComponentMap, inequalityConflicts);

        return BipartiteChecker.getColorMapping(mergedGraph, t.getNonTreeEdges(), edgeComponentMap);
    }
}
