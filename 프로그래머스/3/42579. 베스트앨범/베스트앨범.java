import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, ArrayList<Integer[]>> map = new HashMap<>();
        HashMap<String, Integer> mapPlay = new HashMap<>();
        
        // 각 장르별로 [곡 인덱스, 재생 횟수]를 저장
        for(int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            Integer[] songInfo = {i, plays[i]};
            
            if(!map.containsKey(genre)) {
                map.put(genre, new ArrayList<>());
            }
            map.get(genre).add(songInfo);
        }
        
        // map 내부에서 각 요소들이 재생수 순으로, 고유번호 순으로 정렬
        for(String genre : map.keySet()) {
            ArrayList<Integer[]> songs = map.get(genre);
            Collections.sort(songs, (a, b) -> {
                if(b[1].equals(a[1])) {
                    return a[0] - b[0];
                }
                return b[1] - a[1];
            });
        }
        
        // 장르별 총 재생 횟수 계산
        for(int i = 0; i < genres.length; i++) {
            mapPlay.put(genres[i], mapPlay.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        // 장르를 재생 횟수 순으로 정렬
        ArrayList<String> genreList = new ArrayList<>(mapPlay.keySet());
        Collections.sort(genreList, (a, b) -> mapPlay.get(b) - mapPlay.get(a));
        
        ArrayList<Integer> answerList = new ArrayList<>();
        
        // 각 장르별로 최대 2곡씩 선택
        for(String genre : genreList) {
            ArrayList<Integer[]> songs = map.get(genre);
            answerList.add(songs.get(0)[0]);  // 첫 번째 곡은 무조건 추가
            
            if(songs.size() > 1) {  // 두 번째 곡이 있는 경우에만 추가
                answerList.add(songs.get(1)[0]);
            }
        }
        
        // ArrayList를 배열로 변환
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}