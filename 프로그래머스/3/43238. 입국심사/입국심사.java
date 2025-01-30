class Solution {
    // 기다리는 사람 수
    // time : 각 심사관이 한명을 심사하는데 소요되는 시간 
    public long solution(int n, int[] times) {
        // 최소 시간과 최대 시간 설정
        long left = 1;
        long right = (long) times[0] * n; // 가장 긴 경우: 모든 사람이 가장 첫 심사관에게 가는 경우
        
        // 가장 긴 심사 시간 찾기
        for (int time : times) {
            right = Math.max(right, (long) time * n);
        }
        
        long answer = right; // 최악의 경우로 초기화
        
        // 이분 탐색 수행
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0; // 심사 가능한 사람 수
            
            // 주어진 시간(mid) 동안 각 심사관이 심사할 수 있는 사람의 수 계산
            for (int time : times) {
                sum += mid / time;
                if (sum >= n) break; // 이미 n명을 넘었다면 더 계산할 필요 없음
            }
            
            if (sum >= n) { // 더 많은 사람을 처리할 수 있다면
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else { // 모든 사람을 처리할 수 없다면
                left = mid + 1;
            }
        }
        
        return answer;
    }
}