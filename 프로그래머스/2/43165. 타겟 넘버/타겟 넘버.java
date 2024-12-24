class Solution {
    int cnt = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(0, cnt, numbers, target);
        return cnt;
    }
    
    public void dfs(int depth, int sum, int[] numbers, int target) {
        if (depth == numbers.length) {
            if (sum == target) cnt++;
            return;
        }
        dfs(depth + 1, sum + numbers[depth], numbers, target);
        dfs(depth + 1, sum - numbers[depth], numbers, target);
    }
}