import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<Integer>();
        while(true){
            int num = kb.nextInt();
            if(num==-1){
                break;
            }else if(num==0){
                int count=findDoubleNum(list);
                System.out.println(count);
                list.clear();
                count=0;
            }
            list.add(num);
        }
        kb.close();
    }
    public static int findDoubleNum(ArrayList<Integer> list){
        int count=0;
        for(int i=0;i<list.size();i++){
            int checkNum = list.get(i); //확인해야할 숫자
            for(int j=0;j<list.size();j++){
                if(j!=i){
                    if(checkNum==list.get(j)*2){
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
