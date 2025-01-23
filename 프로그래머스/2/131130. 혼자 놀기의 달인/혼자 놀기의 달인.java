import java.util.*;
// 카드더미가 100장, 1~100 기입
// --- 게임 방법 ---
// 1. 2~ 100 이하의 자연수를 하나 정하고 이 숫자보다 작거나 같은 숫자 카드들은 준비
// 2. 준비한 카드의 수 만큼 작은 상자를 준비하면 게임을 시작할 수 있음
// 3. 준비된 상자에 카드를 한장씩 넣고, 상자를 무작위로 섞어 일렬로 나열
// 4. 상자가 나열되면 순서에 따라 1번부터 순서대로 번호를 부여
// 5. 임의의 상자를 하나 선택해서 선택한 상자 안의 카드를 확인
// 6. 내부의 카드 번호를 하나씩 추적해가면서 열어야 하는 상자가 이미 열려있을 때까지 반복
// 7. 이렇게 연 상자들을 1번상자 그룹, - 1번 상자 그룹을 제외하고 남는 상자가 없다면 게임이 그대로 종료
// 8. 2번 상자에서 임의로 하나를 선택해서 2번그룹에 저장
// 9. 1번상자 그룹의 크기와 2번상자 그룹의 크기를 곱하여 점수를 반환
class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        
        for(int i = 0; i < cards.length; i++){
            Set<Integer> box1 = new HashSet<>();
            Set<Integer> box2 = new HashSet<>();
            
            Queue<Integer> tempQ = new LinkedList<>();
            tempQ.offer(i + 1);
            
            while(!tempQ.isEmpty()){
                int current = tempQ.poll();
                if(!box1.contains(current)){
                    box1.add(current);
                    tempQ.offer(cards[current - 1]);   
                }
            }
            
            if(box1.size() == cards.length) continue;
            
            for(int j = 1; j <= cards.length; j++) {
                if(!box1.contains(j)) {
                    tempQ.clear();
                    tempQ.offer(j);
                    
                    while(!tempQ.isEmpty()) {
                        int currentCard = tempQ.poll();
                        if(!box2.contains(currentCard)) {
                            box2.add(currentCard);
                            tempQ.offer(cards[currentCard - 1]);
                        }
                    }
                    answer = Math.max(answer, box1.size() * box2.size());
                    box2.clear();
                }
            }
        }
        return answer;
    }
}