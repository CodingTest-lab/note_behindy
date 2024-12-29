class Solution {
    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle.length];
        
        // 첫 번째 값 초기화
        dp[0][0] = triangle[0][0];
        
        // 아래로 내려가면서 각 위치까지의 최대합을 계산
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {  // 맨 왼쪽
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                } else if (j == i) {  // 맨 오른쪽
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                } else {  // 중간 위치
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                }
            }
        }
        
        // 마지막 줄에서 최대값 찾기
        int answer = 0;
        for (int j = 0; j < triangle.length; j++) {
            answer = Math.max(answer, dp[triangle.length-1][j]);
        }
        
        return answer;
    }
}