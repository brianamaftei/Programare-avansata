package Lab2.Bonus;

public class Main {
    public static void main(String[] args) {
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long initialTime = System.currentTimeMillis();

        int locations = 500;
        int roads = 500;
        Solution solution = new Solution(locations,roads);
        long runningTime = System.currentTimeMillis() - initialTime;
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Running time: " + runningTime + " ms");
        System.out.println("Memory used: " + memoryIncrease / 1024 + " KB");
    }
}
