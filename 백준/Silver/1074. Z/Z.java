import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int R = Integer.parseInt(tokens[1]);
        int C = Integer.parseInt(tokens[2]);
        int answer = 0;
        int size = (int) Math.pow(2,N);

        answer = find(size, R, C, answer);

        bw.write(Integer.toString(answer)+"\n");
        bw.flush();
    }
	private static int find(int size, int r, int c,int answer) {
		if(size == 1)
			return answer;
		
		if(r < size/2 && c < size/2) {
			return find(size/2, r, c,answer);
		}
		else if(r < size/2 && c >= size/2) {
			answer += size * size / 4;
			return find(size/2, r, c - size/2,answer);
		}
		else if(r >= size/2 && c < size/2) {
			answer += (size * size / 4) * 2;
			return find(size/2, r - size/2, c,answer);
		}
		else {
			answer += (size * size / 4) * 3;
			return find(size/2, r - size/2, c - size/2,answer);
		}
	}
}