import java.io.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException{
		int [] fibo = new int[21];
		fibo[0] = 0;
		fibo[1] = 1;
	
		for(int i=2;i<=20;i++) {
			fibo[i] = fibo[i-2] + fibo[i-1];
		}
		
		int N = Integer.parseInt(br.readLine());
		bw.write(fibo[N]+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
