import java.util.*;

class Solution {
   public long solution(int[] weights) {
       long answer = 0;
       
       // 각 무게가 몇 번 나오는지 저장
       HashMap<Integer, Integer> weightsMap = new HashMap<>();
       
       // 무게 카운팅
       for(int weight : weights) {
           weightsMap.put(weight, weightsMap.getOrDefault(weight, 0) + 1);
       }
       
       // 검사 시작
       for(int weight : weightsMap.keySet()) {
           long count = weightsMap.get(weight);
           if(count > 1) {
               answer += (count * (count-1)) / 2;
           }
           
           // 2:3 비율
           if(weight % 2 == 0 && weightsMap.containsKey(weight*3/2)) {
               answer += (long)weightsMap.get(weight) * weightsMap.get(weight*3/2);
           }
           
           // 2:4 비율
           if(weightsMap.containsKey(weight*2)) {
               answer += (long)weightsMap.get(weight) * weightsMap.get(weight*2);
           }
           
           // 3:4 비율
           if(weight % 3 == 0 && weightsMap.containsKey(weight*4/3)) {
               answer += (long)weightsMap.get(weight) * weightsMap.get(weight*4/3);
           }
       }
       
       return answer;
   }
}