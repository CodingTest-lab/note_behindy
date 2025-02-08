import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i = 0; i < score.length; i++) {
            // 새로운 점수 추가
            pq.add(score[i]);
            
            // k개 이상이면 가장 작은 값 제거
            if(pq.size() > k) {
                pq.poll();
            }
            
            // 현재 명예의 전당 최하위 점수 저장
            answer[i] = pq.peek();
        }
        
        return answer;
    }
}