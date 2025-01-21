import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17086 {

    static int N, M;
    static int[][] board;
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MIN_VALUE;

        // 0인 공간에서 bfs
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    answer = Math.max(answer, bfs(i, j));
                }
            }
        }

        System.out.println(answer);
    }

    private static int bfs(int x, int y) {
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        queue.offer(new Position(x, y, 0));

        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            for (int i = 0; i < 8; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
                    continue;
                }

                // 빈 공간이면
                if (board[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.offer(new Position(nx, ny, pos.count + 1));
                }
                // 아기상어
                else {
                    return pos.count + 1;
                }
            }
        }
        return -1;
    }

    static class Position {
        int x, y, count;
        public Position(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
