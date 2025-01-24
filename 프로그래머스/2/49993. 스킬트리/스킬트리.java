import java.util.*;

class Solution {
    // skill : 선행스킬 순서
    // skill_trees : 스킬트리를 담은 배열
    public int solution(String skill, String[] skill_trees) {
        
        int answer = 0;
        String[] parsed = skill.split("");
        // 스킬A 가 몇번째 인덱스에 존재하는지 확인
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i = 0 ; i <parsed.length; i ++){
            map.put(parsed[i], i);
        }
        
        // 1. 스킬 트리를 읽어 몇번째로 등장하는지 기록
        for(String skill_tree : skill_trees){
           String[] skills = skill_tree.split("");
           int idx = 0;
           boolean flag = true;

           for(String s : skills){
               if(map.containsKey(s)){
                   if(s.equals(parsed[idx])){
                       idx++;
                   } else {
                       flag = false;
                       break;
                   }
               }
           }

           if(flag) answer++;
        }
        
        return answer;
    }
}