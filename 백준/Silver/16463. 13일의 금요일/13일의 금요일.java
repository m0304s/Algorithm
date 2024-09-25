import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int [] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int day = 13;

        int result = 0;
        for(int i=2019; i<=N;i++){
            for(int j=0;j<12;j++){
                if(day % 7 == 4){   //금요일인지 체크
                    result++;
                }
                day += days[j];
                
                if(j==1 && (i % 400 == 0 || (i%4 == 0 && i%100 != 0))) day++;
            }
        }
        System.out.println(result);
    }
}
