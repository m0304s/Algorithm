class Solution {
    public String solution(int[] food) {
        String answer = "";
        int index=0;
        for(int foodNum: food){
            int count = foodNum/2;
            for(int i=0;i<count;i++){
                answer+=Integer.toString(index);
            }index++;
        }
        answer+="0";
        for(int i=answer.length()-2;i>=0;i--){
            answer+=Character.toString(answer.charAt(i));
        }
        System.out.println(answer);
        return answer;
    }
}