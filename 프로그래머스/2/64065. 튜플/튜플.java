import java.util.*;

class Solution {
    public int[] solution(String s) {
        // 맨 앞뒤의 {{ }} 제거
        s = s.substring(2, s.length() - 2);
        
        // "},{"를 기준으로 분리
        String[] parts = s.split("\\},\\{");
        
        // 각 집합을 길이에 따라 정렬하기 위한 리스트
        List<Set<Integer>> setList = new ArrayList<>();
        
        // 각 부분을 집합으로 변환
        for(String part : parts) {
            Set<Integer> set = new HashSet<>();
            String[] numbers = part.split(",");
            for(String num : numbers) {
                set.add(Integer.parseInt(num));
            }
            setList.add(set);
        }
        
        // 집합 크기로 정렬
        Collections.sort(setList, (a, b) -> a.size() - b.size());
        
        // 결과 배열 생성
        int[] answer = new int[setList.size()];
        Set<Integer> used = new HashSet<>();
        
        // 크기가 작은 집합부터 새로운 원소를 찾아 결과 배열에 추가
        for(int i = 0; i < setList.size(); i++) {
            Set<Integer> currentSet = setList.get(i);
            for(int num : currentSet) {
                if(!used.contains(num)) {
                    answer[i] = num;
                    used.add(num);
                    break;
                }
            }
        }
        
        return answer;
    }
}