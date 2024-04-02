import java.util.*;

class Solution {
    class Room{
        int startTime;
        int endTime;
        
        Room(int startTime,int endTime){
            this.startTime = startTime;
            this.endTime = endTime;
        }
        Room(String strStartTime,String strEndTime){
            String[] startToken = strStartTime.split(":");
            String[] endToken = strEndTime.split(":");
            this.startTime = Integer.parseInt(startToken[0])*60 + Integer.parseInt(startToken[1]);
            this.endTime = Integer.parseInt(endToken[0])*60+Integer.parseInt(endToken[1]);
        }
        public int checkRoomCondition(){
            return this.endTime+10;
        }
    }
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, (a, b) -> {  //시작시간이 빠른 순으로 정렬
            int timeA = Integer.parseInt(a[0].replace(":", ""));
            int timeB = Integer.parseInt(b[0].replace(":", ""));
            return Integer.compare(timeA, timeB);
        });
        ArrayList<Room> list = new ArrayList<>();
        for(int i=0;i<book_time.length;i++){
            if(list.isEmpty()){
                list.add(new Room(book_time[i][0],book_time[i][1]));
            }else{
                //순차탐색하면서 방이 사용 가능한지 체크
                Room newMember = new Room(book_time[i][0],book_time[i][1]);
                for(int j=0;j<list.size();j++){
                    Room oldRoom = list.get(j);
                    if(newMember.startTime>=oldRoom.checkRoomCondition()){  //방 이용가능한 경우
                        list.set(j,newMember);    //이전 방을 그대로 사용
                        break;
                    }else if(j==list.size()-1){
                        list.add(newMember);
                        break;
                    }
                }
            }
        }
        int answer = list.size();
        return answer;
    }
}