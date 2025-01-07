import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int num = getN(nums);
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        
        for(int i = 0 ; i < nums.length; i++){
            set.add(nums[i]);
        }
        
        return (set.size() < num )? set.size(): num; 
    }
    
    // 가져갈 포켓몬의 수 얻어오기
    private int getN(int[] nums){
        return (nums.length)/2;
    }
}