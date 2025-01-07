import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        // 마지막은 항상 0 이므로 제외하고 계산
        // 반복에서 인덱스 + 1 을 활용하기위해 마지막은 제외하고 계산
        for(int i = 0; i < prices.length - 1; i++) {
            // 간격을 저장할 공간
            int interval = 0;
            for(int j = i + 1; j < prices.length; j++) {
                interval++;
                if(prices[i] > prices[j]) {
                    break;
                }
            }
            answer[i] = interval;
        }
        answer[prices.length-1] = 0;
        
        return answer;
    }
}