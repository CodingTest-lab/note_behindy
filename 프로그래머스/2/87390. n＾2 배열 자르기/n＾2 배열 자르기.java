class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left + 1)];
        
        // 5x5의 경우에
        // 4번째 줄{4,4,4,4,5}
        // 3번째 줄{3,3,3,4,5}
        // nxn 의 k번째 줄{k,k,k,k,k+1,k+2....n};
        for(int i = 0; i < answer.length; i++) {
            long currentPos = left + i;
            int row = (int)(currentPos / n);
            int col = (int)(currentPos % n);
            answer[i] = Math.max(row, col) + 1;
        }
        
        return answer;
    }
}