package minimiseDisks;

import java.util.*;

public class MinimiseDisks {

    public static void main(String[] args) {
        PriorityQueue<Disk> PQ = new PriorityQueue<>();

        Scanner sc = new Scanner(System.in);
        List<Integer> sizes = new ArrayList<>();
        while (sc.hasNextInt()) {
            sizes.add(sc.nextInt());
        }

        /* *** UNSORTED (to test with sort, do sizes.sort() first) *** */
        PQ.add(new Disk(1));
        for (int size : sizes) {
            Disk largestDisk = PQ.peek();
            if (largestDisk.spaceLeft() >= size) {
                largestDisk.writeFile(size);
            }
            else {
                Disk newDisk = new Disk(PQ.size() + 1);
                newDisk.writeFile(size);
                PQ.add(newDisk);
            }
        }

        System.out.println(PQ.size());
        PQ.forEach(System.out::println);

    }

    private static class Disk implements Comparable<Disk> {

        int id;
        final static int totalCapacity = 1000;
        List<Integer> fileSizes = new ArrayList<>();

        public Disk(int id) {
            this.id = id;
        }

        int spaceLeft() {
            return totalCapacity - fileSizes.stream().mapToInt(e -> e).sum();
        }

        void writeFile(int fileSize) {
            fileSizes.add(fileSize);
        }

        @Override
        public int compareTo(Disk o) {
            return Integer.compare(spaceLeft(), o.spaceLeft());
        }

        @Override
        public String toString() {
            return "Disk " +
                    id +
                    "[" +
                    fileSizes.stream().map(s -> s + "MB").reduce("", (acc, s) -> acc + ", " + s).substring(2) +
                    "]";

        }
    }

}
