import java.util.*;


// 선물하기 기능!
// A, B
// 선물지수..?
// 조건 1. 선물을 주고받은 기록이 있다면? 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 받음
// 조건 2. 선물지수 활용
// 선물지수 : 준 선물 - 받은 선물
// 이 조건을 체크하는 경우, 기록이 없으면 선물을 단 한개 선물지수가 높은 사람이 낮은 사람에게 줌
// 조건 3. 선물을 주고받은 기록이 없거나 주고받은 수고 같다면 -> 다음달에 선물을 주고받지 않음

// 가장 많은 선물을 받을 친구가 받게 될 선물의 개수
class Solution {
    String[] friends;
        
    public int solution(String[] friends, String[] gifts) {
        this.friends = friends;
        int answer = 0;
        
        int[][] trades = new int[friends.length][friends.length];
        int[] presentFactor = new int[friends.length];
        int[] prediction = new int[friends.length];
        
        // 이름과 인덱스를 저장하기 위한 장치
        HashMap<String, Integer> names = new HashMap<>();
        for(int i = 0; i <friends.length; i++){
            names.put(friends[i],i);
        }        
        
        // gifts 를 정리해서 이차원 배열로 정리해보기
        for(int i = 0 ; i < gifts.length; i ++){
            String[] temp = gifts[i].split(" ");
            int from = names.get(temp[0]);
            int to = names.get(temp[1]);
            trades[from][to] ++;   
            presentFactor[from] ++;
            presentFactor[to] --;
        }
        
        // 받을 선물 수 예상 해보기
        for(int i = 0 ; i < friends.length ; i ++){
            // 1. 선물 주고받은 기록에 의해서 유추해보기
            // 반복 회수를 반으로 줄이고 한번에 처리
            for(int j = i + 1 ; j < trades[i].length; j++){
                if(trades[i][j] > trades[j][i]){
                    // i가 j에게 더 많이 줌 -> j가 i에게 선물
                    prediction[i]++;
                } else if(trades[i][j] < trades[j][i]){
                    // j가 i에게 더 많이 줌 -> i가 j에게 선물
                    prediction[j]++;
                } else {
                    // 선물을 주고받지 않았거나 같은 수로 주고받은 경우
                    // 선물계수 검사
                    if(presentFactor[i] > presentFactor[j]){
                        prediction[i]++;
                    } else if(presentFactor[i] < presentFactor[j]){
                        prediction[j]++;
                    }
                }
            }
        }
        
        // 가장 큰 값 찾기
        for(int count : prediction){
            answer = Math.max(answer, count);
        }
        
        return answer;
    }
}
