class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0;
        int right = 0;
        int sum = sequence[0];
        int minLength = sequence.length;
        int[] answer = new int[]{0, sequence.length-1};
        
        while(right < sequence.length) {
            if(sum == k) {
                if(right - left < minLength) {
                    minLength = right - left;
                    answer[0] = left;
                    answer[1] = right;
                }
                if(right + 1 < sequence.length) {
                    sum += sequence[++right];
                } else {
                    break;
                }
            } else if(sum < k) {
                if(right + 1 < sequence.length) {
                    sum += sequence[++right];
                } else {
                    break;
                }
            } else {
                sum -= sequence[left++];
            }
        }
        
        return answer;
    }
}