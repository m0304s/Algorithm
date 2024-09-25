import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        Stack<Character> stack = new Stack<>();

        String input = br.readLine();
        for(int i=0;i<input.length();i++){
            Character inputCharacter = input.charAt(i);
            
            if(Character.isAlphabetic(inputCharacter)){ //알파벳인 경우, 바로 출력
                System.out.print(inputCharacter);
            }else if(inputCharacter == '('){        // "("일 경우, 무조건 스택에 PUSH
                stack.push(inputCharacter);
            }else if(inputCharacter == ')'){        // ")"일 경우, "(" 가 나올때까지 스택에서 POP하여 출력
                while(!stack.isEmpty() && stack.peek() != '('){
                    System.out.print(stack.pop());
                }
                stack.pop();
            }else {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(inputCharacter)) {
                    if (stack.peek() == '(') break;
                    System.out.print(stack.pop());
                }
                stack.push(inputCharacter);
            }
        }
        //스택에서 남은 연산자 출력
        while(!stack.isEmpty()){
            System.out.print(stack.pop());
        }
        System.out.println();
    }
    static int precedence(Character operator){
        if(operator == '(' || operator == ')') return 0;
        if(operator == '+' || operator == '-') return 1;
        if(operator == '*' || operator == '/') return 2;

        return 0;
    }
}
