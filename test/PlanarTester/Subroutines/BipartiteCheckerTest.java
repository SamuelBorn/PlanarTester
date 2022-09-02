package PlanarTester.Subroutines;

import PlanarTester.Conflict;
import PlanarTester.Edge;
import PlanarTester.Subroutines.BipartiteChecker;
import PlanarTester.Subroutines.EqualityConflictMerger;
import PlanarTester.TreeNode;
import Util.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class BipartiteCheckerTest {
    List<Conflict> conflicts;
    List<Edge> edges;
    List<Conflict> equalityConflicts;
    List<Conflict> inequalityConflicts;

    @BeforeEach
    void setUp() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);


        Edge e1 = new Edge(n1, n1, false);
        Edge e2 = new Edge(n2, n1, false);
        Edge e3 = new Edge(n3, n1, false);
        Edge e4 = new Edge(n4, n1, false);
        Edge e5 = new Edge(n5, n1, false);
        Edge e6 = new Edge(n6, n1, false);
        Edge e7 = new Edge(n7, n1, false);
        Edge e8 = new Edge(n8, n1, false);
        Edge e9 = new Edge(n9, n1, false);
        edges = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9);

        Conflict c1 = new Conflict(e1, e2, Conflict.ConflictType.EQUALITY);
        Conflict c2 = new Conflict(e1, e3, Conflict.ConflictType.EQUALITY);
        Conflict c3 = new Conflict(e2, e3, Conflict.ConflictType.EQUALITY);
        Conflict c4 = new Conflict(e2, e4, Conflict.ConflictType.EQUALITY);
        Conflict c5 = new Conflict(e3, e5, Conflict.ConflictType.INEQUALITY);
        Conflict c6 = new Conflict(e5, e6, Conflict.ConflictType.EQUALITY);
        Conflict c7 = new Conflict(e4, e7, Conflict.ConflictType.EQUALITY);
        Conflict c8 = new Conflict(e1, e8, Conflict.ConflictType.INEQUALITY);
        Conflict c9 = new Conflict(e8, e9, Conflict.ConflictType.INEQUALITY);
        //Conflict c10 = new Conflict(e5, e8, Conflict.ConflictType.INEQUALITY); // add to make not bipartite
        conflicts = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9);

        equalityConflicts = conflicts.stream().filter(conflict -> conflict.getType() == Conflict.ConflictType.EQUALITY).toList();
        inequalityConflicts = conflicts.stream().filter(conflict -> conflict.getType() == Conflict.ConflictType.INEQUALITY).toList();
    }

    @Test
    void bipartiteTest() {
        List<Tuple<List<Edge>, List<Edge>>> g = EqualityConflictMerger.getMergedGraph(EqualityConflictMerger.getEdgeComponentMap(edges, equalityConflicts), inequalityConflicts);

        for (Map.Entry<Edge, Color> edgeColorEntry : BipartiteChecker.getColorMapping(g).entrySet()) {
            System.out.println(edgeColorEntry.getKey() + " → " + edgeColorEntry.getValue());
        }
    }
}