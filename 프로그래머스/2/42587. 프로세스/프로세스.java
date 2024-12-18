import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        // 실행대기큐의 길이
        int QLength = priorities.length;
        
        Queue<Integer> que = new LinkedList<>();
        List<Integer> ended_processes = new ArrayList<>();
        
        // 실행 대기 큐 만들기!
        for(int i = 0 ; i < QLength; i ++){
            // 'A' 의 아스키코드 = 65
            // 걍 123456~ 정수로 만들어버리자
            que.offer(i);    
        }
        
        // que가 쪼그라들 때까지 반복
        while (que.size() != 0){
            // 일단 하나 꺼내서 체크하기
            int temp_process = que.poll();
            
           // 현재 프로세스보다 우선순위가 높은 프로세스가 있는지 확인
            boolean checkMaxPriority = false;
            for(int process : que) {
                if(priorities[process] > priorities[temp_process]) {
                    checkMaxPriority = true;
                    break;
                }
            }
            
            // Math.max() 활용해서 우선순위 배열에서 최대값인지 체크하기            
            if(checkMaxPriority){
                // 다시 대기열 마지막에 넣어두기
                que.offer(temp_process);
                // 반복문 중단하고 다음 회차로 진행
                continue;
            }else{
                // 꺼낸거 안넣고 그대로 완료 대기열에 넣기
                ended_processes.add(temp_process);
                priorities[temp_process]=0;
            }
        }
        // que가 없어지면 이제 location이 몇번째 요소에 위치하는지 찾기
        return ended_processes.indexOf(location)+1;
    }
}