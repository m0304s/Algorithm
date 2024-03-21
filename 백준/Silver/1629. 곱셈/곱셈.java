import java.io.*;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        long A = scanner.nextLong();
        long B = scanner.nextLong();
        long C = scanner.nextLong();

        // A를 B번 곱한 수를 C로 나눈 나머지 계산
        long result = powerMod(A, B, C);
        System.out.println(result);
        
        scanner.close();
    }

    // 거듭제곱을 분할 정복을 이용하여 효율적으로 계산하는 함수
    public static long powerMod(long A, long B, long C) {
        if (B == 0) {
            return 1 % C;
        } else if (B % 2 == 0) {
            long temp = powerMod(A, B / 2, C);
            return (temp * temp) % C;
        } else {
            long temp = powerMod(A, B - 1, C);
            return (A * temp) % C;
        }
    }
}
