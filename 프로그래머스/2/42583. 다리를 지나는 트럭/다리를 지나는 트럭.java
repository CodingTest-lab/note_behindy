import java.util.*;

class Solution {
    // bridge_length : 최대 올라갈 수 있는 차량 수
    // 트럭 한대가 건너려면 bridge_length + 1 초 필요 
    // weight : 최대 견딜 수 있는 하중
    // truck_weights : 트럭 무게 배열..
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 다리는 순서대로 건너야 함
        Queue<Integer> que = new LinkedList<>();
        // 다리를 큐로 표현 
        Queue<Integer> bridge = new LinkedList<>();
        // 초기 다리 위에는 아무것도 없음을 표현!
        for(int i = 0 ; i < bridge_length ; i ++){
            bridge.offer(0);
        }

        // 큐에 넣어서 큰 준비!
        for(int truck : truck_weights){
            que.offer(truck);
        }
        
        int answer = 0;
        int now_weight = 0;
        
        // 반복 종료시까지 순회
        // 마지막 트럭이 다리에 올라가면 반복 종료
        while(!que.isEmpty()){
            // 턴이 시작되면! 연산 이전에 한칸씩 이동 -> bridge 큐에서 하나씩 제거
            // 제거하면서 now_weight를 업데이트
            // 1초가 흐름!
            answer++;
            now_weight -= bridge.poll();
            
            if(now_weight + que.peek() <= weight) {
                int truck = que.poll();
                bridge.offer(truck);
                now_weight += truck;
            } else {
                bridge.offer(0);
            }
        }
        
        // 마지막 트럭이 다리에 올라간 순간부터 다리길이만큼의 시간이 흐르면 종료
        return answer + bridge_length;
    }
}