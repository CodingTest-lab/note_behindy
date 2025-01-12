import java.util.*;
// 가능한 작업
// case 1. : x + n
// case 2. : x * 2
// case 3. : x * 3

// answer x -> y가 되기 위한 최소 연산 횟수
// 불가능하다면 -1

class Solution {    
    public int solution(int x, int y, int n) {
        HashMap<Integer, Integer> visited = new HashMap<>();
        Queue<Integer> que = new LinkedList<>();
        
        // 초기정보 제공
        visited.put(x, 0);
        que.offer(x);
        
        // bfs 시작
        while(!que.isEmpty()){
            int curr = que.poll();
            
            if(curr == y){
                return visited.get(curr);
            }
            
            int next = 0;
            for(int i =0 ; i< 3; i ++){
                // 각 연산별로 next 계산
                switch(i){
                    case 0: next = curr + n; break;
                    case 1: next = curr * 2; break;
                    case 2: next = curr * 3; break;
                }
                if(next <= y && !visited.containsKey(next)){
                    visited.put(next, visited.get(curr)+1);
                    que.offer(next);
                }   
            }
        }
        
        // y에 도달하지 못한 경우
        return -1;
    }
}