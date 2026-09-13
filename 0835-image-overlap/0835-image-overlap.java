class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> pts1 = new ArrayList<>();
        List<int[]> pts2 = new ArrayList<>();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) pts1.add(new int[]{i, j});
                if (img2[i][j] == 1) pts2.add(new int[]{i, j});
            }

        Map<Integer, Integer> count = new HashMap<>();
        int best = 0;

        for (int[] p1 : pts1) {
            for (int[] p2 : pts2) {
                int dx = p1[0] - p2[0];
                int dy = p1[1] - p2[1];
                int key = (dx + n) * 200 + (dy + n);
                int c = count.merge(key, 1, Integer::sum);
                best = Math.max(best, c);
            }
        }

        return best;
    }
}