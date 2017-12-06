import java.util.*;

public class Triplicates {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("How many names in each list?");
        int N = sc.nextInt();
        sc.nextLine();

        ArrayList<Set<String>> nameSets = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            nameSets.add(new HashSet<>());
        }

        for (Set set : nameSets) {
            for (int i = 0; i < N; i++) {
                set.add(sc.nextLine());
            }
        }

        Map<String, Integer> occurences = new HashMap<>();

        for (Set set : nameSets) {
            for (Object o : set) {
                String s = (String) o;
                occurences.put(s, occurences.getOrDefault(s, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(occurences.entrySet());
        sortedEntries.sort(Comparator.comparing(Map.Entry::getKey));
        String result = sortedEntries
                .stream()
                .filter(e -> e.getValue() == 3)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("No such name");

        System.out.println(result);

    }



}
