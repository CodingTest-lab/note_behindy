import java.util.*;

class Solution {
    private int nodes = 0; // 전체 정점의 개수
    private List<List<Integer>> graphs = new ArrayList<>(); // 각 정점의 간선 정보
    private List<Integer> entrances = new ArrayList<>(); // 각 정점으로 들어오는 간선의 개수
    private boolean[] visited; // 방문 여부

    public int[] solution(int[][] edges) {
        int origin = 0, donut = 0, stick = 0, eight = 0;
        graphs = new ArrayList<>();
        entrances = new ArrayList<>();
        
        // 1. 전체 정점 갯수 파악 - > 정점 번호의 최대치를 탐색
        for (int[] edge : edges) {
            nodes = Math.max(nodes, Math.max(edge[0], edge[1]));
        }
        
        // 2. 그래프 초기화
        for (int i = 0; i <= nodes; i++) {
            graphs.add(new ArrayList<>());
            entrances.add(0);
        }
        
        visited = new boolean[nodes + 1];

        // 3. 간선 정보 입력
        for (int[] edge : edges) {
            graphs.get(edge[0]).add(edge[1]);
            entrances.set(edge[1], entrances.get(edge[1]) + 1);
        }
        
        // 4. 그래프 시작점 탐색
        origin = findOrigin();
        List<Integer> startWay = graphs.get(origin);

        // 5. 시작점과 연결된 정점의 간선 제거
        for (int node : startWay) {
            entrances.set(node, entrances.get(node) - 1);
        }
        
        // 6. 탐색 시작
        for (int node : startWay) {
            if (!visited[node]) {
                int type = exploreGraph(node);
                if (type == 0) donut++;
                else if (type == 1) stick++;
                else if (type == 2) eight++;
            }
        }
        
        int[] answer = {origin, donut, stick, eight};
        return answer;
    }

    private int findOrigin() {
        for (int i = 1; i <= nodes; i++) {
            if (graphs.get(i).size() >= 2 && entrances.get(i) == 0) {
                return i;
            }
        }
        return -1;
    }
    
    // 그래프 탐색 및 유형 판별
    private int exploreGraph(int origin) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(origin);
        visited[origin] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (graphs.get(node).size() == 2 && entrances.get(node) == 2) {
                return 2; // 8 형태
            } else if (graphs.get(node).isEmpty()) {
                return 1; // 막대 형태
            } else {
                for (int neighbor : graphs.get(node)) {
                    if (!visited[neighbor]) {
                        queue.add(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }
        }
        return 0; // 도넛 형태
    }
}