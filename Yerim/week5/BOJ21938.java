import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21938 {

    static int N, M, T, answer;
    static int[][] board;
    static Pixel[][] pixels;
    static boolean[][] visited;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        pixels = new Pixel[N][M];
        visited = new boolean[N][M];
        answer = 0;


        // 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int r = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
//                int tmp = calculate(r, g, b);
                pixels[i][j] = new Pixel(r, g, b);
            }
        }

        T = Integer.parseInt(br.readLine());

        // 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int tmp = calculate(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b);
                board[i][j] = tmp;
            }
        }

        // bfs 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 255 && !visited[i][j]) {
                    bfs(i, j);
                    answer++;
                }
            }
        }

        System.out.println(answer);


    }

    private static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = curr[0] + dy[i];
                int nx = curr[1] + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || board[ny][nx] == 0) continue;
                visited[ny][nx] = true;
                queue.add(new int[]{ny, nx});
            }
        }
    }

    private static int calculate(int r, int g, int b) {
        int ans = (r + g + b) / 3;

        if (ans >= T) return 255;
        else return 0;

    }

    static class Pixel {
        int r, g, b;

        public Pixel(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

}


/*
* 픽셀값 계산하는 메서드
* 물체 -> 255, 아니면 -> 0
* 레전드 bfs 탐색
*
* */