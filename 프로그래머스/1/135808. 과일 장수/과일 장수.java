import java.util.*;

class Solution {
    public static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    public int solution(int k, int m, int[] score) {
        Integer[] scoreObj = Arrays.stream(score).boxed().toArray(Integer[]::new);
        Arrays.sort(scoreObj, Comparator.reverseOrder());
        
        for(int i=0;i<scoreObj.length;i+=m){
            ArrayList<Integer> scoreList = new ArrayList<>();
            for(int j=0;j<m;j++){
                if(scoreObj.length>i+j){
                    scoreList.add(scoreObj[i+j]);   
                }
            }
            list.add(scoreList);
        }
        int count=0;
        
        for(ArrayList<Integer> box : list){
            int min = Integer.MAX_VALUE;   
            if(box.size()!=m){
                continue;
            }
            for(int i=0;i<box.size();i++){
                if(box.get(i)<min){
                    min=box.get(i);
                }
            }
            count+=min*m;
        }
        // for(ArrayList<Integer> s : list){
        //     for(int i=0;i<s.size();i++){
        //         System.out.print(s.get(i)+" ");
        //     }System.out.println();
        // }
        int answer = count;
        return answer;
    }
}
