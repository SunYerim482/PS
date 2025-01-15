import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12919 {

    // 반대로 output -> input 가능한지 확인
    static String S, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        int answer = dfs(S, T);

        System.out.println(answer);
    }

    private static int dfs(String input, String output) {
        // 기저
        if (input.length() == output.length()) {
            if (input.equals(output)) return 1;
            else return 0;
        }

        int ans = 0;
        // 유도 -> 거꾸로
        // A면
        if (output.charAt(output.length() - 1) == 'A') {
            // A 제거
            ans += dfs(input, output.substring(0, output.length() - 1));
        }
        if (output.charAt(0) == 'B') {
            // 빼고 뒤집는다.
            String tmp = output.substring(1);
            // reverse -> stringbuilder
            StringBuilder sb = new StringBuilder(tmp);
            String tmp2 = sb.reverse().toString();
            ans += dfs(input, tmp2);
        }
        return ans > 0 ? 1 : 0;
    }

}
