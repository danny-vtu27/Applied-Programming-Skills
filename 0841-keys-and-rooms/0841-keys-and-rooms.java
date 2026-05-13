import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        dfs(rooms, visited, 0);
        for (boolean v : visited) if (!v) return false;
        return true;
    }

    void dfs(List<List<Integer>> rooms, boolean[] visited, int i) {
        visited[i] = true;
        for (int key : rooms.get(i)) {
            if (!visited[key]) dfs(rooms, visited, key);
        }
    }
}