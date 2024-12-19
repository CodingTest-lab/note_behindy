class Solution {
    public int solution(String[][] board, int h, int w) {
        int size = board.length;  // board의 크기
        return countColor(board, h, w, size);
    }
    
    public int countColor(String[][] board, int h, int w, int size) {
        String centerColor = board[h][w];
        int result = 0;
        // 좌
        if (w > 0) {
            if (board[h][w-1].equals(centerColor)) {
                result += 1;
            }
        }
        
        // 우
        if (w < size-1) {
            if (board[h][w+1].equals(centerColor)) {
                result += 1;
            }
        }
        // 상
        if (h > 0) {
            if (board[h-1][w].equals(centerColor)) {
                result += 1;
            }
        }
        // 하
        if (h < size-1) {
            if (board[h+1][w].equals(centerColor)) {
                result += 1;
            }
        }
        return result;
    }
}