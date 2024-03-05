import java.io.*;
import java.util.*;
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Integer> AList = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        String [] tokens = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            AList.add(Integer.parseInt(tokens[i]));
        }
        Collections.sort(AList);
        int M = Integer.parseInt(br.readLine());
        String [] numString = br.readLine().split(" ");
        for(int i=0;i<M;i++){
            int num = Integer.parseInt(numString[i]);
            int idx = solution(num);
            if(idx==-1){
                bw.write(Integer.toString(0)+"\n");
            }else{
                bw.write(Integer.toString(1)+"\n");
            }
        }
        bw.flush();
        bw.close();
    }
    public static int solution(int num){
        int low = 0;
        int high = AList.size()-1;
        int idx = -1;   //반환 인덱스

        while(low<=high){
            int mid = (low+high)/2;
            if(AList.get(mid)==num){
                idx = mid;
                break;
            }else if(num<AList.get(mid)){
                high = mid -1;
            }else{
                low = mid +1;
            }
        }
        return idx;
    }
}
