import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int TEST_CASE = 10;
    static Stack<Character> stack = new Stack<>();
    static char [] array;
    public static void main(String[] args) throws IOException {
        for(int t=1;t<=TEST_CASE;t++){
            stack.clear();
            int inputLength = Integer.parseInt(br.readLine());
            String expression = br.readLine();
            array = new char[inputLength];

            for(int i=0;i<inputLength;i++){
                array[i] = expression.charAt(i);
            }
            boolean result = checkAnswer();
            if(result){
                bw.write("#"+t+" "+"1\n");
            }else{
                bw.write("#"+t+" "+"0\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean checkAnswer(){
        for(int i=0;i<array.length;i++){
            Character view = array[i];
            if(isOpenBracket(view)){
                stack.add(view);
            }else{
                if(stack.isEmpty() || stack.pop() != getMatchingCharacter(view)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    static boolean isOpenBracket(Character character){
        return character == '(' || character == '{' || character == '[' || character == '<';
    }
    static Character getMatchingCharacter(Character inputChar){
        if(inputChar == '(') return ')';
        if(inputChar == ')') return '(';
        if(inputChar == '{') return '}';
        if(inputChar == '}') return '{';
        if(inputChar == '[') return ']';
        if(inputChar == ']') return '[';
        if(inputChar == '<') return '>';
        if(inputChar == '>') return '<';

        return null;
    }
}
