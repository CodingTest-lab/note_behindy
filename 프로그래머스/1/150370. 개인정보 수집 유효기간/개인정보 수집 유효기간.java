import java.util.*;
// today : 오늘 날짜
// terms : 약관의 유효 기간을 담은 문자열 배열
// privacies : 수집된 개인 정보를 담은 문자 배열

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 약관 종류별 유효기간을 저장할 Map
        Map<String, Integer> termMap = new HashMap<>();
        
        // 약관 정보 파싱
        for (String term : terms) {
            String[] parts = term.split(" ");
            termMap.put(parts[0], Integer.parseInt(parts[1]));
        }
        
        // 오늘 날짜를 일수로 변환
        int todayDays = convertToDays(today);
        
        // 파기해야 할 개인정보 번호를 저장할 List
        List<Integer> result = new ArrayList<>();
        
        // 각 개인정보별로 유효기간 검사
        for (int i = 0; i < privacies.length; i++) {
            String[] parts = privacies[i].split(" ");
            String date = parts[0];
            String term = parts[1];
            
            // 수집일자를 일수로 변환
            int collectionDays = convertToDays(date);
            
            // 유효기간(월)을 일수로 변환하여 더함
            int validDays = collectionDays + (termMap.get(term) * 28);
            
            // 유효기간이 지났으면 결과에 추가
            if (validDays <= todayDays) {
                result.add(i + 1);
            }
        }
        
        // List를 배열로 변환
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    // 날짜 문자열을 총 일수로 변환하는 메서드
    private int convertToDays(String date) {
        String[] parts = date.split("\\.");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        
        // 2000년 1월 1일부터의 일수 계산
        return (year - 2000) * 12 * 28 + (month - 1) * 28 + day;
    }
}