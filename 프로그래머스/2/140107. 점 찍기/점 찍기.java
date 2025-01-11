import java.util.*;

class Solution {
    // 좌표평면에 점 찍기!
    // k : 계수
    // d : 원점과의 최대 거리
    public long solution(int k, int d) {
        long answer = 0;
        
        for(long x = 0; x <= d; x += k) {
            long xx = x * x;
            long maxY = (long)Math.sqrt((long)d * d - xx);
            answer += (maxY / k) + 1;
        }
        return answer;
    }
}