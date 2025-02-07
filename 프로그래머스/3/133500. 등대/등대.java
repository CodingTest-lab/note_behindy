import java.util.*;

class Solution {
    List<Integer>[] graph;
    int[][] dp;
    boolean[] visited;
    
    public int solution(int n, int[][] lighthouse) {
        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 양방향 간선 추가
        for(int[] edge : lighthouse) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        
        // DP 배열과 방문 배열 초기화
        dp = new int[n + 1][2]; // [노드번호][켜짐/꺼짐]
        visited = new boolean[n + 1];
        
        // 1번 노드부터 시작 (루트로 설정)
        int[] result = dfs(1);
        return Math.min(result[0], result[1]);
    }
    
    private int[] dfs(int node) {
        visited[node] = true;
        int turnOn = 1;  // 현재 노드를 켰을 때
        int turnOff = 0; // 현재 노드를 껐을 때
        boolean needLight = false; // 자식 노드 중 불이 꺼진 노드가 있는지
        
        for(int next : graph[node]) {
            if(visited[next]) continue;
            
            int[] childResult = dfs(next);
            // 자식 노드가 꺼져있으면 현재 노드는 반드시 켜져야 함
            if(childResult[1] == -1) needLight = true;
            
            // 현재 노드를 켰을 때: 자식 노드는 켜도 되고 꺼도 됨
            turnOn += Math.min(childResult[0], childResult[1] == -1 ? childResult[0] : childResult[1]);
            
            // 현재 노드를 껐을 때: 자식 노드는 반드시 켜져야 함
            turnOff += childResult[0];
        }
        
        // 자식 노드 중 불이 꺼진 노드가 있으면 현재 노드는 반드시 켜져야 함
        if(needLight) turnOff = -1;
        
        return new int[]{turnOn, turnOff};
    }
}