import java.util.*;

class Solution {
    public HashMap<String,Integer> hashmap = new HashMap<>();
    public int[] solution(String[] keymap, String[] targets) {
        for(int i=0;i<keymap.length;i++){
            for(int j=0;j<keymap[i].length();j++){
                if(hashmap.containsKey(keymap[i].substring(j,j+1))){
                    if(hashmap.get(keymap[i].substring(j,j+1))>j+1){
                        hashmap.put(keymap[i].substring(j,j+1),j+1);
                    }
                }else{
                    hashmap.put(keymap[i].substring(j,j+1),j+1);   
                }
            }
        }
        System.out.println(hashmap);
        int[] answer = new int[targets.length];
        for(int i=0;i<targets.length;i++){
            int count = 0;
            for(int j=0;j<targets[i].length();j++){
                if(!hashmap.containsKey(targets[i].substring(j,j+1))){
                    count=-1;
                    break;
                }else{
                    count+=hashmap.get(targets[i].substring(j,j+1));
                }
            }
            answer[i]=count;
        }
        System.out.println(answer[0]);
        return answer;
    }
}