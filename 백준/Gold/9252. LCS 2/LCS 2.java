import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String stringA = br.readLine();
        String stringB = br.readLine();
        int lengthA = stringA.length();
        int lengthB = stringB.length();

        int[][] LCS = new int[lengthA + 1][lengthB + 1];

        // LCS 배열 채우기
        for (int i = 1; i <= lengthA; i++) {
            for (int j = 1; j <= lengthB; j++) {
                if (stringA.charAt(i - 1) == stringB.charAt(j - 1)) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }

        // LCS 역추적
        StringBuilder output = new StringBuilder();
        int x = lengthA, y = lengthB;

        while (x > 0 && y > 0) {
            if (stringA.charAt(x - 1) == stringB.charAt(y - 1)) {
                output.append(stringA.charAt(x - 1));
                x--;
                y--;
            } else if (LCS[x - 1][y] >= LCS[x][y - 1]) {
                x--;
            } else {
                y--;
            }
        }

        // 결과 출력
        bw.write(LCS[lengthA][lengthB] + "\n");
        if (output.length() > 0) {
            bw.write(output.reverse().toString() + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}