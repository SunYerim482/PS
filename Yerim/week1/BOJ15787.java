package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ15787 {
    static int N, M;
    // 중복 허용 안됨 -> hashset
    static HashSet<Integer> set = new HashSet<>();
    static int[] arr; // 기차(비트마스킹)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1]; // 기차
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int train = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int seat = Integer.parseInt(st.nextToken());
                // x번째 자리 1로
                arr[train] |= (1 << seat);
            } else if (type == 2) {
                int seat = Integer.parseInt(st.nextToken());
                // x번째 자리 0으로
                arr[train] &= ~(1 << seat);
            } else if (type == 3) {
                // 하차 (가장 왼쪽 0 추가)
                arr[train] <<= 1; // 한 칸씩 뒤로 보내고
                arr[train] &= ((1<<21)-1);
            } else if (type == 4) {
                // 하차 (가장 오른쪽 0 추가)
                arr[train] >>= 1; // 한 칸씩 앞으로 보내고
                arr[train] &= ~1;
            }
        }

        // set에 남아있는 원소 갯수 출력
        for (int i = 1; i <= N; i++) {
            set.add(arr[i]);
        }
        System.out.println(set.size());
    }
}
