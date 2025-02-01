class Solution {
    private int answer = 0;
    private int row;
    private int col;
    // board : 건물의 배치와 각 건물의 내구도
    // skill : [적군/아군, 행 시작, 행 끝, 열 시작, 열 끝, 회복/피해 정도]
    public int solution(int[][] b, int[][] skills) {
        row = b.length;
        col = b[0].length;
                
        // skill 마다 board를 영향주어 변화시키는 부분
        // 누적합을 위한 배열 (원래 배열보다 1칸씩 크게 만듦)
        long[][] board = new long[row + 1][col + 1];
        
        prefixSumBoard(board, skills);
        
        // 완성된 board 를 순회하여 내구도가 남아있는 건물을 찾는 부분
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(b[i][j] + board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
    
    private void prefixSumBoard(long[][] board, int[][] skills){
        // 각 스킬의 효과를 누적합 배열에 기록
        for (int[] skill : skills) {
            int degree = skill[0] == 1 ? -skill[5] : skill[5];
            
            // 누적합 배열에 변화량 기록
            board[skill[1]][skill[2]] += degree;
            board[skill[1]][skill[4] + 1] -= degree;
            board[skill[3] + 1][skill[2]] -= degree;
            board[skill[3] + 1][skill[4] + 1] += degree;
        }
        
        // 행 방향 누적합 계산
        for (int i = 0; i < row; i++) {
            for (int j = 1; j < col; j++) {
                board[i][j] += board[i][j-1];
            }
        }
        
        // 열 방향 누적합 계산
        for (int j = 0; j < col; j++) {
            for (int i = 1; i < row; i++) {
                board[i][j] += board[i-1][j];
            }
        }
    }
}
