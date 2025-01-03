package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ1021 {

    static int n, m, ans = 0;
    static LinkedList<Integer> deque = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            deque.offer(i);
        }

        int[] seq = new int[m];

        // 돌면서 target
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            if (check(seq[i])) {
                while (seq[i] != deque.get(0)) {
                    deque.addLast(deque.pollFirst());
                    ans++;
                }
            } else {
                while (seq[i] != deque.get(0)) {
                    deque.addFirst(deque.pollLast());
                    ans++;
                }
            }
            deque.poll();
        }
        System.out.println(ans);
    }

    // 절반
    private static boolean check(int a) {
        for (int i = 0; i <= deque.size() / 2; i++) {
            if (a == deque.get(i)) {
                return true;
            }
        }
        return false;
    }
}
