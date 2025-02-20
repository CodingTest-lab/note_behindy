class Solution {
    public int solution(int n, int w, int num) {
        // 전체 층수 계산
        int height = (n + w - 1) / w;
        
        // 2차원 배열로 상자 배치
        int[][] boxes = new int[height][w];
        int current = 1;
        
        // 상자 배치하기
        for(int i = 0; i < height; i++) {
            if(i % 2 == 0) {
                // 짝수층: 왼쪽 -> 오른쪽
                for(int j = 0; j < w && current <= n; j++) {
                    boxes[i][j] = current++;
                }
            } else {
                // 홀수층: 오른쪽 -> 왼쪽
                for(int j = w-1; j >= 0 && current <= n; j--) {
                    boxes[i][j] = current++;
                }
            }
        }
        // 목표 상자의 위치 찾기
        int targetRow = -1;
        int targetCol = -1;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < w; j++) {
                if(boxes[i][j] == num) {
                    targetRow = i;
                    targetCol = j;
                    break;
                }
            }
            if(targetRow != -1) break;
        }
        // 꺼내야 하는 상자 개수 계산
        int answer = 1;  // 목표 상자 포함
        for(int i = targetRow + 1; i < height; i++) {
            if(boxes[i][targetCol] != 0) {
                answer++;
            }
        }
        return answer;
    }
}