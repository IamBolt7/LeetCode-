class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> reserved = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Seats 1 and 10 never affect a 4-person family
            if (col >= 2 && col <= 9) {
                reserved
                    .computeIfAbsent(row, k -> new HashSet<>())
                    .add(col);
            }
        }

        // Every completely free row can fit 2 families
        int answer = (n - reserved.size()) * 2;

        for (Set<Integer> seats : reserved.values()) {
            boolean left =
                !seats.contains(2) &&
                !seats.contains(3) &&
                !seats.contains(4) &&
                !seats.contains(5);

            boolean middle =
                !seats.contains(4) &&
                !seats.contains(5) &&
                !seats.contains(6) &&
                !seats.contains(7);

            boolean right =
                !seats.contains(6) &&
                !seats.contains(7) &&
                !seats.contains(8) &&
                !seats.contains(9);

            if (left && right) {
                answer += 2;
            } else if (left || middle || right) {
                answer += 1;
            }
        }

        return answer;
    }
}