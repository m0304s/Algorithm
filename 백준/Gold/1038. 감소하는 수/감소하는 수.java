import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static List<Long> answers;

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        answers = new ArrayList<>();
        for(int i=0;i<10;i++){
            backtracking(i);
        }
        Collections.sort(answers);

        if(N >= answers.size()) bw.write("-1\n");
        else bw.write(answers.get(N)+"\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static void backtracking(long number){
        answers.add(number);
        int lastDigit = (int)(number % 10);

        for(int next = 0; next < lastDigit ; next++){
            backtracking(number * 10 + next);
        }
    }
}
