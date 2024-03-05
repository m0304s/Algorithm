import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Stack<String> stack = new Stack<>();

    public static int cnt=0;    //좋은 단어의 개수
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            solution(br.readLine());
        }
        bw.write(Integer.toString(cnt)+"\n");
        bw.flush();
        bw.close();
    }
    public static void solution(String word){
        Boolean checkWord = false;
        stack.push(Character.toString(word.charAt(0)));
        for(int i=1;i<word.length();i++){
            String s = Character.toString(word.charAt(i));
            if(stack.isEmpty()){
                stack.push(s);
            }else{
                if(stack.peek().equals(s)){
                    stack.pop();
                }else{
                    stack.push(s);
                }
            }
        }
        if(stack.isEmpty()){
            cnt++;
        }
        stack.clear();
    }
}
