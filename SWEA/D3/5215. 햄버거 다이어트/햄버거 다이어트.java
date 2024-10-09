import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [][] ingredients;
    static int N;
    static int totalCalories;
    static int answer;

    static boolean [] visited;
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            answer = 0;
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            totalCalories = Integer.parseInt(tokens[1]);
            ingredients = new int[N][2];
            for(int i=0;i<N;i++){
                tokens = br.readLine().split(" ");
                ingredients[i][0] = Integer.parseInt(tokens[0]);
                ingredients[i][1] = Integer.parseInt(tokens[1]);
            }
            dfs(0,0, 0);
            bw.write("#"+t+" " + answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int index,int currentScore, int currentCalories){
        if(currentCalories > totalCalories) return;

        if(index == N){
            answer = Math.max(currentScore, answer);
            return;
        }

        dfs(index+1,currentScore + ingredients[index][0], currentCalories + ingredients[index][1]); //현재 재료를 선택하는 경우
        dfs(index+1, currentScore, currentCalories);  //현재 재료를 선택하지 않는 경우
    }
    
}
