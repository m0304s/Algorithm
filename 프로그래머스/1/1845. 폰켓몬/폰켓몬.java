import java.util.HashSet;
class Solution {
    public int solution(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        int answer=0;
        for(int i=0;i<nums.length;i++){
            hs.add(nums[i]);
        }
        if(hs.size()<=nums.length/2){
            answer=hs.size();
        }else{
            answer=nums.length/2;
        }
        return answer;
    }
}