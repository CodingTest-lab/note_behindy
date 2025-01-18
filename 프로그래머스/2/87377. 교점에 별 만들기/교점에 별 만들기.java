import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        // 교점을 저장할 Set
        Set<long[]> points = new HashSet<>();
        
        for(int i = 0; i < line.length; i++) {
            for(int j = i + 1; j < line.length; j++) {
                long A = line[i][0];
                long B = line[i][1];
                long C = line[i][2];
                
                long D = line[j][0];
                long E = line[j][1];
                long F = line[j][2];
                
                // 분모가 0인 경우 (평행 또는 일치)
                if(A * E - B * D == 0) continue;
                
                // 교점 구하기
                double x = (double)(B * F - C * E) / (A * E - B * D);
                double y = (double)(C * D - A * F) / (A * E - B * D);
                
                // 정수 좌표만 저장
                if(x == (long)x && y == (long)y) {
                    points.add(new long[]{(long)x, (long)y});
                }
            }
        }
        
        // 최대/최소 좌표 구하기
        long maxX = Long.MIN_VALUE;
        long minX = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        
        for(long[] point : points) {
            maxX = Math.max(maxX, point[0]);
            minX = Math.min(minX, point[0]);
            maxY = Math.max(maxY, point[1]);
            minY = Math.min(minY, point[1]);
        }
        
        // 배열 크기 계산
        int width = (int)(maxX - minX + 1);
        int height = (int)(maxY - minY + 1);
        
        // 결과 배열 초기화
        char[][] board = new char[height][width];
        for(int i = 0; i < height; i++) {
            Arrays.fill(board[i], '.');
        }
        
        // 별 그리기
        for(long[] point : points) {
            int x = (int)(point[0] - minX);
            int y = (int)(maxY - point[1]);
            board[y][x] = '*';
        }
        
        // 결과 변환
        String[] result = new String[height];
        for(int i = 0; i < height; i++) {
            result[i] = new String(board[i]);
        }
        
        return result;
    }
}