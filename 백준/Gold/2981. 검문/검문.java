import java.io.*;
import java.util.*;
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Integer> array = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            int m = Integer.parseInt(br.readLine());
            array.add(m);
        }
        Collections.sort(array);    //배열 정렬
        int gcdVal = array.get(1)-array.get(0);
        for(int i=2;i<N;i++){
            gcdVal = gcd(gcdVal,array.get(i)-array.get(i-1));
        }
        for(int i=2;i<=gcdVal;i++){
            if(gcdVal%i==0){
                bw.write(i+" ");
            }
        }
        bw.flush();
        bw.close();

    }
    public static int gcd(int a,int b){
        while(b!=0){
            int r = a%b;
            a=b;
            b=r;
        }
        return a;
    }
}
