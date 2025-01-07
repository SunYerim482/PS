import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17129 {
    static int n, m, startX, startY, answer;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visited = new boolean[n][m];
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                int num = s.charAt(j) - '0';
                graph[i][j] = num;
                if (num == 2) {
                    startX = i;
                    startY = j;
                }
            }
        }

        bfs(startX, startY);

        if (answer == Integer.MAX_VALUE) {
            sb.append("NIE");
        } else {
            sb.append("TAK").append("\n").append(answer);
        }
        System.out.print(sb.toString());

    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCount = cur[2];
            // 음식에 도착했다면
            if (graph[curX][curY] >= 3) {
                answer = Math.min(curCount, answer);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || graph[nx][ny] == 1) continue;

                queue.add(new int[]{nx, ny, curCount + 1});
                visited[nx][ny] = true;
            }
        }
    }
}
