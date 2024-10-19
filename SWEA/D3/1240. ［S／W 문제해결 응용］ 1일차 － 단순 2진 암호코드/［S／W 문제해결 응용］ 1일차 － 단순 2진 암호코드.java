import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<List<Integer>> passwordMap = new ArrayList<>();
    static HashMap<List<Integer>, Integer> numMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        initNumMap();
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            int N = Integer.parseInt(tokens[0]);
            int M = Integer.parseInt(tokens[1]);
            readArray(N,M);
            int answer = encodePassword();
            bw.write("#"+t+" "+answer+"\n");
            passwordMap.clear();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void initNumMap() {
        numMap.put(Arrays.asList(0,0,0,1,1,0,1),0);
        numMap.put(Arrays.asList(0,0,1,1,0,0,1),1);
        numMap.put(Arrays.asList(0,0,1,0,0,1,1),2);
        numMap.put(Arrays.asList(0,1,1,1,1,0,1),3);
        numMap.put(Arrays.asList(0,1,0,0,0,1,1),4);
        numMap.put(Arrays.asList(0,1,1,0,0,0,1),5);
        numMap.put(Arrays.asList(0,1,0,1,1,1,1),6);
        numMap.put(Arrays.asList(0,1,1,1,0,1,1),7);
        numMap.put(Arrays.asList(0,1,1,0,1,1,1),8);
        numMap.put(Arrays.asList(0,0,0,1,0,1,1),9);
    }

    static int encodePassword(){
        for (int i = 0; i < passwordMap.size(); i++) {
            // 한 줄의 암호
            List<Integer> password = passwordMap.get(i);

            // 홀수 자리 합 * 3 + 짝수 자리 합 % 10 == 0
            int oddSum = 0;
            int evenSum = 0;
            // 7개씩 나누어 처리
            int bit = 1;
            for (int x = 0; x < password.size(); x+=7) {
                List<Integer> passwordCode = password.subList(x, x + 7);
                int number = numMap.get(passwordCode);
                bit++;
                //홀수일 경우
                if(bit %2 == 1) oddSum+=number;
                else evenSum += number;
            }
            if ((evenSum * 3 + oddSum) % 10 != 0) {
                return 0;
            } else {
                // 복호화된 값 추가
                return oddSum+evenSum;
            }
        }
        // 합계 반환
        return 0;
    }

    static void readArray(int N,int M) throws IOException{
        for(int i=0;i<N;i++){
            String inputString = br.readLine();
            boolean isValid = validateInputString(inputString);
            if(isValid){
                parsePasswordArray(inputString);
            }
        }
    }

    private static void parsePasswordArray(String inputString) {
        // 최소 56자리 이상이 남아있을 때만 처리
        for (int i = inputString.length() - 1; i >= 55; i--) {
            // 끝에서부터 탐색 후 1이 나오면 56자리 암호 탐색
            if (inputString.charAt(i) == '1') {
                List<Integer> password = new ArrayList<>();
                // i부터 i-55까지 56자리의 비트값을 추가
                for (int start = i; start >= i - 55; start--) {
                    password.add(inputString.charAt(start) - '0');
                }
                // 순서를 뒤집기
                Collections.reverse(password);
                passwordMap.add(password);
                return;
            }
        }
    }

    private static boolean validateInputString(String inputString) {
        for(int i=0;i<inputString.length();i++){
            if(inputString.charAt(i) == '1') return true;
        }
        return false;
    }

    private static void debug(){
        for(int i=0;i<passwordMap.size();i++){
            List<Integer> password = passwordMap.get(i);
            for (Integer integer : password) {
                System.out.print(integer);
            }
            System.out.println();
        }
    }
}
