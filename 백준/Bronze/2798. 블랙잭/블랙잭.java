import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int N = kb.nextInt();   //카드의 개수
        int M = kb.nextInt();   //넘지 않아야 하는 수
        int [] array = new int[N];
        for(int i=0;i<N;i++){
            array[i]=kb.nextInt();
        }
        int max = 0;    //최대한 M에 가까운 합
        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                for(int k=j+1;k<array.length;k++){
                    if(array[i]+array[j]+array[k]>M){
                        continue;
                    }else if(array[i]+array[j]+array[k]<=M){
                        if(max<array[i]+array[j]+array[k]){
                            max=array[i]+array[j]+array[k];
                        }
                    }
                }
            }
        }
        System.out.println(max);

        kb.close();
    }
}
