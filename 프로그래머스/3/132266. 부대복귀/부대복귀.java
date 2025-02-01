import java.util.*;

// // 정방향 bfs
// class Solution {
//     public int[] solution(int n, int[][] roads, int[] sources, int destination) {
//         int[] answer = new int[sources.length];
//         // <지역, 연결지역배열> 으로 정리할 예정
//         // set 으로 중복을 처리하여 활용할까?
//         HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
//         for(int[] road : roads){
//             // 각 지역 번호에 대한 HashSet이 없다면 새로 생성
//             map.putIfAbsent(road[0], new ArrayList<Integer>());
//             map.putIfAbsent(road[1], new ArrayList<Integer>());

//             // 양방향 연결 추가
//             if(!map.get(road[0]).contains(road[1])){
//                 map.get(road[0]).add(road[1]);
//             }
//             if(!map.get(road[1]).contains(road[0])){
//                 map.get(road[1]).add(road[0]);    
//             }
//         }
        
//         for(int i = 0 ; i < sources.length ; i ++){
//             // start, end, map 을 넣어서 확인
//             answer[i] = returnHome(n, sources[i], destination, map);
//         }
        
//         return answer;
//     }
    
//     // 최단거리 또는 돌아갈 수 없는 경우 -1 을 반환하는 매서드
//     private int returnHome(int n, int start, int end, HashMap<Integer, ArrayList<Integer>> map ){
//         int cnt = 0;
        
//         // 조기종료 조건 1
//         if (!map.containsKey(start)) {
//             // 시작점이 목적지와 같은 경우만 0 반환, 아니면 -1
//             return start == end ? 0 : -1;
//         }
        
//         Queue<Integer> que = new LinkedList<>();
//         int[] visited = new int[n+1];
        
//         que.offer(start);
//         visited[start] = 0;
        
//         while(!que.isEmpty()){
//             int curr = que.poll();
//             // 종료조건 확인
//             if( curr == end ){
//                 return visited[curr];
//             }
            
//             for(int i = 0 ; i < map.get(curr).size() ; i ++){
//                 int next = map.get(curr).get(i);
//                 // 첫 방문이 아닌 경우 종료
//                 if(visited[next] != 0){
//                     continue;
//                 }  
//                 visited[next] = visited[curr] + 1;
//                 que.offer(next);
//             }
//         }
//         // while 내에서 답을 찾지 못하고 이곳에 도달하면 경로가 없다는 의미 -> -1 반환
//         return -1;   
//     }
// }

// 역방향 bfs
// 이전의 정방향 탐색이 시간복잡도가 높았기 때문에 목적지로부터 각 지점까지 거리를 반대로 구하는 bfs를 구현
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        for(int[] road : roads){
            // 각 지역 번호에 대한 HashSet이 없다면 새로 생성
            map.putIfAbsent(road[0], new ArrayList<Integer>());
            map.putIfAbsent(road[1], new ArrayList<Integer>());

            // 양방향 연결 추가
            if(!map.get(road[0]).contains(road[1])){
                map.get(road[0]).add(road[1]);
            }
            if(!map.get(road[1]).contains(road[0])){
                map.get(road[1]).add(road[0]);    
            }
        }
        int[] returnArray = returnHome(n, destination, map);
        
        for(int i = 0 ; i < sources.length ; i ++){
            // start, end, map 을 넣어서 확인
            answer[i] = returnArray[sources[i]];
        }
        
        return answer;
    }
    
    // 최단거리 또는 돌아갈 수 없는 경우 -1 을 반환하는 매서드
    private int[] returnHome(int n, int end, HashMap<Integer, ArrayList<Integer>> map ){
        int[] visited = new int[n+1];
        Arrays.fill(visited, -1);
        
        Queue<Integer> que = new LinkedList<>();
        que.offer(end);        
        visited[end] = 0;
        
        while(!que.isEmpty()){
            int curr = que.poll();
            // 반복 조기종료 조건
            if(!map.containsKey(curr)) continue;
            
            for(int next : map.get(curr)){
                if(visited[next] != -1) continue;
                que.offer(next);
                visited[next] = visited[curr] + 1;
            }
        }
        
        return visited;
    }
}