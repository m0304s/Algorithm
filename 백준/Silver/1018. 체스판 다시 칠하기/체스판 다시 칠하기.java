import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int N = kb.nextInt();
        int M = kb.nextInt();
        String [] input = new String[N];
        kb.nextLine();
        for(int i=0;i<N;i++){
            input[i]= kb.nextLine();
        }
        kb.close();
        int count = Integer.MAX_VALUE; //가장 큰값으로 지정해두고(초기값)
        for(int i = 0; i <= N-8; i++){
            for(int j = 0; j <= M-8; j++){
                int resultCount = answer(i, j, input);

                if(count > resultCount){
                    count = resultCount;
                }
            }
        }
        System.out.println(count);
    }
    public static int answer(int startRow,int startCol, String [] array){
        String[] board = {"WBWBWBWB", "BWBWBWBW"};
        int whiteCnt = 0;
        for(int i=0;i<8;i++){
            int row = startRow+i;
            for(int j=0;j<8;j++){
                int col = startCol+j;
                if(array[row].charAt(col)!=board[row%2].charAt(j)){
                    whiteCnt++;
                }
            }
        }
        return Math.min(whiteCnt,64-whiteCnt);
    }
}
