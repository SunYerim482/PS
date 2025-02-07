import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1911 {
    static int N, L;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken()); // 웅덩이 길이

        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Node(start, end));
        }

        int nulpan = 0;
        int range = 0;

        int queueSize = pq.size();

        for (int i = 0; i < queueSize; i++) {
            Node node = pq.poll();
            if (node.start > range) {
                range = node.start;
            }
            if (node.end >= range) {
                while (node.end > range) {
                    range += L;
                    nulpan++;
                }
            }
        }

        System.out.println(nulpan);


    }

    static class Node implements Comparable<Node> {
        int start, end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            return this.start - o.start;
        }
    }
}
