import java.util.*;

//이분탐색
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;
        
        int answer = right;
        
        while(left <= right){
            int mid = (left + right) / 2;
            
            if(isValid(diffs, times, limit, mid)){
                answer = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    static boolean isValid(int [] diffs, int [] times, long limit, int level){
        long totalTime = 0;
        
        for(int i=0;i<diffs.length;i++){
            int diff = diffs[i];
            long timeCur = times[i];
            
            if(diff <= level){
                totalTime += timeCur;
            }else if(diff > level){
                long timePrev = (i == 0) ? 0 : times[i-1];
                long mistakes = diff - level;
                
                totalTime += mistakes * (timeCur + timePrev) + timeCur;
            }
            
            if(totalTime > limit) return false;
        }
        
        return true;
    }
}