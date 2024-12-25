import java.util.*;

class Solution {
   public int solution(int[][] targets) {
       Arrays.sort(targets, (a,b) -> a[1] - b[1]);
       
       // targets 의 최소길이 1
       // 최소 하나는 발사해야 미사일을 격추 가능
       int count = 1;
       double intercept = targets[0][1] - 0.1;  
       
       for(int[] target : targets) {
           if(target[0] > intercept) {
               intercept = target[1] - 0.1;
               count++;
           }
       }
       
       return count;
   }
}