import java.util.*;

class Solution {
    // 이진법으로 표현된 숫자 문자열
    public int[] solution(String s) {
        int[] answer = new int[2];
        String target = s;

        while(!target.equals("1")){
            String temp = "";
            for(char character : target.toCharArray()){
                if(character == '1') temp += "1";
            }

            int tempLength = temp.length();
            String binary = "";
            while(tempLength > 0) {
                binary = (tempLength % 2) + binary;
                tempLength = tempLength / 2;
            }

            answer[0]++;
            answer[1] += (target.length() - temp.length());
            target = binary;
        }
        return answer;
    }
}