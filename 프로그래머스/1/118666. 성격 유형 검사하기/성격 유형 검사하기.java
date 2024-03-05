import java.util.*;

class Solution {
    public static HashMap<String,Integer> map = new HashMap<>();    //점수를 저장할 해시맵
    public String solution(String[] survey, int[] choices) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<survey.length;i++){
            String[] list = survey[i].split("");
            if(!map.containsKey(list[0])){
                map.put(list[0],0);
            }
            if(!map.containsKey(list[1])){
                map.put(list[1],0);
            }
        }
        for(int i=0;i<survey.length;i++){
            String[] list = survey[i].split("");
            int choice = choices[i];
            int reward = 0;
                switch(choice){
                    case 1 :
                        reward = 3;
                        break;
                    case 2 :
                        reward = 2;
                        break;
                    case 3 :
                        reward = 1;
                        break;
                    case 4 :
                        reward = 0;
                        break;
                    case 5 :
                        reward = 1;
                        break;
                    case 6 :
                        reward = 2;
                        break;
                    case 7 :
                        reward = 3;
                        break;
                }
            if(choice>4){
                map.put(list[1],map.get(list[1])+reward);
            }else if(choice<4){
                map.put(list[0],map.get(list[0])+reward);
            }
        }
        System.out.println(map.toString());
        if(map.containsKey("R")){
            if(map.get("R")<map.get("T")){
                sb.append("T");
            }else{
                sb.append("R");
            }
        }else{
            sb.append("R");
        }
        if(map.containsKey("C")){
            if(map.get("C")<map.get("F")){
                sb.append("F");
            }else{
                sb.append("C");
            }
        }else{
            sb.append("C");
        }
        if(map.containsKey("J")){
            if(map.get("J")<map.get("M")){
                sb.append("M");
            }else{
                sb.append("J");
            }
        }else{
            sb.append("J");
        }
        if(map.containsKey("A")){
            if(map.get("A")<map.get("N")){
                sb.append("N");
            }else{
                sb.append("A");
            }
        }else{
            sb.append("A");
        }
        String answer = sb.toString();
        System.out.println(answer);
        return answer;
    }
}