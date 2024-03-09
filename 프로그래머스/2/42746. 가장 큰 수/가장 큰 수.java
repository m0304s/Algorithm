import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String [] numberStrings = new String[numbers.length];
        for(int i=0;i<numbers.length;i++){
            numberStrings[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(numberStrings, (o1,o2)-> (o2+o1).compareTo(o1+o2));
        StringBuilder br = new StringBuilder();
        if(numberStrings[0].equals("0")){
            return "0";
        }else{
            for(String s : numberStrings){
                br.append(s);
            }
        }
        String answer = br.toString();
        return answer;
    }
}