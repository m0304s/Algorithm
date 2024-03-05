import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int[][] gift = new int[friends.length][friends.length];
        int[][] score = new int[friends.length][3];
        int[] finalScore = new int[friends.length]; // 최종 받는 선물의 개수를 저장하는 배열

        for (int i = 0; i < gifts.length; i++) {
            String[] token = gifts[i].split(" "); // token[0] : 준 사람, token[1] : 받은 사람
            int A_index = 0;
            int B_index = 0;
            for (int j = 0; j < friends.length; j++) { // index 찾기
                if (friends[j].equals(token[0])) {
                    A_index = j;
                }
                if (friends[j].equals(token[1])) {
                    B_index = j;
                }
            }
            gift[A_index][B_index]++;
        }

        for (int i = 0; i < friends.length; i++) { // 선물 지수 계산
            for (int j = 0; j < friends.length; j++) {
                score[i][0] += gift[i][j];
                score[i][1] += gift[j][i];
            }
            score[i][2] = score[i][0] - score[i][1];
        }

        for (int i = 0; i < friends.length; i++) {
            for (int j = 0; j < friends.length; j++) {
                if (i != j && (gift[i][j] != 0 || gift[j][i] != 0)) { // 두 사람이 선물을 주고받은 기록이 있다면, 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받음
                    int gift_i = gift[i][j];
                    int gift_j = gift[j][i];
                    if (gift_i > gift_j) {
                        finalScore[i]++;
                    } else if (gift_i < gift_j) {
                        finalScore[j]++; // 받는 사람 숫자 +1
                    }
                }
                if (i != j && (gift[i][j] == gift[j][i])) { // 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면, 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받음
                    int score_i = score[i][2];
                    int score_j = score[j][2];
                    if (score_i > score_j) {
                        finalScore[i]++;
                    } else if (score_i < score_j) {
                        finalScore[j]++;
                    }
                }
            }
        }
        System.out.println("===============");
        for (int i = 0; i < finalScore.length; i++) {
            finalScore[i]=finalScore[i]/2;
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<finalScore.length;i++){
            if(max<finalScore[i]){
                max=finalScore[i];
            }
        }
        return max;
    }
}
