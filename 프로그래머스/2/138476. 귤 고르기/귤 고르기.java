import java.util.*;

class Solution {
    // k : 판매할 귤 수
    // tangerine : 귤 크기 배열
    // answer : k 개를 선택했을 때, 최소 수
    public int solution(int k, int[] tangerine) {
        // { size, 개수 } 로 정리
        HashMap<Integer,Integer> sizeSort = new HashMap<>();
        
        // tangerine을 순회해서 sizeSort를 정리
        for(int size: tangerine){
            sizeSort.put(size, sizeSort.getOrDefault(size, 0) + 1);
        }
        
        // 많은 순서대로 정렬하기
        List<Integer> sorted = new ArrayList<>(sizeSort.values());
        Collections.sort(sorted, Collections.reverseOrder());
        
        // 모든 귤을 포장할 때 까지 반복
        int packed = 0;
        int cnt = 0;
        
        for(int size : sorted){
            cnt += size;
            packed ++;
            // k 개 만큼 포장되었으면 종료
            if(cnt >= k){
                break;
            }
        }
        
        return packed;
    }
}