class Solution {
    public long solution(int r1, int r2) {     
        long answer = 0;
        
        for(int x = 0; x <= r2; x++) {
            long y2 = (long)Math.floor(Math.sqrt((long)r2*r2 - (long)x*x));
            long y1 = x >= r1 ? 0 : (long)Math.ceil(Math.sqrt(Math.max(0, (long)r1*r1 - (long)x*x)));
            answer += (y2 - y1 + 1);
        }
        
        return 4 * answer - 4 * (r2 - r1 + 1);
    }
}