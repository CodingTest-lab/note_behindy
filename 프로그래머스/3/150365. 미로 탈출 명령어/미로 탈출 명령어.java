import java.util.*;

class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // 조기 종료 조건 확인
        int diff = Math.abs(x-r) + Math.abs(y-c);
        if (k-diff<0 || (k-diff)%2==1) return "impossible";
        
        StringBuilder temp = new StringBuilder();
        int new_diff=0;
        
        // 정답 문자열 만들기
        for (int i = k; i > 0; i--){
            if ((i-diff)==0) {
                --diff;
                if (r-x>0) {
                    temp.append("d");
                    ++x;
                }
                else if (y-c>0) {
                    temp.append("l");
                    --y;
                }
                else if (c-y>0) {
                    temp.append("r");
                    ++y;
                }
                else {
                    temp.append("u");
                    --x;
                }
            }
            else {
                if (x+1<=n && i-1>=(new_diff=Math.abs(x+1-r)+Math.abs(y-c))) {
                    temp.append("d");
                    ++x;
                }
                else if (y-1>=1 && i-1>=(new_diff=Math.abs(x-r)+Math.abs(y-1-c))) {
                    temp.append("l");
                    --y;
                }
                else if (y+1<=m && i-1>=(new_diff=Math.abs(x-r)+Math.abs(y+1-c))) {
                    temp.append("r");
                    ++y;
                }
                else if (x-1>=1 && i-1>=(new_diff=Math.abs(x-1-r)+Math.abs(y-c))) {
                    temp.append("u");
                    --x;
                }
                diff = new_diff;
            }
        }
        return temp.toString();
    }
    
    private void GreedyCheck(){
        
    }
}