import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int num = kb.nextInt();
        String [] words = new String[num];
        for(int i=0;i<num;i++){
            words[i]=kb.next();
        }
        int wordLen = words[0].length();
        Boolean [] bool = new Boolean[wordLen];
        for(int i=0;i<wordLen;i++){
            bool[i]=false;
        }
        for(int i=0;i<wordLen;i++){
            //파일명을 하나씩 탐색 후,같은 위치에 동일하지 않은 파일명 존재 -> 패턴 적용
            for(int j=0;j<num;j++){
                for(int x=j+1;x<num;x++){
                    if(words[j].charAt(i)!=words[x].charAt(i)){
                        bool[i]=true;
                    }
                }
            }
        }
        for(int i=0;i<wordLen;i++){
            if(bool[i]==true){
                System.out.print("?");
            }else{
                System.out.print(words[0].charAt(i));
            }
        }
        kb.close();
    }
}
