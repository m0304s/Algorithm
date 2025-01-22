import java.io.*;
import java.util.*;

class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int N;
	private static char[][] arr;
	
	public static void main(String [] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		
		arr = new char[N][N];
		
		for(char [] a : arr) {
			Arrays.fill(a,'a');
		}
		
		star(0,0,N);
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j] == 'a') bw.write(" ");
				else bw.write("*");
			}bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void star(int x,int y,int size) {
		if(size == 3) {
			arr[x][y] = '*';
			arr[x][y+1] = '*';
			arr[x][y+2] = '*';
			
			arr[x+1][y] = '*';
			arr[x+1][y+2] = '*';
			
			arr[x+2][y] = '*';
			arr[x+2][y+1] = '*';
			arr[x+2][y+2] = '*';
			
			return;
		}
		
		int pow = size/3;
		
		star(x,y,pow);
		star(x,y+pow,pow);
		star(x,y+pow*2,pow);
		star(x+pow,y,pow);
		star(x+pow,y+pow*2,pow);
		star(x+pow*2,y,pow);
		star(x+pow*2,y+pow,pow);
		star(x+pow*2,y+pow*2,pow);
	}
}
