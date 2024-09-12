import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Register{
        int number;
        String command;
        public Register(int number, String command){
            this.number = number;
            this.command = command;
        }
    }
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            String [] tokens = br.readLine().split(" ");
            boolean [] visited = new boolean[10000];
            bfs(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),visited);
        }
        bw.flush();
    }
    public static void bfs(int startNum, int targetNum, boolean [] visited) throws IOException{
        Queue<Register> queue = new LinkedList<>();
        queue.add(new Register(startNum, ""));
        visited[startNum] = true;
        while(!queue.isEmpty()){
            Register cur = queue.poll();

            if(cur.number == targetNum){
                bw.write(cur.command+"\n");
                break;
            }
            
            if(!visited[changeNum(cur.number, "D")]){
                visited[changeNum(cur.number, "D")] = true;
                queue.add(new Register(changeNum(cur.number, "D"), cur.command + "D"));
            }

            if(!visited[changeNum(cur.number, "S")]){
                visited[changeNum(cur.number, "S")] = true;
                queue.add(new Register(changeNum(cur.number, "S"), cur.command + "S"));
            }

            if(!visited[changeNum(cur.number, "L")]){
                visited[changeNum(cur.number, "L")] = true;
                queue.add(new Register(changeNum(cur.number, "L"), cur.command + "L"));
            }

            if(!visited[changeNum(cur.number, "R")]){
                visited[changeNum(cur.number, "R")] = true;
                queue.add(new Register(changeNum(cur.number, "R"), cur.command + "R"));
            }
        }
    }
    public static int changeNum(int num, String command){
        int d1 = num/1000;
        int d2 = (num/100)%10;
        int d3 = (num/10)%10;
        int d4 = num%10;
        switch(command){
            case "D":
                return (num*2)%10000;
            case "S":
                return num == 0 ? 9999 : (num-1);
            case "L":
                return d2*1000 + d3 * 100 + d4 * 10 + d1;
            case "R":
                return d4*1000 + d1 * 100 + d2 * 10 + d3;
        }
        return 0;
    }
}
