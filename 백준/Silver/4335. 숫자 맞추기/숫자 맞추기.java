import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        while(true){
            ArrayList<Integer> high = new ArrayList<>();
            ArrayList<Integer> low = new ArrayList<>();
            int right = 0;
            while(true){
                int num = kb.nextInt();
                if(num==0){
                    return;
                }
                kb.nextLine();
                String isRight = kb.nextLine();

                if(isRight.equals("too high")){
                    high.add(num);
                }else if(isRight.equals("too low")){
                    low.add(num);
                }else{
                    right=num;
                    break;
                }
            }
            boolean isTrue=true;
            for(int i=0;i<high.size();i++){
                if(right>=high.get(i)){
                    isTrue=false;
                    break;
                }
            }
            if(isTrue){
                for(int i=0;i<low.size();i++){
                    if(right<=low.get(i)){
                        isTrue=false;
                        break;
                    }
                }
            }
            if(isTrue){
                System.out.println("Stan may be honest");
            }else{
                System.out.println("Stan is dishonest");
            }
        }
    }
}
