

import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner kb = new Scanner(System.in);
        int number = kb.nextInt();
        int total=0;
        boolean map[][] = new boolean[101][101];
        for(int i=0;i<number;i++){
            int x = kb.nextInt();
            int y= kb.nextInt();
            for(int j=x;j<x+10;j++){
                for(int k=y;k<y+10;k++){
                    if(!map[j][k]){
                        map[j][k]=true;
                        total++;
                    }
                }
            }
        }
        System.out.println(total);
    }

}
