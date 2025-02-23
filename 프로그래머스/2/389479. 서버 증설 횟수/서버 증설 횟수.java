import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int currServerCnt = 0;
        
        // 종료시간이 이른 순서대로 정렬
        PriorityQueue<Server> activeServers = new PriorityQueue<>((a, b) -> a.end - b.end);
        
        // 0 ~ 24 각 정각에 확인
        for(int i = 0 ; i < 24 ; i ++){
            // 각 시점에 접속해있는 이용자의 수
            int people = players[i];
            
            // 서버 닫기 : 현재 켜진 서버 중 종료 시간이 t보다 작은 서버를 모두 닫음
            while (!activeServers.isEmpty() && activeServers.peek().end < i) {
                currServerCnt -= activeServers.poll().dur;
            }
            
            // 지금 모자란 서버 수 = (사람 수 / 처리량) - 이미 켜진 서버 수
            int needServerCnt = Math.max(0, people / m - currServerCnt);

            // 모자란 서버 수가 1대 이상인 경우, 모자란만큼 "즉시" 서버를 켜야함
            if (needServerCnt > 0) {
                activeServers.add(new Server(needServerCnt, i + k - 1));
                currServerCnt += needServerCnt;
                answer += needServerCnt;
            }
        }
        
        return answer;
    }
    
    private class Server{
        int end;
        int dur;
        
        // 생성자
        private Server(int dur, int end) {
            this.dur = dur;
            this.end = end;
        }
    }
}