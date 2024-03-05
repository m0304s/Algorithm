class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        while(true){
            if(n<a){
                break;
            }
            int left = n-(a*(n/a))+(n/a)*b;
            if(left<b){
                break;
            }else{
                answer+=(n/a)*b;
            }
            System.out.println("교환한 병: "+(n/a)+"남은 병: "+left);
            n=left;
        }
        
        return answer;
    }
}