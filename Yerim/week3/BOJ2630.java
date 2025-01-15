import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630 {

    static int N, white = 0, blue = 0;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 조각을 냅니다. (최대한)
        partition(0, 0, N); // 초기는 N

        System.out.println(white);
        System.out.println(blue);
    }

    // 조각을 냅니다.
    private static void partition(int x, int y, int size) {
        // color check
        if (colorCheck(x, y, size)) {
            if (arr[x][y] == 0) {
                white++;
            }
            else {
                blue++;
            }
            return;
        }

        // 재귀로 돌립니다.
        int newSize = size / 2;
        partition(x, y, newSize);
        partition(x, y + newSize, newSize);
        partition(x + newSize, y, newSize);
        partition(x + newSize, y + newSize, newSize);
    }


    // 현재 큰 네모의 색상이 같은지 확인한다.
    private static boolean colorCheck(int x, int y, int size) {
        int color = arr[x][y];

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                // 색상이 같지 않다면
                if (arr[i][j] != color)
                    return false;
            }
        }
        return true;
    }
}
