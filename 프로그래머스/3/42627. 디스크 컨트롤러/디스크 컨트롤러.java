import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // 작업 요청 시간 순으로 정렬
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        // 작업 우선순위 : 작업의 소요시간이 짧은 것, 작업의 요청 시각이 빠른 것, 작업의 번호가 작은 것 순
        // 작업 소요 시간 순으로 정렬되는 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        int index = 0;      // jobs 배열의 인덱스
        int currentTime = 0; // 현재 시간
        int totalTime = 0;  // 전체 소요 시간
        int count = 0;      // 처리된 작업 수
        
        while(count < jobs.length) {
            // 현재 시간까지 들어온 모든 요청을 큐에 넣음
            while(index < jobs.length && jobs[index][0] <= currentTime) {
                pq.offer(jobs[index++]);
            }
            
            if(pq.isEmpty()) {
                // 큐가 비어있다면 다음 작업의 요청시간으로 이동
                currentTime = jobs[index][0];
            } else {
                // 가장 짧은 작업 처리
                int[] current = pq.poll();
                totalTime += currentTime - current[0] + current[1]; // 대기시간 + 처리시간
                currentTime += current[1];
                count++;
            }
        }
        
        return totalTime / jobs.length;
    }
}