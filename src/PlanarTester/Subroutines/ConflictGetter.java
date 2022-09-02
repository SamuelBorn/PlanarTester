package PlanarTester.Subroutines;

import PlanarTester.Conflict;
import PlanarTester.Edge;
import PlanarTester.Tree;
import PlanarTester.TreeNode;
import Util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConflictGetter {

    public static List<Conflict> getConflicts(Tree tree, Map<Edge, Integer> lowPoints) {
        List<Edge> edgeList = tree.getEdgeList();

        List<Conflict> conflicts = new ArrayList<>();
        for (TreeNode node : tree.getNodes()) { // for every node, iterate over all forks
            for (int i = 0; i < node.getEdges().size(); i++) {
                for (int j = i + 1; j < node.getEdges().size(); j++) {
                    Edge e1 = node.getEdges().get(i);
                    Edge e2 = node.getEdges().get(j);

                    List<Edge> r_e1_e2 = getRSet(e1, e2, lowPoints);
                    List<Edge> r_e2_e1 = getRSet(e2, e1, lowPoints);
                    conflicts.addAll(getConflictsForFork(r_e1_e2, r_e2_e1, edgeList));
                }
            }
        }
        return conflicts;
    }

    private static List<Conflict> getConflictsForFork(List<Edge> r_e1_e2, List<Edge> r_e2_e1, List<Edge> edgeList) {
        List<Conflict> conflicts = new ArrayList<>();
        for (Edge f1 : edgeList) {
            for (Edge f2 : edgeList) {
                if ((r_e1_e2.contains(f1) && r_e1_e2.contains(f2)) || (r_e2_e1.contains(f1) && r_e2_e1.contains(f2))) {
                    conflicts.add(new Conflict(f1, f2, Conflict.ConflictType.EQUALITY));
                }
                if ((r_e1_e2.contains(f1) && r_e2_e1.contains(f2)) || (r_e2_e1.contains(f1) && r_e1_e2.contains(f2))) {
                    conflicts.add(new Conflict(f1, f2, Conflict.ConflictType.INEQUALITY));
                }
            }
        }
        return conflicts;
    }

    private static List<Edge> getRSet(Edge e1, Edge e2, Map<Edge, Integer> lowPoints) {
        if (e1.getNodeA() != e2.getNodeA()) throw new IllegalArgumentException("edgeA and edgeB must be a fork");

        TreeNode forkNode = e1.getNodeA();
        List<Edge> backEdges = e1.getBackEdges();

        return backEdges.parallelStream()
                .filter(edge -> lowPoints.get(e2) < lowPoints.get(edge))
                .filter(edge -> lowPoints.get(edge) < forkNode.getDFSNumber())
                .toList();
    }
}
