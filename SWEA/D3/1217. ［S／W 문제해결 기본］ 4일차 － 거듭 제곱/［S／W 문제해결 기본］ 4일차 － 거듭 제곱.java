import java.util.Scanner;

public class Solution {
    static final int TEST_CASE = 10;
    static int child;
    static int parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int t=1;t<=TEST_CASE;t++){
            sc.nextInt();
            child = sc.nextInt();
            parent = sc.nextInt();

            pow(child,0,1,t);
        }
    }
    static void pow(int a,int b,int result,int testCase){
        if(b == parent){
            //결과물 출력
            System.out.println("#"+testCase+" "+result);
            return;
        }
        pow(a,b+1,a*result,testCase);
    }
}
