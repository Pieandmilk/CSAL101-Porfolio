import java.util.ArrayList;
import java.util.List;

public class BackpackProblem {

    // Class to represent an item
    static class Item {
        String name;
        int weight;
        int value;

        public Item(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }
    }

    // Function to solve the backpack problem using brute-force
    public static List<Item> maximizeValue(List<Item> items, int capacity) {
        List<Item> bestSubset = new ArrayList<>();
        int maxValue = 0;

        // Generate all possible subsets
        int n = items.size();
        for (int i = 0; i < (1 << n); i++) {
            List<Item> subset = new ArrayList<>();
            int totalWeight = 0;
            int totalValue = 0;

            // Check each item in the subset
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(items.get(j));
                    totalWeight += items.get(j).weight;
                    totalValue += items.get(j).value;
                }
            }

            // Check if the subset is feasible and has maximum value so far
            if (totalWeight <= capacity && totalValue > maxValue) {
                maxValue = totalValue;
                bestSubset = new ArrayList<>(subset);
            }
        }

        return bestSubset;
    }


    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Water Bottle", 2, 10));
        items.add(new Item("Lamp", 3, 7));
        items.add(new Item("First aid", 4, 14));
        items.add(new Item("Food and Snacks", 5, 5));
        items.add(new Item("Map", 1, 3));
        items.add(new Item("Compass", 1, 6));
        items.add(new Item("Pocket Knife", 1, 8));
        items.add(new Item("Portable Stove", 3, 12));
        items.add(new Item("Sleeping Bag", 4, 15));
        items.add(new Item("Camera", 2, 9));

        int capacity = 15;

        List<Item> result = maximizeValue(items, capacity);

        System.out.println("Items to maximize value:");
        for (Item item : result) {
            System.out.println("Name: " + item.name + ", Weight: " + item.weight + ", Value: " + item.value);
        }
    }
}
