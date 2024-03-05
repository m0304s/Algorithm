import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Stack<Integer> stack = new Stack<>();
    public static int sum=0;
    public static void main(String[] args) throws IOException{
        int K = Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            solution(Integer.parseInt(br.readLine()),stack);
        }
        output(stack);
        bw.write(Integer.toString(sum)+"\n");
        bw.flush();
        bw.close();
    }
    public static void solution(int inputNum, Stack<Integer>stack){
        if(inputNum!=0){
            stack.push(inputNum);
        }else{
            stack.pop();
        }
    }
    public static void output(Stack<Integer>stack){
        while(!stack.isEmpty()){
            sum+=stack.pop();
        }
    }
}
