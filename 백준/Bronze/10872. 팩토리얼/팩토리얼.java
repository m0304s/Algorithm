import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String [] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		
		bw.write(factorial(N)+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int factorial(int i) {
		if(i == 0) return 1;
		if(i == 1) {
			return 1;
		}
		return i * factorial(i-1);
	}
}
