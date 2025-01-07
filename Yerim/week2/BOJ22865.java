import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ22865 {

    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static int[] friends;
    static ArrayList<ArrayList<Node>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 자취할 땅 후보의 개수
        friends = new int[3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            friends[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        // 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        M = Integer.parseInt(br.readLine()); // 도로

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            // 양방향
            graph.get(a).add(new Node(b, dist));
            graph.get(b).add(new Node(a, dist));
        }

        int[] minDistance = new int[N+1];
        // 최단거리 테이블 갱신
        Arrays.fill(minDistance, INF);

        for (int friend : friends) {
            dijkstra(friend, minDistance);
        }

        // 정답
        int answerNode = -1;
        int maxMinDistance = -1;

        for (int i = 1; i <= N; i++) {
            // 친구 노드는 제외
            boolean isFriend = false;
            for (int friend : friends) {
                if (i == friend) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) continue;
            if (minDistance[i] > maxMinDistance) {
                maxMinDistance = minDistance[i];
                answerNode = i;
            }
        }

        System.out.println(answerNode);
    }

    public static void dijkstra(int start, int[] minDistance) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] d = new int[N+1];
        Arrays.fill(d, INF);
        pq.offer(new Node(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int dist = curr.dist;
            int now = curr.idx;
            // 현재 노드 이미 처리됐다면
            if (d[now] < dist) continue;
            // 현재 노드와 인접한 다른 노드들 확인
            for (Node neighbor : graph.get(now)) {
                int cost = d[now] + neighbor.dist;
                if (cost < d[neighbor.idx]) {
                    d[neighbor.idx] = cost;
                    pq.offer(new Node(neighbor.idx, cost));
                }
            }
        }

        // 최단 거리 갱신
        for (int i = 1; i <= N; i++) {
            minDistance[i] = Math.min(minDistance[i], d[i]);
        }
    }

    static class Node implements Comparable<Node> {
        int idx, dist;
        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

}
