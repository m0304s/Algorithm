import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Solution {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Edge implements Comparable<Edge>{
        int start,end;
        double cost;

        public Edge(int start, int end, double cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
        public int compareTo(Edge o){
            return Double.compare(this.cost, o.cost);
        }
    }

    static int N;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            int [] X = new int[N];
            int [] Y = new int[N];
            String [] tokens = br.readLine().split(" ");
            for(int i=0;i<N;i++){
                X[i] = Integer.parseInt(tokens[i]);
            }
            tokens = br.readLine().split(" ");
            for(int i=0;i<N;i++){
                Y[i] = Integer.parseInt(tokens[i]);
            }
            double E = Double.parseDouble(br.readLine());

            /**
             * 모든 연결 가능한 간선들을 우선순위큐에 저장
             */

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            for(int i=0;i<N;i++){
                for(int j=i+1;j<N;j++){
                    double dist = Math.pow(X[i]-X[j],2)+Math.pow(Y[i]-Y[j],2);
                    pq.add(new Edge(i,j,E * dist));     //비용 계산 후 큐에 추가
                }
            }

            /**
             * 크루스칼 알고리즘을 사용해 최소 신장 트리 구성
             * parent : 각 섬의 부모 노드를 저장하는 배열
             */

            int [] parent = new int[N];
            for(int i=0;i<N;i++){
                parent[i] = i;  //초기화
            }

            double result = 0;
            int count = 0;

            while(!pq.isEmpty()){
                Edge edge = pq.poll();      //연결 비용이 가장 낮은 연결을 꺼냄
                if(union(edge.start,edge.end,parent)){      //사이클이 없으면 해당 간선을 추가
                    result+=edge.cost;
                    count++;
                    if(count == N-1) break; // 모든 섬들이 연결되면 종료
                }
            }
            bw.write("#"+t+" "+Math.round(result)+"\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
    static boolean union(int a, int b, int [] parent){
        int rootA = find(a,parent); //각 노드의 부모 노드를 찾음
        int rootB = find(b,parent); //각 노드의 부모 노드를 찾음

        if(rootA != rootB){
            parent[rootB] = rootA;  //두 섬을 연결
            return true;
        }
        return false;
    }
    static int find(int a,int [] parent){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a],parent);
    }
}
