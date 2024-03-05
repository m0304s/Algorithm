import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        int N = kb.nextInt();
        int [] array = new int[N];
        for(int i=0;i<N;i++){
            array[i]=kb.nextInt();
        }
        Stack <Integer> stack = new Stack<>();
        ArrayList <String> answer = new ArrayList<>();
        Boolean status = true;
        int final_input = 1;
        for(int i=0;i<N;i++){
            if(!stack.isEmpty()){
                if(stack.peek()>array[i]){
                    status=false;
                }
            }
            while(array[i]>=final_input){
                stack.push(final_input++);
                answer.add("+");
            }
            if(array[i]==stack.peek()){
                stack.pop();
                answer.add("-");
            }
        }
        if(status){
            Iterator<String> iterator = answer.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }else{
            System.out.println("NO");
        }
    }
}
