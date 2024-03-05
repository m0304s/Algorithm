import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int money = Integer.parseInt(br.readLine());
        int leftMoney = 1000-money; //잔돈
        for(int i=0;i<=5;i++){
            switch (i) {
                case 0: //500엔                    
                    list.add(leftMoney/500);
                    leftMoney%=500;
                    break;
                case 1: //100엔                    
                    list.add(leftMoney/100);
                    leftMoney%=100;
                    break;
                case 2: //50엔                    
                    list.add(leftMoney/50);
                    leftMoney%=50;
                    break;
                case 3: //10엔                    
                    list.add(leftMoney/10);
                    leftMoney%=10;
                    break;
                case 4: //5엔    
                    list.add(leftMoney/5);
                    leftMoney%=5;
                    break;
                case 5: //1엔    
                    list.add(leftMoney/1);
                    leftMoney%=1;
                    break;
            }
        }
        int sum=0;
        for (int count : list) {
            sum+=count;
        }
        bw.write(Integer.toString(sum)+"\n");
        bw.flush();
        bw.close();
    }
}
