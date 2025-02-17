import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2294 {

    static int n, k, maxValue = 10001;
    static int[] coins;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        dp = new int[k+1];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.fill(dp, maxValue);

        // 최소 개수
        // 현 cost에서 - coin + 1과 현 cost 중 min값
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i-coin] + 1, dp[i]);
                }
            }
        }

        System.out.println(dp[k] == maxValue ? -1 : dp[k]);

    }

}
