import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read input
        String[] tokens = br.readLine().split(" ");
        int R = Integer.parseInt(tokens[0]);
        int C = Integer.parseInt(tokens[1]);
        int N = Integer.parseInt(tokens[2]);

        char[][] grid = new char[R][C];
        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        // Handle base cases
        if (N == 1) {
            printGrid(grid);
            return;
        } else if (N % 2 == 0) {
            printFullBombGrid(R, C);
            return;
        }

        // Find the state after first explosion (N % 4 == 3)
        char[][] afterFirstExplosion = simulateExplosion(grid, R, C);

        // If N % 4 == 1, we need to simulate the next explosion
        if (N % 4 == 1) {
            char[][] afterSecondExplosion = simulateExplosion(afterFirstExplosion, R, C);
            printGrid(afterSecondExplosion);
        } else {
            printGrid(afterFirstExplosion);
        }
    }

    // Function to simulate the explosion and return the resulting grid
    private static char[][] simulateExplosion(char[][] grid, int R, int C) {
        char[][] newGrid = new char[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newGrid[i][j] = 'O';  // Start with all bombs
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 'O') {  // If there was a bomb originally
                    newGrid[i][j] = '.';
                    if (i > 0) newGrid[i - 1][j] = '.';
                    if (i < R - 1) newGrid[i + 1][j] = '.';
                    if (j > 0) newGrid[i][j - 1] = '.';
                    if (j < C - 1) newGrid[i][j + 1] = '.';
                }
            }
        }

        return newGrid;
    }

    // Function to print the grid
    private static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            System.out.println(grid[i]);
        }
    }

    // Function to print a full bomb grid (all 'O')
    private static void printFullBombGrid(int R, int C) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < C; i++) {
            sb.append('O');
        }
        String fullRow = sb.toString();
        for (int i = 0; i < R; i++) {
            System.out.println(fullRow);
        }
    }
}