import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        // 모든 전화번호를 HashSet에 저장
        HashSet<String> set = new HashSet<>(Arrays.asList(phone_book));
        
        // 각 전화번호의 모든 접두어 확인
        for(String phoneNumber : phone_book) {
            for(int i = 1; i < phoneNumber.length(); i++) {
                if(set.contains(phoneNumber.substring(0, i))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}