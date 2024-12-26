import java.util.*;
// java 재밌다!
// 과제는 시작하기로 한 시각에 시작
// 새로운 과제시간이 되었을 때, 진행중이던 과제가 있으면
// 잠시 멈춘 과제가 있다면 멈춰둔 과제를 이어서 진행
// 새로 시작 해야하는 과제부터 진행
// 멈춰둔 과제가 여러개일 경우, 가장 최근에 멈춘 과제부터 시작
class Solution {
    // 계획을 별도 클래스로 구현
    static class plan{
        String name;
        int start;
        int demand;
        
        public plan(String name, int start, int demand) {
            this.name = name;
            this.start = start;
            this.demand = demand;
        }
    }
    
    public String[] solution(String[][] plans) {
        ArrayList<String> answer = new ArrayList<>();
        // stack 으로 선언
        // 잠시 정지한 과제들을 임시 보관할 배열
        Stack<plan> stopped = new Stack<>();
        // 정렬된 배열
        plan[] sortedPlans = sorting(plans);
        
        // 과제 수행
        for(int i = 0 ; i < sortedPlans.length - 1 ; i ++){
            plan now = sortedPlans[i];
            plan next = sortedPlans[i+1];
            
            // 이번 과제를 수행할 수 있는 시간 연산
            int timeNext = next.start - now.start;
            
            // 
            if(now.demand <= timeNext) {
                // 현재 과제 완료
                answer.add(now.name);
                int remain = timeNext - now.demand;
                
                // 남은 시간동안 중단된 과제 처리
                while(remain > 0 && !stopped.isEmpty()) {
                    plan stoppedPlan = stopped.pop();
                    if(stoppedPlan.demand <= remain) {
                        answer.add(stoppedPlan.name);
                        remain -= stoppedPlan.demand;
                    } else {
                        stoppedPlan.demand -= remain;
                        stopped.push(stoppedPlan);
                        break;
                    }
                }
            } else {
                // 현재 과제 중단
                now.demand -= timeNext;
                stopped.push(now);
            }
        }
        
        // 마지막 과제 처리
        answer.add(sortedPlans[sortedPlans.length - 1].name);
        
        // 중단된 과제 처리
        while(!stopped.isEmpty()) {
            answer.add(stopped.pop().name);
        }
        
        int resultL = answer.size();
        String[] result = answer.toArray(new String[resultL]);
        
        // 과제가 끝난 순서대로 과목명을 배열에 담아 반환
        return result;
    }
    
    // plans 의 길이 2 ~ 10
    // plans[0] : 과목명
    // plans[1] : 시작시간
    // plans[2] : 소요시간
    private plan[] sorting(String[][] plans){
        plan[] sortedPlans = new plan[plans.length];
        
        // 시간 변환 및 정렬을 위한 초기화
        for(int i = 0; i < plans.length; i++) {
            String[] time = plans[i][1].split(":");
            int start = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            sortedPlans[i] = new plan(plans[i][0], start, Integer.parseInt(plans[i][2]));
        }
        
        // 시작 시간 기준 정렬
        Arrays.sort(sortedPlans, (a, b) -> a.start - b.start);
        
        // 정렬이 완료된 plan 배열을 반환
        return sortedPlans;
    }
}