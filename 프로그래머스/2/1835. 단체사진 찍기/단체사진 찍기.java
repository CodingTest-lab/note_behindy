import java.util.*;
// 단체사진 찍기
// 어떤 순서로 설지 정해주기!
// 1. 네오-프로도 나란히 서기
// 2. 튜브 - 라이언은 세칸 이상 떨어져서 서기
// 이 조건을 만족하는 경우의 수를 계산하는 프로그램 작성하기
class Solution {
    // n : 조건의 개수
    // 입력 조건 data[0] -> X~Y>N
    // X 와 Y 사이의 프렌즈의 수가 N 이상
    // 프렌즈의 총 수
    private static final String[] FRIENDS = {"A", "C", "F", "J", "M", "N", "R", "T"};
    private int answer;
    private String[] conditions;
   
    
    public int solution(int n, String[] data) {
        answer = 0;
        conditions = data;
        boolean[] visited = new boolean[8];
        dfs("", visited);
        return answer;
    }
    // dfs 를 통해 일단 프렌즈를 모두 배치한 후, 배치가 완료된 상태에서만 조건 체크를 시작
    private void dfs(String current, boolean[] visited) {
        if(current.length() == 8) {
           if(checkConditions(current)) answer++;
           return;
        }

        for(int i = 0; i < 8; i++) {
           if(!visited[i]) {
               visited[i] = true;
               dfs(current + FRIENDS[i], visited);
               visited[i] = false;
           }
        }
    }

    private boolean checkConditions(String order) {
        for(String condition : conditions) {
            int pos1 = order.indexOf(condition.charAt(0));
            int pos2 = order.indexOf(condition.charAt(2));
            char op = condition.charAt(3);
            int gap = condition.charAt(4) - '0';
            int actualGap = Math.abs(pos1 - pos2) - 1;

            if(op == '=' && actualGap != gap) return false;
            if(op == '>' && actualGap <= gap) return false;
            if(op == '<' && actualGap >= gap) return false;
        }
        return true;
    }
}