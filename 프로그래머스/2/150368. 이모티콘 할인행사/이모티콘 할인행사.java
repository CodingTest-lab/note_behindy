class Solution {
    int[] discounts; // 각 이모티콘의 할인율을 저장할 배열
    int maxSubscribers = 0; // 최대 가입자 수
    int maxSales = 0; // 최대 판매액
    
    public int[] solution(int[][] users, int[] emoticons) {
        discounts = new int[emoticons.length];
        // DFS로 모든 할인율 조합 확인
        dfs(0, users, emoticons);
        
        return new int[]{maxSubscribers, maxSales};
    }
    
    private void dfs(int depth, int[][] users, int[] emoticons) {
        // 모든 이모티콘의 할인율을 정했다면
        if (depth == emoticons.length) {
            calculateResult(users, emoticons);
            return;
        }
        
        // 각 이모티콘에 대해 가능한 할인율(10%,20%,30%,40%) 적용
        for (int i = 1; i <= 4; i++) {
            discounts[depth] = i * 10;
            dfs(depth + 1, users, emoticons);
        }
    }
    
    private void calculateResult(int[][] users, int[] emoticons) {
        int subscribers = 0;
        int sales = 0;
        
        // 각 사용자별로 구매 결과 확인
        for (int[] user : users) {
            int userPrice = 0;
            
            // 각 이모티콘에 대해 구매 여부 확인
            for (int i = 0; i < emoticons.length; i++) {
                if (discounts[i] >= user[0]) { // 할인율이 기준 이상이면
                    userPrice += emoticons[i] * (100 - discounts[i]) / 100;
                }
            }
            
            // 구매 금액이 기준 이상이면 구독
            if (userPrice >= user[1]) {
                subscribers++;
            } else {
                sales += userPrice;
            }
        }
        
        // 최대값 갱신
        if (subscribers > maxSubscribers || 
            (subscribers == maxSubscribers && sales > maxSales)) {
            maxSubscribers = subscribers;
            maxSales = sales;
        }
    }
}