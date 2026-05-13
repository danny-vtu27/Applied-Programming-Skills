import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] red, int[][] blue) {
        List<Integer>[] r = new ArrayList[n], b = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            r[i] = new ArrayList<>();
            b[i] = new ArrayList<>();
        }

        for (int[] e : red) r[e[0]].add(e[1]);
        for (int[] e : blue) b[e[0]].add(e[1]);

        int[][] dist = new int[n][2];
        for (int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE);

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        q.offer(new int[]{0,1});
        dist[0][0] = dist[0][1] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0], color = cur[1];

            List<Integer>[] next = color==0 ? b : r;
            for (int nei : next[node]) {
                if (dist[nei][1-color] == Integer.MAX_VALUE) {
                    dist[nei][1-color] = dist[node][color] + 1;
                    q.offer(new int[]{nei,1-color});
                }
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int d = Math.min(dist[i][0], dist[i][1]);
            res[i] = d==Integer.MAX_VALUE ? -1 : d;
        }
        return res;
    }
}