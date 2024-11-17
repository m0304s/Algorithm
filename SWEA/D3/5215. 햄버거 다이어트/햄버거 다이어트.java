import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,L;
    static int [] costs;
    static int [] calories;
    static int maxCost;
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            L = Integer.parseInt(tokens[1]);

            maxCost = Integer.MIN_VALUE;
            costs = new int[N];
            calories = new int[N];

            for(int i=0;i<N;i++){
                tokens = br.readLine().split(" ");
                costs[i] = Integer.parseInt(tokens[0]);
                calories[i] = Integer.parseInt(tokens[1]);
            }

            combination(0,0,0);
            System.out.println("#"+t+" "+maxCost);
        }
    }

    private static void combination(int index, int currentCost, int currentCalories){
        if(currentCalories > L) return;
        maxCost = Math.max(maxCost,currentCost);
        if(index>=N) return;
        //지금 현재 재료를 선택하는 경우
        combination(index+1,currentCost+costs[index],currentCalories+calories[index]);
        //지금 현재 재료를 선택하지 않는 경우
        combination(index+1,currentCost,currentCalories);
    }
}
