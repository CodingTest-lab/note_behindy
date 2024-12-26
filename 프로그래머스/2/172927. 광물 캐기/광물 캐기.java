import java.util.*;
// 각 곡괭이는 5번씩만 사용 가능
// 필요 최소한도의 피로도를 return
// 작업의 종료
// case 1 - 모든 광물을 캐는 경우
// case 2 - 곡괭이를 모두 소모한 경우
    
class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int totalPicks = picks[0] + picks[1] + picks[2];
        int groups = Math.min((minerals.length + 4) / 5, totalPicks);
        
        // 5개씩 묶어서 Priority Queue에 넣기
        PriorityQueue<int[]> pQue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for(int i = 0; i < groups; i++) {
            int dia = 0, iron = 0, stone = 0;
            for(int j = i * 5; j < Math.min(minerals.length, i * 5 + 5); j++) {
                if(minerals[j].equals("diamond")) {
                    dia++;
                } else if(minerals[j].equals("iron")) {
                    iron++;
                } else {
                    stone++;
                }
            }
            pQue.add(new int[]{i, dia * 25 + iron * 5 + stone});
        }
        
        // 채굴 시작
        while(!pQue.isEmpty()) {
            int[] current = pQue.poll();
            int start = current[0] * 5;
            int end = Math.min(minerals.length, start + 5);
            
            // 다이아몬드 곡괭이가 남아있는 경우
            if(picks[0] > 0) {
                picks[0]--;
                // 다이아몬드 곡괭이 사용시 피로도 추가
                for(int i = start; i < end; i++) answer++;
            // 철 곡괭이가 남아있는 경우
            } else if(picks[1] > 0) {
                picks[1]--;
                // 
                for(int i = start; i < end; i++) {
                    if(minerals[i].equals("diamond")) answer += 5;
                    else answer++;
                }
            // 돌 곡괭이가 남아있는 경우
            } else if(picks[2] > 0) {
                picks[2]--;
                for(int i = start; i < end; i++) {
                    if(minerals[i].equals("diamond")) answer += 25;
                    else if(minerals[i].equals("iron")) answer += 5;
                    else answer++;
                }
            }
        }
        
        return answer;
    }
}