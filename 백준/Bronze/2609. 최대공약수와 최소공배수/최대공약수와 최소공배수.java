import java.io.*;

public class Main{
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        String[] command = br.readLine().split(" ");
        int a=Integer.parseInt(command[0]);
        int b=Integer.parseInt(command[1]);
        int num1 = findMin(a,b);
        int num2 = findMax(a,b,num1);

        bw.write(Integer.toString(num1)+"\n");
        bw.write(Integer.toString(num2)+"\n");
        bw.flush();
        bw.close();
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
    public static int findMax(int num1, int num2,int minNum){
        return num1*num2/minNum;   
    }
}