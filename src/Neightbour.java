public class Neightbour {

    private int idOperation;
    private Combination combination;

    public Neightbour(Combination combination, int idOperation) {
        this.combination = combination;
        this.idOperation = idOperation;
    }

    public int getIdOperation() {
        return idOperation;
    }

    public Combination getCombination() {
        return combination;
    }

    public boolean isSameOperation(Neightbour neightbour) {
        return neightbour != null && neightbour.idOperation == this.idOperation;
    }
}
