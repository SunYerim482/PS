package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5212 {
    static int R, C;
    static int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE; // 나중에 출력 좌표
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static char[][] earth, newEarth;
    static Queue<int[]> queue = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        earth = new char[R][C];
        newEarth = new char[R][C];

        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                earth[i][j] = tmp.charAt(j);
                if (earth[i][j] == 'X') {
                    queue.offer(new int[]{i, j}); // 땅 위치를 저장
                }
            }
        }

        // 복사
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newEarth[i][j] = earth[i][j];
            }
        }

        // bfs
        bfs();

        // 출력
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                sb.append(newEarth[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            // 탐색
            int sea = 0;
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dr[i];
                int ny = curr[1] + dc[i];

                // 벗어나거나 바다면
                if (nx < 0 || ny < 0 || nx >= R || ny >= C || earth[nx][ny] == '.') {
                    sea++; // 바다
                }
            }

            // 이때 갯수가 3개 이상이면 물에 잠김
            if (sea >= 3) {
                newEarth[curr[0]][curr[1]] = '.';
            } else {
                // 최대 최소 좌표
                minX = Math.min(minX, curr[0]);
                minY = Math.min(minY, curr[1]);
                maxX = Math.max(maxX, curr[0]);
                maxY = Math.max(maxY, curr[1]);
            }
        }
    }

}
