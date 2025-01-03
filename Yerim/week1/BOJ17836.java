package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17836 {
    static int N, M, T;
    static int[][] arr;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken()); // 제한 시간

        arr = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = bfs(0, 0);

        if (answer == -1)
            System.out.println("Fail");
        else
            System.out.println(answer);

    }

    private static int bfs(int x, int y) {
        Queue<Brave> queue = new LinkedList<>();
        visited[x][y][0] = true;
        queue.offer(new Brave(x, y, 0, false));

        while (!queue.isEmpty()) {
            Brave brave = queue.poll();

            int braveX = brave.x;
            int braveY = brave.y;
            int braveTime = brave.time;
            boolean braveGram = brave.isGram;

            if (braveTime > T) break;

            // 기저
            if (braveX == N-1 && braveY == M-1) {
                return braveTime;
            }

            // 유도
            for (int i = 0; i < 4; i++) {
                int nx = braveX + dx[i];
                int ny = braveY + dy[i];
                // 범위 밖
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                // gram 획득 못 한 경우
                if (!braveGram) {
                    if (!visited[nx][ny][0] && arr[nx][ny] == 0) {
                        queue.offer(new Brave(nx, ny, braveTime + 1, false));
                        visited[nx][ny][0] = true;
                    } else if (!visited[nx][ny][0] && arr[nx][ny] == 2) {
                        queue.offer(new Brave(nx, ny, braveTime + 1, true));
                        visited[nx][ny][0] = true;
                    }
                } else {
                    if (!visited[nx][ny][1]) {
                        queue.offer(new Brave(nx, ny, braveTime + 1, true));
                        visited[nx][ny][1] = true;
                    }
                }
            }
        }
        return -1;
    }

    static class Brave {
        int x, y, time;
        boolean isGram; // gram획득 유무

        public Brave(int x, int y, int time, boolean isGram) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.isGram = isGram;
        }
    }
}
