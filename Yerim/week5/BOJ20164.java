import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20164 {

    static int N;
    static int minNum = Integer.MAX_VALUE;
    static int maxNum = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cutNumber(N, 0);

        System.out.println(minNum + " " + maxNum);

    }

    private static void cutNumber(int n, int cnt) {

        // 홀수 개수를 종이에 적고
        cnt += countOddNum(n);

        // 한 자리면 -> 종료
        if (n / 10 == 0) {
            minNum = Math.min(minNum, cnt);
            maxNum = Math.max(maxNum, cnt);
        }

        // 두 자리면 -> 2개로 나눠서 합을 구하여 새로운 수로 생각
        else if (n / 100 == 0) {
            int next = n / 10;
            next += n % 10;
            cutNumber(next, cnt);
        }

        // 세 자리 이상 -> 임의의 위치에서 끊어서 3개의 수로 분할하고, 3개를 더한 값을 새로운 수로 생각
        else {
            String str = String.valueOf(n);
            for (int i = 0; i < str.length() - 2; i++) {
                for (int j = i + 1; j < str.length() - 1; j++) {
                    int next = Integer.parseInt(str.substring(0, i + 1));
                    next += Integer.parseInt(str.substring(i+1, j+1));
                    next += Integer.parseInt(str.substring(j+1));

                    cutNumber(next, cnt);
                }
            }
        }
    }

    // 홀수 count
    private static int countOddNum(int n) {
        int count = 0;

        while (n > 0) {
            int tmp = n % 10;
            if (tmp % 2 == 1) {
                count++;
            }
            n /= 10;
        }
        return count;
    }

}
