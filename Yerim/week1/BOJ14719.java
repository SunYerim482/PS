package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719 {
    // 내 기준에서 left & right max hegiht값 찾기 -> 여기서 min값 내 높이에서 -
    static int H, W;
    static int[] block;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        block = new int[W];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < W; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;

        // 두번째 블록부터 순회
        for (int i = 1; i < W - 1; i++) {
            int maxHeight = rain(i);
            int tmp = maxHeight - block[i];
            if (tmp > 0) {
                answer += tmp;
            }
        }
        System.out.println(answer);

    }

    private static int rain(int idx) {
        int leftMax = 0, rightMax = 0;

        // 내 위치에서 left height
        for (int i = 0; i < idx; i++) {
            leftMax = Math.max(leftMax, block[i]);
        }

        // 내 위치에서 right height
        for (int i = idx + 1; i < W; i++) {
            rightMax = Math.max(rightMax, block[i]);
        }

        return Math.min(leftMax, rightMax);
    }

}
