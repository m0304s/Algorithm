import java.io.*;

/**
 * 메모리 : kb
 * 실행시간:  ms
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
   
    static int N,K,maxLength;
    static int [][] map;
    static boolean [][] visited;
    static int [] dx = {0,0,-1,1};
    static int [] dy = {-1,1,0,0};
    
    public static void main(String [] args) throws IOException{
    	int T = Integer.parseInt(br.readLine());
    	for(int t=1;t<=T;t++) {
    		bw.write("#"+t+" "+solution()+"\n");
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    static int solution() throws IOException{
    	String [] tokens = br.readLine().split(" ");
    	N = Integer.parseInt(tokens[0]);
    	K = Integer.parseInt(tokens[1]);
    	map = new int[N][N];
    	maxLength = -1;
    	int maxHeight = Integer.MIN_VALUE;
    	for(int i=0;i<N;i++) {
    		tokens = br.readLine().split(" ");
    		for(int j=0;j<N;j++) {
    			map[i][j] = Integer.parseInt(tokens[j]);
    			maxHeight = Math.max(maxHeight, map[i][j]);
    		}
    	}
    	
		visited = new boolean[N][N];
    	for(int i=0;i<N;i++){
    		for(int j=0;j<N;j++) {
    			if(map[i][j] == maxHeight) {
    				//높이가 최대인 봉우리부터 완탐 진행
    				visited[i][j] = true;
    				backtracking(i,j,maxHeight,1,K);
    				visited[i][j] = false;
    			}
    		}
    	}
    	return maxLength;
    }
    
    static void backtracking(int x, int y, int currHeight, int length, int k) {
        maxLength = Math.max(maxLength, length);
        
        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if(!inRange(nx, ny) || visited[nx][ny]) continue;

            if(map[nx][ny] < currHeight) {
                visited[nx][ny] = true;
                backtracking(nx, ny, map[nx][ny], length + 1, k);
                visited[nx][ny] = false;
            } else if(k > 0) {
                for(int cut = 1; cut <= k; cut++) {
                    if(map[nx][ny] - cut < currHeight) {
                        visited[nx][ny] = true;
                        backtracking(nx, ny, map[nx][ny] - cut, length + 1, 0);
                        visited[nx][ny] = false;
                    }
                }
            }
        }
    }

    
    private static boolean inRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static class Node{
    	int x,y;
    	public Node(int x,int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
}
