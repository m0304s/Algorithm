import java.io.*;
import java.util.*;

class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private static class Node implements Comparable<Node>{
    	int idx,cost;
    	
    	public Node(int idx,int cost) {
    		this.idx = idx;
    		this.cost = cost;
    	}
    	
    	@Override
    	public int compareTo(Node o) {
    	    return Integer.compare(this.cost, o.cost);
    	}
    }
    
    private static class Result {
        int distance;
        int[] prev;
        
        public Result(int distance, int[] prev) {
            this.distance = distance;
            this.prev = prev;
        }
    }
    
    private static int N,M;
    private static ArrayList<ArrayList<Node>> graph;
    
    public static void main(String [] args) throws IOException{
    	String [] tokens = br.readLine().split(" ");
    	N = Integer.parseInt(tokens[0]);
    	M = Integer.parseInt(tokens[1]);
    	graph = new ArrayList<>();
    	for(int i=0;i<=N;i++) {
    		graph.add(new ArrayList<>());
    	}
    	
    	for(int i=0;i<M;i++) {
    		tokens = br.readLine().split(" ");
    		int a = Integer.parseInt(tokens[0]);
    		int b = Integer.parseInt(tokens[1]);
    		int t = Integer.parseInt(tokens[2]);
    		
    		graph.get(a).add(new Node(b,t));
    		graph.get(b).add(new Node(a,t));
    	}
    	
    	Result result = dijkstra(1,N);	//검문이 없을 경우
    	int minTime = result.distance;
    	int [] prev = result.prev;
    	
    	List<Integer> path = getPath(1,N,prev);
    	
        int maxDelay = Integer.MIN_VALUE;
        boolean unreachable = false;
        
        for (int i = 0; i < path.size() - 1; i++) {
            int blockA = path.get(i);
            int blockB = path.get(i + 1);
            
            Node removedFromA = null, removedFromB = null;
            for (Node node : graph.get(blockA)) {
                if (node.idx == blockB) {
                    removedFromA = node;
                    break;
                }
            }
            for (Node node : graph.get(blockB)) {
                if (node.idx == blockA) {
                    removedFromB = node;
                    break;
                }
            }
            
            if (removedFromA == null || removedFromB == null) {
                continue;
            }
            
            graph.get(blockA).remove(removedFromA);
            graph.get(blockB).remove(removedFromB);
            
            Result resultWithBlock = dijkstra(1, N);
            int timeWithBlock = resultWithBlock.distance;

            if(timeWithBlock == Integer.MAX_VALUE) {
                unreachable = true;
                graph.get(blockA).add(removedFromA);
                graph.get(blockB).add(removedFromB);
                break;
            }
            
            maxDelay = Math.max(maxDelay, timeWithBlock - minTime);
            
            graph.get(blockA).add(removedFromA);
            graph.get(blockB).add(removedFromB);
        }
        
        if(unreachable) {
            bw.write(-1+"\n");
        } else {
        	bw.write(maxDelay+"\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static Result dijkstra(int start, int end) {
    	int [] dist = new int[N+1];
    	int [] prev = new int[N+1];
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	
    	Arrays.fill(dist, Integer.MAX_VALUE);
    	Arrays.fill(prev, -1);
    	dist[start] = 0;
    	pq.add(new Node(start,0));
    	
    	while(!pq.isEmpty()) {
    		Node curNode = pq.poll();
    		
    		if(curNode.idx == end) {
    			return new Result(dist[end],prev);
    		}
    		if(dist[curNode.idx] < curNode.cost) continue;
    		
    		for(int i=0;i<graph.get(curNode.idx).size();i++) {
    			Node nextNode = graph.get(curNode.idx).get(i);
    			
    			if(dist[nextNode.idx] > curNode.cost + nextNode.cost) {
    				dist[nextNode.idx] = curNode.cost + nextNode.cost;
    				pq.offer(new Node(nextNode.idx,dist[nextNode.idx]));
    				prev[nextNode.idx] = curNode.idx; 
    			}
    		}
    	}
		return new Result(dist[end],prev);
    }
    
    private static List<Integer> getPath(int start, int end, int[] prev) {
        List<Integer> path = new ArrayList<>();
        for (int at = end; at != -1; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

}
