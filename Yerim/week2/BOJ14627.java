import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14627 {

    static int S, C;
    static int[] greenOnions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken()); // 파 갯수
        C = Integer.parseInt(st.nextToken()); // 파닭 갯수
        long total = 0;

        greenOnions = new int[S];

        for (int i = 0; i < S; i++) {
            greenOnions[i] = Integer.parseInt(br.readLine());
            total += greenOnions[i];
        }

        Arrays.sort(greenOnions);

        // 이분탐색
        long start = 1;
        long end = greenOnions[greenOnions.length - 1];
        long result = 0;

        while (start <= end) {
            long mid = (start + end) / 2;
            int count = 0;

            // 갯수
            for (int i = 0; i < S; i++) {
                count += (int) bowl(mid, i);
            }

            if (count < C) {
                end = mid - 1;
            } else {
                result = mid;
                start = mid + 1;
            }
        }

        System.out.println(total - (result * C));

    }

    private static long bowl(long num, int idx) {
        // 몇 그릇 담길 수 있는지
        return greenOnions[idx] / num;
    }

}
