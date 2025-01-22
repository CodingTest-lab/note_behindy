import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        int keepDistance = 1;
        
        for(int i = 0 ; i < places.length; i ++){
            String[] place = places[i];
            
            keepDistance = 1;
            ArrayList<int[]> candidates = new ArrayList<>();
            
            // 좌표값을 정확히 저장하도록 수정
            for(int r = 0; r < place.length ; r++){
                for(int c = 0; c < place[r].length(); c++){
                    if(place[r].charAt(c) == 'P'){
                        // int 배열에 행과 열 좌표를 저장
                        candidates.add(new int[]{r, c});
                    }
                }
            }
            
            for(int j = 0; j < candidates.size(); j++){
                int[] people1 = candidates.get(j);
                for(int k = j + 1; k < candidates.size(); k++){
                    int[] people2 = candidates.get(k);
                    
                    int Mdistance = Math.abs(people1[0] - people2[0]) + Math.abs(people1[1] - people2[1]);
                    if(Mdistance == 1){
                        keepDistance = 0;
                        break;
                    }
                    
                    if(Mdistance > 2){
                        continue;
                    }else{
                        if(Mdistance == 2){
                            if(people1[0] == people2[0] || people1[1] == people2[1]){
                                // 맨해튼거리가 2 이지만 두 사람 사이에 파티션이 일렬로 존재하는 경우 PXP
                                char tempSlice = place[(people1[0] + people2[0])/2].charAt((people1[1] + people2[1])/2);
                                if(tempSlice == 'X'){
                                    continue;
                                }   
                            }
                            
                            // 맨해튼 거리가 2 이지만 두 사람 사이에 모두 파티션이 중복으로 존재하는 경우
                            char tempSlice1 = place[people1[0]].charAt(people2[1]);
                            char tempSlice2 = place[people2[0]].charAt(people1[1]);
                            
                            if(tempSlice1 =='X' && tempSlice2 == 'X'){
                                continue;
                            }
                        }
                        keepDistance = 0;
                    }
                }
            }
            answer[i] = keepDistance;
        }
        return answer;
    }
}