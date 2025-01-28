import java.util.*;

class Solution {
    // n : 진법
    // t : 숫자의 갯수
    // m : 참가 인원
    // p : 튜브의 순서
    public String solution(int digit, int t, int m, int p) {
        ArrayList<String> arr = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        
        // 충분한 수의 숫자를 생성하기 위해 t*m보다 더 큰 범위까지 생성
        for(int i = 0; i < t*m; i++) {
            String converted = Integer.toString(i, digit).toUpperCase();
            for(int j = 0; j < converted.length(); j++) {
                arr.add(converted.substring(j, j+1));
            }
        }
        
        // 일단 충분한 수를 한글자씩 배열에 넣고 p번째 인덱스만 골라내서 문자열로 반환
        for(int i = 0; i < t; i++) {
            int position = (i*m) + (p-1);
            if(position < arr.size()) {
                result.append(arr.get(position));
            }
        }
        
        return result.toString();
    }
}