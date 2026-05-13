class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length, count = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                count++;
            }
        }
        return count;
    }

    void dfs(int[][] g, boolean[] v, int i) {
        v[i] = true;
        for (int j = 0; j < g.length; j++) {
            if (g[i][j] == 1 && !v[j]) dfs(g, v, j);
        }
    }
}