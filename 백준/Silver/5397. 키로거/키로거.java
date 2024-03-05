import java.util.*;
import java.io.*;
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Deque<String> deque = new LinkedList<>();
    public static Deque<String> deque2 = new LinkedList<>();    //deque에서 빼온 문자열 저장
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            solution(br.readLine());
        }
        bw.flush();
        bw.close();
    }
    public static void solution(String command) throws IOException{
        for(int i=0;i<command.length();i++){
            if(command.charAt(i)=='<'){
                if(deque.isEmpty()){
                    continue;
                }else{
                    deque2.addFirst(deque.removeLast());
                }
            }else if(command.charAt(i)=='>'){
                if(!deque2.isEmpty()){
                    deque.addLast(deque2.removeFirst());
                }else{
                    continue;
                }
            }else if(command.charAt(i)=='-'){
                if(!deque.isEmpty()){
                    deque.removeLast();
                }
            }else{
                deque.addLast(Character.toString(command.charAt(i)));
            }
        }
        while(!deque.isEmpty()){
            bw.write(deque.removeFirst());            
        }while(!deque2.isEmpty()){
            bw.write(deque2.removeFirst());            
        }
        bw.write("\n");
    }
}
