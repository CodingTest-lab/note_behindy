import java.util.*;

class Solution {
    private int[][] diceNumbers;
    private int n;
    private int maxWinCount = 0;
    private ArrayList<Integer> answer = new ArrayList<>();
    
    public int[] solution(int[][] dice) {
        this.diceNumbers = dice;
        this.n = dice.length;
        
        boolean[] selected = new boolean[n];
        selectDice(0, 0, selected);
        
        return answer.stream()
                    .mapToInt(i -> i + 1)
                    .toArray();
    }
    
    private void selectDice(int index, int count, boolean[] selected) {
        if(count == n/2) {
            calculateWinRate(selected);
            return;
        }
        
        // 남은 선택 가능한 수가 필요한 수보다 적다면 early return
        if(n - index < n/2 - count) return;
        
        // 현재 인덱스를 선택하는 경우
        selected[index] = true;
        selectDice(index + 1, count + 1, selected);
        selected[index] = false;
        
        // 현재 인덱스를 선택하지 않는 경우
        selectDice(index + 1, count, selected);
    }
    
    private void calculateWinRate(boolean[] selected) {
        Map<Integer, Integer> aFreq = new HashMap<>();
        Map<Integer, Integer> bFreq = new HashMap<>();
        
        ArrayList<Integer> aDice = new ArrayList<>();
        ArrayList<Integer> bDice = new ArrayList<>();
        
        // A와 B의 주사위 나누기
        for(int i = 0; i < n; i++) {
            if(selected[i]) aDice.add(i);
            else bDice.add(i);
        }
        
        // 각 주사위 조합의 합과 빈도수 계산
        getSumsWithFrequency(aDice, 0, 0, aFreq);
        getSumsWithFrequency(bDice, 0, 0, bFreq);
        
        int winCount = 0;
        
        // 승패 계산 - 빈도수를 이용하여 계산
        for(Map.Entry<Integer, Integer> aEntry : aFreq.entrySet()) {
            for(Map.Entry<Integer, Integer> bEntry : bFreq.entrySet()) {
                if(aEntry.getKey() > bEntry.getKey()) {
                    winCount += aEntry.getValue() * bEntry.getValue();
                }
            }
        }
        
        // 승률이 더 높다면 갱신
        if(winCount > maxWinCount) {
            maxWinCount = winCount;
            answer = new ArrayList<>(aDice);
        } else if(winCount == maxWinCount && !answer.isEmpty()) {
            // 승률이 같을 경우 사전순으로 더 작은 것을 선택
            boolean update = false;
            for(int i = 0; i < aDice.size(); i++) {
                if(aDice.get(i) < answer.get(i)) {
                    update = true;
                    break;
                } else if(aDice.get(i) > answer.get(i)) {
                    break;
                }
            }
            if(update) {
                answer = new ArrayList<>(aDice);
            }
        }
    }
    
    private void getSumsWithFrequency(ArrayList<Integer> diceIndexes, int index, int currentSum, Map<Integer, Integer> freq) {
        if(index == diceIndexes.size()) {
            freq.merge(currentSum, 1, Integer::sum);
            return;
        }
        
        for(int num : diceNumbers[diceIndexes.get(index)]) {
            getSumsWithFrequency(diceIndexes, index + 1, currentSum + num, freq);
        }
    }
}