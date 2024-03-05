import java.util.*;
class Solution {
        public int[] solution(int N, int[] stages) {
        HashMap<Integer,Double> hm = new HashMap<>();
        int [] answer = new int[N];
        //실패율 계산하는 구간
        for(int i=1;i<=N;i++){  //i == 스테이지 수 j==사용자 인덱스
            int count=0;    //스테이지까지 올라온 사람 수
            int num=0;  //스테이지에서 실패한 사람 수
            for(int j=0;j<stages.length;j++){
                if(stages[j]==i){
                    num++;
                }
                if(stages[j]>=i){
                    count++;
                }
            }
            if(count==0){
                hm.put(i,0.0);
            }else{
                hm.put(i,(double)num/count);
            }
        }
        List<Integer> keySetList = new ArrayList<>(hm.keySet());
        Collections.sort(keySetList,(o1,o2)-> (hm.get(o2).compareTo(hm.get(o1))));
        int num=0;
        for(Integer key: keySetList){
            answer[num++]=key;
        }
        return answer;
    }
}