import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        // 1. 두 개의 HashMap을 사용하여 O(1) 접근 가능하도록 설정
        Map<String, Integer> rankMap = new HashMap<>();    // 선수 -> 등수
        Map<Integer, String> playerMap = new HashMap<>();  // 등수 -> 선수
        
        // 초기 데이터 설정 O(n)
        for (int i = 0; i < players.length; i++) {
            rankMap.put(players[i], i);
            playerMap.put(i, players[i]);
        }
        
        // 순위 변경 처리 O(m)
        for (String calling : callings) {
            // 1. 현재 호출된 선수의 등수 확인
            int currentRank = rankMap.get(calling);
            // 2. 앞 선수 정보 확인
            String frontPlayer = playerMap.get(currentRank - 1);
            
            // 3. 순위 교체
            rankMap.put(calling, currentRank - 1);
            rankMap.put(frontPlayer, currentRank);
            playerMap.put(currentRank - 1, calling);
            playerMap.put(currentRank, frontPlayer);
        }
        
        // 4. 최종 결과 배열 생성 O(n)
        String[] result = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            result[i] = playerMap.get(i);
        }
        
        return result;
    }
}