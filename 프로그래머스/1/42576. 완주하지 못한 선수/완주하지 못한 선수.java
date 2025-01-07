import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String result = "";
        
        HashMap<String, Integer> marathon = new HashMap<>();
        
        // marathon 을 정리 , 이름과 등장 횟수를 카운트
        for(int i = 0 ; i < participant.length; i ++){
            marathon.put(participant[i], marathon.getOrDefault(participant[i], 0) + 1);   
        }
        
        // 완주자는 카운트를 하나씩 낮춤
        for(String player : completion){
            marathon.put(player, marathon.get(player) - 1);
        }
        
        for(String key : marathon.keySet() ){
            if( marathon.get(key) != 0){
                result = key;
                break;
            }
        }
            
        return result;
    }
}