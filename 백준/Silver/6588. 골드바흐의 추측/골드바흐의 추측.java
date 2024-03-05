import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static HashMap<Integer,Boolean> map = new HashMap<>();
    public static void main(String[] args) throws IOException{
        while(true){
            int input = Integer.parseInt(br.readLine());
            if(input!=0){
                solution(input);
            }else{
                break;
            }
        }
        bw.flush();
        bw.close();
    }
    public static void solution(int input) throws IOException{
        for(int i=3;i<=input/2;i+=2){
            int a = i;
            int b = input-i;
            boolean check = isPrime(a) && isPrime(b);
            if(check){
                bw.write(Integer.toString(input) + " = " +Integer.toString(a)+" + "+Integer.toString(b)+"\n");
                return;
            }
        }
        bw.write("Goldbach's conjecture is wrong.\n");
    }
    public static boolean isPrime(int a){
        if(map.containsKey(a)){
            return map.get(a);
        }else{
            if(a==1){
                return false;
            }else if(a==2){
                return true;
            }else{
                int sqrt=(int) Math.sqrt(a)+1;
                for(int i=2;i<=sqrt;i++){
                    if(a%i==0){
                        map.put(a,false);            
                        return false;
                    }
                }
                map.put(a,true);
                return true;
            }
        }
    }
}
