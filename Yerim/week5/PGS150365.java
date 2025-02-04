import java.util.*;

// 사전순 -> dlru순서 (하좌우상)
// 애초에 방문을 못하는 경우 -> 이동할 거리가 k보다 크거나, 이동거리 - 남은 이동거리
// dfs -> 가지치기
// bfs로 한다면 ....visited배열이 무조건 있어야할 거 같은데 어떤식으로 해야할지고민
class PGS150365 {
    static int[] dy = {0, -1, 1, 0};
    static int[] dx = {1, 0, 0, -1}; // 상하
    static String[] dir = {"d", "l", "r" ,"u"};
    static int endX, endY, mapX, mapY;
    static ArrayList<String> answers;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        answers = new ArrayList<>();
        mapX = n;
        mapY = m;
        endX = r-1;
        endY = c-1;

        int diff = Math.abs((r-1)-(x-1)) + Math.abs((c-1)-(y-1));
        dfs(x-1, y-1, k, "", diff);

        Collections.sort(answers);

        if (answers.isEmpty())
            answer = "impossible";
        else
            answer = answers.get(0);
        return answer;
    }

    private static boolean dfs(int x, int y, int k, String route, int diff) {
        // 기저
        if (k == 0 && diff == 0) {
            answers.add(route);
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if (nx >= 0 && ny >= 0 && nx < mapX && ny < mapY && diff <= k) {
                // 남은 거리 도달이 가능한지 판단
                if ((k%2 == 0 && diff % 2 == 0) || (k % 2 == 1 && diff % 2 == 1)) {
                    if (dfs(nx, ny, k -1, route + dir[i], Math.abs(nx - endX) + Math.abs(ny - endY)))
                        return true;
                }
            }
        }
        return false;
    }
}