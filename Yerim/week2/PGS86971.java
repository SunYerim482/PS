import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // graph에 연결관계 
        for (int i = 0; i < wires.length; i++) {
            int num1 = wires[i][0];
            int num2 = wires[i][1];
            graph.get(num1).add(num2);
            graph.get(num2).add(num1);
        }

        // 간선을 하나씩 제거하면서 전력망 크기를 계산
        for (int[] wire : wires) {
            // 제거
            graph.get(wire[0]).remove((Integer) wire[1]);
            graph.get(wire[1]).remove((Integer) wire[0]);

            // 전력망의 크기를 계산
            visited = new boolean[n+1];
            int size1 = dfs(n, wire[0]);
            int size2 = n - size1;

            answer = Math.min(answer, Math.abs(size1 - size2));

            // 다시 복구
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }

        return answer;
    }

    // dfs -> 전력망 나눔
    private static int dfs(int n, int depth) {
        visited[depth] = true;
        int cnt = 1;

        for (int neighbor : graph.get(depth)) {
            if (!visited[neighbor]) {
                cnt += dfs(n, neighbor);
            }
        }

        return cnt;
    }

}