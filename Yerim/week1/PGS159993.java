package week1;

import java.util.*;

class Solution {
    static int rows, cols, startX, startY, endX, endY, leverX, leverY;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static char[][] newMap;

    public int solution(String[] maps) {
        rows = maps.length;
        cols = maps[0].length();
        newMap = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char tmp = maps[i].charAt(j);
                newMap[i][j] = tmp;
                // 출발지
                if (tmp == 'S') {
                    startX = i;
                    startY = j;
                } else if (tmp == 'L') {
                    leverX = i;
                    leverY = j;
                } else if (tmp == 'E') {
                    endX = i;
                    endY = j;
                }
            }
        }

        // S -> L
        int distToLever = bfs(startX, startY, leverX, leverY);
        if (distToLever == -1) return -1;

        // L -> E
        int distToEnd = bfs(leverX, leverY, endX, endY);
        if (distToEnd == -1) return -1;

        return distToLever + distToEnd;
    }

    private static int bfs(int startX, int startY, int targetX, int targetY) {
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        // 처음
        queue.offer(new int[] {startX, startY, 0});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int dist = curr[2];
            // 도달했으면
            if (x == targetX && y == targetY)
                return dist;

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 벽이거나 범위 밖이면 방문했으면
                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols || newMap[nx][ny] == 'X' || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny, dist + 1});
            }
        }
        return -1;
    }
}