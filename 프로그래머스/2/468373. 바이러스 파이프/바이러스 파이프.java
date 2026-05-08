import java.util.*;

class Solution {
    static int N, K;
    static int originalInfection;
    static ArrayList<ArrayList<Edge>> graph;
    static int answer;
    static int [] order;
    
    public int solution(int n, int infection, int[][] edges, int k) {
        N = n;
        K = k;
        originalInfection = infection;
        graph = new ArrayList<>();
        order = new int[K];
        
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }
        
        for(int [] e : edges){
            int a = e[0];
            int b = e[1];
            int type = e[2];
            
            graph.get(a).add(new Edge(b, type));
            graph.get(b).add(new Edge(a, type));
        }
        
        dfs(0, 0);
        return answer;
    }
    
    static void dfs(int depth, int prevType){
        if(depth > 0){
            answer = Math.max(answer, simulation(depth));
        }
        
        if(depth == K) return;
        
        for(int type = 1; type <= 3; type++){
            if(type == prevType) continue;
            
            order[depth] = type;
            dfs(depth+1, type);
        }
    }
    
    static int simulation(int len){
        boolean[] infected = new boolean[N+1];
        infected[originalInfection] = true;
        
        for(int step = 0; step < len; step++){
            int openType = order[step];
            
            Queue<Integer> queue = new ArrayDeque<>();
            boolean [] visited = new boolean[N+1];
            
            for(int i=1;i<=N;i++){
                if(infected[i]){
                    queue.add(i);
                    visited[i] = true;
                }
            }
            
            while(!queue.isEmpty()){
                int curNode = queue.poll();
                
                for(Edge next : graph.get(curNode)){
                    if(next.type != openType) continue;
                    if(visited[next.to]) continue;
                    
                    visited[next.to] = true;
                    queue.add(next.to);
                    infected[next.to] = true;
                }
            }
        }
        
        int cnt = 0;
        for(int i=1;i<=N;i++){
            if(infected[i]) cnt++;
        }
        
        return cnt;
    }
    static class Edge{
        int to;
        int type;
        
        public Edge(int to, int type){
            this.to = to;
            this.type = type;
        }
        
        public String toString(){
            return "TO : " + to + " TYPE : " + type;
        }
    }
}