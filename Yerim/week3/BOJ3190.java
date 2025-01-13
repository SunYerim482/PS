import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3190 {
    /*  logic
    * 1. bfs, queue
    * 2. 처음에 오른쪽을 향함. 꼬리 ...
    * 3. 그냥 칸 - 0, 뱀의 머리 - 2, 사과 - 1
    * 4. 방향 변환은 idx로
    * */
    static int N, K, L, time, dir;
    static int[] dx = {1, 0, -1, 0}; // 동남서북
    static int[] dy = {0, 1, 0, -1};
    static int[][] arr;
    static Queue<Point> points;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        points = new LinkedList<>();
        time = 0;
        dir = 0;

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            arr[x][y] = 1; // 사과
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            points.offer(new Point(time, direction));
        }

        arr[1][1] = 2;
        // 뱀 게임 시작
        snakeGame(1, 1); // x, y, 방향

        System.out.println(time);

    }

    private static void snakeGame(int startX, int startY) {
        // bfs
        Queue<Snake> queue = new LinkedList<>();
        queue.offer(new Snake(startX, startY));

        int x = startX, y = startY;

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            time++;

//            System.out.println(nx + ", " + ny + " : " + dir + " & " + time);
            // queue상태 확인
//            System.out.println("--------" + queue.size());

            // 멈춘경우 -> 벽 또는 자기자신의 몸과 부딪히는 경우
            if (nx < 1 || ny < 1 || nx > N || ny > N) break;
            if (arr[nx][ny] == 2) break;

            // 사과 없으면 -> 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다.
            if (arr[nx][ny] == 0) {
                // 몸 길이 줄여서 꼬리 위치한 칸을 비운다.
                Snake tail = queue.poll();
                arr[tail.x][tail.y] = 0; // 비움
            }

            if (!points.isEmpty()) {
                if (time == points.peek().time) {
                    Point p = points.poll();
                    if (p.direction.equals("L")) {
                        dir = (dir + 3) % 4;
                    }
                    if (p.direction.equals("D")) {
                        // 오른쪽으로 방향 틈
                        dir = (dir + 1) % 4;
                    }
                }
            }

            arr[nx][ny] = 2;
            queue.offer(new Snake(nx, ny));
            x = nx;
            y = ny;

        }

    }

    static class Snake {
        int x, y;

        public Snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Point {
        int time;
        String direction;
        public Point(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }
    }

}
