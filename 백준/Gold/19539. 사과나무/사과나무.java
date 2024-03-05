import java.util.*;
import java.io.*;
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        /**
         * 물뿌리개 2개
         * 1번 : 1만큼 성장
         * 2번 : 2만큼 성장
         * 1번과 2번 동시 사용
         * 1번과 2번 함께 사용해서 3만큼 성장 가능
         * N : 사과나무 개수
         * N개의 정수 : 갊자가 바라는 i번째 나무의 높이
         */
    public static ArrayList<Integer> one = new ArrayList<>();
    public static ArrayList<Integer> two = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int twoSum = 0;
        int oneSum = 0;
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(input[i]);
            twoSum+=num/2;
            oneSum+=num%2;
            two.add(num/2);
            one.add(num%2);
        }

        if((twoSum-oneSum)%3==0&&(twoSum-oneSum)>=0){
            bw.write("YES\n");
        }else{
            bw.write("NO\n");
        }
        bw.flush();
        bw.close();
    }
}
