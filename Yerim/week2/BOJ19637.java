import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19637 {
    static int N, M;
    static String[] title;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        title = new String[N];
        numbers = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            int standard = Integer.parseInt(st.nextToken());
            title[i] = tmp;
            numbers[i] = standard;
        }

        // 입력받기
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());

            int start = 0;
            int end = N - 1;

            while (start <= end) {
                int mid = (start + end) / 2;

                if (numbers[mid] < num) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            sb.append(title[start]).append("\n");
        }

        System.out.print(sb.toString());
    }


}
