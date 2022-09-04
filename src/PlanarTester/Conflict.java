package PlanarTester;

public class Conflict {
    private Edge edge1;
    private Edge edge2;
    private ConflictType type;

    public Conflict(Edge edge1, Edge edge2, ConflictType type) {
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.type = type;
    }

    public Edge getEdge1() {
        return edge1;
    }

    public Edge getEdge2() {
        return edge2;
    }

    public ConflictType getType() {
        return type;
    }

    @Override
    public String toString() {
        //return "Conflict{" + edge1 + ", " + edge2 + ", " + type + '}';
        return "con{" + edge1+ ", " + edge2+"}";
    }

    public enum ConflictType {EQUALITY, INEQUALITY}
}
