package PlanarTester;

import GraphComponents.UIGraph;
import PlanarTester.Subroutines.*;
import UserInterface.ExampleGraphCreator;
import Util.Tuple;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class MainPlanarTester {
    public static void testPlanar(UIGraph g) {

        Tree t = TreeBuilder.buildTree(g);
        System.out.println(t);

        Map<Edge, Integer> lowPoints = LowPointGetter.getLowPoints(t.getEdgeList());
        System.out.println(lowPoints);

        List<Conflict> conflicts = ConflictGetter.getConflicts(t, lowPoints);
        List<Conflict> equalityConflicts = conflicts.stream().filter(c -> c.getType() == Conflict.ConflictType.EQUALITY).toList();
        List<Conflict> inequalityConflicts = conflicts.stream().filter(c -> c.getType() == Conflict.ConflictType.INEQUALITY).toList();
        System.out.println("conflicts: " + conflicts + "\n");

        Map<Edge, List<Edge>> edgeComponentMap = EqualityConflictMerger.getEdgeComponentMap(t.getEdgeList(), equalityConflicts);
        for (Map.Entry<Edge, List<Edge>> edgeListEntry : edgeComponentMap.entrySet()) {
            System.out.println(edgeListEntry.getKey() + " → " + edgeListEntry.getValue());
        }
        List<Tuple<List<Edge>, List<Edge>>> mergedGraph = EqualityConflictMerger.getMergedGraph(edgeComponentMap, inequalityConflicts);
        System.out.println("\nmerged graph:" + mergedGraph);
        for (Tuple<List<Edge>, List<Edge>> tuple : mergedGraph) {
            System.out.println(tuple);
        }

        Map<Edge, Color> edgeToColorMap = BipartiteChecker.getColorMapping(mergedGraph, t.getNonTreeEdges(), edgeComponentMap);

        for (Map.Entry<Edge, Color> edgeColorEntry : edgeToColorMap.entrySet()) {
            System.out.println(edgeColorEntry.getKey() + "→" + edgeColorEntry.getValue());
        }
    }

    public static void main(String[] args) {
        MainPlanarTester.testPlanar(ExampleGraphCreator.getExampleGraph1());
    }
}
