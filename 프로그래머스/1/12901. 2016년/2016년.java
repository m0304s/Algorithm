class Solution {
    public String solution(int a, int b) {
       //2016년 1월 1일은 금요일
        String[] Month = {"FRI","SAT","SUN","MON","TUE","WED","THU"};
        int [] days = {31,29,31,30,31,30,31,31,30,31,30,31};
        //A달 B일까지 도달하는 날짜를 계산
        int day=b;
        for(int k=0;k<a-1;k++){
            day+=days[k];
        }
        int index=day%7;
        if(index==0){
            index=7;
        }
        String answer=Month[index-1];
        return answer;
    }
}