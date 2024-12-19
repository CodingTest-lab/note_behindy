import java.util.*;

class Solution {
    // data [코드번호, 제조일, 최대수량, 현재수량]
    // ext 추출 기준 항목
    // val_ext 추출 기준 수치
    // sort_by 정렬 기준 항목
    // 추출/정렬 가능 항목 => "code", "date", "maximum", "remain"
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int index = findIndex(ext);
        
        // 조건에 맞는 데이터만 추출하여 임시 리스트에 저장
        List<int[]> tempList = new ArrayList<>();
        for(int i = 0; i < data.length; i++) {
            if(data[i][index] < val_ext) {
                tempList.add(data[i]);
            }
        }
        
        // List를 배열로 변환
        int[][] tempdata = tempList.toArray(new int[0][]);
        
        // 정렬 기준 인덱스 찾기
        int sortIndex = findIndex(sort_by);
        
        // 정렬 수행
        return ascData(tempdata, sortIndex);
    }
    
    // 항목의 인덱스를 추출해 오는 메서드
    public int findIndex(String item) {
        switch(item) {
            case "code":
                return 0;
            case "date":
                return 1;
            case "maximum":
                return 2;
            // 입력값의 종류에 4가지만 존재함을 무조건 신뢰
            default: // "remain"
                return 3;
        }
    }

    // 오름차순 정렬
    public int[][] ascData(int[][] data, int index) {
        Arrays.sort(data, (a, b) -> a[index] - b[index]);
        return data;
    }
}