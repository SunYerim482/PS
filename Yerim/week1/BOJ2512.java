package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2512 {

    static int N;
    static long M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }

        Arrays.sort(arr); // 이분탐색 정렬

        M = Long.parseLong(br.readLine()); // 총 예산

        long answer = 0;
        long start = 0;
        long end = arr[N - 1];

        // 이분탐색
        while (start <= end) {
            long mid = (start + end) / 2;
            long total = 0;
            for (long money : arr) {
                if (money >= mid) {
                    total += mid;
                } else {
                    total += money;
                }
            }

            if (total > M)
                end = mid - 1;
            else {
                start = mid + 1;
                answer = Math.max(answer, mid);
            }

        }

        System.out.println(answer);

    }
}
