import java.util.*;

class Solution {
   public int solution(String begin, String target, String[] words) {
       // target이 words에 없으면 변환 불가
       if (!Arrays.asList(words).contains(target)) {
           return 0;
       }
       
       Queue<Word> queue = new LinkedList<>();
       Set<String> visited = new HashSet<>();
       
       queue.add(new Word(begin, 0));
       
       while (!queue.isEmpty()) {
           Word current = queue.poll();
           
           if (current.word.equals(target)) {
               return current.count;
           }
           
           for (String word : words) {
               if (visited.contains(word)) continue;
               
               if (canChange(current.word, word)) {
                   queue.add(new Word(word, current.count + 1));
                   visited.add(word);
               }
           }
       }
       
       return 0;
   }
   
   // 한 글자만 다른지 확인하는 메서드
   private boolean canChange(String word1, String word2) {
       int diff = 0;
       for (int i = 0; i < word1.length(); i++) {
           if (word1.charAt(i) != word2.charAt(i)) {
               diff++;
           }
           if (diff > 1) return false;
       }
       return diff == 1;
   }
   
   // 단어와 변환 횟수를 저장하는 클래스
   private class Word {
       String word;
       int count;
       
       Word(String word, int count) {
           this.word = word;
           this.count = count;
       }
   }
}