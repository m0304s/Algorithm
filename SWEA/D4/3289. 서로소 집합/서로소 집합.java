import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static int[] parent, rank;

    // Find 연산: a의 부모를 찾고, 경로 압축을 통해 트리의 깊이를 최소화
    static int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);  // 경로 압축
        }
        return parent[a];
    }

    // Union 연산: a와 b의 집합을 합침 (Union by Rank 기법 사용)
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            // 랭크(rank)가 더 큰 집합을 루트로 설정
            if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            bw.write("#" + t + " ");
            String[] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            M = Integer.parseInt(tokens[1]);

            // 초기화
            parent = new int[N + 1];
            rank = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            for (int i = 0; i < M; i++) {
                tokens = br.readLine().split(" ");
                int type = Integer.parseInt(tokens[0]);
                int a = Integer.parseInt(tokens[1]);
                int b = Integer.parseInt(tokens[2]);

                if (type == 0) {
                    // Union 연산
                    union(a, b);
                } else if (type == 1) {
                    // Find 연산 (같은 집합인지 확인)
                    if (find(a) == find(b)) {
                        bw.write("1");
                    } else {
                        bw.write("0");
                    }
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
