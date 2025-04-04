import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    // 이동 방향: 0: 이동 없음, 1: 상, 2: 우, 3: 하, 4: 좌
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};
    
    static int M, A;
    static User userA;
    static User userB;
    static int[] userADir;  // 사용자 A의 이동경로
    static int[] userBDir;  // 사용자 B의 이동경로
    static Station[] stations;
    
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            bw.write("#" + t + " " + solution() + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    static int solution() throws IOException {
        inputBaseInfo();
        return simulation();
    }
    
    private static int simulation() {
        int totalCharge = 0;
        userA = new User(1, 1);
        userB = new User(10, 10);
        
        for (int time = 0; time <= M; time++) {
            // 현재 위치에서 각 사용자가 이용할 수 있는 충전소 후보
            List<Integer> aList = getAvailableStations(userA);
            List<Integer> bList = getAvailableStations(userB);
            
            // 두 사용자가 선택할 충전소 조합에 대해 최대 충전량
            int maxCharge = 0;

            if(aList.isEmpty()) aList.add(-1);
            if(bList.isEmpty()) bList.add(-1);
            
            for (int a : aList) {
                for (int b : bList) {
                    int chargeA = 0, chargeB = 0;
                    if(a == -1 && b == -1) {
                        // 둘 다 충전소 없음
                        chargeA = 0;
                        chargeB = 0;
                    } else if(a == b) {
                        // 같은 충전소를 선택하면 충전량은 해당 충전소의 성능만큼만 충전됨
                        int performance = stations[a].performance;
                        chargeA = performance;
                        chargeB = performance;

                        int sum = performance;
                        maxCharge = Math.max(maxCharge, sum);
                        continue;
                    } else {
                        if(a != -1) chargeA = stations[a].performance;
                        if(b != -1) chargeB = stations[b].performance;
                    }
                    maxCharge = Math.max(maxCharge, chargeA + chargeB);
                }
            }
            
            totalCharge += maxCharge;
            
            if(time == M) break;
            
            userA.move(userADir[time], dx, dy);
            userB.move(userBDir[time], dx, dy);
        }
        
        return totalCharge;
    }
    
    private static List<Integer> getAvailableStations(User user) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            if(canCharge(stations[i], user)) {
                list.add(i);
            }
        }
        return list;
    }
    
    private static boolean canCharge(Station station, User user) {
        int range = Math.abs(station.x - user.x) + Math.abs(station.y - user.y);
        return range <= station.coverage;
    }
    
    private static void inputBaseInfo() throws IOException {
        String[] tokens = br.readLine().split(" ");
        M = Integer.parseInt(tokens[0]);
        A = Integer.parseInt(tokens[1]);
        
        userADir = new int[M];
        userBDir = new int[M];
        
        tokens = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            userADir[i] = Integer.parseInt(tokens[i]);
        }
        
        tokens = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            userBDir[i] = Integer.parseInt(tokens[i]);
        }
        
        stations = new Station[A];
        for (int i = 0; i < A; i++) {
            tokens = br.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            int coverage = Integer.parseInt(tokens[2]);
            int performance = Integer.parseInt(tokens[3]);
            
            stations[i] = new Station(x, y, coverage, performance);
        }
    }
    
    static class Station {
        int x, y;
        int coverage;
        int performance;
        public Station(int x, int y, int coverage, int performance) {
            this.x = x;
            this.y = y;
            this.coverage = coverage;
            this.performance = performance;
        }
    }
    
    static class User {
        int x, y;
        public User(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void move(int direction, int[] dx, int[] dy) {
            x += dx[direction];
            y += dy[direction];
        }
    }
}
