package PlanarTester;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EqualityConflictMergerTest {
    List<Conflict> conflicts;

    @BeforeEach
    void setUp() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);

        Edge e1 = new Edge(n1, n1, false);
        Edge e2 = new Edge(n2, n1, false);
        Edge e3 = new Edge(n3, n1, false);
        Edge e4 = new Edge(n4, n1, false);
        Edge e5 = new Edge(n5, n1, false);
        Edge e6 = new Edge(n6, n1, false);
        Edge e7 = new Edge(n7, n1, false);

        Conflict c1 = new Conflict(e1, e2, Conflict.ConflictType.EQUALITY);
        Conflict c2 = new Conflict(e1, e3, Conflict.ConflictType.EQUALITY);
        Conflict c3 = new Conflict(e2, e3, Conflict.ConflictType.EQUALITY);
        Conflict c4 = new Conflict(e2, e4, Conflict.ConflictType.EQUALITY);
        Conflict c5 = new Conflict(e3, e5, Conflict.ConflictType.INEQUALITY);
        Conflict c6 = new Conflict(e5, e6, Conflict.ConflictType.EQUALITY);
        Conflict c7 = new Conflict(e4, e7, Conflict.ConflictType.EQUALITY);

        conflicts = Arrays.asList(c1, c2, c3, c4, c5, c6, c7);
    }

    @Test
    void testAdjacent() {
        List<Conflict> x = EqualityConflictMerger.getAdjacentEqualityConflicts(conflicts.get(1), conflicts, new HashSet<>(Arrays.asList(conflicts.get(1))));
        System.out.println("13: " + x);
    }

    @Test
    void testAdjacent2() {
        List<Conflict> x = EqualityConflictMerger.getAdjacentEqualityConflicts(conflicts.get(0), conflicts, new HashSet<>(Arrays.asList(conflicts.get(0))));
        System.out.println("12: " + x);
    }

    @Test
    void getConnectedComponentTest() {
        List<Conflict> x = EqualityConflictMerger.getConnectedComponent(conflicts.get(0), conflicts, new HashSet<>());
        System.out.println("12: " + x);
        System.out.println("count = " + x.size());

        x = EqualityConflictMerger.getConnectedComponent(conflicts.get(1), conflicts, new HashSet<>());
        System.out.println("13: " + x);
        System.out.println("count = " + x.size());

        x = EqualityConflictMerger.getConnectedComponent(conflicts.get(5), conflicts, new HashSet<>());
        System.out.println("56: " + x);
        System.out.println("count = " + x.size());
    }

    @Test
    void getCompressedNodesTest() {
        for (List<Conflict> compressedNode : EqualityConflictMerger.getCompressedNodes(conflicts)) {
            System.out.println(compressedNode.size() + ": " +compressedNode);
        }
    }
}