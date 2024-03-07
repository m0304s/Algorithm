import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        String [] nc = br.readLine().split(" ");
        int N = Integer.parseInt(nc[0]);
        int C = Integer.parseInt(nc[1]);

        int [] list = new int[N];
        for(int i=0;i<N;i++){
            list[i] = Integer.parseInt(br.readLine());
        }

        // C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.
        Arrays.sort(list);

        int left = 1;
        int right = list[list.length-1]-list[0]+1;

        while(left<right){
            int mid = (left+right)/2;
            if(C>numHouse(mid,list,N)){    //설치할 수 있는 공유기 개수 > 설치해야하는 공유기 개수 : 거리를 줄여야함
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        bw.write(Integer.toString(right-1)+"\n");
        bw.flush();
        bw.close();
    }

    private static int numHouse(int length, int[] list,int N) {  //거리가 주어졌을때, 설치할 수 있는 최대 공유기 개수
        int startIdx = 0;
        int endIdx = 0;
        int answer = 1;

        while (endIdx!=N) {
            if(list[endIdx]-list[startIdx]<length){
                endIdx++;
            }else{
                startIdx = endIdx;
                endIdx++;
                answer++;
            }
        }
        return answer;
    }
}
