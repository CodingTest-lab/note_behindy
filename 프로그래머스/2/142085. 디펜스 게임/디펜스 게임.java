import java.util.*;

class Solution {
    // n - 초기 병사 수
    // k - 무적권 사용 횟수
    // enemies - 적 배열
    // enemy - 마리의 적
    // 가장 멀리갈 수 있는 라운드의 수
    public int solution(int n, int k, int[] enemies) {
        // 최대 힙으로 구현된 우선순위 큐
       PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
       
       long remainSoldiers = n;  // 남은 병사 수 (int 범위 초과 가능성)
       
       // 라운드 진행
       for(int i = 0; i < enemies.length; i++) {
           pq.offer(enemies[i]);   // 현재 라운드의 적 수를 큐에 추가
           remainSoldiers -= enemies[i];  // 일단 병사로 처리했다고 가정
           
           // 병사가 부족해지면 무적권 사용 시도
           if(remainSoldiers < 0) {
               // 무적권이 남아있고, 큐가 비어있지 않으면
               if(k > 0 && !pq.isEmpty()) {
                   // 가장 큰 적의 수를 무적권으로 처리
                   remainSoldiers += pq.poll();
                   k--;
               } else {
                   // 무적권도 없고 병사도 부족하면 게임 오버
                   return i;
               }
           }
       }
       
       // 모든 라운드를 클리어한 경우
       return enemies.length;
   }
}