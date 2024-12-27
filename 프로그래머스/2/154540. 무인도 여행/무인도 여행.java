import java.util.*;

class Solution {
    // 이동에 활용하기 위한 방향 벡터들
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    // 위치 정보를 클래스로 구체화
    static class Position {
        int[] pos;
        char food;
        
        public Position(int[] pos, char food) {
            this.pos = pos;
            this.food = food;
        }
    }
    
    public int[] solution(String[] maps) {
        List<Integer> island = island(maps);
        if (island.isEmpty()) {
            return new int[]{-1};
        }
        // List를 배열로 변환하고 정렬
        return island.stream()
            .sorted()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    // 섬끼리 분류해서 넣기
    //List
    //      섬1. List <pos1, pos2, ....>
    //      섬2. List <posa, posb, ....>
    //      섬n. List <posA, posB, ....>
    //    >
    private List<Integer> island(String[] maps) {
        // 방문 여부를 체크하기 위해 좌표공간을 마련
        // 방문기록은 탐색함수 스코프 내에서 활용
        int n = maps.length;
        int m = maps[0].length();
        int[][] visited = new int[n][m];
        List<Integer> result = new ArrayList<>();
        
        // 탐색 시작
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // 방문하지 않았고, 'X' 가 아닌경우에 새로운 무인도의 좌표라고 인식하고 bfs를 응용해 탐색을 시도
                if(visited[i][j] != 1 && maps[i].charAt(j) != 'X') {
                    // 초기좌표와 방문기록을 저장!
                    // 새로운 섬에 도달할 때 마다 새로 큐를 선언
                    Queue<Position> que = new LinkedList<>();
                    visited[i][j] = 1;
                    que.offer(new Position(new int[]{i,j}, maps[i].charAt(j)));
                    int tempIsland = Character.getNumericValue(maps[i].charAt(j));  // 초기 위치의 값도 더해줌
                    
                    // 해당 섬을 탐색 시작
                    while(!que.isEmpty()) {
                        // 현재 위치를 꺼내기
                        Position current = que.poll();
                        
                        // 네 방향벡터로 이동을 시도함
                        for(int k = 0; k < 4; k++) {
                            int nx = current.pos[0] + dx[k];
                            int ny = current.pos[1] + dy[k];
                            
                            if(nx >= 0 && nx < n && ny >= 0 && ny < m  // 격자 안에 존재하는지
                               && maps[nx].charAt(ny) != 'X'  // 바다 위가 아닌지
                               && visited[nx][ny] != 1) {  // 이미 방문한 지역인지 아닌지
                                visited[nx][ny] = 1;
                                que.offer(new Position(new int[]{nx, ny}, maps[nx].charAt(ny)));
                                tempIsland += Character.getNumericValue(maps[nx].charAt(ny));
                            }
                        }
                    }
                    result.add(tempIsland);
                }
            }
        }
        return result;
    }
}