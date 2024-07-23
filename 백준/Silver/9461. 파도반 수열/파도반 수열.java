import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        ArrayList<Long> blue = new ArrayList<>();
        ArrayList<Long> white = new ArrayList<>();

        blue.add(1L);
        blue.add(1L);
        blue.add(2L);
        blue.add(4L);
        white.add(1L);
        white.add(2L);
        white.add(3L);
        white.add(5L);

        for(int i=4;i<50;i++){
            blue.add(white.get(i-3) + white.get(i-1));
            white.add(blue.get(i-2) + blue.get(i));
        }
        ArrayList<Long> answer = new ArrayList<>();
        for(int i=0;i<50;i++){
            answer.add(blue.get(i));
            answer.add(white.get(i));
        }
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            bw.write(answer.get(Integer.parseInt(br.readLine())-1)+"\n");
        }
        bw.flush();
    }
}
