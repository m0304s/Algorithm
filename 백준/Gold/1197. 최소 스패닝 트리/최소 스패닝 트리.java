import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int V,E;
    static int [] parents;
    static ArrayList<Edge> graphs;
    
    public static void main(String [] args) throws IOException{
    	String [] tokens = br.readLine().split(" ");
    	V = Integer.parseInt(tokens[0]);
    	E = Integer.parseInt(tokens[1]);
    	graphs = new ArrayList<>();
    	
    	for(int i=0;i<E;i++) {
    		tokens = br.readLine().split(" ");
    		int from = Integer.parseInt(tokens[0]);
    		int to = Integer.parseInt(tokens[1]);
    		int cost = Integer.parseInt(tokens[2]);
    		graphs.add(new Edge(from,to,cost));
    	}
    	
    	Collections.sort(graphs, (e1,e2) -> (e1.cost - e2.cost));
    	parents = new int[V+1];
    	for(int i=1;i<=V;i++) {
    		parents[i] = i;
    	}
    	
    	int count = 0;
    	int idx = 0;
    	int totalCost = 0;
    	while(count < V-1 && idx < graphs.size()) {
    		Edge edge = graphs.get(idx++);
    		
    		if(find(edge.from) != find(edge.to)) {
    			count++;
    			union(edge.from,edge.to);
    			totalCost += edge.cost;
    		}
    	}
    	
    	bw.write(totalCost+"\n");
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    static void union(int i,int j) {
    	int rootA = find(i);
    	int rootB = find(j);
    	
    	if(rootA < rootB) {
    		parents[rootB] = rootA;
    	}else {
    		parents[rootA] = rootB;
    	}
    }
    
    static int find(int index) {
    	if(index == parents[index]) return index;
    	else return parents[index] = find(parents[index]);
    }
    
    static class Edge{
    	int from, to, cost;
    	public Edge(int from, int to, int cost) {
    		this.from = from;
    		this.to = to;
    		this.cost = cost;
    	}
    }
}
