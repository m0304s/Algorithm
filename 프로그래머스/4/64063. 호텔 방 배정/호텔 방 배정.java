import java.util.*;

class Solution {
    static HashMap<Long, Long> roomMap;
    
    public long[] solution(long k, long[] room_number) {
        // K : 전체 방 개수
        // room_number : 고객이 원하는 방
        long[] answer = new long[room_number.length];
        roomMap = new HashMap<>();
        
        for(int i=0; i<room_number.length;i++){
            answer[i] = findEmptyRoom(room_number[i]);
        }
        
        return answer;
    }
    
    static long findEmptyRoom(long roomNumber){
        if(!roomMap.containsKey(roomNumber)){
            roomMap.put(roomNumber, roomNumber + 1);
            return roomNumber;
        }
        
        long nextAvailable = findEmptyRoom(roomMap.get(roomNumber));
        
        roomMap.put(roomNumber, nextAvailable+1);
        
        return nextAvailable;
    }
}