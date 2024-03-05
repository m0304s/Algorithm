import java.util.*;

class Solution {
    public static HashMap<String, Integer> hashMap = new HashMap<>();
    public static ArrayList<Integer> answer = new ArrayList<>();
    public ArrayList<Integer> solution(String s) {
        for(int i=0;i<s.length();i++){
            if(!hashMap.containsKey(s.substring(i,i+1))){
                hashMap.put(s.substring(i,i+1),i);
                answer.add(-1);
            }else{
                answer.add(i-hashMap.get(s.substring(i,i+1)));
                hashMap.put(s.substring(i,i+1),i);
            }
        }
        System.out.println(hashMap);
        return answer;
    }
}