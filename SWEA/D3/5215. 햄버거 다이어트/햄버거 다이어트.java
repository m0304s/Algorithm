import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int N,L;
	private static int [] score;
	private static int [] kalories;
	
	private static int maxScore;
    public static void main(String[] args) throws IOException{
    	int T = Integer.parseInt(br.readLine());
    	
    	for(int t=1;t<=T;t++) {
    		maxScore = Integer.MIN_VALUE;
    		solution();
    		bw.write("#"+t+" "+ maxScore+"\n");
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    private static void solution() throws IOException{
    	String [] tokens = br.readLine().split(" ");
    	N = Integer.parseInt(tokens[0]);	//재료의 수
    	L = Integer.parseInt(tokens[1]);	//제한 칼로리
    	
    	score = new int[N];
    	kalories = new int[N];
    	for(int i=0;i<N;i++) {
    		tokens = br.readLine().split(" ");
    		score[i] = Integer.parseInt(tokens[0]);
    		kalories[i] = Integer.parseInt(tokens[1]);
    	}
    	
    	backtracking(0, 0, 0);
    }
    
    private static void backtracking(int depth, int totalScore,int totalCalories) {
    	if(totalCalories > L) return;
    	if(depth > N) return;
    	if(depth == N) {    		
    		maxScore = Math.max(maxScore, totalScore);
    		return;
    	}
    	//현재 재료를 선택하는 경우
    	backtracking(depth+1, totalScore + score[depth],totalCalories+kalories[depth]);
    	backtracking(depth+1, totalScore, totalCalories);
    }
}