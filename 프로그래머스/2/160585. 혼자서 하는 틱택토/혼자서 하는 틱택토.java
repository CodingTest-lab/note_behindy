class Solution {
    public int solution(String[] board) {
        // 승리 확인
        boolean oWin = isWin(board, 'O');
        boolean xWin = isWin(board, 'X');
        
        // O와 X의 개수 세기
        int oCnt = 0;
        int xCnt = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i].charAt(j) == 'O') oCnt++;
                else if(board[i].charAt(j) == 'X') xCnt++;
            }
        }
        
        // 불가능한 경우들 체크
        // 1. O와 X가 동시에 이긴 경우
        if(oWin && xWin) return 0;
        
        // 2. O가 이겼을 때는 O가 X보다 1개 많아야 함
        if(oWin && oCnt - xCnt != 1) return 0;
        
        // 3. X가 이겼을 때는 O와 X의 개수가 같아야 함
        if(xWin && oCnt != xCnt) return 0;
        
        // 4. X가 O보다 많거나, O가 X보다 2개 이상 많은 경우
        if(xCnt > oCnt || oCnt > xCnt + 1) return 0;
        
        return 1;
    }
    
    // 승리 여부 확인
    private boolean isWin(String[] board, char player) {
        // 가로 확인
        for(int i = 0; i < 3; i++) {
            if(board[i].charAt(0) == player &&
               board[i].charAt(1) == player &&
               board[i].charAt(2) == player) return true;
        }
        
        // 세로 확인
        for(int i = 0; i < 3; i++) {
            if(board[0].charAt(i) == player &&
               board[1].charAt(i) == player &&
               board[2].charAt(i) == player) return true;
        }
        
        // 대각선 확인
        if(board[0].charAt(0) == player &&
           board[1].charAt(1) == player &&
           board[2].charAt(2) == player) return true;
        
        if(board[0].charAt(2) == player &&
           board[1].charAt(1) == player &&
           board[2].charAt(0) == player) return true;
           
        return false;
    }
}