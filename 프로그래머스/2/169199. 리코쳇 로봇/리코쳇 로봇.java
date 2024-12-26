// 포켓몬 얼음동굴...
import java.util.*;

class Solution {
    // 단위벡터의 기록
    static int[] dn = {-1, 1, 0, 0}; // direction n
    static int[] dm = {0, 0, -1, 1}; // direction m
    
    public int solution(String[] board) {
        int[] start = findObject(board, 'R');
        int[] end = findObject(board, 'G');
        
        int answer = bfs(board, start, end);
        
        return answer;
    }
    
    // bfs 탐색 시도 
    private int bfs(String[] board, int[] start, int[] end){
        // 공간의 n 거리 계산
        int n = board.length;
        // 공간의 m 거리 계산
        int m = board[0].length();
        
        // bfs 탐색을 위한 공간 마련
        Queue<int[]> queue = new LinkedList<>();
        // board 와 같은 공간에 방문했는지 체크, 중복이동, 무한순회를 방지하기 위함
        int[][] visited = new int[n][m];
        
        // 초기 정보 제공
        queue.offer(new int[]{start[0], start[1]});
        visited[start[0]][start[1]] = 1;
        
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            
            // 현재 상태가 종료 상태이면 반복 종료, 좌표 반환
            if(curr[0] == end[0] && curr[1]== end[1]){
                return visited[curr[0]][curr[1]] - 1;
            }
            // 이동 시도
            for(int i = 0; i < 4; i++) {
                int[] next = slide(board, curr, i);
                
                if( visited[next[0]][next[1]] == 0 ){
                    visited[next[0]][next[1]] = visited[curr[0]][curr[1]] + 1;
                    queue.offer(new int[]{next[0], next[1]});
                }
            }
        }
        return -1;
    }
    private int[] slide(String[] board, int[] curr, int direction){
        // 현재 좌표에서 제시된 direction(0,1,2,3) 으로 이동
        // 벽(D) 또는 격자와 마주칠 때 까지 이동
        int tn = curr[0]; // temp N
        int tm = curr[1]; // temp M
        // 한칸씩 이동
        // 직전 좌표와 새로운 좌표를 보관해서
        // 새로운 좌표가 벽 또는 격자 밖으로 이동하는 경우 직전 좌표를 반환
        while(true){
            int nn = tn + dn[direction];
            int nm = tm + dm[direction];
            if(nn >= 0 && nn < board.length 
               && nm >= 0 && nm < board[0].length()
               && (board[nn].charAt(nm) != 'D'))
            {    
                tn = nn;
                tm = nm;
            // 새로운 이동좌표가 조건을 충족하지 못하는 경우 바로 반복문 중지
            }else{
                break;
            }
        }
        // while 문에서 정지할 좌표를 찾지 못한 이상현상
        return new int[]{tn,tm};
    }
    
    // 제시한 오브젝트의 좌표를 탐색하는 매서드
    private int[] findObject(String[] board, char target){
        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0 ; j < board[0].length() ; j++){
                if( board[i].charAt(j) == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}