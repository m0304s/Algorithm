import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int num = kb.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        int len = Integer.toString(num).length();
        for(int i=num;i>0;i--){
            int sum = i;
            int L = Integer.toString(i).length();
            for(int j=0;j<L;j++){
                Character s = Integer.toString(i).charAt(j);
                sum+=Integer.parseInt(Character.toString(s));
            }

            if(sum==num){
                list.add(i);
            }
        }
        if(list.size()==0){
            System.out.println(0);
        }else{
            Collections.sort(list);
            System.out.println(list.get(0));
        }
        kb.close();
    }
}
