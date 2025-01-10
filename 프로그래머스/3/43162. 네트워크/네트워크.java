import java.util.*;

class Solution {
    
    public int solution(int n, int[][] computers) {
        // n 컴퓨터의 개수
        // computers 연결정보
        int answer = 0;                 // 네트워크의 개수
        // 모든 컴퓨터를 순회 각 컴퓨터가 어떤 네트워크에 속해있는지 확인하면 가능
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {       
                // 탐색 대기열
                Queue<Integer> queue = new LinkedList<>();
                // 초기정보 입력
                queue.offer(i);
                visited[i] = true;
                
                // 탐색 시작
                while (!queue.isEmpty()) {
                    int current = queue.poll();

                    for (int j = 0; j < computers.length; j++) {
                        if (computers[current][j] == 1 && !visited[j]) {
                            queue.offer(j);
                            visited[j] = true;
                        }
                    }
                }
                answer++;
            }
        }
        
        return answer;
    }
}