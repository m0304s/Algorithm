import java.util.*;
class Solution {
    public int solution(String[][] board, int h, int w) {

        int n = board.length;
        int count = 0;  //같은 색으로 색칠된 칸의 개수
        
        ArrayList<Integer> dh = new ArrayList<>();
        dh.add(0);
        dh.add(1);
        dh.add(-1);
        dh.add(0);
        
        ArrayList<Integer> dw = new ArrayList<>();
        dw.add(1);
        dw.add(0);
        dw.add(0);
        dw.add(-1);
        
        for(int i=0;i<=3;i++){
            int h_check = h+dh.get(i);
            int w_check = w+dw.get(i);
            
            if(h_check<n&&h_check>=0&&w_check<n&&w_check>=0){
                if(board[h][w].equals(board[h_check][w_check])){
                    count++;
                }
            }
        }
        return count;
    }
}