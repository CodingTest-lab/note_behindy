class Solution {    
    int[][][] evidences;
    int[][] info;
    int n, m;
    // final int INF = 1000000;
    
    public int solution(int[][] info, int n, int m) {
        this.info = info;
        this.n = n;
        this.m = m;
        
        // dp 배열 생성
        evidences = new int[info.length][n][m];
        
        // dp 배열 초기화
        for(int i = 0; i < info.length; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < m; k++) {
                    evidences[i][j][k] = -1;
                }
            }	
        }
        
        int answer = beLupin(0,0,0);
        
        return answer >= 1000000 ? -1 : answer;
    }
    
    // dp 연산 메소드
    private int beLupin(int index, int aEvidence, int bEvidence){
        // 종료 
        if(index == info.length) {
            return aEvidence;
        }
        
        if(evidences[index][aEvidence][bEvidence] != -1) {
            return evidences[index][aEvidence][bEvidence];
        }
        
        int result = 1000000;
        
        // A가 훔치는 경우
        int nextAEvidence = aEvidence + info[index][0];
        if(nextAEvidence < n) {
            result = Math.min(result, beLupin(index + 1, nextAEvidence, bEvidence));
        }
        
        // B가 훔치는 경우
        int nextBEvidence = bEvidence + info[index][1];
        if(nextBEvidence < m) {
            result = Math.min(result, beLupin(index + 1, aEvidence, nextBEvidence));
        }
        
        evidences[index][aEvidence][bEvidence] = result;
        return result;
    }
}