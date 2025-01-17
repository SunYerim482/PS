import java.util.*;
/*
    - move함수 -> 로봇이 움직일때마다 방향별로 맨끝까지 or 장애물에 멈추게 하는 함수
    - 상하좌우(0, 1, 2, 3)으로 판단하기
    - 움직일때마다 이동 거리 + 1, 방문했던 적이 없다면 큐에 넣고, 아니면 방향을 바꿔가면서 이동시킴.
    - answer => min값 갱신
*/
class PGS169199 {
    static int answer = Integer.MAX_VALUE;
    static boolean[][] visited;

    public int solution(String[] board) {
        visited = new boolean[board.length][board[0].length()];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    queue.offer(new int[]{i, j, 0}); // 좌표와 count
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();

                        // 도착지
                        if (board[curr[0]].charAt(curr[1]) == 'G') {
                            answer = Math.min(answer, curr[2]);
                        }

                        for (int dir = 0; dir < 4; dir++) {
                            // 움직임
                            int[] tmp = move(dir, curr[0], curr[1], curr[2], board);
                            int x = tmp[0];
                            int y = tmp[1];

                            if (!visited[x][y]) {
                                queue.offer(tmp);
                                visited[x][y] = true;
                            }
                        }
                    }
                }
            }
        }
        answer = answer == Integer.MAX_VALUE ? -1 : answer;
        return answer;
    }

    private static int[] move(int dir, int x, int y, int cnt, String[] board) {
        // 반환할 
        int[] result = new int[3];
        // 상
        if (dir == 0) {
            for (int i = x; i >= 0; i--) {
                if (board[i].charAt(y) == 'D') {
                    return new int[]{i+1, y, cnt + 1};
                }
            }
            // 장애물에 안 부딪히면 -> 끝까지 감
            result[0] = 0;
            result[1] = y;
            result[2] = cnt+1;
        }
        // 하
        else if (dir == 1) {
            for (int i = x; i < board.length; i++) {
                if (board[i].charAt(y) == 'D') {
                    return new int[]{i-1, y, cnt+1};
                }
            }
            result[0] = board.length - 1;
            result[1] = y;
            result[2] = cnt+1;
        }
        // 좌
        else if (dir == 2) {
            for (int i = y; i >= 0; i--) {
                if (board[x].charAt(i) == 'D') {
                    return new int[]{x, i+1, cnt + 1};
                }
            }
            result[0] = x;
            result[1] = 0;
            result[2] = cnt + 1;
        }
        // 우
        else if (dir == 3) {
            for (int i = y; i < board[0].length(); i++) {
                if (board[x].charAt(i) == 'D') {
                    return new int[]{x, i-1, cnt+1};
                }
            }
            result[0] = x;
            result[1] = board[0].length() - 1;
            result[2] = cnt + 1;
        }

        return result;
    }
}