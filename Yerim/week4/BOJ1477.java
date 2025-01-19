import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1477 {
    static int N, M, L, minDiff = Integer.MAX_VALUE;
    static int[] road;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 휴게소의 개수
        M = Integer.parseInt(st.nextToken()); // 더 지으려는 휴게소
        L = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        road = new int[N+2];
        road[0] = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            road[i] = Integer.parseInt(st.nextToken());
        }
        road[N+1] = L;

        Arrays.sort(road);

        // binary search
        search(1, L - 1);

        System.out.println(minDiff);

    }

    private static void search(int start, int end) {
        // M개를 더 지어야한다.
        while (start <= end) {
            int mid = (start + end) / 2;
            int total = 0;

            // 현재 휴게소 사이에 거리 기준으로 몇 개 휴게소를 설치할 수 있는지 확인
            for (int i = 1; i < road.length; i++) {
                // 현재 휴게소 포함 -1
                total += (road[i] - road[i-1] - 1) / mid;
            }

            // 많이 지었으면 덜 지어라
            if (total > M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        minDiff = start;
    }

}
