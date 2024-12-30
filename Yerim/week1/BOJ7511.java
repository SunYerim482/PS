package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7511 {
    static StringBuilder sb = new StringBuilder();
    static int n, k,  m; // n: 친구, k: 관계
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int count = 0;
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("Scenario ").append(tc).append(":").append("\n");
            n = Integer.parseInt(br.readLine());

            parent = new int[n];

            for (int i = 1; i < n; i++) {
                parent[i] = i;
            }

            k = Integer.parseInt(br.readLine());

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                unionParent(a, b);
            }

            m = Integer.parseInt(br.readLine());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 친구라면
                if (findParent(a) == findParent(b)) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }

            }
            sb.append("\n");
        }

        System.out.print(sb.toString());

    }

    private static int findParent(int x) {
        if (parent[x] == x)
            return x;
        return findParent(parent[x]);
    }

    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }

}
