import java.util.*;

class Solution {
    public class Group{
        int tiredNess;
        int diaCnt;
        int ironCnt;
        int stoneCnt;
        
        public Group(int diaCnt, int ironCnt, int stoneCnt){
            this.diaCnt = diaCnt;
            this.ironCnt = ironCnt;
            this.stoneCnt = stoneCnt;
            setTiredNess();
        }
        public void setTiredNess(){
            int tiredNess = this.diaCnt * 25 + this.ironCnt * 5 + this.stoneCnt;
            this.tiredNess = tiredNess;
        }
        public String toString(){
            return "tiredNess : " + this.tiredNess + " diaCnt : " + this.diaCnt + " ironCnt : "
                + this.ironCnt + " stoneCnt : " + this.stoneCnt;
        }
    }
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int totalPicks = picks[0]+picks[1]+picks[2];    //전체 곡갱이 수
        int maxMinerals = totalPicks*5; //채굴 가능한 광물 개수
        
        if(minerals.length>maxMinerals){
            minerals = Arrays.copyOf(minerals,maxMinerals);
        }
        
        List<Group> groups = new ArrayList<>();
        
        for(int i=0;i<minerals.length;i+=5){
            // int tiredNess = 0;
            int diaCnt = 0;
            int ironCnt = 0;
            int stoneCnt = 0;
            
            for(int j = 0; j<5 && i+j<minerals.length; j++){
                switch(minerals[i + j]){
                    case "diamond":
                        diaCnt++;
                        break;
                    case "iron":
                        ironCnt++;
                        break;
                    case "stone":
                        stoneCnt++;
                        break;
                }
            }
            groups.add(new Group(diaCnt,ironCnt,stoneCnt));
        }
        Collections.sort(groups, new Comparator<Group>(){
            @Override
            public int compare(Group o1, Group o2){
                return o2.tiredNess - o1.tiredNess;
            }
        });
        
        //최악의 피로도로 정렬된 그룹을 현재 가진 최선의 곡갱이로 배정
        for(Group group : groups){
            if(picks[0]>0){
                answer += (group.diaCnt + group.ironCnt + group.stoneCnt);
                picks[0]--;
            }else if(picks[1]>0){
                answer += (group.diaCnt * 5 + group.ironCnt + group.stoneCnt);
                picks[1]--;
            }else{
                answer += (group.diaCnt * 25 + group.ironCnt * 5 + group.stoneCnt);
                picks[2]--;
            }
        }
        return answer;
    }
}