import java.util.*;

class Solution {
    private boolean[] visited;
    private int maxCount = 0;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        
        dfs(0, k, dungeons);
        
        return maxCount;
    }
    
    private void dfs(int count, int currentK, int[][] dungeons) {
        maxCount = Math.max(maxCount, count);
        
        for(int i = 0; i < dungeons.length; i++) {
            if(!visited[i] && currentK >= dungeons[i][0]) {
                visited[i] = true;
                dfs(count + 1, currentK - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
    }
}