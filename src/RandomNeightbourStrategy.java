import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomNeightbourStrategy implements NeightboursStrategy {

    private int neightbourNumber;

    private static Random random = new Random();

    private int rand1, rand2;

    public RandomNeightbourStrategy(int neightbourNumber) {
        this.neightbourNumber = neightbourNumber;
    }

    @Override
    public List<Neightbour> getNeightbours(Combination combination) {
        List<Neightbour> neightbourList = new LinkedList<>();

        Neightbour neightbour;

        for (int i = 0; i < neightbourNumber; ++i) {
            neightbourList.add(getNeightbour(combination, i));
        }

        /*
        while (neightbourList.size() < neightbourNumber) {
            neightbour = getNeightbour(combination, 0);
            if (neightbour.getCombination().getFitness() <= 1.2D * combination.getFitness()) neightbourList.add(neightbour);
        }*/

        return neightbourList;
    }

    @Override
    public Neightbour getNeightbour(Combination combination, int index) {
        rand1 = random.nextInt(combination.size);
        rand2 = random.nextInt(combination.size - 1);

        if (rand2 >= rand1) {
            ++rand2;
        }

        return combination.swapSlots(rand1, rand2);
    }
}
