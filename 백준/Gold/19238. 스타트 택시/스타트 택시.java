import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int ROAD = 0, WALL = 1;
    private static List<Person> personList;
    private static Taxi taxi;
    private static int N, M, fuel;
    private static int[][] map;

    private static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Person {
        Node start, end;
        public Person(int sx, int sy, int ex, int ey) {
            this.start = new Node(sx, sy);
            this.end = new Node(ex, ey);
        }
    }

    private static class Taxi {
        int x, y, remainFuel;
        public Taxi(int x, int y, int remainFuel) {
            this.x = x;
            this.y = y;
            this.remainFuel = remainFuel;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        fuel = Integer.parseInt(tokens[2]);

        map = new int[N][N];
        createMap();
        createTaxi();
        readInfo();
        simulation();
    }

    private static void simulation() throws IOException {
        while (!personList.isEmpty()) {
            Person personToRide = findMinimumLengthFromTaxi();
            if (personToRide == null) {
                fuel = -1;
                break;
            }

            int toStart = findMinRange(new Node(taxi.x, taxi.y), personToRide.start);
            int toEnd = findMinRange(personToRide.start, personToRide.end);

            // Check fuel constraints
            if (toStart == -1 || toEnd == -1 || fuel < toStart + toEnd) {
                fuel = -1;
                break;
            }

            taxi.x = personToRide.end.x;
            taxi.y = personToRide.end.y;
            fuel -= (toStart + toEnd);
            fuel += (toEnd * 2);

            // Remove the passenger
            personList.remove(personToRide);
        }

        // Print result
        bw.write(fuel + "\n");
        bw.flush();
    }

    private static Person findMinimumLengthFromTaxi() {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        queue.add(new Node(taxi.x, taxi.y));
        visited[taxi.x][taxi.y] = true;

        List<Person> candidates = new ArrayList<>();
        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();

                for (Person person : personList) {
                    if (cur.x == person.start.x && cur.y == person.start.y) {
                        candidates.add(person);
                    }
                }
                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    if (inRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == ROAD) {
                        queue.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }

            if (!candidates.isEmpty()) {
                candidates.sort((p1, p2) -> {
                    if (p1.start.x != p2.start.x) {
                        return Integer.compare(p1.start.x, p2.start.x);
                    }
                    return Integer.compare(p1.start.y, p2.start.y);
                });
                return candidates.get(0);
            }
            distance++;
        }

        return null;
    }

    private static int findMinRange(Node start, Node end) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        queue.add(start);
        visited[start.x][start.y] = true;

        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.x == end.x && cur.y == end.y) {
                    return distance;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    if (inRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == ROAD) {
                        queue.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
            distance++;
        }

        return -1;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static void createTaxi() throws IOException {
        String[] tokens = br.readLine().split(" ");
        taxi = new Taxi(Integer.parseInt(tokens[0]) - 1, Integer.parseInt(tokens[1]) - 1, fuel);
    }

    private static void readInfo() throws IOException {
        personList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String[] tokens = br.readLine().split(" ");
            personList.add(new Person(
                    Integer.parseInt(tokens[0]) - 1,
                    Integer.parseInt(tokens[1]) - 1,
                    Integer.parseInt(tokens[2]) - 1,
                    Integer.parseInt(tokens[3]) - 1
            ));
        }
    }

    private static void createMap() throws IOException {
        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
    }
}
