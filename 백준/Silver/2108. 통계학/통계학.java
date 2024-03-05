import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Integer> list = new ArrayList<>();
    public static ArrayList<Integer> count = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        /*
         *  sum = 총 합계
         *  max = 최댓값
         *  min = 최솟값
         *  median = 중앙값
         *  mode = 최빈값
         */
        int N = Integer.parseInt(br.readLine());
        int [] numRange = new int[8001];    //-4000~4000 사이 범위의 수 입력
        int sum=0;
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(br.readLine());
            list.add(num);
            numRange[num+4000]++;
            sum+=num;
        }

        int maxCount = Integer.MIN_VALUE;
        int index = 0;
        for(int i=0;i<numRange.length;i++){
            if(maxCount<numRange[i]){
                index = i;
                maxCount=numRange[i];
            }
        }
        for(int i=0;i<numRange.length;i++){
            if(numRange[index]==numRange[i]){
                count.add(i);
            }
        }
        Collections.sort(list);
        bw.write(Long.toString((int)Math.round((double)sum / N))+"\n");    //산술평균
        bw.write(Long.toString(list.get(N/2))+"\n"); //중앙값
        if(count.size()!=1){
            Collections.sort(count);
            bw.write(Long.toString(count.get(1)-4000)+"\n");   //최빈값
        }else{
            bw.write(Long.toString(count.get(0)-4000)+"\n");   //최빈값
        }
        bw.write(Long.toString(list.get(list.size()-1)-list.get(0))+"\n");
        bw.flush();
        bw.close();
    }
}
