import java.util.*;

class PGS68936 {
    static int[] answer;
    public int[] solution(int[][] arr) {
        answer = new int[2];

        partition(0, 0, arr.length, arr);

        return answer;
    }

    private static void partition(int x, int y, int size, int[][] board) {
        // 기저
        if (numberCheck(x, y, size, board)) {
            if (board[x][y] == 0) answer[0]++;
            else if (board[x][y] == 1) answer[1]++;
            return;
        }

        // 유도
        int newSize = size / 2;
        partition(x, y, newSize, board);
        partition(x + newSize, y, newSize, board);
        partition(x, y + newSize, newSize, board);
        partition(x + newSize, y + newSize, newSize, board);

    }

    // 같은 숫자인지 check
    private static boolean numberCheck(int x, int y, int size, int[][] board) {
        int num = board[x][y];

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (num != board[i][j]) return false;
            }
        }
        return true;
    }

}