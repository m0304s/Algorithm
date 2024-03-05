import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Stack<String> stack = new Stack<>();
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());    //테스트 케이스 개수
        for(int i=0;i<T;i++){
            solution(br.readLine());
        }
        bw.flush();
        bw.close();
    }
    public static void solution(String command) throws IOException{
        for(int i=0;i<command.length();i++){
            String bracket = Character.toString(command.charAt(i));
            if(bracket.equals("(")){
                stack.push(bracket);
            }else if(bracket.equals(")")){
                if(!stack.isEmpty()){
                    stack.pop();
                }else{
                    bw.write("NO\n");
                    stack.clear();
                    return;                    
                }
            }
        }
        if(stack.isEmpty()){
            bw.write("YES\n");
            stack.clear();
        }else{
            bw.write("NO\n");
            stack.clear();
        }
        return;
    }
}