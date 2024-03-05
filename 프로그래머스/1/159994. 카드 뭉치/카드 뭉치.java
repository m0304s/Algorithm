class Solution {
public String solution(String[] cards1, String[] cards2, String[] goal) {
        int card1=0;
        int card2=0;
        boolean result=true;
        String answer;
        for(int i=0;i<goal.length;i++){
            if(card1<cards1.length&&goal[i].equals(cards1[card1])){
                card1++;
                continue;
            }else if(card2<cards2.length&&goal[i].equals(cards2[card2])){
                card2++;
                continue;
            }
            if(i==goal.length){
                result=true;
                break;
            }
            result=false;
            break;
        }
        if(result){
            answer="Yes";
        }
        else{
            answer="No";
        }
        return answer;
    }
}