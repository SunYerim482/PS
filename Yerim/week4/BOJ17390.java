import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17390 {

    static int N, Q;
    static int[] arr;
    static int[] sum;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        arr = new int[N];
        sum = new int[N + 2]; // 누적합

        st = new StringTokenizer(br.readLine());
        int total = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
        Arrays.sort(arr);

        sum[0] = 0;
        for (int i = 1; i <= N; i++) {
            total += arr[i-1];
            sum[i] = total;
//            System.out.println(sum[i]);
        }

        // 답
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a==b) {
                sb.append(arr[a-1]).append("\n");
            } else {
                sb.append(sum[b]-sum[a-1]).append("\n");
            }

        }
        System.out.print(sb.toString());
    }

}
