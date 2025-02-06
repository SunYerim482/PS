import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21937 {

    // 2차원 arraylist로 연결관계 표현
    static ArrayList<ArrayList<Integer>> graph;
    static int N, M, X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        // 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int prev = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            graph.get(next).add(prev);
        }

        X = Integer.parseInt(br.readLine());

        // 탐색
        int ans = bfs(X);

        System.out.println(ans);

    }

    private static int bfs(int idx) {
        // idx를 하기 위해 먼저 해야하는 일의 개수
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        queue.add(idx);
        visited[idx] = true;
        int answer = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph.get(curr)) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    answer++;
                }
            }
        }
        return answer;
    }
}
