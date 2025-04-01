import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N,M,LOG;
	static List<Integer>[] tree;
	static int [][] parent;	//a 노드에서 2^b 번째 부모노드 저장
	static int [] depth;
	
    public static void main(String [] args) throws IOException{
    	N = Integer.parseInt(br.readLine());
    	LOG = (int)(Math.log(N)/Math.log(2))+1;
    	
    	tree = new ArrayList[N+1];
    	for(int i=0;i<=N;i++) {
    		tree[i] = new ArrayList<>();
    	}
    	
    	//간선 입력
    	for(int i=0;i<N-1;i++) {
    		String [] tokens = br.readLine().split(" ");
    		int u = Integer.parseInt(tokens[0]);
    		int v = Integer.parseInt(tokens[1]);
    		
    		tree[u].add(v);
    		tree[v].add(u);
    	}
    	
    	preprocess(1);
    	
    	M = Integer.parseInt(br.readLine());
    	for(int i=0;i<M;i++) {
    		String [] tokens = br.readLine().split(" ");
    		int u = Integer.parseInt(tokens[0]);
    		int v = Integer.parseInt(tokens[1]);
    		
    		int result = query(u,v);
    		bw.write(result+"\n");
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    static int query(int u,int v) {
    	//u의 깊이를 항상 v보다 깊에 유지
    	if(depth[u] < depth[v]) {
    		int temp = u;
    		u = v;
    		v = temp;
    	}
    	
    	//깊이 차이 맞추기
    	for(int i=LOG-1;i>=0;i--) {
    		if(depth[u] - (1<<i) >= depth[v]) {
    			u = parent[u][i];
    		}
    	}
    	
    	if(u == v) return u;
    	
    	for(int i=LOG-1;i>=0;i--) {
    		if(parent[u][i] !=  parent[v][i]) {
    			u = parent[u][i];
    			v = parent[v][i];
    		}
    	}
    	
    	return parent[u][0];
    }
    
    static void preprocess(int root) {
    	parent = new int[N+1][LOG];
    	depth = new int[N+1];
    	
    	Arrays.fill(depth, -1);
    	
    	dfs(root,-1);
    	
    	//희소 테이블 구성
    	for(int j=1;j<LOG;j++) {
    		for(int i=1;i<=N;i++) {
    			if(parent[i][j-1] != -1) {
    				//부모가 존재하는 경우
    				parent[i][j] = parent[parent[i][j-1]][j-1];
    			}
    		}
    	}
    }
    
    static void dfs(int node, int par) {
    	parent[node][0] = par;
    	depth[node] = (par == -1) ? 0 : depth[par] + 1;
    	
    	for(int child : tree[node]) {
    		if(child != par) dfs(child, node);
    	}
    }
}
