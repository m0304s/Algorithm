import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Deque<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            solution(br.readLine());
        }
        bw.flush();
        bw.close();
    }
    public static void solution(String command) throws IOException{
        if(command.equals("pop")){  //pop
            if(queue.isEmpty()){
                bw.write("-1\n");
            }else{
                bw.write(Integer.toString(queue.removeFirst())+"\n");
            }
        }else if(command.equals("size")){   //size
            bw.write(Integer.toString(queue.size())+"\n");
        }else if(command.equals("empty")){  //empty
            if(queue.isEmpty()){
                bw.write("1\n");
            }else{
                bw.write("0\n");
            }
        }else if(command.equals("front")){
            if(queue.isEmpty()){
                bw.write("-1\n");
            }else{
                bw.write(Integer.toString(queue.getFirst())+"\n");
            }
        }else if(command.equals("back")){   //수정필요
            if(queue.isEmpty()){
                bw.write("-1\n");
            }else{
                bw.write(Integer.toString(queue.getLast())+"\n");
            }
        }else{
            String [] token = command.split(" ");
            queue.add(Integer.parseInt(token[1]));
        }
    }
}
