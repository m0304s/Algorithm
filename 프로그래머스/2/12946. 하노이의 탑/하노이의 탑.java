import java.util.*;

class Solution {
    public ArrayList<int[]> solution(int n) {
        ArrayList<int[]> answer = new ArrayList<>();
        hanoi(n,1,3,2,answer);
        return answer;
    }
    public ArrayList<int[]> hanoi(int N, int start, int end, int via, ArrayList<int[]>answer){
        if(N==1){
            int[] list = {start, end};
            answer.add(list);
        }else{
            hanoi(N-1,start,via,end,answer);
            int [] list = {start,end};
            answer.add(list);
            hanoi(N-1,via,end,start,answer);
        }
        return answer;
    }
}