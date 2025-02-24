import java.util.*;

class Solution {
    public String solution(String s) {
        String[] temp = s.split(" ");
        int[] numbers = new int[temp.length];
        
        for(int i = 0 ; i < temp.length ; i ++){
            numbers[i] = Integer.parseInt(temp[i]);
        }
        
        int minNumber = Integer.MAX_VALUE;
        int maxNumber = Integer.MIN_VALUE;
        
        for(int number : numbers){
            maxNumber = Math.max(maxNumber, number);
            minNumber = Math.min(minNumber, number);
        }
        
        return minNumber + " " + maxNumber;
    }
}