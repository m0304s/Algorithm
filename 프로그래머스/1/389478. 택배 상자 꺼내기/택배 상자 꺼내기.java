class Solution {
    public int solution(int n, int w, int num) {
        int row = (num - 1) / w;
        int col;
        if(row % 2 == 0){   //짝수 층이라면
            col = (num - 1) % w;
        }else{  //홀수 층이라면
            col = w - 1 - ((num - 1) % w);
        }
        
        int max_row = (n - 1) / w;
        int top_box_num;
        if (max_row % 2 == 0) {
            top_box_num = max_row * w + col + 1;
        } else {
            top_box_num = (max_row + 1) * w - col;
        }
        
        if (top_box_num <= n) {
            return max_row - row + 1;
        } else {
            return max_row - row;
        }
    }
}