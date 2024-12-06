import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Shark {
        int x, y, speed, direction, size;

        public Shark(int x, int y, int speed, int direction, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        public void move() {
            int moveDistance = speed;
            if (direction <= 2) moveDistance %= (2 * (R - 1));
            else moveDistance %= (2 * (C - 1));
            while (moveDistance > 0) {
                if (direction == 1) {
                    if (x > moveDistance) {
                        x -= moveDistance;
                        break;
                    }
                    moveDistance -= (x - 1);
                    x = 1;
                    direction = 2;
                } else if (direction == 2) {
                    if (R - x >= moveDistance) {
                        x += moveDistance;
                        break;
                    }
                    moveDistance -= (R - x);
                    x = R;
                    direction = 1;
                } else if (direction == 3) {
                    if (C - y >= moveDistance) {
                        y += moveDistance;
                        break;
                    }
                    moveDistance -= (C - y);
                    y = C;
                    direction = 4;
                } else if (direction == 4) {
                    if (y > moveDistance) {
                        y -= moveDistance;
                        break;
                    }
                    moveDistance -= (y - 1);
                    y = 1;
                    direction = 3;
                }
            }
        }
    }

    static int R, C, M;
    static List<Shark> sharkList;
    static int fishingKingIndex = 0, totalCatchSize = 0;

    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");
        R = Integer.parseInt(tokens[0]);
        C = Integer.parseInt(tokens[1]);
        M = Integer.parseInt(tokens[2]);

        sharkList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            tokens = br.readLine().split(" ");
            int r = Integer.parseInt(tokens[0]);
            int c = Integer.parseInt(tokens[1]);
            int s = Integer.parseInt(tokens[2]);
            int d = Integer.parseInt(tokens[3]);
            int z = Integer.parseInt(tokens[4]);
            sharkList.add(new Shark(r, c, s, d, z));
        }

        simulate();
        System.out.println(totalCatchSize);
    }

    private static void simulate() {
        while (fishingKingIndex++ < C) {
            catchShark();
            moveSharks();
            resolveConflicts();
        }
    }

    private static void catchShark() {
        Shark nearestShark = null;
        for (Shark shark : sharkList) {
            if (shark.y == fishingKingIndex) {
                if (nearestShark == null || shark.x < nearestShark.x) {
                    nearestShark = shark;
                }
            }
        }

        if (nearestShark != null) {
            totalCatchSize += nearestShark.size;
            sharkList.remove(nearestShark);
        }
    }

    private static void moveSharks() {
        for (Shark shark : sharkList) {
            shark.move();
        }
    }

    private static void resolveConflicts() {
        Map<Integer, Map<Integer, Shark>> grid = new HashMap<>();

        for (Shark shark : sharkList) {
            grid.putIfAbsent(shark.x, new HashMap<>());
            Map<Integer, Shark> row = grid.get(shark.x);

            if (row.containsKey(shark.y)) {
                Shark existingShark = row.get(shark.y);
                if (existingShark.size < shark.size) {
                    row.put(shark.y, shark);
                }
            } else {
                row.put(shark.y, shark);
            }
        }

        sharkList.clear();
        for (Map<Integer, Shark> row : grid.values()) {
            sharkList.addAll(row.values());
        }
    }
}