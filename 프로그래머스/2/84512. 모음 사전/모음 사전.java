import java.util.*;

class Solution {
    // word : 탐색 대상 단어
    // word 는 AEIOU 로만 이루어져 있음 -> 사실상 5진수
    // 길이는 1 ~ 5
    public int solution(String word) {
        // 사전에 기입된 색인 순서
        String AEIOU = "AEIOU";
        int answer = 0;
        // word.length();
        String[] sliced = word.split("");
        
        for (int i = 0; i < word.length(); i++) {
            // 현재 자릿수의 알파벳이 AEIOU에서 몇 번째인지(0~4)
            int digit = AEIOU.indexOf(word.charAt(i));
            
            // 현재 자릿수의 값 계산
            // 5^(5-i)에서 1을 빼는 이유는 빈 자리도 하나의 경우(0)로 치기 때문
            answer += digit * (Math.pow(5, 5-i) - 1) / 4 + 1;
        }
                
        return answer;
    }
}