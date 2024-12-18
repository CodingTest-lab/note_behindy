import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> answer = new LinkedList<>();
        
        // 각각의 작업 완료 일자를 계산
        int[] endedDays = new int[progresses.length]; 
        // 이녀석을 Queue 로 활용한다면..?
        // Queue 가 완성된 후 while 문으로 queue의 size 가 0이 될 때 까지 반복한다.
        // 제일 앞에 하나를 뽑아서 숫자를 저장
        // 그 숫자보다 큰 숫자가 나올때까지 갯수를 카운트 해서 ++
        // 다음 숫자를 저장해서
        // 그 숫자보다 큰 숫자가 나올때까지 카운트 해서 배열에 저장
        // ArrayList로 선언해서 동적할당한 후
        // int[] 형태로 변환하여 답을 내는 방식으로 구현..?
        
        for(int i = 0; i < progresses.length; i++) {
            endedDays[i] = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }
        
        int maxDay = endedDays[0];
        int count = 1;
        
        // 앞에서부터 순차적으로 비교하면서 배포 가능한 기능 묶기
        for(int i = 1; i < endedDays.length; i++) {
            if(maxDay >= endedDays[i]) {
                count++;
            } else {
                answer.offer(count);
                count = 1;
                maxDay = endedDays[i];
            }
        }
        answer.offer(count);  // 마지막 그룹 처리
        
        // Queue를 배열로 변환하여 반환
        int[] result = new int[answer.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = answer.poll();
        }
        
        return result;
    }
}