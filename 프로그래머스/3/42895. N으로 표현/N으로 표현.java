import java.util.*;
// N 과 사칙연산을 사용해서 number 달성하기
// NN 처럼 자릿수를 다르게도 활용 가능
// N/N을 number 만큼 더하면 달성 가능 -> 불가능한 경우는 존재하지 않음
// 범위를 빠르게 좁히는 경우를 찾는다
// 3, 71 의 경우
// 33 * 3 - (3 * 3) -(3 / 3)    -> 7
// 3 * 3 * 3 -(3 * 3) - (3 / 3) -> 7
// 3 * 3

// N 과 사칙연산을 사용해서 만들 수 있는 수
// case 1. 1 : ( N / N ) 
// case 2. N : N 자체의 활용
// case 3. NN : N의 자리수에 배치 -> 11, 111, 1, 의 배수와 얼마나 가까운지 확인

// 3, 121 의 경우
// (33 / 3) * (33 / 3)
// 

class Solution {
    public int solution(int N, int number) {
        // N을 i번 사용해서 만들 수 있는 수들을 저장할 리스트
        List<Set<Integer>> dp = new ArrayList<>();
        
        // 0번 인덱스는 더미 데이터로 채움
        dp.add(new HashSet<>());
        
        // 1부터 8까지 시도
        for (int i = 1; i <= 8; i++) {
            // 현재 횟수로 만들 수 있는 수들을 저장할 집합
            Set<Integer> currentSet = new HashSet<>();
            
            // 숫자를 이어붙인 경우 추가 (예: 5, 55, 555 등)
            int concatenatedNum = 0;
            for (int j = 0; j < i; j++) {
                concatenatedNum = concatenatedNum * 10 + N;
            }
            currentSet.add(concatenatedNum);
            
            // 1부터 현재 횟수 이전까지의 조합으로 만들 수 있는 수 계산
            for (int j = 1; j < i; j++) {
                // j와 i-j번 사용한 조합으로 만들 수 있는 수들
                for (int num1 : dp.get(j)) {
                    for (int num2 : dp.get(i - j)) {
                        currentSet.add(num1 + num2);  // 덧셈
                        currentSet.add(num1 - num2);  // 뺄셈
                        currentSet.add(num1 * num2);  // 곱셈
                        if (num2 != 0) {  // 0으로 나누기 방지
                            currentSet.add(num1 / num2);  // 나눗셈
                        }
                    }
                }
            }
            
            // 현재 만든 집합을 dp 리스트에 추가
            dp.add(currentSet);
            
            // number를 찾았다면 현재 사용 횟수 반환
            if (currentSet.contains(number)) {
                return i;
            }
        }
        
        // 8번 이하로 만들 수 없는 경우
        return -1;
    }
}