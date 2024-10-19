import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        for (int t = 1; t <= 10; t++) {
            int T = Integer.parseInt(br.readLine());
            String delimiter = br.readLine();
            String inputString = br.readLine();

            int count = 0;
            int delimiterLength = delimiter.length();
            int inputLength = inputString.length();

            for (int i = 0; i <= inputLength - delimiterLength; i++) {
                // 첫 번째 문자가 같으면...
                if (inputString.charAt(i) == delimiter.charAt(0)) {
                    boolean isMatch = true;
                    // 구분자 길이만큼 검사
                    for (int j = 0; j < delimiterLength; j++) {
                        if (inputString.charAt(i + j) != delimiter.charAt(j)) {
                            isMatch = false;
                            break;
                        }
                    }
                    if (isMatch) count++;
                }
            }
            bw.write("#" + T + " " + count + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}