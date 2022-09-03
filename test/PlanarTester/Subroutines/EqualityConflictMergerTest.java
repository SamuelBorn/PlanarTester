package PlanarTester.Subroutines;

import PlanarTester.Conflict;
import PlanarTester.Edge;
import PlanarTester.Subroutines.EqualityConflictMerger;
import PlanarTester.TreeNode;
import Util.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

class EqualityConflictMergerTest {
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
        TreeNode n10 = new TreeNode(10);
        TreeNode n11 = new TreeNode(11);
        TreeNode n12 = new TreeNode(12);


        Edge e1 = new Edge(n1, n1, false);
        Edge e2 = new Edge(n2, n1, false);
        Edge e3 = new Edge(n3, n1, false);
        Edge e4 = new Edge(n4, n1, false);
        Edge e5 = new Edge(n5, n1, false);
        Edge e6 = new Edge(n6, n1, false);
        Edge e7 = new Edge(n7, n1, false);
        Edge e8 = new Edge(n8, n1, false);
        Edge e9 = new Edge(n9, n1, false);
        Edge e10 = new Edge(n10, n1, false);
        Edge e11 = new Edge(n11, n1, false);
        Edge e12 = new Edge(n12, n1, false);
        edges = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11,e12);

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
        Conflict c10 = new Conflict(e11, e10, Conflict.ConflictType.EQUALITY);
        Conflict c11 = new Conflict(e11, e12, Conflict.ConflictType.INEQUALITY);
        conflicts = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11);

        equalityConflicts = conflicts.stream().filter(conflict -> conflict.getType() == Conflict.ConflictType.EQUALITY).toList();
        inequalityConflicts = conflicts.stream().filter(conflict -> conflict.getType() == Conflict.ConflictType.INEQUALITY).toList();
    }


    @Test
    void getCompressedNodesTest2() {
        System.out.println(EqualityConflictMerger.getAdjacentEdges(edges.get(0), equalityConflicts, new ArrayList<>()));
        System.out.println(EqualityConflictMerger.getConnectedComponent(edges.get(0), equalityConflicts));
        System.out.println(EqualityConflictMerger.getConnectedComponent(edges.get(7), equalityConflicts));
    }

    @Test
    void getMappingTest() {
        Map<Edge, List<Edge>> map = EqualityConflictMerger.getEdgeComponentMap(edges, equalityConflicts);
        for (Map.Entry<Edge, List<Edge>> edgeListEntry : map.entrySet()) {
            System.out.println(edgeListEntry.getKey().getNodeA().getDFSNumber() + " → " + edgeListEntry.getValue());
        }
    }

    @Test
    void getMergedGraphTest() {
        List<Tuple<List<Edge>, List<Edge>>> g = EqualityConflictMerger.getMergedGraph(EqualityConflictMerger.getEdgeComponentMap(edges, equalityConflicts), inequalityConflicts);

        for (Tuple<List<Edge>, List<Edge>> listListTuple : g) {
            System.out.println(listListTuple.getX() + " → " + listListTuple.getY());
        }
    }

    @Test
    void testForestGetMergedGraph() {
        List<Tuple<List<Edge>, List<Edge>>> g = EqualityConflictMerger.getMergedGraph(EqualityConflictMerger.getEdgeComponentMap(edges, equalityConflicts), inequalityConflicts);

        for (Tuple<List<Edge>, List<Edge>> listListTuple : g) {
            System.out.println(listListTuple.getX() + " → " + listListTuple.getY());
        }
    }

    @Test
    void testForestComponent() {
        Map<Edge, List<Edge>> x = EqualityConflictMerger.getEdgeComponentMap(edges, equalityConflicts);
        for (Map.Entry<Edge, List<Edge>> edgeListEntry : x.entrySet()) {
            System.out.println(edgeListEntry.getKey() + " → " + edgeListEntry.getValue());
        }
        System.out.println("\n\n");
    }

    @Test
    void testForestColor() {
        List<Tuple<List<Edge>, List<Edge>>> g = EqualityConflictMerger.getMergedGraph(EqualityConflictMerger.getEdgeComponentMap(edges, equalityConflicts), inequalityConflicts);
        Map<Edge, List<Edge>> z = EqualityConflictMerger.getEdgeComponentMap(edges, equalityConflicts);

        Map<Edge, Color> x = BipartiteChecker.getColorMapping(g, edges, z);
        for (Map.Entry<Edge, Color> edgeColorEntry : x.entrySet()) {
            System.out.println(edgeColorEntry.getKey() + " → " + edgeColorEntry.getValue());
        }
    }
}