import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Combination {

    protected int combination[];

    protected Grid grid;

    protected int size;
    private int fitness = -1;

    public Combination(Grid grid) {

        this.size = grid.getSize();
        this.grid = grid;

        combination = new int[size];
    }

    public void setSlot(int slot, int component) {
        combination[slot] = component;
    }

    public int getIndexOfElement(int element) {
        for (int i = 0; i < size; ++i) {
            if (combination[i] == element) return i;
        }

        return -1;
    }

    public static Combination generateRandom(Grid grid) {
        int size = grid.getSize();
        boolean[] isUsed = new boolean[size];

        Combination combination = new Combination(grid);

        Random random = new Random();

        for (int i = 0; i < size; ++i) {
            int rand = random.nextInt(size - i);

            int j = -1;

            while (rand >= 0) {

                ++j;

                if (!isUsed[j]) {
                    --rand;
                }
            }

            isUsed[j] = true;
            combination.setSlot(i, j);
        }

        combination.calculateFitness();

        return combination;
    }

    public int getSum() {

        int sum = 0;

        for (int i = 0; i < size; ++i) {
            sum += combination[i];
        }

        return sum;
    }

    private int calculateFitness() {
        fitness = 0;

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                fitness += grid.getFlow(combination[i], combination[j]) * grid.getDistance(i, j);
            }
        }

        return fitness;
    }

    public int getFitness() {
        if (fitness == -1) return calculateFitness();
        return fitness;
    }

    public Combination clone() {
        Combination combination = new Combination(grid);

        for (int i = 0; i < size; ++i) {
            combination.setSlot(i, this.combination[i]);
        }

        return combination;
    }

    public Neightbour swapSlots(int slot1, int slot2) {
        if (slot1 > slot2) {
            int s = slot1;
            slot1 = slot2;
            slot2 = s;
        }

        Combination combination = this.clone();

        combination.setSlot(slot1, this.combination[slot2]);
        combination.setSlot(slot2, this.combination[slot1]);

        combination.fitness = this.getFitness();

        for (int i = 0; i < size; ++i) {
            combination.fitness -= 2 * grid.getDistance(slot1, i) * grid.getFlow(this.combination[slot1], this.combination[i]);
            combination.fitness -= 2 * grid.getDistance(slot2, i) * grid.getFlow(this.combination[slot2], this.combination[i]);

            combination.fitness += 2 * grid.getDistance(slot1, i) * grid.getFlow(combination.combination[slot1], combination.combination[i]);
            combination.fitness += 2 * grid.getDistance(slot2, i) * grid.getFlow(combination.combination[slot2], combination.combination[i]);
        }


        return new Neightbour(combination, slot2 + slot1 * size);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; ++i) s += (getIndexOfElement(i) + 1) + ", ";
        s = s.substring(0,s.length() - 2);

        return s;
    }
}
