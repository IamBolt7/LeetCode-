class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) grid[i] = classroom[i].toCharArray();

        int sr = -1, sc = -1;
        int litterCount = 0;
        int[][] litterIndex = new int[m][n];
        for (int[] row : litterIndex) java.util.Arrays.fill(row, -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];
                if (c == 'S') { sr = i; sc = j; }
                else if (c == 'L') { litterIndex[i][j] = litterCount++; }
            }
        }

        int fullMask = (litterCount == 0) ? 0 : ((1 << litterCount) - 1);
        if (litterCount == 0) return 0;

        // state: (r, c, energy, mask)
        int energyStates = energy + 1;
        int maskStates = 1 << litterCount;
        int totalStates = m * n * energyStates * maskStates;
        boolean[] visited = new boolean[totalStates];

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        java.util.ArrayDeque<int[]> queue = new java.util.ArrayDeque<>();
        int startState = encode(sr, sc, energy, 0, n, energyStates, maskStates);
        visited[startState] = true;
        queue.offer(new int[]{sr, sc, energy, 0, 0}); // r, c, energy, mask, moves

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], e = cur[2], mask = cur[3], moves = cur[4];

            if (e <= 0) continue; // no energy to move further

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (grid[nr][nc] == 'X') continue;

                int newEnergy = e - 1;
                int newMask = mask;

                if (grid[nr][nc] == 'R') {
                    newEnergy = energy; // reset to full
                } else if (grid[nr][nc] == 'L') {
                    int bit = litterIndex[nr][nc];
                    newMask = mask | (1 << bit);
                }

                if (newMask == fullMask) {
                    return moves + 1;
                }

                int stateId = encode(nr, nc, newEnergy, newMask, n, energyStates, maskStates);
                if (!visited[stateId]) {
                    visited[stateId] = true;
                    queue.offer(new int[]{nr, nc, newEnergy, newMask, moves + 1});
                }
            }
        }

        return -1;
    }

    private int encode(int r, int c, int e, int mask, int n, int energyStates, int maskStates) {
        return ((r * n + c) * energyStates + e) * maskStates + mask;
    }
}