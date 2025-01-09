import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7795 {

    static StringBuilder sb = new StringBuilder();
    static int T, N, M;
    static int[] arr1;
    static int[] arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr1 = new int[N];
            arr2 = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr1[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                arr2[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr1);
            Arrays.sort(arr2);

            // 계산
            int cnt = calc(arr1, arr2);
            sb.append(cnt).append("\n");

        }

        System.out.print(sb.toString());
    }

    private static int calc(int[] arr1, int[] arr2) {
        int ans = 0;
        // a가 b보다 큰 쌍의 개수
        for (int i = 0; i < arr1.length; i++) {
            int target = arr1[i];
            // arr2에서 target보다 작은 숫자 갯수만 알면 됨.
            int count = search(target, arr2);
            ans += count;
        }
        return ans;
    }

    private static int search(int target, int[] arr){
        int count = 0;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] < target) {
                start = mid + 1;
                count = mid + 1;
            } else
                end = mid - 1;
        }
        return count;
    }

}
