import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* logic
    * - arraylist로 친구관계 저장
    * - 친구 방문 안 했으면 dfs
    * - depth가 4가 충족됐을때, 기저
    * - boolean 변수
    * */
public class BOJ13023 {
    static int N, M;
    static boolean result = false;
    static boolean[] visited;
    static Friend[] friends;

    static class Friend {
        ArrayList<Integer> relationship; // 친구관계
        int num;
        boolean isVisited;

        public Friend() {

        }

        public Friend(ArrayList<Integer> relationship, int num, boolean isVisited) {
            this.relationship = relationship;
            this.num = num;
            this.isVisited = isVisited;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        friends = new Friend[N];

        // 초기화
        for (int i = 0; i < N; i++) {
            friends[i] = new Friend();
            friends[i].relationship = new ArrayList<>();
            friends[i].num = i;
        }

        // 친구관계 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].relationship.add(b);
            friends[b].relationship.add(a);
        }

        // 돌면서 dfs
        for (int i = 0; i < N; i++) {
            dfs(i, 0);
            // 이미 충족
            if (result) break;
        }

        System.out.println(result ? 1 : 0);

    }

    private static void dfs(int idx, int depth) {
        // 기저
        if (depth == 4) {
            result = true; // 가능합니다.
            return;
        }

        // 유도
        // idx의 친구를 돌았을때, 아직 방문하지 않았다고 판별이 된다면, dfs 실행 -> Depth + 1
        friends[idx].isVisited = true;
        for (Integer friend : friends[idx].relationship) {
            if (!friends[friend].isVisited) {
                dfs(friend, depth + 1);
            }
        }
        friends[idx].isVisited = false;
    }
}
