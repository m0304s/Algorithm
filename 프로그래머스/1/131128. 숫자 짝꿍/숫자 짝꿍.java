import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        StringBuilder st = new StringBuilder();
        String[] arrayX = X.split("");
        String[] arrayY = Y.split("");
        
        int [] Xcount = new int[10];
        int [] Ycount = new int[10];
        
        for(String x : arrayX){
            Xcount[Integer.parseInt(x)]++;
        }
        for(String y : arrayY){
             Ycount[Integer.parseInt(y)]++;
        }
        
        ArrayList<Integer> list = new ArrayList<>();
     
        for(int i=9;i>=0;i--){
            int minCount = Math.min(Xcount[i],Ycount[i]);

            for(int j=0;j<minCount;j++){
                list.add(i);
            }
        }

        String answer = "";
        
        while(true){
            if(list.size()>1 && list.get(0)==0){
                list.remove(0);
            }else{
                break;
            }   
        }
        if(list.size()==0){
            return "-1";
        }else{
            for(int t : list){
                // answer+=Integer.toString(t);
                st.append(Integer.toString(t));
            }
            answer = st.toString();
            return answer;
        }
    }
}