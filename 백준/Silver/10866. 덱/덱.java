import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Deque<Integer> deque = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());    //명령의 수
        for(int i=0;i<N;i++){
            solution(br.readLine());
        }
        bw.flush();
        bw.close();
    }
    public static void solution(String command) throws IOException{
        if(command.equals("pop_front")){
            if(deque.isEmpty()){
                bw.write("-1\n");
            }else{
                bw.write(Integer.toString(deque.removeFirst())+"\n");
            }
        }else if(command.equals("pop_back")){
            if(deque.isEmpty()){
                bw.write("-1\n");
            }else{
                bw.write(Integer.toString(deque.removeLast())+"\n");
            }
        }else if(command.equals("size")){
            bw.write(Integer.toString(deque.size())+"\n");
        }else if(command.equals("empty")){
            if(deque.isEmpty()){
                bw.write("1\n");
            }else{
                bw.write("0\n");
            }
        }else if(command.equals("front")){
            if(deque.isEmpty()){
                bw.write("-1\n");
            }else{
                bw.write(Integer.toString(deque.getFirst())+"\n");
            }
        }else if(command.equals("back")){
            if(deque.isEmpty()){
                bw.write("-1\n");
            }else{
                bw.write(Integer.toString(deque.getLast())+"\n");
            }
        }else{
            String [] token = command.split(" ");
            if(token[0].equals("push_front")){
                deque.addFirst(Integer.parseInt(token[1]));
            }else{
                deque.addLast(Integer.parseInt(token[1]));
            }
        }
    }
}
