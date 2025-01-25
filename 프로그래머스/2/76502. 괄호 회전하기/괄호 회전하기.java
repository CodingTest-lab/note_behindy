import java.util.*;
// (), {}, [] 모두 올바름 

class Solution {
    // 모든 경우의 수 탐색 요구
    // s 1~ 1000 의 String
    public int solution(String s) {
        int answer = 0;
        
        // 문자열을 회전시켜 검사 대상이 되는 문자열을 만드는 부분 
        for(int i = 0 ; i < s.length(); i ++){
            String left = s.substring(0, i); 
            String right = s.substring(i,s.length());
            StringBuffer sbf = new StringBuffer();
            sbf.append(right);
            sbf.append(left);
            String concat = sbf.toString();
            if(checkString(concat)){
                answer++;
            }
        }
        return answer;
    }
    
    private boolean checkString(String target) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for(char temp : target.toCharArray()) {
            if(temp == '(' || temp == '{' || temp == '[') {
                stack.push(temp);
            } else {
                if(stack.isEmpty()) return false;

                char last = stack.pop();
                if((temp == ')' && last != '(') || 
                   (temp == '}' && last != '{') || 
                   (temp == ']' && last != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}