import java.util.*;

class Solution {
    // n : 쏜 화살 수
    // info : 각 10-index 에 적중한 어피치의 화살 수ㅠ
    // 같은 숫자를 쏘면 어피치가 획득
    // 최종 점수가 동점이면 어피치가 승리
    // 둘다 0 이면 아무도 획득하지 않음
    // 1 ~ 11 까지의 수를
    // answer : 가장 큰 점수차이를 내기 위한 화살 분배 -> dfs 활용해보기
    // [-1] <<< 승리할 수 없는 경우
    // 초기 최대 점수 0
    private int[] answer = {-1};
    private int maxDiff = 0;
    
    public int[] solution(int n, int[] info) {
        
        // a : 어피치
        // r : 라이언
        int[] r = new int[11];
        dfs(n, info, r, 0);
        return answer;
    }
    
    // dfs 메서드 구현
    // 재귀적으로 호출가능한 형태
    // 1. 라이언이 가장 큰 점수차이로 승리하는 경우
    // 2. 여러가지 경우의 수가 존재하는 경우 가장 낮은 점수를 더 많이 맞힌 경우
    private void dfs(int arrows, int[] a, int[] r, int index) {
        // 화살을 모두 쏜 경우  -> 종료 여부 검사
        if (arrows == 0) {
            int diff = score(a, r);
            
            // 점수 차이가 더 크거나, 같은 경우 더 낮은 점수를 많이 맞힌 경우 선택
            if (diff > maxDiff || (diff == maxDiff && checkResult(r, answer))) {
                maxDiff = diff;
                answer = r.clone();
            }
            return;
        }
        
        
        // 재귀 탐색 진행중
        // case 1. 인덱스가 범위를 벗어난 경우
        if (index == 11) {
            if (arrows > 0) {
                r[10] += arrows;  // 남은 화살은 0점에 몰아주기
                dfs(0, a, r, index);
                r[10] -= arrows;  // 백트래킹
            }
            return;
        }
        
        // case 2. 현재 과녁에 화살을 쏘지 않는 경우
        dfs(arrows, a, r, index + 1);
        
        // case 3. 현재 과녁에 화살을 쏘는 경우
        if (arrows >= a[index] + 1) {
            r[index] = a[index] + 1;
            dfs(arrows - (a[index] + 1), a, r, index + 1);
            r[index] = 0;
        }
    }
    
    // 가장 큰 점수차이로 우승할 수 있는 방법을 찾기 위해서 점수를 계산하는 함수
    // 어피치가 승리한 경우 -1을 반환
    // 라이언이 상리한 경우 점수 차이를 반환
    private int score(int[] a, int[] r) {
        int aScore = 0;
        int rScore = 0;
        
        for (int i = 0; i < 11; i++) {
            if (a[i] == 0 && r[i] == 0) continue;
            
            if (a[i] >= r[i]) {
                aScore += 10 - i;
            } else {
                rScore += 10 - i;
            }
        }
        
        int diff = rScore - aScore;
        return diff <= 0 ? -1 : diff;
    }
    
    // 여러가지 존재하는 경우 더 낮은 점수를 많이 맞힌 경우를 찾기 위함
    // r : 라이언
    // c : 점수 배열
    private boolean checkResult(int[] ryan, int[] current) {
        if (current[0] == -1) return true;
        
        // 가장 낮은 점수부터 비교
        for (int i = 10; i >= 0; i--) {
            if (ryan[i] != current[i]) {
                return ryan[i] > current[i];
            }
        }
        return false;
    }
}