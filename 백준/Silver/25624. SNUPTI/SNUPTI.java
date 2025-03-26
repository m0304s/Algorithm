import java.util.*;

public class Main {
    static int n, m;
    static String[] inputStrings;
    static Set<Character>[] possibleResults;
    static List<String> generatedCombinations = new ArrayList<>();
    static char[] selected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        inputStrings = new String[m];
        possibleResults = new HashSet[n];
        selected = new char[n];

        for (int i = 0; i < n; i++) {
            possibleResults[i] = new HashSet<>();
        }

        for (int i = 0; i < m; i++) {
            inputStrings[i] = sc.nextLine();
            for (int j = 0; j < n; j++) {
                possibleResults[j].add(inputStrings[i].charAt(j));
            }
        }
        int[] charCount = new int[26];
        for (int i = 0; i < n; i++) {
            for (char c : possibleResults[i]) {
                charCount[c - 'A']++;
            }
        }
        for (int count : charCount) {
            if (count > 1) {
                System.out.println("NO");
                return;
            }
        }

        long totalCombinations = 1;
        for (int i = 0; i < n; i++) {
            totalCombinations *= possibleResults[i].size();
            if (totalCombinations > m) {
                System.out.println("NO");
                return;
            }
        }
        if (totalCombinations != m) {
            System.out.println("NO");
            return;
        }
        generateCombinations(0);

        Arrays.sort(inputStrings);
        Collections.sort(generatedCombinations);

        for (int i = 0; i < m; i++) {
            if (!generatedCombinations.get(i).equals(inputStrings[i])) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
        for (int i = 0; i < n; i++) {
            List<Character> sorted = new ArrayList<>(possibleResults[i]);
            Collections.sort(sorted);
            for (char c : sorted) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    static void generateCombinations(int depth) {
        if (depth == n) {
            StringBuilder sb = new StringBuilder();
            for (char c : selected) {
                sb.append(c);
            }
            generatedCombinations.add(sb.toString());
            return;
        }

        for (char c : possibleResults[depth]) {
            selected[depth] = c;
            generateCombinations(depth + 1);
        }
    }
}