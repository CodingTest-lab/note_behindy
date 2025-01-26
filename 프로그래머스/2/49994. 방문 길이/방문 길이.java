import java.util.*;

class Solution {
    public int solution(String dirs) {
       int answer = 0;
       int[] curr = new int[]{0,0};
       HashSet<String> visited = new HashSet<>();

       for(char dir : dirs.toCharArray()){
           int[] temp = move(dir, curr);
           if(temp[0] != curr[0] || temp[1] != curr[1]) {
               String path = curr[0] + "," + curr[1] + ":" + temp[0] + "," + temp[1];
               String reversePath = temp[0] + "," + temp[1] + ":" + curr[0] + "," + curr[1];

               if(!visited.contains(path) && !visited.contains(reversePath)) {
                   visited.add(path);
                   answer++;
               }
               curr = temp;
           }
       }
       return answer;
    }
    
    private int[] move(char dir, int[] position){
        switch(dir){
            case 'U':
                int[] movedU = new int[]{position[0],position[1]+1};
                if(Math.abs(movedU[1]) > 5){
                    return position;
                }
                return movedU;
            case 'D':
                int[] movedD = new int[]{position[0],position[1]-1};
                if(Math.abs(movedD[1]) > 5){
                    return position;
                }
                return movedD;
            case 'L':
                int[] movedL = new int[]{position[0]-1,position[1]};
                if(Math.abs(movedL[0]) > 5){
                    return position;
                }
                return movedL;
            case 'R':
                int[] movedR = new int[]{position[0]+1,position[1]};
                if(Math.abs(movedR[0]) > 5){
                    return position;
                }
                return movedR;
        }
        return position;
    }
}