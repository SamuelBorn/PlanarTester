package Util;

import java.util.List;

public class NestedListUtils {
    public static <X> boolean contains(X toSearch, List<List<X>> elements) {
        for (List<X> elementsInner : elements) {
            if (elementsInner.contains(toSearch)) return true;
        }
        return false;
    }

    public static <X> List<X> getContainingList(X toSearch, List<List<X>> elements) {
        for (List<X> elementsInner : elements) {
            if (elements.contains(toSearch)) return elementsInner;
        }
        return null;
    }
}
