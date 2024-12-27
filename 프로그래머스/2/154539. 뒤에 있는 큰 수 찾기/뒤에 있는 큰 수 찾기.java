import java.util.*;
// // 뒷 큰수 찾기 - 스택 활용하기
// class Solution {
//     public int[] solution(int[] numbers) {
//         int leng = numbers.length;
//         int[] answer = new int[leng];
        
//         // 마지막 요소는 고정
//         answer[leng-1] = -1;
        
//         // numbers 의 모든 요소에 대해서 뒷 큰수 찾기를 실행
//         for(int i = 0 ; i < leng-1; i ++){
//             int temp = numbers[i];
//             // 지금부터 뒤로 탐색 시작
//             for(int j = i + 1 ; j < leng ; j ++){
//                 temp = Math.max(temp, numbers[j]);
//                 // 처음으로 temp가 바뀌는 경우 - 뒷 큰수를 마주침
//                 if(temp != numbers[i]){
//                     break;
//                 }
//             }
//             if(temp == numbers[i]){
//                 temp = -1;
//             }

//             answer[i] = temp;
//         }
//         return answer;
//     }
// }

class Solution {
    public int[] solution(int[] numbers) {
        int len = numbers.length;
        int[] answer = new int[len];
        
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        
        // 1번 인덱스부터 순회하며 뒷큰수 찾기
        for (int i = 1; i < len; i++) {
            // 스택이 비어있지 않고, 현재 숫자가 스택 top 인덱스의 숫자보다 크면
            // 현재 숫자가 스택 top의 뒷큰수가 됨
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.pop()] = numbers[i];
            }
            stack.push(i);
        }
        
        // 스택에 남아있는 인덱스들은 뒷큰수가 없는 경우
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }
        return answer;
    }
}