import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] costs;
    static int[] calories;

    static int N,L;
    static int maxCost;
    static int[] output;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            maxCost = Integer.MIN_VALUE;
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            L = Integer.parseInt(tokens[1]);

            costs = new int[N];
            calories = new int[N];
            output = new int[N];

            for(int i=0;i<N;i++){
                tokens = br.readLine().split(" ");
                costs[i] = Integer.parseInt(tokens[0]);
                calories[i] = Integer.parseInt(tokens[1]);
            }

            combination(0,0,0);
            bw.write("#"+t+" "+maxCost+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void combination(int index, int totalCost, int totalCalories){
        if(index == N){
            if(totalCalories<=L){
                maxCost = Math.max(maxCost,totalCost);
            }
            return;
        }

        combination(index+1,totalCost,totalCalories);
        combination(index+1,totalCost+costs[index],totalCalories+calories[index]);
    }
}
