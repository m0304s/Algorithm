import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Meet> list = new ArrayList<>();

    public static class Meet {
        int start;
        int end;

        Meet(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split(" ");
            Meet meet = new Meet(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
            list.add(meet);
        }
        Collections.sort(list, new Comparator<Meet>() {
            public int compare(Meet o1, Meet o2) {
                if(o1.end==o2.end){
                    return o1.start-o2.start;
                }
                return o1.end - o2.end;
            }
        });
        int count = checkEnable(list);
        bw.write(Integer.toString(count) + "\n");
        bw.flush();
        bw.close();
    }

    public static int checkEnable(ArrayList<Meet> list) {
        int count = 0;
        int endTime = -1; // 초기값 설정
        for (int i = 0; i < list.size(); i++) {
            if (endTime <= list.get(i).start) { // 현재 회의의 시작 시간이 이전 회의의 종료 시간보다 크거나 같은 경우
                count++; // 회의 가능
                endTime = list.get(i).end; // 종료 시간 갱신
            }
        }
        return count;
    }
}
