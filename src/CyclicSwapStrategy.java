import java.util.LinkedList;
import java.util.List;

public class CyclicSwapStrategy implements NeightboursStrategy {

    private static CyclicSwapStrategy instance;

    private CyclicSwapStrategy() {}

    public static CyclicSwapStrategy getInstance() {
        if (instance == null) instance = new CyclicSwapStrategy();
        return instance;
    }

    @Override
    public List<Neightbour> getNeightbours(Combination combination) {
        List<Neightbour> neighbours = new LinkedList<Neightbour>();

        for (int i = 0; i < combination.size - 1; ++i) neighbours.add(getNeightbour(combination, i));

        return neighbours;
    }

    @Override
    public Neightbour getNeightbour(Combination combination, int index) {
        return combination.swapSlots(index, (index + 1) % combination.size);
    }
}
