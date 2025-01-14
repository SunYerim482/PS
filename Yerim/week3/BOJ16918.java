import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16918 {

    static int R, C, N;
    static Cell[][] map;
    static int[] dy = {1, -1, 0, 0}; // 동서남북
    static int[] dx = {0, 0, 1, -1};
    static StringBuilder sb = new StringBuilder(); // 정답

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new Cell[R][C];

        for (int j = 0; j < R; j++) {
            String tmp = br.readLine();
            for (int i = 0; i < C; i++) {
                char ch = tmp.charAt(i);
                if (ch == 'O') {
                    map[j][i] = new Cell(j, i, 3, 'O');
                } else if (ch == '.') {
                    map[j][i] = new Cell(j, i, 0, '.');
                }
            }
        }

        startBomb(N); // N초동안 폭탄 폭발

        for (int j = 0; j < R; j++) {
            for (int i = 0; i < C; i++) {
                sb.append(map[j][i].status);
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    private static void startBomb(int endTime) {
        int time = 0;

        while (time < endTime) {
            time++;

            // 기존 폭탄 시간 감소
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j].status == 'O') {
                        map[i][j].time--;
                    }
                }
            }

            // 폭발 처리
            boolean[][] exploded = new boolean[R][C]; // 폭발 위치 기록
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j].status == 'O' && map[i][j].time == 0) {
                        exploded[i][j] = true; // 폭발 위치 기록
                    }
                }
            }

            // 폭발 반영
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (exploded[i][j]) {
                        explode(i, j);
                    }
                }
            }

            // 짝수 초에 폭탄 설치
            if (time % 2 == 0) {
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (map[i][j].status == '.') {
                            map[i][j].status = 'O';
                            map[i][j].time = 3;
                        }
                    }
                }
            }
        }
    }

    // 폭탄 폭발 로직
    private static void explode(int y, int x) {
        map[y][x].status = '.';
        map[y][x].time = -1;

        for (int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (ny >= 0 && nx >= 0 && ny < R && nx < C) {
                map[ny][nx].status = '.';
                map[ny][nx].time = -1;
            }
        }
    }



    // 폭탄
    static class Cell {
        int y, x, time;
        char status;
        public Cell(int y, int x, int time, char status) {
            this.y = y;
            this.x = x;
            this.time = time;
            this.status = status;
        }
    }


}
