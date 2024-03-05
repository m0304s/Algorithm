import java.util.*;

class Solution {
    public class Privacy{
        int year;
        int month;
        int day;
        String type;
        
        public Privacy(int year,int month, int day, String type){
            this.year = year;
            this.month = month;
            this.day = day;
            this.type = type;
        }
    }
    
    public static ArrayList<Integer> todayList = new ArrayList<>();
    public static HashMap<String,Integer> termsMap = new HashMap<>();
    public static ArrayList<Privacy> privaciesList = new ArrayList<>();
    public ArrayList<Integer> solution(String today, String[] terms, String[] privacies) {
        
        /*HashMap에 약관 별 보관 기간 저장*/
        for(int i=0;i<terms.length;i++){
            String [] tokens = terms[i].split(" ");
            termsMap.put(tokens[0],Integer.parseInt(tokens[1]));
        }
        
        StringTokenizer today_st = new StringTokenizer(today,".");    
        while(today_st.hasMoreTokens()){
            todayList.add(Integer.parseInt(today_st.nextToken()));
        }
        
        /*privaciesList에 privacies 저장*/
        for(int i=0;i<privacies.length;i++){
            String [] tokens = privacies[i].split(" ");
            int [] token = new int[3];
            StringTokenizer privacy_st = new StringTokenizer(tokens[0],".");
            int count=0;
            while(privacy_st.hasMoreTokens()){
                token[count++]=Integer.parseInt(privacy_st.nextToken());
            }
            Privacy p = new Privacy(token[0],token[1],token[2],tokens[1]);
            privaciesList.add(p);
        }

        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int i=0;i<privaciesList.size();i++){
            Privacy p = privaciesList.get(i);
            int year = p.year;
            int month = p.month;
            int day = p.day;
            String type = p.type;
            
            int duration = termsMap.get(type);
            
            int count = duration* 28;
            int num = ((todayList.get(0)-year)*12*28)+
                (todayList.get(1)-month)*28 + (todayList.get(2)-day);
            if(num>=count){
                answer.add(i+1);
            }
        }
        
        return answer;
    }
}