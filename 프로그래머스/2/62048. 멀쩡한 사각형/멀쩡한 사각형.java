import java.util.*;

class Solution {
    // w : width, h : height
    // 각각 1억 이하의 자연수
    // w * h - count;
    // 직선의 기울기 : h/w;
    // w 1칸 이동에 (int) h/w 만큼의 타일 이동
    public long solution(int w, int h) {
        long W = (long) w;
        long H = (long) h;
        
        return (W * H) - (W + H - gcd(W, H));
    }
    
    // 최대공약수 계산
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}