import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0, sum2 = 0;
        
        // 큐 초기화 및 합계 계산
        for (int num : queue1) {
            q1.offer(num);
            sum1 += num;
        }
        for (int num : queue2) {
            q2.offer(num);
            sum2 += num;
        }
        
        // 전체 합이 홀수면 불가능
        if ((sum1 + sum2) % 2 != 0) return -1;
        
        long target = (sum1 + sum2) / 2;
        int count = 0;
        int limit = (queue1.length + queue2.length) * 2; // 최대 시도 횟수
        
        while (count < limit) {
            if (sum1 == target) return count;
            
            if (sum1 > target) {
                int val = q1.poll();
                sum1 -= val;
                sum2 += val;
                q2.offer(val);
            } else {
                int val = q2.poll();
                sum2 -= val;
                sum1 += val;
                q1.offer(val);
            }
            count++;
        }
        
        return -1;
    }
}