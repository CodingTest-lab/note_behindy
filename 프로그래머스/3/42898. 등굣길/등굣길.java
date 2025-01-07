import java.util.*;
// 격자의 크기는 1~100, 직사각형


class Solution {
    // 1000000007
    // 오른쪽과 아래로만 움직임
    // 최단거리, 각 단위벡터로 m , n  번 이동하면 완성
    
    public int solution(int m, int n, int[][] puddles) {
        int MOD = 1000000007;
        
        // DP 배열 초기화 - 행렬 순서 수정
        int[][] map = new int[m + 1][n + 1];
        
        // 물웅덩이 표시
        for (int[] puddle : puddles) {
            map[puddle[0]][puddle[1]] = -1;
        }

        // 시작점 설정
        map[1][1] = 1;

        // DP로 경로 계산
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue;
                
                if (map[i][j] == -1) {
                    map[i][j] = 0;
                    continue;
                }
                
                int up = (i == 1) ? 0 : map[i - 1][j];
                int left = (j == 1) ? 0 : map[i][j - 1];
                
                map[i][j] = ((up % MOD) + (left % MOD)) % MOD;
            }
        }
        
        return map[m][n];
    }
}