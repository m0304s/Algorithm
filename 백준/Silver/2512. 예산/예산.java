import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        String []nTokens = br.readLine().split(" ");
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(nTokens[i]);
            list.add(num);
        }
        int M = Integer.parseInt(br.readLine());

        Collections.sort(list);
        //이분 탐색
        int left = 0;
        int right = list.get(list.size()-1);

        while(left<=right){
            int mid = (left+right)/2;
            int sumOfBudget=0;
            for(int i=0;i<list.size();i++){
                if(list.get(i)<mid){
                    sumOfBudget+=list.get(i);
                }else{
                    sumOfBudget+=mid;
                }
            }
            if(sumOfBudget<=M){  //예산이 남는 경우
                left = mid+1;
            }else{
                right=mid-1;
            }
        }
        bw.write(Integer.toString(right)+"\n");
        bw.flush();
    }
}
