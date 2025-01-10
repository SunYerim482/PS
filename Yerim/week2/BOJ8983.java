import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ8983 {

    static int M, N, L;
    static int[] hunts; // 사대 저장
    static Pair[] animals; // 동물 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        hunts = new int[M];
        animals = new Pair[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            hunts[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(hunts); // 이분탐색 -> 정렬

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            animals[i] = new Pair(x, y);
        }

        int answer = 0;
        // 이분탐색
        for (int i = 0; i < N; i++) {
            int start = 0;
            int end = M - 1;

            while (start <= end) {
                int mid = (start + end) / 2;
                // 조건 충족
                if ((Math.abs(hunts[mid] - animals[i].x) + animals[i].y) <= L) {
                    answer++;
                    break;
                }

                // 조건
                if (hunts[mid] < animals[i].x) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        System.out.print(answer);
    }

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
