import java.util.*;

class PGS87946 {
    static boolean[] visited;
    static int answer = Integer.MIN_VALUE;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(0, k, 0, dungeons);
        if (answer == Integer.MIN_VALUE) return 0;
        return answer;
    }

    private static void dfs(int depth, int currK, int count, int[][] dungeons) {
        answer = Math.max(count, answer);

        // 유도
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && currK >= dungeons[i][0]) {
                visited[i] = true;
                dfs(i + 1, currK - dungeons[i][1], count+1, dungeons);
                visited[i] = false;
            }
        }
    }
}