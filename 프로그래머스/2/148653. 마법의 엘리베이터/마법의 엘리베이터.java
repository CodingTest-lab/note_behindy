import java.util.*;

class Solution {
    // 버튼한번에 돌 한개! -> 카운트
    public int solution(int storey) {
        int answer = 0;
        int digit = digit(storey);
        int[] sliced = sliceNumber(storey, digit);
        
        for(int i = 0; i < digit; i ++){    
            // 10이 넘어가면 
            if(sliced[i] >= 10){
                sliced[i] = sliced[i] % 10;
                sliced[i+1] = sliced[i+1] + 1 ;
            }
            
            int[] distance = updown(sliced[i], sliced[i+1]);
            
            answer += distance[0];
            
            if(distance[1] == 1)
                sliced[i+1] += 1;
        }
        if( sliced[sliced.length-1] != 0)
            answer += sliced[sliced.length-1] % 10 ;
            
        return answer;
    }
    
    // 입력받은 숫자 (정수) 의 자리수를 반환하는 매서드
    private int digit(int number){
        int cnt = 0;
        int wheel = number;
        while(wheel >= 1){
            wheel = wheel / 10;
            cnt ++;
        }
        return cnt;
    }
    
    // 입력받은 숫자를 한글자씩 잘라서 배열로 반환하는 매서드
    private int[] sliceNumber(int number, int digit){
        int[] result = new int[digit + 1];
        int temp = number;
        int tens = 1;
        for(int i = 0 ; i < digit; i++){
            result[i] = (temp / tens) % 10;
            temp = temp/10;
        }
        return result;
    }
    
    // 입력 받은 한자리 정수가 0에 가까운지 10에 가까운지를 판별해 몇번 시행할지를 반환
    // [거리, 0 or 1]
    // 0 -> 0에 가까움
    // 1 -> 1에 가까움
    private int[] updown(int number, int nextNumber) {
        // 5보다 큰 경우는 무조건 올림
        // 5인 경우는 다음 숫자가 5 이상일 때만 올림
        // 나머지는 내림
        if (number > 5 || (number == 5 && nextNumber >= 5)) {
            return new int[]{10 - number, 1};
        }
        return new int[]{number, 0};
    }
}