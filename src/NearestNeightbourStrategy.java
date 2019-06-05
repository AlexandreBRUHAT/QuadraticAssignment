import java.util.LinkedList;
import java.util.List;

public class NearestNeightbourStrategy implements NeightboursStrategy {

    private static NearestNeightbourStrategy instance;

    private NearestNeightbourStrategy() {}

    public static NearestNeightbourStrategy getInstance() {
        if (instance == null) instance = new NearestNeightbourStrategy();
        return instance;
    }


    @Override
    public List<Neightbour> getNeightbours(Combination combination) {
        List<Neightbour> neightbours = new LinkedList<Neightbour>();

        for (int index = 0; index < combination.size; ++index) {

            neightbours.add(combination.swapSlots(index, combination.grid.getNearestSlot(index)));
        }

        return neightbours;
    }

    @Override
    public Neightbour getNeightbour(Combination combination, int index) {
        return null;
    }
}
