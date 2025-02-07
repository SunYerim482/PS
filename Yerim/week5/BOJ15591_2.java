import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15591_2 {

    // bfs로 탐색
    // 인접리스트에 저장
    static int N, Q;
    static ArrayList<ArrayList<Node>> list;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        // 리스트 초기화
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            list.get(p).add(new Node(q, r));
            list.get(q).add(new Node(p, r));
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sb.append(count(k, v)).append("\n");
        }

        System.out.print(sb.toString());
    }

    private static int count(int k, int v) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        queue.add(new Node(v, Integer.MAX_VALUE));

        visited[v] = true;

        int cnt = 0, linkMinCost = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            // 연결된 영상들
            ArrayList<Node> link = list.get(curr.v);

            for (int i = 0; i < link.size(); i++) {
                // 이미 확인했다면
                if (visited[link.get(i).v]) {
                    continue;
                }

                // 최소 유사도
                linkMinCost = Math.min(link.get(i).cost, curr.cost);

                if (linkMinCost >= k) {
                    visited[link.get(i).v] = true;
                    cnt++;
                    queue.add(new Node(link.get(i).v, linkMinCost));
                }
            }
        }

        return cnt;

    }

    static class Node {
        int v, cost;
        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

}
