import java.util.*;
// 어떤 하루 동안의 예약 상황을 표시!
class Solution {
    // 단일 예약의 객체를 구성
    static class Book {
        int start;
        int end;
        
        public Book(int start, int end) {
            this.start = start;
            // 청소시간 10분 추가
            this.end = end + 10; 
        }
    }
    
    public int solution(String[][] book_time) {
        // 예약 시간을 분 단위로 변환
        Book[] books = conversion(book_time);
        // 시작 시간 기준으로 정렬
        Arrays.sort(books, (a, b) -> a.start - b.start);
        
        // 각 방의 예약 목록을 저장하는 리스트
        List<List<Book>> rooms = new ArrayList<>();
        
        // 모든 예약에 대해 순회
        for(Book book : books) {
            boolean booked = false;
            
            // 기존 방 중에 배정 가능한 방이 있는지 확인
            for(List<Book> room : rooms) {
                if(canBook(room, book)) {
                    room.add(book);
                    booked = true;
                    break;
                }
            }
            
            // 배정 가능한 방이 없으면 새로운 방 생성
            if(!booked) {
                List<Book> newRoom = new ArrayList<>();
                newRoom.add(book);
                rooms.add(newRoom);
            }
        }
        
        return rooms.size();
    }
    // 해당 방에 새로운 예약을 배정할 수 있는지 확인
    private boolean canBook(List<Book> room, Book newBook) {
        for(Book booked : room) {
            // 시간이 겹치는지 확인
            if(!(booked.end <= newBook.start || booked.start >= newBook.end)) {
                return false;
            }
        }
        return true;
    }
    
    // HH:MM String 을 00:00~ HH:MM 까지의 분으로 치환해서
    // 예약 시작점, 끝점을 정수로 표현할 수 있게 가공
    private Book[] conversion(String[][] book_time){
        Book[] result = new Book[book_time.length];
        for(int i = 0 ; i < book_time.length; i++){
            int tempS = parse(book_time[i][0]); // temp Start
            int tempE = parse(book_time[i][1]); // temp End
            result[i] = new Book(tempS, tempE);
        }
        return result;
    }
    
    // HH:MM -> M
    private int parse(String time){
        String[] temp = time.split(":");
        return Integer.parseInt(temp[0])*60 + Integer.parseInt(temp[1]) ;
    }
}