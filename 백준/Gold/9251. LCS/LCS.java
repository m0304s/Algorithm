import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static String stringA;
    static String stringB;
    static int [][] LCS;

    public static void main(String[] args) throws IOException {
        stringA = br.readLine();
        stringB = br.readLine();
        LCS = new int[stringA.length()+1][stringB.length()+1];

        for (int i = 1; i <= stringA.length(); i++) {
            for (int j = 1; j <= stringB.length(); j++) {
                if (stringA.charAt(i - 1) == stringB.charAt(j - 1)) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }
        bw.write(LCS[stringA.length()][stringB.length()] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
