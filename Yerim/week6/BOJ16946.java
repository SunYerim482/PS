import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ16946 {

    static int N, M;
    static int[][] graph, groupId, groupSize;
    static boolean[][] visited;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static StringBuilder sb = new StringBuilder(); // 정답
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        groupId = new int[N][M];
        groupSize = new int[N][M];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                graph[i][j] = tmp.charAt(j) - '0';
            }
        }

        // grouping
        visited = new boolean[N][M];
        int groupCounter = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 0 && !visited[i][j]) {
                    bfsGrouping(i, j, groupCounter++);
                }
            }
        }

        // bfs
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 0) sb.append(0);
                else {
                    sb.append(getWallCount(i, j));
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    // 좌표 값이 0인 곳을 그룹을 만들어 놓고
    // 인접한다면 그 그룹의 0 갯수를 누적하는 식으로 풀이
    // 기존에 벽이라면 좌표값 -1로 배치
    private static void bfsGrouping(int y, int x, int groupNum) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{y, x});
        visited[y][x] = true;
        groupId[y][x] = groupNum;

        int count = 1;
        List<int[]> cells = new ArrayList<>(); // 0인 곳 좌표값 관리
        cells.add(new int[]{y, x});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = curr[0] + dy[i];
                int nx = curr[1] + dx[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[ny][nx] || graph[ny][nx] == 1) continue;

                queue.offer(new int[]{ny, nx});
                visited[ny][nx] = true;
                groupId[ny][nx] = groupNum;
                cells.add(new int[]{ny, nx});
                count++;
            }
        }

        // 그룹에 속한 모든 칸에 그룹 크기 저장
        for (int[] cell : cells) {
            groupSize[cell[0]][cell[1]] = count;
        }
    }

    private static int getWallCount(int y, int x) {
        int count = 1;
        // group에 대해 visited배열을 만듭니다.
        Set<Integer> uniqueGroups = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= N || nx >= M || graph[ny][nx] == 1) continue;

            int gId = groupId[ny][nx];
            if (!uniqueGroups.contains(gId)) {
                uniqueGroups.add(gId);
                count += groupSize[ny][nx];
            }
        }

        return (count % 10);
    }
}
