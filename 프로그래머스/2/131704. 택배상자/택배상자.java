import java.util.*;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> subBelt = new Stack<>();
        int count = 0;  // 트럭에 실은 상자 개수
        int currBox = 1;  // 현재 메인 벨트의 상자 번호
        int orderIdx = 0;  // 현재 찾아야 하는 order의 인덱스
        
        while(currBox <= order.length) {
            // 현재 메인 벨트의 상자가 필요한 상자인 경우
            if(currBox == order[orderIdx]) {
                count++;
                orderIdx++;
                currBox++;
            }
            // 보조 벨트의 맨 위 상자가 필요한 상자인 경우
            else if(!subBelt.empty() && subBelt.peek() == order[orderIdx]) {
                subBelt.pop();
                count++;
                orderIdx++;
            }
            // 둘 다 아닌 경우 보조 벨트에 보관
            else {
                subBelt.push(currBox);
                currBox++;
            }
            
            // 보조 벨트의 맨 위 상자가 필요한 상자인 동안 계속 처리
            while(!subBelt.empty() && subBelt.peek() == order[orderIdx]) {
                subBelt.pop();
                count++;
                orderIdx++;
            }
            
            // 더 이상 진행이 불가능한 경우
            if(currBox > order.length && !subBelt.empty() && 
               subBelt.peek() != order[orderIdx]) {
                break;
            }
        }
        
        return count;
    }
}