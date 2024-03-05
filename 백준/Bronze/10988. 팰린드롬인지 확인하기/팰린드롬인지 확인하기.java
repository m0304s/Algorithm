import java.util.Scanner;

public class Main {
    public static void main(String []args){
        Scanner kb = new Scanner(System.in);
        String word = kb.next();
        int flag = 1;
        for(int i=0;i<word.length();i++){

            if(word.charAt(i)!=word.charAt(word.length()-i-1)){
                flag=0;
                break;
            }
        }
        System.out.println(flag);
    }
}