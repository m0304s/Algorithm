import java.util.*;
import java.io.*;
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Deque<String> deque = new LinkedList<>();
    public static Deque<String> deque2 = new LinkedList<>(); //기존 덱에서 빠진 문자열들을 저장하는 덱
    public static void main(String[] args) throws IOException{
        String string = br.readLine();
        int count = Integer.parseInt(br.readLine());
        for(int i=0;i<string.length();i++){
            deque.addLast(Character.toString(string.charAt(i)));
        }
        for(int i=0;i<count;i++){
            String command = br.readLine();
            if(command.equals("L")){ //커서를 왼쪽으로 한 칸 이동  
                if(!deque.isEmpty()){
                    deque2.addFirst(deque.removeLast());
                }
            }else if(command.equals("D")){   //커서를 오른쪽으로 한 칸 이동
                if(!deque2.isEmpty()){
                    deque.addLast(deque2.removeFirst());
                }
            }else if(command.equals("B")){   //커서 왼쪽에 있는 문자를 삭제
                if(!deque.isEmpty()){
                    deque.removeLast();
                }
            }else{  //뒤에 오는 문자를 커서 왼쪽에 추가
                String [] token = command.split(" ");
                deque.addLast(token[1]);
            }
        }
        while(!deque2.isEmpty()){
            deque.addLast(deque2.removeFirst());
        }
        while(!deque.isEmpty()){
            bw.write(deque.removeFirst());
        }
        bw.write("\n");
        bw.flush();
        bw.close();
    }
}