import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> rightMap = new HashMap<>();
        Set<Integer> leftSet = new HashSet<>();    
        
        for (int t : topping) {
            rightMap.put(t, rightMap.getOrDefault(t, 0) + 1);
        }
        
        for (int t : topping) {
            leftSet.add(t);
            int count = rightMap.get(t);
            if (count == 1) {
                rightMap.remove(t);
            } else {
                rightMap.put(t, count - 1);
            }
            if(leftSet.size() > rightMap.size()) {
                break;
            }
            if (leftSet.size() == rightMap.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}