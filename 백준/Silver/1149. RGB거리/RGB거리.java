import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N;
	static int [][] color;
	static int [][] costs;
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		color = new int[N][3];
		costs = new int[N][3];
		
		for(int i=0;i<N;i++) {
			String [] tokens = br.readLine().split(" ");
			color[i][0] = Integer.parseInt(tokens[0]);
			color[i][1] = Integer.parseInt(tokens[1]);
			color[i][2] = Integer.parseInt(tokens[2]);
		}
		
		costs[0][0] = color[0][0];
		costs[0][1] = color[0][1];
		costs[0][2] = color[0][2];
		
		costs[1][0] = Math.min(costs[0][1] + color[1][0], costs[0][2] + color[1][0]);
		costs[1][1] = Math.min(costs[0][0] + color[1][1], costs[0][2] + color[1][1]);
		costs[1][2] = Math.min(costs[0][0] + color[1][2], costs[0][1] + color[1][2]);
		
		for(int i=2;i<N;i++) {
			costs[i][0] = Math.min(costs[i-1][1] + color[i][0], costs[i-1][2] + color[i][0]);
			costs[i][1] = Math.min(costs[i-1][0] + color[i][1], costs[i-1][2] + color[i][1]);
			costs[i][2] = Math.min(costs[i-1][0] + color[i][2], costs[i-1][1] + color[i][2]);
		}
		
		bw.write(Math.min(Math.min(costs[N-1][0], costs[N-1][1]), costs[N-1][2])+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
