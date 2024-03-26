import java.util.*;

class Solution {
    class Pair{
        String name;
        int remainTime;
        
        Pair(String name, int remainTime){
            this.name = name;
            this.remainTime = remainTime;
        }
    }
    public static int convert(String timeString){
        String[] tokens = timeString.split(":");
        return Integer.parseInt(tokens[0])*60+Integer.parseInt(tokens[1]);
    }
    public ArrayList<String> solution(String[][] plans) {
        ArrayList<String> answer = new ArrayList<>();
        Stack<Pair> working = new Stack<>();
        
        Arrays.sort(plans,new Comparator<String[]>(){
           public int compare(String[] s1, String[] s2){
               return s1[1].compareTo(s2[1]);
           } 
        });
        
        for(int i=0;i<plans.length;i++){
            String name = plans[i][0];
            int nowTime = convert(plans[i][1]);
            int playTime = Integer.parseInt(plans[i][2]);
            
            if(i==plans.length-1){
                answer.add(name);
            }else{
                int nextTime = convert(plans[i+1][1]);
                int remainTime = nextTime-nowTime;
                
                working.push(new Pair(name,playTime));
                
                while(!working.isEmpty() && remainTime > 0){
                    Pair nowWorking = working.pop();
                    int played = Math.min(remainTime,nowWorking.remainTime);
                    
                    if(played == nowWorking.remainTime){
                        answer.add(nowWorking.name);
                    }else{
                        working.add(new Pair(nowWorking.name, nowWorking.remainTime-played));
                    }
                    remainTime-=played;
                }
            }
        }
        while(!working.isEmpty()){
            answer.add(working.pop().name);
        }

        return answer;
    }
}