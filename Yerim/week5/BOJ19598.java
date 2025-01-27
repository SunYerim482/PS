import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ19598 {

    static int N;
    static PriorityQueue<Time> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            queue.add(new Time(a, true));
            queue.add(new Time(b, false));
        }

        // 회의수 개수
        int cnt = 0;
        int answer = 0;

        while (!queue.isEmpty()) {
            Time time = queue.poll();

            if (time.isStart) {
                cnt++;
                answer = Math.max(answer, cnt);
            } else {
                cnt--;
            }
        }
        System.out.println(answer);


    }

    static class Time implements Comparable<Time>{
        int time;
        boolean isStart;
        public Time(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Time o) {
            return this.time - o.time;
        }
    }
}
