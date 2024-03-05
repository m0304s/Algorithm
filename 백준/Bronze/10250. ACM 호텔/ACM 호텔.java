import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int testCase = kb.nextInt();
        for(int i=0;i<testCase;i++){
            int H = kb.nextInt();   //호텔의 층 수
            int W = kb.nextInt();   //각 층의 방 수
            int N = kb.nextInt();   //몇 번째 손님

            int finalH = N%H;
            int finalW = N/H+1;

            if(N%H==0){
                finalH = H;
            }
            finalW = N/H+1;
            if(N%H==0){
                finalW-=1;
            }
            String outputW;
            if(finalW<10){
                outputW = "0"+Integer.toString(finalW);
            }else{
                outputW = Integer.toString(finalW);
            }
            String outputH = Integer.toString(finalH);
            System.out.println(outputH+outputW);
        }
        kb.close();        
    }
}
