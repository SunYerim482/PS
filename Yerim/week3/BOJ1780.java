import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1780 {

    static int N, a = 0, b = 0, c = 0;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        partition(0, 0, N);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    private static void partition(int x, int y, int size) {
        // 기저
        if (numberCheck(x, y, size)) {
            if (board[x][y] == -1) a++;
            else if (board[x][y] == 0) b++;
            else if (board[x][y] == 1) c++;
            return;
        }

        int newSize = size / 3;
        // 재귀 -> 9개?
        partition(x, y, newSize);
        partition(x + newSize, y, newSize);
        partition(x + (newSize * 2), y, newSize);
        partition(x, y + newSize, newSize);
        partition(x, y + (newSize * 2), newSize);
        partition(x + newSize, y + newSize, newSize);
        partition(x + newSize, y + (newSize * 2), newSize);
        partition(x + (newSize * 2), y + newSize, newSize);
        partition(x + (newSize * 2), y + (newSize * 2), newSize);
    }

    // 같은 숫자인가?
    private static boolean numberCheck(int x, int y, int size) {
        int num = board[x][y];

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (board[i][j] != num) return false;
            }
        }
        return true;
    }

}
