import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9084 {

    static int T, N, M;
    static int[] money;
    static int[] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            money = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                money[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());

            // 실행 -> dp테이블 갱신
            // dp[i] = i원을 만들 수 있는 경우의 수

            dp = new int[M + 1];
            dp[0] = 1;

            for (int coin : money) {
                for (int i = coin; i <= M; i++) {
                    dp[i] += dp[i - coin];
                }
            }
            sb.append(dp[M]).append("\n");

        }

        System.out.print(sb.toString());
    }

}
