import java.io.*;
import java.util.*;

class Main
{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private static int N, M, L, K;
    private static class Star{
    	int x,y;
    	
    	public Star(int x,int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
    
    private static ArrayList<Star> stars;

    public static void main(String args[]) throws Exception {
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        L = Integer.parseInt(tokens[2]);
        K = Integer.parseInt(tokens[3]);
        
        stars = new ArrayList<Star>();
        
        for(int i=0;i<K;i++) {
        	tokens = br.readLine().split(" ");
        	stars.add(new Star(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])));
        }
        
        int resMax = Integer.MIN_VALUE;
        for(Star star1 : stars) {
        	for(Star star2 : stars) {
        		resMax = Math.max(resMax, boundStar(star1.x,star2.y));
        	}
        }
        bw.write(K-resMax+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static int boundStar(int i, int j) {
    	int res = 0;
    	for(Star star : stars) {
    		if(star.x >= i && star.x <= i + L && star.y >= j && star.y <= j+L) res++;
    	}
    	
    	return res;
    }
}
