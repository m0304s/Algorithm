import java.util.*;

class Solution {
    static int [] servers;  //각 시간 대 서버 수
    static int NUMBER_OF_USER_PER_SERVER;   //1개의 서버 안에 접속할 수 있는 유저의 수
    static int RUNNING_TIME;    //증설된 서버 운영 시간
    
    public int solution(int[] players, int m, int k) {
        servers = new int[24];
        RUNNING_TIME = k;
        NUMBER_OF_USER_PER_SERVER = m;
        
        int answer = 0;
        
        for(int i=0;i<24;i++){
            int required = players[i] / NUMBER_OF_USER_PER_SERVER;
            int needToIncrease = required - servers[i];
            
            if(needToIncrease > 0){
                increaseServer(i, needToIncrease);
                answer += needToIncrease;
            }
        }
                           
        return answer;
    }
    
    static void increaseServer(int time, int increaseCnt){
        for(int t=time;t<time+RUNNING_TIME;t++){
            if(t >= 24) break;
            servers[t]+=increaseCnt;
        }
        return;
    }
}