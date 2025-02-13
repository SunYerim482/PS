import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2304 {

    static Point[] points;
    static int N, maxHeight = Integer.MIN_VALUE, firstMaxIdx = -1, lastMaxIdx = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        points = new Point[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            points[i] = new Point(L, H);
            maxHeight = Math.max(maxHeight, H);
        }

        Arrays.sort(points);

        // 최대 높이 구간 찾기
        for (int i = 0; i < N; i++) {
            if (points[i].height == maxHeight) {
                if (firstMaxIdx == -1) {
                    firstMaxIdx = i;
                }
                lastMaxIdx = i;
            }
        }

        int answer = 0;

        // 왼쪽
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i <= firstMaxIdx; i++) {
            if (points[i].height > points[stack.peek()].height) {
                answer += (points[i].location - points[stack.peek()].location) * points[stack.peek()].height;
                stack.push(i);
            }
        }

        // 오른쪽부터 감소하는 부분
        stack.clear();
        stack.push(N-1);
        for (int i = N - 2; i >= lastMaxIdx; i--) {
            if (points[i].height > points[stack.peek()].height) {
                answer += (points[stack.peek()].location - points[i].location) * points[stack.peek()].height;
                stack.push(i);
            }
        }

        // 최대 높이
        answer += (points[lastMaxIdx].location - points[firstMaxIdx].location + 1) * maxHeight;

        System.out.println(answer);

    }

    static class Point implements Comparable<Point> {
        int location, height;

        public Point(int location, int height) {
            this.location = location;
            this.height = height;
        }

        @Override
        public int compareTo(Point o) {
            return this.location - o.location;
        }
    }

}