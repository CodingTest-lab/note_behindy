// 격자 미로 탈출하기
// 최소 시간 반환
// bfs?
// 시작 -> 레버 -> 도착
// 이동의 중간지점이 존재
// 두번의 최단거리 계산이 필요
import java.util.*;

class Solution {
    // 이동 단위 벡터
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        int[] start = findObject(maps, 'S');
        int[] lever = findObject(maps, 'L');
        int[] exit = findObject(maps, 'E');
        
        int toLever = bfs(maps, start, lever);
        int toExit = bfs(maps, lever, exit);
        
        return (toLever == -1 || toExit == -1) ? -1 : toLever + toExit;
    }
    private int bfs(String[] maps, int[] start, int[] end){
        // 수평 길이 계산
        int n = maps.length;
        // 수직길이 계산
        int m = maps[0].length();
        
        // bfs 탐색을 위한 공간 마련
        Queue<int[]> queue = new LinkedList<>();
        // maps 와 같은 공간에 방문했는지 체크, 중복이동, 무한순회를 방지하기 위함
        int[][] visited = new int[n][m];
        
        // 초기 정보 기록
        queue.offer(new int[]{start[0], start[1]});
        visited[start[0]][start[1]] = 1;
        
        // 
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            
            if(curr[0] == end[0] && curr[1] == end[1]) {
                return visited[curr[0]][curr[1]] - 1;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                
                if(nx >= 0 && nx < n && ny >= 0 && ny < m 
                   && maps[nx].charAt(ny) != 'X' 
                   && visited[nx][ny] == 0) {
                    visited[nx][ny] = visited[curr[0]][curr[1]] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return -1;
    }
    
    // target (S 시작, L 레버, E 도착)
    private int[] findObject(String[] maps, char target){
        // 지도(maps) 순회를 통해 target을 탐색
        for(int i = 0; i<maps.length; i++){
            for(int j = 0 ; j< maps[0].length(); j++){
                if(maps[i].charAt(j)==target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}