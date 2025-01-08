import java.io.*;
import java.util.*;

class Solution
{
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String [] args) throws Exception{
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int N = Integer.parseInt(br.readLine());
			bw.write("#"+t+" "+N/3+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
