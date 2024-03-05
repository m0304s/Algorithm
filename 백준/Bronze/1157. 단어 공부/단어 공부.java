import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String word = kb.next();
        word=word.toUpperCase();    //대문자로 변환
        int [] index = new int[26]; //알파벳의 위치 인덱스, A=0번, B=1번...
        for(int i=0;i<word.length();i++){
            int point = word.charAt(i)-(int)'A';
            index[point]++;
        }
        int max=0;
        int in = 0;
        for(int i=0;i<26;i++){
            if(max<index[i]){
                max=index[i];
                in=i;
            }
        }
        Arrays.sort(index);
        if(index[25]==index[24]){
            System.out.println("?");
        }else{
            System.out.println((char)(in+(int)'A'));
        }
        kb.close();
    }
}
