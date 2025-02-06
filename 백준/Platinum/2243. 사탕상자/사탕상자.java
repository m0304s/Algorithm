import java.io.*;
import java.util.*;

class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private static final int SIZE = 1000001;
    
    private static int N;
    private static int [] tree;
    public static void main(String [] args) throws IOException{
    	N = Integer.parseInt(br.readLine());
    	tree = new int[SIZE*4];
    	for(int i=0;i<N;i++) {
    		String [] tokens = br.readLine().split(" ");
    		if(tokens[0].equals("1")) {
    			//사탕을 꺼내는 경우
    			int num = Integer.parseInt(tokens[1]);
    			int candy = query(1,SIZE,1,num);
    			bw.write(candy+"\n");
    		}else {
    			//사탕을 넣는 경우
    			int flavor = Integer.parseInt(tokens[1]);
    			int count = Integer.parseInt(tokens[2]);
    			
    			update(1,SIZE,1,flavor,count);
    		}
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    private static int query(int start, int end, int node, int target) {
    	if(start == end) {
    		update(1,SIZE,1,start,-1);
    		return start;
    	}
    	
    	int mid = (start + end) / 2;
    	if(target <= tree[node*2]) {
    		return query(start,mid,node*2,target);
    	}else {
    		return query(mid+1,end,node*2+1,target-tree[node*2]);
    	}
    }
    
    private static void update(int start, int end, int node, int target, int diff) {
    	if(target < start || target > end) return;
    	
  
    	tree[node] += diff;
    	
    	if(start == end) return;
    	
    	int mid = (start + end) / 2;
    	update(start, mid, node*2, target, diff);
    	update(mid+1,end,node*2+1,target,diff);
    }
}
