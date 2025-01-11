import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        // 오른쪽 토핑의 종류와 개수를 저장하는 Map
        Map<Integer, Integer> rightMap = new HashMap<>();
        // 왼쪽 토핑의 종류를 저장하는 Set
        Set<Integer> leftSet = new HashSet<>();    
        
        // 처음에는 모든 토핑이 오른쪽에 있다고 가정
        for (int t : topping) {
            rightMap.put(t, rightMap.getOrDefault(t, 0) + 1);
        }
        
        // 왼쪽으로 하나씩 이동하면서 확인
        for (int t : topping) {
            // 왼쪽에 토핑 추가
            leftSet.add(t);
            
            // 오른쪽에서 토핑 제거
            int count = rightMap.get(t);
            if (count == 1) {
                rightMap.remove(t);
            } else {
                rightMap.put(t, count - 1);
            }
            
            // 양쪽의 토핑 종류 수가 같은지 확인
            if (leftSet.size() == rightMap.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}