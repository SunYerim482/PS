import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ25916 {

    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // hamster

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int ans = Integer.MIN_VALUE;
        int count = 0; // 누적합
        while (right < N) {
            if (count + arr[right] <= M) {
                count += arr[right];
                ans = Math.max(ans, count);
                right++;
            } else {
                count -= arr[left];
                left++;
            }
        }
        System.out.println(ans);

    }
}
