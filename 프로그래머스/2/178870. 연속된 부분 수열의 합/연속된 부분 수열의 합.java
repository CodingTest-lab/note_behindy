class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0;
        int right = 0;
        int sum = sequence[0];
        int minLength = sequence.length;
        int[] answer = new int[]{0, sequence.length-1};
        
        while(right < sequence.length) {
            // filter 1 연속된 부분 수열의 합이 k 에 도달했는지 확인
            if(sum == k) {
                
                // filter 1-1
                // 현재 조건이 최소 길이임이 판명된 경우 현재 조건을 기록
                // 이전과 동일한 경우 이전 기록을 사용
                if(right - left < minLength) {
                    minLength = right - left;
                    answer[0] = left;
                    answer[1] = right;
                }

                // filter 1-2-1 
                // k 에 도달했지만 최소길이를 만족하지 못한 경우엔 바로 진
                // k 에 도달했으나 더 짧은 길이의 수열이 있을 수 있으므로 right를 이동하여 탐색 진행
                if(right + 1 < sequence.length) {
                    sum += sequence[++right];
                // filter 1-2-2
                // 탐색이 종료된 경우 반복문 탈출
                } else {
                    break;
                }
            // filter 2 연속된 부분 수열의 합이 k 에 도달하지 못한 경우 확인
            } else if(sum < k) {
                // filter 2-1
                // k에 도달하지 못했으므로 right를 이동하여 합을 증가시켜 탐색 진행
                if(right + 1 < sequence.length) {
                    sum += sequence[++right];
                // filter 2-2
                // 탐색이 종료된 경우 반복문 탈출
                } else {
                    break;
                }
            // filter 3 연속된 부분 수열의 합이 k 를 초과한 경우 확인
            // 현재 기록을 제외
            } else {
                sum -= sequence[left++];
            }
        }
        
        return answer;
    }
}
