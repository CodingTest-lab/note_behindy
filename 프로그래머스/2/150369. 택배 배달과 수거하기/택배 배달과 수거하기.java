class Solution {
    // cap : 최대 적재량
    // n 집 수 -> deliveries.len, pickups.len
    // deliveries : 배달 대상 배열
    // pickups : 수거할 택배 상자 배열
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliver = 0;
        int pickup = 0;
        
        // 가장 먼 집부터 처리
        for (int i = n - 1; i >= 0; i--) {
            deliver += deliveries[i];
            pickup += pickups[i];
            
            // 현재 위치까지 왕복해야 하는 횟수 계산
            int cnt = 0;
            while (deliver > 0 || pickup > 0) {
                deliver -= cap;
                pickup -= cap;
                cnt++;
            }
            
            // 왕복 거리 계산
            if (cnt > 0) {
                answer += (i + 1) * 2 * cnt;
            }
        }
        
        return answer;
    }
}
