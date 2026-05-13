import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> before) {
        for (int i = 0; i < n; i++) if (group[i] == -1) group[i] = m++;

        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();
        int[] itemIndeg = new int[n];
        int[] groupIndeg = new int[m];

        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) groupGraph.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            for (int b : before.get(i)) {
                itemGraph.get(b).add(i);
                itemIndeg[i]++;
                if (group[i] != group[b]) {
                    groupGraph.get(group[b]).add(group[i]);
                    groupIndeg[group[i]]++;
                }
            }
        }

        List<Integer> itemOrder = topo(itemGraph, itemIndeg, n);
        List<Integer> groupOrder = topo(groupGraph, groupIndeg, m);

        if (itemOrder.size() == 0 || groupOrder.size() == 0) return new int[0];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i : itemOrder) {
            map.computeIfAbsent(group[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> res = new ArrayList<>();
        for (int g : groupOrder) {
            res.addAll(map.getOrDefault(g, new ArrayList<>()));
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    List<Integer> topo(List<List<Integer>> g, int[] indeg, int n) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) if (indeg[i]==0) q.offer(i);
        List<Integer> res = new ArrayList<>();

        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);
            for (int nei : g.get(cur)) {
                if (--indeg[nei]==0) q.offer(nei);
            }
        }
        return res.size()==n ? res : new ArrayList<>();
    }
}