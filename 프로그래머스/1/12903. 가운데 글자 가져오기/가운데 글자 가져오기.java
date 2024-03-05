class Solution {
    public StringBuilder solution(String s){
        String[] result = s.split("");
        StringBuilder answer = new StringBuilder();
        if(result.length%2==0){  //단어의 길이가 짝수일 경우
            answer.append(result[result.length/2-1]);
            answer.append(result[result.length/2]);
        }else{
            answer.append(result[result.length/2]);
        }
        return answer;
    }
}