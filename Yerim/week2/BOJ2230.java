import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2230 {

    static int[] numbers;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(numbers);

        // binary
        int left = 0;
        int right = 0;
        int minDiff = Integer.MAX_VALUE;

        while (right < N) {
            int diff = numbers[right] - numbers[left];
            // M이상이면서 가장 작은 차이인가?
            if (diff >= M && diff < minDiff) {
                minDiff = diff;
            }
            if (diff == M) {
                break;
            } if (diff < M) {
                right++;
            } else {
                left++;
            }
        }

        System.out.println(minDiff);
    }

}
