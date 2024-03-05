import java.util.*;
class Solution {
        public static ArrayList<String> list = new ArrayList<>();
        public int solution(String s) {
            while(!s.equals("")){
                if(s.length()==1){
                    list.add(s);
                    break;
                }
                for (int i = 0; i <= s.length(); i++) {
                    int a_count = 0;
                    int b_count = 0;
                    String sub = s.substring(0, 1); // 첫 i글자를 substring으로 가져옴
                    String add="";
                    for (int j = 0; j < s.length(); j++) {
                        if (sub.equals(s.substring(j, j + 1))) {
                            a_count++;
                        } else {
                            b_count++;
                        }
    
                        add+=s.substring(j, j + 1);
                        if (a_count == b_count || a_count + b_count == s.length()) {
                            list.add(add);
                            s=s.replaceFirst(add,"");
                            break;
                        }
    
                    }
                }
                
            }
            
            System.out.println(list.size());
            return list.size();
        }
    }