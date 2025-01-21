import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static List<Integer>[] tree;
    private static boolean[] isDeleted;
    private static int root;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N];
        isDeleted = new boolean[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(input[i]);
            if (parent == -1) {
                root = i; // 루트 노드 설정
            } else {
                tree[parent].add(i); // 부모-자식 관계 설정
            }
        }

        int removeNode = Integer.parseInt(br.readLine());
        deleteNode(removeNode);

        if (isDeleted[root]) { // 루트가 삭제되었다면
            System.out.println(0);
        } else {
            System.out.println(countLeafNodes(root));
        }
    }

    private static void deleteNode(int node) {
        isDeleted[node] = true; // 노드 삭제
        for (int child : tree[node]) { // 자식 노드도 삭제 처리
            deleteNode(child);
        }
    }

    private static int countLeafNodes(int node) {
        if (isDeleted[node]) return 0; // 삭제된 노드라면 리프 아님
        if (tree[node].isEmpty() || tree[node].stream().allMatch(child -> isDeleted[child])) {
            return 1; // 자식이 없거나 자식이 모두 삭제되었다면 리프 노드
        }

        int leafCount = 0;
        for (int child : tree[node]) {
            leafCount += countLeafNodes(child);
        }
        return leafCount;
    }
}