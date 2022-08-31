package PlanarTester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class EqualityConflictMerger {
    public static void mergeEqualityConflicts(List<Conflict> conflicts) {
        List<Conflict> equalityConflicts = conflicts.parallelStream().filter(conflict -> conflict.getType() == Conflict.ConflictType.EQUALITY).toList();
        List<List<Integer>> compressedNodes = new ArrayList<>();
        for (Conflict conflict : equalityConflicts) {

        }
    }

    public static List<Conflict> getConnectedComponent(Conflict start, List<Conflict> conflicts, Set<Conflict> forbidden) {
        if (getAdjacentEqualityConflicts(start, conflicts, forbidden).isEmpty()) return Arrays.asList(start);

        forbidden.add(start);
        for (Conflict c : getAdjacentEqualityConflicts(start, conflicts, forbidden)) {
            forbidden.addAll(getConnectedComponent(c, conflicts, forbidden));
        }
        return new ArrayList<>(forbidden);
    }

    public static List<Conflict> getAdjacentEqualityConflicts(Conflict start, List<Conflict> conflicts, Set<Conflict> forbidden) {
        Edge e1 = start.getEdge1();
        Edge e2 = start.getEdge2();

        return conflicts.parallelStream()
                .filter(c -> c.getType() == Conflict.ConflictType.EQUALITY)
                .filter(c -> !forbidden.contains(c))
                .filter(c -> c.getEdge1() == e1 || c.getEdge1() == e2 || c.getEdge2() == e1 || c.getEdge2() == e2)
                .toList();
    }
}
