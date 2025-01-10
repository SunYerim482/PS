import java.util.*;
import java.io.*;

// 3차원 visited 배열 or 객체 set
class Solution {
    static int[] dx = {0, 0, 1, -1}; // 위 아래 오 왼
    static int[] dy = {-1, 1, 0, 0};
    static HashSet<Node> answers;
    static HashMap<String, Integer> dir;
    public int solution(String dirs) {
        int answer = 0;
        answers = new HashSet<>();
        dir = new HashMap<String, Integer>();
        dir.put("U", 0);
        dir.put("D", 1);
        dir.put("R", 2);
        dir.put("L", 3);


        int x = 0, y = 0;

        // dirs만큼
        for (int i = 0; i < dirs.length(); i++) {
            String direction = String.valueOf(dirs.charAt(i)); // 방향
            // 가고자하는 방향이 범위 밖이면 continue;
            int idx = dir.get(direction);
            int nx = x + dx[idx];
            int ny = y + dy[idx];
            if (nx < -5 || ny < -5 || nx > 5 || ny > 5) continue;
            // 방향별로 분리
            answers.add(new Node(x, y, nx, ny));
            answers.add(new Node(nx, ny, x, y));
            x = nx;
            y = ny;
        }

        answer = answers.size() / 2; // 양방향 중복 제거
        return answer;
    }

    static class Node {
        int startX, startY, endX, endY;

        public Node(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.startX,
                this.startY, this.endX, this.endY);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node node = (Node) obj;
                return this.hashCode() == node.hashCode();
            }
            return false;
        }
    }
}