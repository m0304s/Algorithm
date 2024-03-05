import java.util.*;
import java.lang.*;
class Solution {
    public int solution(int n, int[][] wires) {
        int answer=Integer.MAX_VALUE;
        boolean [][] connection = new boolean[n+1][n+1];
        for(int i=0;i<n-1;i++){
            int a = wires[i][0];
            int b= wires[i][1];
            connection[a][b]=true;
            connection[b][a]=true;
        }
        for(int i=0;i<wires.length;i++){
            Queue <Integer> q = new LinkedList<>();
            int a = wires[i][0];
            int b= wires[i][1];
            connection[a][b]=false; //전력망 끊기
            connection[b][a]=false;
            
            boolean [] visited = new boolean[n+1];
            q.add(1);
            int count=0;
            while(!q.isEmpty()){
                int node = q.poll();
                if(visited[node]==true){
                    continue;
                }else{
                    visited[node]=true;
                    count++;   //연결된 Node 개수
                    for(int j=1;j<connection.length;j++){
                        if(connection[node][j]==true){
                            q.add(j);
                        }
                    }
                }
            }
            //answer 갱신
            answer = Math.min(answer,Math.abs((visited.length-1)-count*2));
            // System.out.println(a+" : "+b+" : "+answer);
            connection[a][b]=true;
            connection[b][a]=true;
        }
        
        return answer;
    }
}