import java.util.Arrays;
class Solution {
public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        Arrays.fill(answer, 9999);
        int week=0;
        while(week<score.length){
            int result[] = new int[score.length];
            Arrays.fill(result, 9999);
            for(int i=0;i<=week;i++){
                result[i]=score[i];
            }
            Arrays.sort(result);
            if(week<k){
                answer[week]=result[0];
            }else{
                answer[week]=result[week-k+1];
            }
            week++;
        }
        return answer;
    }
}