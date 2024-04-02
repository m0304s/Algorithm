import java.util.*;

class Solution {
    class SubSequence{
        int start;
        int end;
        int length;
        
        SubSequence(int start,int end, int length){
            this.start = start;
            this.end = end;
            this.length = length;
        }
        public String toString(){
            return "Subject: start= "+start + ", end= "+end+", length= "+length;
        }
    }
    public int[] solution(int[] sequence, int k) {
        Arrays.sort(sequence);
        List<SubSequence> subs = new ArrayList<>();
        
        int left = 0;
        int right = 0;
        int sum = sequence[0];
        int n = sequence.length;
        while(true){
            if(sum==k){
                subs.add(new SubSequence(left,right,(right-left)));
            }
            if(left==n && right==n){
                break;
            }
            if(sum <= k && right < n){
                right++;
                // 새로운 원소가 추가되었으므로, right에 위치하는 값을 부분 수열 합에 더함
                if(right < n) sum += sequence[right];
            } else {
                // 기존의 left의 위치한 원소를 부분 수열의 합에서 제외
                if(left < n) sum -= sequence[left];
                left++;
            }
        }
        Collections.sort(subs,new Comparator<SubSequence>(){
           public int compare(SubSequence o1, SubSequence o2){
               if(o1.length==o2.length){
                   return o1.start-o2.start;
               }else{
                   return o1.length-o2.length;
               }
           } 
        });
        int start = subs.get(0).start;
        int end = subs.get(0).end;
        int[] answer = {start,end};
        return answer;
    }
}