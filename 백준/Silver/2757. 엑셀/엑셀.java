import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        while (true) {
            String input = br.readLine();
            if (input.equals("R0C0")) break;

            StringBuilder answer = new StringBuilder();
            int columIndex = input.indexOf('C');
            int col = Integer.parseInt(input.substring(1, columIndex));
            int row = Integer.parseInt(input.substring(columIndex + 1));

            // 열(column)을 알파벳으로 변환
            StringBuilder sb = new StringBuilder();
            while (row > 0) {
                row--;  // 1-based index를 0-based로 변환
                sb.append((char) ('A' + row % 26));
                row /= 26;
            }

            // 결과 조립
            answer.append(sb.reverse()).append(col);
            System.out.println(answer);
        }
    }
}