import java.util.*;

class Solution {
    public int solution(String s) {
        String[] expression = s.split(" ");
        
        Stack<String> stk = new Stack<>();
        
        // 직전 실행 대상을 기억할 공간
        int memory_number = 0;
        
        // 정답을 저장할 공간
        int answer = 0;
        
        // stack에 넣기
        for(int i = expression.length - 1; i >= 0; i--) {
            stk.push(expression[i]);   
        }
        
        // 꺼내서 계산하기
        while(!stk.isEmpty()) {
            String current = stk.pop();
            
            if(!current.equals("Z")) {
                memory_number = Integer.parseInt(current);
                answer += memory_number;
            } else {
                answer -= memory_number;
            }
        }
        
        return answer;
    }
}