import java.util.*;

class Solution {
    public static HashSet<String> set = new HashSet<>();
    public static HashMap<String, Integer> map = new HashMap<>();
    public static HashMap<Integer, String> map_c = new HashMap<>();
    public String solution(String s, String skip, int index) {
        for(int a = 97;a<97+26;a++){
            String d = Character.toString((char)a);
            set.add(d);
        }
        for(int i=0;i<skip.length();i++){
            set.remove(skip.substring(i,i+1));
        }
        
        int count =0;
        for(String st : set){
            map_c.put(count,st);
            map.put(st,count);
            count++;
        }
        System.out.println(map);
        System.out.println(map_c);
        String answer = "";
        for(int i=0;i<s.length();i++){
            String f = s.substring(i,i+1);
            System.out.println(f);
            int k = map.get(f);
            System.out.println("k : "+k);
            System.out.println("k+index : "+(k+index));
            answer+=map_c.get((k+index)%map.size());
            
        }
        System.out.println(answer);
        
        return answer;
    }
}