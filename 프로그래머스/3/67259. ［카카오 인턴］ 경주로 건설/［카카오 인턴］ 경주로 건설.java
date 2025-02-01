import java.util.*;

// 필요기능 1. - 최단거리 탐색
// 필요기능 2. - 현재 칸에 자리할 트랙이 코너 트랙 인지 직선 트랙인지
class Solution {
    private int answer = Integer.MAX_VALUE;
    private int n;
    
    // 트랙의 단위 가격
    private int cornerTrack = 500;
    private int straightTrack = 100;
    
    // bfs 탐색 이동단위
    private int[] dx = new int[]{0,0,1,-1};
    private int[] dy = new int[]{1,-1,0,0};
    
    // board : 0 은 비어있음, 1은 벽, 2차원 공간배열
    public int solution(int[][] board) {
        n = board.length;
        // 기능 1의 탐색을 위한 bfs 준비
        Queue<int[]> que = new LinkedList<>();
        int[][][] visited = new int[n][n][4];  // [x][y][방향]의 최소 비용
        
        // 초기화
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < 4; k++) {
                    visited[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        
        // 시작 지점 초기화 (x좌표, y좌표, 이전x, 이전y, 방향, 비용)
        que.offer(new int[]{0,0,0,0,-1,0});
        visited[0][0][0] = 0;
        
        // 기능 2의 트랙 종류 확인을 위한 준비
        // 시작지점에서 첫번째 이동은 항상 직선트랙
        while(!que.isEmpty()){
            int[] temp = que.poll();
            int[] curr = new int[]{temp[0],temp[1]};
            int[] before = new int[]{temp[2],temp[3]};
            int prevDir = temp[4];
            int cost = temp[5];
            
            // 반복중 종료지점에 도달했다면 반복 종료
            if(curr[0] == n-1 && curr[1] == n-1) {
                answer = Math.min(answer, cost);
                continue;
            }
            
            for(int i = 0 ; i < 4 ; i ++){
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                
                // 새로운 좌표가 visited 에 체크되어있는지?
                // 새로운 좌표가 벽(1)인지 확인하기
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;  // 경계 체크
                if(board[nx][ny] == 1) continue;  // 벽 체크
                
                // 비용 계산
                int newCost = cost + straightTrack;
                if(prevDir != -1 && prevDir != i) {  // 방향이 다르면 코너 추가
                    newCost += cornerTrack;
                }
                
                // 이미 더 적은 비용으로 방문했다면 스킵
                if(visited[nx][ny][i] <= newCost) continue;
                
                // 검사 통과시 작업
                visited[nx][ny][i] = newCost;
                que.offer(new int[]{nx, ny, curr[0], curr[1], i, newCost});
            }
        }
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}