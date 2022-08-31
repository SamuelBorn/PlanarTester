package Util;

import java.util.List;

public class NestedListChecker {
    public static <X> boolean contains(X toSearch, List<List<X>> elements){
        for (List<X> elementsInner : elements) {
            for (X element : elementsInner) {
                if (toSearch == element) return true;
            }
        }
        return false;
    }
}
