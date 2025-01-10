import java.util.*;

class Solution {
    // 상하좌우 이동을 위한 방향 배열
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        // 방문 배열 초기화
        int[][] visited = new int[n][m];
        
        // BFS를 위한 큐 생성
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = 1;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            
            // 목적지에 도달한 경우
            if (x == n-1 && y == m-1) {
                return visited[x][y];
            }
            
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                // 맵 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                
                // 벽이 아니고 아직 방문하지 않은 경우
                if (maps[nx][ny] == 1 && visited[nx][ny] == 0) {
                    visited[nx][ny] = visited[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        
        // 도달할 수 없는 경우
        return -1;
    }
}