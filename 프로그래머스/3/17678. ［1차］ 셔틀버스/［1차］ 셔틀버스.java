import java.util.*;

class Solution {
    static PriorityQueue<Integer> pq = new PriorityQueue<>(
            new Comparator<Integer>(){
                public int compare(Integer o1, Integer o2){
                    return Integer.compare(o1, o2);
                }
            }        
        );
    public String solution(int n, int t, int m, String[] timetable) {
        //우선순위 큐에 크루 도착 시간 입력
        for(String time : timetable){
            pq.add(timeToInteger(time));
        }
        
        //9시 정각부터 셔틀을 N-1대 보냄
        //각 셔틀 도착 시간 이하인 크루를 최대 M명
        int curTime = timeToInteger("9:00");
        for(int i=0;i<n-1;i++){
            int curCount = 0;   //현재 셔틀에 탄 인원
            while(!pq.isEmpty() && pq.peek() <= curTime && curCount < m){
                pq.poll();
                curCount++;
            }
            curTime += t;
        }
        
        //마지막 셔틀에서만 체크
        int curCount = 0;
        int lastBoarded = -1;
        
        while(!pq.isEmpty() && pq.peek() <= curTime && curCount < m){
            lastBoarded = pq.poll();
            curCount++;
        }
        
        if(curCount < m){
            return timeToString(curTime);
        }else{
            //마지막으로 탄 크루 -1 에 도착하면 됨
            return timeToString(lastBoarded - 1);
        }
    }
    
    static String timeToString(int time){
        int hour = time / 60;
        int minute = time % 60;
        
        String hourString = ""+hour;
        String minuteString = ""+minute;
        
        if(hour < 10){
            hourString = "0" + hourString;
        }
        if(minute < 10){
            minuteString = "0" + minuteString;
        }
        
        return hourString + ":" + minuteString;
    }
    
    static public int timeToInteger(String time){
        String [] tokens = time.split(":");
        int hour = Integer.parseInt(tokens[0]);
        int minute = Integer.parseInt(tokens[1]);
        
        return hour * 60 + minute;
    }
}