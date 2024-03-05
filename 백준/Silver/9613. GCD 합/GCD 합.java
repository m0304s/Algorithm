import java.io.*;
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            String[] command = br.readLine().split(" ");
            int N = Integer.parseInt(command[0]);   //수의 개수
            int [] array = new int[N];
            for(int j=0;j<N;j++){
                array[j]=Integer.parseInt(command[j+1]);
            }
            bw.write(Long.toString(solution(array))+"\n");
        }
        bw.flush();
        bw.close();
    }
    public static long solution(int[] array){
        long sum =0;
        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                int gcd = findMin(array[i], array[j]);
                sum+=gcd;
                }
            }
            return sum;
        }
    public static int findMin(int num1, int num2){
        int r = num1%num2;
        if(r==0){
            return num2;
        }else{
            num1 = num2;
            num2 = r;
            return findMin(num1,num2);            
        }   
    }
}