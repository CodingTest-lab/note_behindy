import java.util.*;
// 트리 형태로 연결된 송전탑들

class Solution {
    private ArrayList<Integer>[] network;
    // n : 송전탑의 개수
    // wires :  연결관계 [v1, v2] v1,v2 는 연결되어있음( v1, v2 )
    public int solution(int n, int[][] wires) {
        // 초기화
        network = new ArrayList[n + 1];
    
        for (int i = 1; i <= n; i++) {
            network[i] = new ArrayList<>();
        }
        
        for (int[] wire : wires) {
            network[wire[0]].add(wire[1]);
            network[wire[1]].add(wire[0]);
        }
        
        int answer = n;
        
        // 각 전선을 끊어보며 최소 차이 계산
        for (int[] wire : wires) {
            // 한 쪽 서브트리의 노드 수 계산
            int count = findNodes(wire[0], wire[1], n);
            // 다른 쪽 서브트리의 노드 수는 전체 노드 수에서 뺀 것
            int diff = Math.abs(count - (n - count));
            answer = Math.min(answer, diff);
        }
        
        return answer;    
    }
    
    // target 과 연결된 노드의 수를 찾기
    private int findNodes(int start, int excluded, int n) {
        boolean[] visited = new boolean[n + 1];
        return dfs(start, excluded, visited);
    }
    
    // dfs 로 탐색
    private int dfs(int node, int excluded, boolean[] visited) {
        visited[node] = true;
        int count = 1;
        
        for (int next : network[node]) {
            // 끊어진 전선을 통과하지 않고, 방문하지 않은 노드만 탐색
            if (!visited[next] && next != excluded) {
                count += dfs(next, excluded, visited);
            }
        }
        
        return count;
    }
}