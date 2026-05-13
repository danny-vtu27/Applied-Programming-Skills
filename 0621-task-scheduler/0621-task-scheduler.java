class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        
        for (char t : tasks) {
            freq[t - 'A']++;
        }

        int max = 0;
        for (int f : freq) {
            max = Math.max(max, f);
        }

        int countMax = 0;
        for (int f : freq) {
            if (f == max) countMax++;
        }

        int partCount = max - 1;
        int partLength = n - (countMax - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - max * countMax;
        int idles = Math.max(0, emptySlots - availableTasks);

        return tasks.length + idles;
    }
}