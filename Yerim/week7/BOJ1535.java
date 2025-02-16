import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1535 {

    static int[][] dp;
    static int[] power;
    static int[] delight;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        power = new int[N+1];
        delight = new int[N+1];
        dp = new int[101][N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            delight[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < 101; i++) { // 체력
            for (int j = 1; j <= N; j++) { // 사람
                // 인사를 할 수 있다면
                if (i > power[j]) {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-power[j]][j-1] + delight[j]);
                }
                // 할 수 없으면
                else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        System.out.println(dp[100][N]);
    }
}
