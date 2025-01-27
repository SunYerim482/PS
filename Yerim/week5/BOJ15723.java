import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ15723 {

    static final int INF = Integer.MAX_VALUE;
    static int[][] board;
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 그래프
        board = new int[27][27];

        for (int i = 0; i <= 26; i++) {
            Arrays.fill(board[i], INF);
        }

        // 초기화
        for (int i = 1; i <= 26; i++) {
            for (int j = 1; j <= 26; j++) {
                if (i == j) {
                    board[i][j] = 0;
                }
            }
        }

        // 입력
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            String[] str = tmp.split(" ");
            int a = str[0].charAt(0) - 'a' + 1;
            int b = str[2].charAt(0) - 'a' + 1;

            // 연결관계
            board[a][b] = 1;

        }

        for (int k = 1; k <= 26; k++) {
            for (int a = 1; a <= 26; a++) {
                for (int b = 1; b <= 26; b++) {
                    if (a == k || a == b || k == b) continue;
                    if (board[a][k] == 1 && board[k][b] == 1) {
                        board[a][b] = 1;
                    }
                }
            }
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String tmp = br.readLine();
            String[] str = tmp.split(" ");
            int a = str[0].charAt(0) - 'a' + 1;
            int b = str[2].charAt(0) - 'a' + 1;

            // 연결 판별
            if (board[a][b] == INF) {
                sb.append("F").append("\n");
            } else {
                sb.append("T").append("\n");
            }
        }
        System.out.print(sb.toString());
    }

}
