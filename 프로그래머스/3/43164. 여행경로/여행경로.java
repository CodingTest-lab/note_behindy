import java.util.*;

class Solution {
    // 주어진 항공권을 모두 이용해서 경로를 작성
    // 모든 공항은 알파벳 대문자 3글자
    // 공항의 갯수는 자유
    // tickets 항공권 정보가 담긴 배열
    // ticket [a, b] a 공항 출발, b 공항 도착
    // 항상 ICN 에서 출발
    
    // 경로가 두개 이상인 경우 알파벳 순서가 앞서는 경로를 return
    
    private boolean[] used;
    private ArrayList<String> route;
    private String[][] tickets;
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        used = new boolean[tickets.length];
        route = new ArrayList<>();
        
        // 알파벳 순서로 정렬
        Arrays.sort(tickets, (a, b) -> {
            if(a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });
        
        route.add("ICN");
        dfs("ICN", 0);
        
        return route.toArray(new String[0]);
    }
    
    private boolean dfs(String current, int count){
        // 재귀 탐색 종료 조건
        // 모든 티켓을 사용한 경우
        if(count == tickets.length) {
            return true;
        }
        
        for(int i = 0; i < tickets.length; i++) {
            if(!used[i] && tickets[i][0].equals(current)) {
                used[i] = true;
                route.add(tickets[i][1]);
                
                if(dfs(tickets[i][1], count + 1)) return true;
                
                // 백트래킹
                used[i] = false;
                route.remove(route.size() - 1);
            }
        }
        
        return false;
    }
}