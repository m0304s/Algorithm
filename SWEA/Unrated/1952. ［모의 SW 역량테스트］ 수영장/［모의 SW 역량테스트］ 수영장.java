import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private static int [] plan;
    private static int [] days;
    private static int [] costs;
    
    public static void main(String [] args) throws IOException{
    	int T = Integer.parseInt(br.readLine());
    	for(int t=1;t<=T;t++) {
    		int minCost = solution();
    		bw.write("#"+t+" "+minCost+"\n");
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    private static int solution() throws IOException{
    	String [] tokens = br.readLine().split(" ");
    	plan = new int[13];
    	costs = new int[4];
    	days = new int[13];
    	
    	for(int i=0;i<4;i++) {	//1일, 1달, 3달, 1년 이용권 가격
    		costs[i] = Integer.parseInt(tokens[i]);
    	}
    	
    	tokens = br.readLine().split(" ");
    	for(int i=1;i<=12;i++) {
    		days[i] = Integer.parseInt(tokens[i-1]);
    	}
    	
    	for(int i=1;i<=12;i++){
    		plan[i] = Math.min(plan[i-1] + costs[1], plan[i-1] + days[i] * costs[0]);
    		
    		if(i >= 3) {
    			//3달 이용권 사용 가능
    			plan[i] = Math.min(plan[i], plan[i-3] + costs[2]);
    		}
    	}
    	return Math.min(costs[3], plan[12]);
    }
}
