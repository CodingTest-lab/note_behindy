import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        // 각 날짜를 시작점으로 하여 10일간의 기간을 체크
        for (int i = 0; i <= discount.length - 10; i++) {
            // 원하는 제품의 개수를 저장할 맵 생성
            Map<String, Integer> wantMap = new HashMap<>();
            for (int j = 0; j < want.length; j++) {
                wantMap.put(want[j], number[j]);
            }
            
            // 10일간의 할인 제품 확인
            boolean isPossible = true;
            for (int j = i; j < i + 10; j++) {
                String item = discount[j];
                // 원하는 제품이 아니거나, 이미 원하는 개수를 모두 구매한 경우
                if (!wantMap.containsKey(item) || wantMap.get(item) == 0) {
                    isPossible = false;
                    break;
                }
                wantMap.put(item, wantMap.get(item) - 1);
            }
            
            // 모든 제품을 원하는 개수만큼 구매할 수 있는지 확인
            if (isPossible) {
                boolean allZero = true;
                for (int count : wantMap.values()) {
                    if (count > 0) {
                        allZero = false;
                        break;
                    }
                }
                if (allZero) answer++;
            }
        }
        
        return answer;
    }
}