import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1717 {

    static int[] parent;
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    // 분리집합
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 집합 추가
            if (type == 0) {
                union(a, b);
            }
            // 확인
            else if (type == 1) {
                if (findParent(a) == findParent(b)) {
                    sb.append("yes").append("\n");
                } else {
                    sb.append("no").append("\n");
                }
            }
        }

        System.out.print(sb.toString());
    }

    private static int findParent(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = findParent(parent[x]);
    }

    private static void union(int x, int y) {
        int a = findParent(x);
        int b = findParent(y);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

}
