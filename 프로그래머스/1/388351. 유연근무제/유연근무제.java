import java.util.*;

class Solution {
    // n : 직원 수
    // schedules : 출근 희망 시각
    // timelogs : 일주일동한 출근 기록을 담음
    // startday : 이벤트를 시작한 요일
    // 1 : 월요일 ~ 7 : 일요일
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for(int i = 0 ; i < timelogs.length ; i++ ){
            if( checkPrize(timelogs[i], startday, schedules[i]) ) answer++ ;    
        }
        
        return answer;
    }
    
    // 한 사람분의 시간 기록을 받아서 수령 가능 여부를 확인
    private boolean checkPrize(int[] timelog, int startday, int schedule){
        for(int i = 0 ; i < timelog.length ; i++){
            // 토요일 또는 일요일이면 넘김
            if((startday + i) % 7 == 0 || (startday + i) % 7 == 6) continue;
            
            int temp = schedule + 10;
            if( temp % 100 >= 60 ){
                temp -= 60;
                temp += 100;
            }
            
            // 출근 제한시간 schedule + 10 분을 넘기면 false
            if(timelog[i] > temp ) return false;
        }
        
        return true;
    }
}