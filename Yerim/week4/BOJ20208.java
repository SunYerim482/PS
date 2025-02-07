import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20208 {

    static int N, M, H, houseX, houseY, answer, milkCount;
    static int[][] country;
    static boolean[] drink;
    static int[] homeDist;
    static ArrayList<int[]> milk = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // 진우의 초기체력
        H = Integer.parseInt(st.nextToken()); // 우유 마실때마다 증가하는 체력

        country = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                country[i][j] = num;
                if (num == 1) {
                    houseX = i;
                    houseY = j;
                } else if (num == 2) {
                    milk.add(new int[]{i, j});
                    milkCount++;
                }
            }
        }

        drink = new boolean[milkCount];
        homeDist = new int[milkCount];

        // 각 우유 위치에서 집까지 돌아가는 거리도 계산해야함.
        for (int i = 0; i < milkCount; i++) {
            int[] currMilk = milk.get(i);
            homeDist[i] = getDistance(houseX, houseY, currMilk[0], currMilk[1]);
        }

        mintChoco(houseX, houseY, M, 0);

        System.out.println(answer);

    }


    private static void mintChoco(int x, int y, int move, int cnt) {
        // 이미 최댓값만큼 먹었거나 못 움직이면 탈출
        if (answer == milkCount)
            return;

        // 유도
        for (int i = 0; i < milk.size(); i++) {
            int[] tmp = milk.get(i);
            int dist = getDistance(x, y, tmp[0], tmp[1]);
            // 갈 수 없거나 이미 먹었다면 continue
            if (drink[i] || dist > move)
                continue;

            int nextMove = move - dist + H;
            // 다음으로 갈 곳이 집으로 돌아갈 수 있으며, 우유 갯수가 갱신이 된다면 갱신.
            if (nextMove >= homeDist[i] && cnt + 1 > answer) {
                answer = cnt + 1;
            }
            drink[i] = true;
            mintChoco(tmp[0], tmp[1], nextMove, cnt + 1);
            drink[i] = false;
        }

    }

    // 거리 계산
    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}
