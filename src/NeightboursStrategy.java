import java.util.List;

public interface NeightboursStrategy {

    public List<Neightbour> getNeightbours(Combination combination);

    public Neightbour getNeightbour(Combination combination, int index);
}
