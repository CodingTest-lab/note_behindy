import java.util.*;

class Solution {
    boolean solution(String s) {
        
        // 들어온 문자열 s 를 char 로 잘라서 보관하기
        char[] expression = s.toCharArray();
        // 일단 아무튼 stk 선언해보기
        Stack<Character> stk = new Stack<>();
        
        for(int i = 0; i < expression.length; i++) {
            // 열린 괄호는 조건없이 넣기! => count 처럼 활용 가능 , 열린 조건 확인 가능
            if (expression[i] == '(') {        
                stk.push(expression[i]);
            // 닫힌 괄호의 경우에는 조건 판단이 필요 
            } else {
                // 열린괄호가 없는 경우에는 새로 넣을 수 없음
                if(stk.size()!= 0) {
                    // 열린 괄호를 스택에서 추출 - 논리적으로 괄호가 닫혔다고 인식
                    stk.pop();
                // 반복문과 함수의 조기 종료
                } else {
                    return false;
                }
            }       
        }
        
        // java의 경우 truthy, falsy 를 지원하지 않음,
        // 반복문 종료 시점에서 함수가 반환할 값을 판별함
        if (stk.size() == 0){
            return true;
        }
        return false;
    }
}