import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int count = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 모든 스코빌 지수를 우선순위 큐에 전부 넣기
        for (int scovilleValue : scoville) {
            pq.offer(scovilleValue);
        }
        
        // 가장 앞에서 꺼낸 스코빌지수가 조건보다 작고, pq의 길이가 2보다 큰 경우에만 실행
        while (pq.peek() < K && pq.size() >= 2) {
            // 가장 맵지 않은 두 음식 꺼내기
            int first = pq.poll();
            int second = pq.poll();
            
            // 새로운 스코빌 지수 계산
            int newScoville = first + (second * 2);
            
            // 새로운 음식을 큐에 넣기
            pq.offer(newScoville);
            count++;
        }
        
        return pq.peek() >= K ? count : -1;
    }
}