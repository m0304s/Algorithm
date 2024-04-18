import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int have_to_pick = 0;
        int have_to_deli = 0;
        long answer = 0;
        for(int i=n-1;i>=0;i--){
            have_to_deli -= deliveries[i];
            have_to_pick -= pickups[i];
            
            while(have_to_deli<0 || have_to_pick<0){
                have_to_deli+=cap;
                have_to_pick+=cap;
                answer+=(i+1)*2;
            }
        }
        return answer;
    }
}