import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int [] arr;
    static int [] dist;
    
    public static void main(String [] ars) throws IOException{
    	while(true) {
    		String [] tokens = br.readLine().split(" ");
    		int numOfNums = Integer.parseInt(tokens[0]);
    		if(numOfNums == 0) break;
    		
    		arr = new int[numOfNums];
    		for(int i=1;i<tokens.length;i++) {
    			arr[i-1] = Integer.parseInt(tokens[i]);
    		}
    		dist = new int[6];
    		backtracking(0,0,numOfNums);
    		bw.write("\n");
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    static void backtracking(int index, int depth, int numOfNums) throws IOException{
    	if(depth == 6) {
    		for(int i=0;i<dist.length;i++) {
    			bw.write(dist[i]+" ");
    		}bw.write("\n");
    		
    		return;
    	}
    	
    	if(index >= numOfNums) return;
    	
    	dist[depth] = arr[index];
    	backtracking(index+1, depth+1, numOfNums);
    	
    	
    	backtracking(index+1,depth,numOfNums);
    }
}
