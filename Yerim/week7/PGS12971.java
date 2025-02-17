import java.util.*;

// 완탐 -> 시간초과, dp
class PGS12971 {
    public int solution(int sticker[]) {
        int n = sticker.length;
        if (n == 1) return sticker[0];

        int[][] dp = new int[2][sticker.length];

        // 첫번째 스티커를 선택하는 경우
        dp[0][0] = sticker[0];
        dp[0][1] = sticker[0]; // 두번째 스티커 선택 불가

        for (int i = 2; i < n - 1; i++) {
            dp[0][i] = Math.max(dp[0][i-1], dp[0][i-2] + sticker[i]);
        }

        // 첫번째 스티커를 선택 안 하는 경우
        dp[1][0] = 0;
        dp[1][1] = sticker[1]; // 두번째 스티커는 선택
        for (int i = 2; i < n; i++) {
            dp[1][i] = Math.max(dp[1][i-1], dp[1][i-2] + sticker[i]);
        }


        return Math.max(dp[0][n-2], dp[1][n-1]);

    }
}