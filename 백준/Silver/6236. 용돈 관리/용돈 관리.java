import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] list;
    static int max = 0;
    static int result;
    public static void main(String[] args) throws IOException{
        String [] nm = br.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);

        list = new int[N];

        max = Integer.MIN_VALUE;
        for(int i=0;i<N;i++){
            list[i] = Integer.parseInt(br.readLine());
            if(max<list[i]){
                max = list[i];
            }
        }
        int left = max;
        int right = 10_000 * 100_000;
        // 이진 탐색을 이용하여 해답을 찾는다.
        while (left <= right) {
            int mid = (left + right) / 2;
            // 지정한 횟수 이하의 횟수만큼 인출해야 할 경우,
            // 인출 금액이 더 적은 경우에 해답이 있는지 탐색해 봐야 한다.
            if (M >= getWithdrawalCount(mid,list)) {
                result = mid;
                right = mid - 1;
                // 지정한 횟수보다 더 많이 인출해야 할 경우,
                // 인출 금액이 더 커야한다.
            } else {
                left = mid + 1;
            }
        }
        bw.write(Integer.toString(result)+"\n");
        bw.flush();
        bw.close();
    }
    public static int getWithdrawalCount(int withdrawalAmount,int [] list){
        int count = 1;
        int money = withdrawalAmount;
        for (int i : list) {
            money-=i;
            if(money<0){
                ++count;
                money=withdrawalAmount-i;
            }
        }
        return count;
    }
}

/**
 *  2 2
    500
    501
 */
