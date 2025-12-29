import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int numberOfComputer;
    static boolean [] visited;
    static HashSet<Integer> totalComputer;
    static Queue<Integer> queue;
    static List<List<Integer>> graph;
    
    public static void main(String [] args) throws IOException{
        numberOfComputer = Integer.parseInt(br.readLine());
    
        totalComputer = new HashSet<>();
        queue = new ArrayDeque<Integer>();
        visited = new boolean[numberOfComputer+1];
        
        // 연결리스트 생성
        graph = new ArrayList<>();        
        for(int i=0;i<=numberOfComputer;i++){
            graph.add(new ArrayList<>());
        }
        
        //연결 정보 추가
        int numberOfConnection = Integer.parseInt(br.readLine());
        
        for(int i=0;i<numberOfConnection;i++){
            String [] tokens = br.readLine().split(" ");
            int start = Integer.parseInt(tokens[0]);
            int end = Integer.parseInt(tokens[1]);

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        // BFS 탐색
        visited[1] = true;
        queue.add(1);

        while(!queue.isEmpty()){
            int current = queue.poll();

            if(!totalComputer.contains(current)){
                totalComputer.add(current);
            }

            List<Integer> connectedComputers = graph.get(current);

            for(Integer next : connectedComputers){
                if(visited[next]) continue;

                queue.add(next);
                visited[next] = true;
            }
        }

        bw.write(totalComputer.size() - 1 +"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}