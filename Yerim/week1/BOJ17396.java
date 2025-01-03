package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17396 {
    static final long INF = Long.MAX_VALUE;
    static int N, M;
    static boolean[] sight;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static long[] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sight = new boolean[N];
        d = new long[N + 1];

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) sight[i] = false;
            else if (num == 1) sight[i] = true; // 시야에 보이는 정점
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, time));
            graph.get(end).add(new Node(start, time));
        }

        Arrays.fill(d, INF);

        dijkstra(0);

        if (d[N - 1] == INF)
            System.out.println(-1);
        else
            System.out.println(d[N - 1]);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            // 꺼내기
            Node curr = pq.poll();
            long dist = curr.dis;
            int now = curr.idx;

            if (d[now] < dist) continue; // 이미 처리

            // 다른 노드들 확인
            for (int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i).idx;
                long cost = d[now] + graph.get(now).get(i).dis;

                if (sight[next] && next != N - 1) continue;

                // 다음 노드가 적의 시야에 있지 않거나 도착지점이라면
                if (cost < d[next]) {
                    d[next] = cost;
                    pq.offer(new Node(next, cost));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{

        private int idx; // 도착지점
        private long dis; // 비용

        public Node(int idx, long dis) {
            this.idx = idx;
            this.dis = dis;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dis, o.dis);
        }
    }
}
