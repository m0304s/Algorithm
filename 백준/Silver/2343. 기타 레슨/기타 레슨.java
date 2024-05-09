import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        ArrayList<Integer> list = new ArrayList<>();
        String[] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);

        tokens = br.readLine().split(" ");
        int max = 0;
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(tokens[i]);
            list.add(num);
            if(max<num){
                max=num;
            }
        }
        
        int left = max;
        int right = 1000000000;

        while(left<=right){
            int mid = (left+right)/2;
            int numOfBlueray = 0;
            
            int tempSum=0;  //현재 블루레이에 들어가는 총 강의 분 수
            for(int i=0;i<list.size();i++){
                if(tempSum+list.get(i)>mid){
                    tempSum=0;
                    numOfBlueray++;
                }
                tempSum+=list.get(i);
            }
            numOfBlueray++;
            if(numOfBlueray<=M){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        bw.write(Integer.toString(left)+"\n");
        bw.flush();
    }
}
