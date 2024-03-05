
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        String [] array = new String[5];
        int max_length=0;   //한 줄에 들어오는 최대 단어의 길이
        for(int i=0;i<5;i++){
            array[i]=kb.next();
            if(max_length<array[i].length()){
                max_length=array[i].length();
            }
        }
        char [][] arr = new char[5][15];
        for(int i=0;i<5;i++){
            for(int j=0;j<array[i].length();j++){
                arr[i][j]=array[i].charAt(j);
            }
        }
//        System.out.print(arr);
        for(int i=0;i<max_length;i++){
           for(int j=0;j<5;j++){
               if(arr[j][i]!=0){
                   System.out.print(arr[j][i]);
               }

            }
        }
    }
}
