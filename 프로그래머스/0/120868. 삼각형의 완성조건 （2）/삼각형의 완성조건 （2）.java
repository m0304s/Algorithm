import java.util.*;

class Solution {
    public int solution(int[] sides) {
        Arrays.sort(sides);
        int min = sides[0];
        int max = sides[1];
        
        int low_range = max - min;
        int high_range = max + min;
        
        int answer = high_range - low_range - 1;
        return answer;
    }
}