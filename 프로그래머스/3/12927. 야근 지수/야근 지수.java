import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        // 내림차순으로 꺼내오기! 꺼내면 가장 큰 값이 나옴
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        // 배열의 값들을 우선순위 큐에 넣기
        for(int work : works) {
            pq.offer(work);
        }
        
        // n 시간동안 일 할 예정
        for(int i = 0; i < n; i++) {
            if(pq.isEmpty() || pq.peek() <= 0) break;
            
            // 가장 큰 값을 1씩 줄여나감
            int max = pq.poll();
            pq.offer(max - 1);
        }
        
        return fatigue(pq);
    }
    
    // 남은 일을 기반으로 피로도를 계산하는 함수
    public long fatigue(PriorityQueue<Integer> pq) {
        long fatigueParam = 0;
        for(int work : pq) {
            fatigueParam += (long)work * work;
        }
        return fatigueParam;
    }
}