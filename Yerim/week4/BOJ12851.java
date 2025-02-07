import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851 {

    static int N, K, ans = 0, time = Integer.MAX_VALUE;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new int[100001];

        // bfs
        bfs(N, K);

        System.out.println(time);
        System.out.println(ans);
    }

    // bfs
    private static void bfs(int N, int K) {
        // N -> K로 가야함.
        Queue<int[]> queue = new LinkedList<>();
        visited[N] = 0;
        queue.add(new int[]{N, 0}); // 현 위치와 몇 초 경과했는지

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int position = curr[0];
            int currTime = curr[1];

            // 기저
            if (position == K) {
                // 시간이 갱신 되는 경우
                if (time > currTime) {
                    time = currTime;
                    ans = 1; // 방법의 수
                }
                // 시간이 갱신 안 되는 경우
                else if (time == currTime) {
                    ans++;
                }
            }

            // 유도
            int[] nextPositions = {position - 1, position + 1, position * 2};

            for (int nextPosition : nextPositions) {
                if (nextPosition >= 0 && nextPosition < 100001) {
                    if (visited[nextPosition] == 0 || visited[nextPosition] >= currTime + 1) {
                        queue.add(new int[]{nextPosition, currTime + 1});
                        visited[nextPosition] = currTime + 1;
                    }
                }
            }
        }
    }
}
