import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int orderCnt=Integer.parseInt(br.readLine());
        String [] command = new String[orderCnt];
        for(int i=0;i<orderCnt;i++){
            command[i]=br.readLine();
        }
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<orderCnt;i++){
            solution(command[i],stack);
        }
        bw.close();
    }
    public static void solution(String command, Stack<Integer>stack) throws IOException{
        if(command.equals("top")){
            if(stack.isEmpty()){
                bw.write("-1\n");
            }else{
                bw.write(stack.peek()+"\n");
            }
        }else if(command.equals("empty")){
            if(stack.isEmpty()){
                bw.write("1\n");
            }else{
                bw.write("0\n");
            }
        }else if(command.equals("size")){
            bw.write(stack.size()+"\n");
        }else if(command.equals("pop")){
            if(stack.isEmpty()){
                bw.write("-1\n");
            }else{
                bw.write(stack.pop()+"\n");
            }
        }else{  //push
            String [] token = command.split(" ");
            stack.push(Integer.parseInt(token[1]));
        }
    }
}
