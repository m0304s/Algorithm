import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [][] map;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            map = new int[9][9];
            for(int i=0;i<9;i++){
                String [] tokens = br.readLine().split(" ");
                for(int j=0;j<9;j++){
                    map[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            boolean checkAnswer = checkAnswer();
            if(checkAnswer) bw.write("#"+t+" "+1+"\n");
            else bw.write("#"+t+" "+0+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean checkAnswer() {
        // 가로
        for(int i=0;i<9;i++){
            List<Integer> numList = new ArrayList<>();
            for(int j=0;j<9;j++){
                numList.add(map[i][j]);
            }
            if(!checkDistinct(numList)) return false;
        }
        // 세로
        for(int j=0;j<9;j++){
            List<Integer> numList = new ArrayList<>();
            for(int i=0;i<9;i++){
                numList.add(map[i][j]);
            }
            if(!checkDistinct(numList)) return false;
        }
        // 3x3 정사각형
        for(int i=0;i<9;i+=3){
            for(int j=0;j<9;j+=3){
                List<Integer> numList = new ArrayList<>();
                numList.add(map[i][j]);
                numList.add(map[i][j+1]);
                numList.add(map[i][j+2]);
                numList.add(map[i+1][j]);
                numList.add(map[i+1][j+1]);
                numList.add(map[i+1][j+2]);
                numList.add(map[i+2][j]);
                numList.add(map[i+2][j+1]);
                numList.add(map[i+2][j+2]);
                if(!checkDistinct(numList)) return false;
            }
        }
        return true;
    }

    private static boolean checkDistinct(List<Integer> numList){
        if(numList.stream().distinct().count() == numList.size()) return true;

        return false;
    }
}
