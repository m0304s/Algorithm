import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int hour = kb.nextInt();    //시간
        int min = kb.nextInt();     //분

        if(hour<1){
            if(min<45){ //0:45 이전의 시간이 입력되었을 경우 Ex 0:40 -> 23:55
                hour+=23;
                min=min-45+60;
            }else{      //0:50 -> 0:05
                min=min-45;
            }
        }else{
            if(min<45){ // Ex: 2:30 -> 1:45
                hour-=1;
                min=min-45+60;
            }else{
                min=min-45;
            }
        }
        System.out.println(hour+" "+min);
        kb.close();
    }
}
