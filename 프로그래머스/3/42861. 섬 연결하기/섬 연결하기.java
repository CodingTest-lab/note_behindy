import java.util.*;

class Solution {
    // Union-Find를 위한 부모 배열
    int[] parent;
    
    // Find 연산
    public int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);  // 경로 압축
    }
    
    // Union 연산
    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px < py) parent[py] = px;
        else parent[px] = py;
    }
    
    public int solution(int n, int[][] costs) {
        // 부모 배열 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        // 비용을 기준으로 오름차순 정렬
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
        
        int totalCost = 0;
        int connectCount = 0;
        
        // 크루스칼 알고리즘 수행
        for (int[] edge : costs) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];
            
            // 두 섬이 아직 연결되지 않았다면
            if (find(from) != find(to)) {
                union(from, to);  // 연결
                totalCost += cost;  // 비용 추가
                connectCount++;  // 연결 카운트 증가
                
                // 모든 섬이 연결되었다면 종료
                if (connectCount == n - 1) break;
            }
        }
        
        return totalCost;
    }
}