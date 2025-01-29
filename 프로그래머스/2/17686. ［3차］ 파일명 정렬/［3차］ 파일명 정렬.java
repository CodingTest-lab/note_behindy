import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[][] parsed = new String[files.length][3];
        
        // 파일명 파싱
        parseName(files, parsed);
        
        Integer[] temp = new Integer[files.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = i;
        }
        
        // 정렬 수행
        Arrays.sort(temp, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                // HEAD 비교 (대소문자 구분 없이)
                String head1 = parsed[i1][0].toLowerCase();
                String head2 = parsed[i2][0].toLowerCase();
                int headCompare = head1.compareTo(head2);
                if (headCompare != 0) return headCompare;
                
                // NUMBER 비교 (숫자값으로)
                int num1 = Integer.parseInt(parsed[i1][1]);
                int num2 = Integer.parseInt(parsed[i2][1]);
                if (num1 != num2) return num1 - num2;
                
                // 원본 순서 유지
                return i1 - i2;
            }
        });
        
        // 정렬된 순서대로 결과 배열 생성
        String[] answer = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            answer[i] = files[temp[i]];
        }
        
        return answer;
    }
    
    private void parseName(String[] files, String[][] parsed) {
        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            int len = file.length();
            
            // HEAD 추출 (숫자가 나올 때까지의 문자열)
            int numberStart = 0;
            while (numberStart < len && !Character.isDigit(file.charAt(numberStart))) {
                numberStart++;
            }
            parsed[i][0] = file.substring(0, numberStart);
            
            // NUMBER 추출 (최대 5자리 연속된 숫자)
            int numberEnd = numberStart;
            int count = 0;
            while (numberEnd < len && Character.isDigit(file.charAt(numberEnd)) && count < 5) {
                numberEnd++;
                count++;
            }
            parsed[i][1] = file.substring(numberStart, numberEnd);
            
            // TAIL 추출 (나머지 부분)
            parsed[i][2] = file.substring(numberEnd);
        }
    }
}