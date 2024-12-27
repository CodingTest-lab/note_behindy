import java.util.*;

class Solution {
    // m,n 당구대 공간
    // startX,Y 공 시작위치
    // balls 타겟 위치
    // 공 -> 타겟을 원쿠션을 통해 노려야 함, 거리의 제곱을 배열로 반환
    // 각 좌표는 좌 하단이 원점
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int[] start = {startX, startY};
        int[] board = {m,n};
        for(int i = 0; i < balls.length; i++){
            answer[i] = impact(start, balls[i], board);
        }        
        return answer;
    }
    
    // 시작점, 종료점, 당구대 정보를 입력받아 네개의 벽에 원쿠션을 시도, 최소값을 반환   
    private int impact(int[] start, int[] end, int[] board){
       int result = Integer.MAX_VALUE;
       
       // 위쪽 벽에 충돌 - 두 점의 x 좌표가 같고 시작점이 목표점보다 아래에 있으면 불가능
       if(!(start[0] == end[0] && start[1] < end[1])) {
           int temp = (start[0] - end[0]) * (start[0] - end[0]) + 
                     (2 * board[1] - start[1] - end[1]) * (2 * board[1] - start[1] - end[1]);
           result = Math.min(result, temp);
       }
       
       // 아래쪽 벽에 충돌 - 두 점의 x 좌표가 같고 시작점이 목표점보다 위에 있으면 불가능
       if(!(start[0] == end[0] && start[1] > end[1])) {
           int temp = (start[0] - end[0]) * (start[0] - end[0]) + 
                     (start[1] + end[1]) * (start[1] + end[1]);
           result = Math.min(result, temp);
       }

       // 왼쪽 벽에 충돌 - 두 점의 y 좌표가 같고 시작점이 목표점보다 오른쪽에 있으면 불가능
       if(!(start[1] == end[1] && start[0] > end[0])) {
           int temp = (start[0] + end[0]) * (start[0] + end[0]) + 
                     (start[1] - end[1]) * (start[1] - end[1]);
           result = Math.min(result, temp);
       }
       
       // 오른쪽 벽에 충돌 - 두 점의 y 좌표가 같고 시작점이 목표점보다 왼쪽에 있으면 불가능
       if(!(start[1] == end[1] && start[0] < end[0])) {
           int temp = (2 * board[0] - start[0] - end[0]) * (2 * board[0] - start[0] - end[0]) + 
                     (start[1] - end[1]) * (start[1] - end[1]);
           result = Math.min(result, temp);
       }
       
       return result;
   }
}