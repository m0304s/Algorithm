import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int S = Integer.parseInt(tokens[1]);

        int [] numbers = new int[N];

        tokens = br.readLine().split(" ");

        for(int i=0;i<N;i++){
            numbers[i] = Integer.parseInt(tokens[i]);
        }

        int minLength = Integer.MAX_VALUE;
        int currentSum = 0;
        int firstPoint = 0;

        for(int secondPoint = 0; secondPoint < N; secondPoint++){
            currentSum += numbers[secondPoint];

            while (currentSum >= S){
                minLength = Math.min(minLength, secondPoint - firstPoint + 1);
                currentSum-=numbers[firstPoint++];
            }
        }

        bw.write((minLength == Integer.MAX_VALUE ? 0 : minLength) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
