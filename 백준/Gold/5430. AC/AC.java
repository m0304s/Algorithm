import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Deque<Integer> deque = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());    // 테스트 케이스 개수
        for (int i = 0; i < T; i++) {
            String command = br.readLine(); // 실행할 명령어
            br.readLine();    // 배열에 들어있는 수의 개수
            command = command.replace("RR", "");

            String s = br.readLine();
            s = s.replace("[", "");
            s = s.replace("]", "");
            String[] array = s.split(",");

            if (!array[0].equals("")) {
                for (int j = 0; j < array.length; j++) {
                    deque.add(Integer.parseInt(array[j]));
                }
            }

            solution(command);
            deque.clear();
        }

        bw.flush();
        bw.close();
    }

    public static void solution(String command) throws IOException {
        boolean error = false;  // 에러 여부
        boolean direction = false;  // false: 순방향, true: 역방향

        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'R') {
                direction = !direction; // 반대 방향으로 접근
            } else {  // D -> 순방향: 앞에 있는 숫자 제거, 역방향: 뒤에 있는 숫자 제거
                if (!direction) {
                    if (deque.isEmpty()) {
                        error = true;
                        break;
                    } else {
                        deque.removeFirst();
                    }
                } else {
                    if (deque.isEmpty()) {
                        error = true;
                        break;
                    } else {
                        deque.removeLast();
                    }
                }
            }
        }

        if (error) {
            bw.write("error\n");
        } else {
            if (!direction) {
                bw.write("[");
                while (!deque.isEmpty()) {
                    bw.write(Integer.toString(deque.removeFirst()));
                    if (!deque.isEmpty()) {
                        bw.write(",");
                    }
                }
                bw.write("]\n");
            } else {
                bw.write("[");
                while (!deque.isEmpty()) {
                    bw.write(Integer.toString(deque.removeLast()));
                    if (!deque.isEmpty()) {
                        bw.write(",");
                    }
                }
                bw.write("]\n");
            }
        }
    }
}
