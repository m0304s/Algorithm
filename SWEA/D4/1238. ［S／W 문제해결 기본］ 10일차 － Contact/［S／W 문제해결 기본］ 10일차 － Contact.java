import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int length;
    static int startPoint;
    static int maxDepth;

    static ArrayList<ArrayList<Integer>> data;
    static ArrayList<Integer> numbers = new ArrayList<>();
    static List<Integer> answerList = new ArrayList<>();
    static boolean [] visited;

    static class Node{
        int nodeNum,depth;

        public Node(int nodeNum,int depth){
            this.nodeNum = nodeNum;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException {
        for(int t=1;t<=10;t++){
            String [] tokens = br.readLine().split(" ");
            maxDepth = Integer.MIN_VALUE;
            length = Integer.parseInt(tokens[0]);
            startPoint = Integer.parseInt(tokens[1]);
            numbers.clear();  // 새로운 테스트 케이스마다 초기화
            answerList.clear();

            tokens = br.readLine().split(" ");
            for(int i=0;i<tokens.length;i++){
                numbers.add(Integer.parseInt(tokens[i]));
            }

            int maxNum = Collections.max(numbers);
            data = new ArrayList<>(maxNum+1);
            visited = new boolean[maxNum+1];

            for(int i=0;i<=maxNum;i++){
                data.add(new ArrayList<>());
            }

            //그래프 입력
            for(int i=0;i<numbers.size();i=i+2){
                //from to 형식
                int from = numbers.get(i);
                int to = numbers.get(i+1);

                data.get(from).add(to);
            }
            bfs(startPoint);
            bw.write("#"+t+" "+Collections.max(answerList)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void bfs(int startPoint){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startPoint,0));
        visited[startPoint] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.depth == maxDepth){
                answerList.add(cur.nodeNum);
            }else if(cur.depth > maxDepth){
                maxDepth = cur.depth;
                answerList.clear();
                answerList.add(cur.nodeNum);
            }
            for (Integer i : data.get(cur.nodeNum)) {
                if(!visited[i]){
                    q.add(new Node(i,cur.depth+1));
                    visited[i] = true;
                }
            }
        }
    }
}
