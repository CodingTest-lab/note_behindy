import java.util.*;
        
class Solution {
    public int solution(int[][] board, int[] moves) {
        // 받아온 board를 활용할 새로운 board 적용
        Stack<Integer>[] crane_board = new Stack[board[0].length];
        
        // 각 요소가 될 Stack 넣기
        for (int i = 0; i < crane_board.length; i++) {
            crane_board[i] = new Stack<>();
        }
        
        // Stack 에 인형들 정렬하기
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 0) {
                    crane_board[j].push(board[i][j]);
                }
            }
        }
    
        // moves 대로 인형 뽑기
        // 뽑으면서 basket에 넣을 것
        // 바구니에 인형이 추가되었을 때, 작동하는 매서드가 하나 필요
        // 같은 인형이 연속해서 존재하게 되면 두 인형을 뽑고. pop() 두번실행
        // 전체 함수가 반환할 점수 카운트에 + 1
        // 바구니는 크기 제한이 없음 => moves의 길이로 사용할래
        Stack<Integer> basket = new Stack<>();
        int answer = 0;
        
        // 반복문대로 moves 에서 추출
        for (int i = 0; i < moves.length; i++) {
            // moves 배열은 1부터 시작하므로 인덱스를 1 감소시켜야 함
            int position = moves[i] - 1;

            // 스택이 비어있지 않은 경우에만 인형을 뽑음
            if (!crane_board[position].isEmpty()) {
                int doll_number = crane_board[position].pop();
                
                basket.push(doll_number);
                // basket의 크기가 2 이상인 경우에 확인
                if( basket.size() >= 2){
                    // 위에있는 인형 (방금 넣은)
                    int temp_doll1 = basket.pop();
                    // 아래있는 인형 (이미 들어가있는)
                    int temp_doll2 = basket.pop();
                    if(temp_doll1 == temp_doll2){
                        answer += 2;
                    }else{
                        // 꺼낸 순서 반대로 다시 집어넣기
                        basket.push(temp_doll2);
                        basket.push(temp_doll1);
                    }
                    
                }
            }
        }
        
        return answer;
    }
}