import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        for(int t=1;t<=10;t++){
            int length = Integer.parseInt(br.readLine());
            String input = br.readLine();
            String postFix = getPostFix(input,length);
            int answer = calcPostFix(postFix);
            bw.write("#"+t+" " + answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static Stack<Integer> numberStack = new Stack<>();
    static int calcPostFix(String postFix){
        int answer = 0;
        for(int i=0;i<postFix.length();i++){
            Character pos = postFix.charAt(i);
            if(pos>='0' && pos<='9'){
                numberStack.push(getNumeric(pos));
            }else{
                int num1 = numberStack.pop();
                int num2 = numberStack.pop();
                switch (pos){
                    case '*':
                        numberStack.push(num1 * num2);
                        break;
                    case '+':
                        numberStack.push(num1+num2);
                }
            }
        }
        answer = numberStack.pop();
        return answer;
    }
    static int getNumeric(Character c){
        return c - '0';
    }
    static String getPostFix(String input, int length){
        String postFix = "";
        for(int i=0;i<length;i++){
            Character pos = input.charAt(i);
            if(pos >='0' && pos<='9'){  //숫자인 경우
                postFix+=pos;
            }else if( pos == '('){
                stack.push(pos);
            }else if(pos == ')'){
                while (!stack.isEmpty() && stack.peek() != '('){
                    postFix+=stack.pop();
                }
                stack.pop();
            }else{
                //연산자일 경우
                if(stack.isEmpty()){
                    stack.push(pos);
                }else{
                    while(!stack.isEmpty() && isPrecedence(stack.peek()) >= isPrecedence(pos)){
                        if(stack.peek() =='(') break;
                        postFix+=stack.pop();
                    }
                    stack.push(pos);
                }
            }
        }
        while (!stack.isEmpty()){
            postFix+=stack.pop();
        }
        return postFix;
    }
    static int isPrecedence(Character operator){
        if(operator == '(' || operator == ')') return 0;
        if(operator == '+' || operator == '-') return 1;
        return 2;
    }}
