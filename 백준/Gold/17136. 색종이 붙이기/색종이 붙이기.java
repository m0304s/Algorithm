import java.io.*;
import java.util.*;

public class Main {
	private static final int SIZE = 10;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int [][] map;
	private static int minCount = Integer.MAX_VALUE;
	private static int [] paper = {0,5,5,5,5,5};
	
	public static void main(String[] args) throws IOException{
		map = new int[SIZE][SIZE];
		for(int i=0;i<SIZE;i++) {
			String [] tokens = br.readLine().split(" ");
			for(int j=0;j<SIZE;j++) {
				map[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		
		dfs(0,0,0);
		if(minCount == Integer.MAX_VALUE) bw.write("-1\n");
		else {
			bw.write(minCount+"\n");	
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int x,int y,int count) {
		if(x >= SIZE-1 && y > SIZE-1) {
			minCount = Math.min(minCount, count);
			return;
		}
		if(count >= minCount) return;
		
		if(y>= SIZE) {
			//다음 행으로 이동
			dfs(x+1,0,count);
			return;
		}
		
		if(map[x][y] == 1) {
			for(int i=5;i>=1;i--) {
				if(paper[i] > 0 && canAttach(x,y,i)) {
					attach(x,y,i,0);
					paper[i]--;
					dfs(x,y+1,count+1);
					attach(x,y,i,1);
					paper[i]++;
				}
			}
		}else {
			dfs(x,y+1,count);
		}
	}
	
	private static void attach(int x,int y,int size, int value) {
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++) {
				map[i][j] = value;
			}
		}
	}
	
	private static boolean canAttach(int x,int y,int size) {
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++) {
				if(!inRange(i,j)) return false;
				if(map[i][j] != 1) return false;
			}
		}
		return true;
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
	}
}