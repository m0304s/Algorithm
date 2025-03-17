import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int R,C;
	static char [][] map;
	
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		R = Integer.parseInt(tokens[0]);
		C = Integer.parseInt(tokens[1]);
		
		map = new char[R][C];
		for(int i=0;i<R;i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int val = 0;
		for(int i=0;i<R;i++) {
			if(check(i,0)) {
				val++;
			}
		}
		bw.write(val+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static boolean check(int x,int y) {
		map[x][y] = '-';
		
		//마지막 열에 도착했으면..
		if(y == C-1) return true;
		
		//오른쪽 위
		if(x > 0 && map[x-1][y+1] == '.') {
			if(check(x-1,y+1)) return true;
		}
		
		//오른쪽
		if(map[x][y+1] == '.') {
			if(check(x,y+1)) return true;
		}
		
		//오른쪽 아래
		if(x+1 < R && map[x+1][y+1] == '.') {
			if(check(x+1,y+1)) return true;
		}
		
		return false;
	}
}
