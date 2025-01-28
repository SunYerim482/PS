import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15591 {

    // 시간초과
    static int N, Q, INF = Integer.MAX_VALUE;
    static int[][] graph;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], INF);
        }

        // 나 자신 0
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            graph[p][q] = r;
            graph[q][p] = r;
        }

        // USADO 계산
        int[][] res1 = calcUsado();


        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 계산
            int ans = 0;
            for (int j = 1; j <= N; j++) {
                if (res1[v][j] >= k)
                    ans++;
            }
            sb.append(ans).append("\n");
        }

        System.out.print(sb.toString());
    }

    private static int[][] calcUsado() {
        int[][] res = new int[N+1][N+1];
        // 초기화: 그래프 복사
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                res[i][j] = graph[i][j];
            }
        }

        // 한 정점에서 다른 정점으로 갈 때, min값을 넣습니다.
        for (int k = 1; k <= N; k++) {
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    // 갱신할 필요 없음
                    if (a==k || k == b || a == b) continue;
                    if (res[a][b] < graph[a][k] || res[a][b] < graph[k][b]) continue;
                    res[a][b] = Math.min(graph[a][k], graph[k][b]);

                }
            }
        }
        return res;
    }
}
