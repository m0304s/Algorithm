import java.io.*;
import java.util.*;

class Solution
{
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String [] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			String [] tokens = br.readLine().split(" ");
			int L = Integer.parseInt(tokens[0]);
			int U = Integer.parseInt(tokens[1]);
			int X = Integer.parseInt(tokens[2]);
			
			if(X > U) bw.write("#"+t+" "+"-1\n");
			else if(X >= L) bw.write("#"+t+" 0\n");
			else {
				bw.write("#"+t+" "+(L-X)+"\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
